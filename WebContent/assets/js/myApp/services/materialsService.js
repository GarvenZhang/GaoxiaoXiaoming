/**
 * Created by Govern on 2016/10/6.
 */
angular.module('xiaoMing').service('materialsService',['$rootScope','$state','$http',function ($rootScope, $state, $http) {
    //保存当前作用域
    var that=this;
    /**
     *物资表
     */
    var toCorCompare;
    this.materialsList=function (pageSize,pageNum,materialsSelect) {
        $http({
            method:'POST',
            url:'material_list.action',
            data:JSON.stringify({
                "pageSize":pageSize,
                "pageNum":pageNum
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'&&materialsSelect){
                //物资下拉框数据
                $rootScope.$broadcast('materialsSelectData',data.recordList);
            }else if(data.status=='true'){
                //给用户触发ng-change后检查是否真的有修改和取出有修改的数据；
                //避免mvvm的影响
                toCorCompare=deepCopy(data.recordList);
                //临时储存
                var temp={recordList:data.recordList,total:data.recordCount};
                $rootScope.$broadcast('materialsList',temp);
            }else if(data.status=='false'){
                console.info('获取失败，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    /**
     * 添加物资
     */
    this.addMaterials=function (newMaterialsData) {
        console.log(newMaterialsData);
        $http({
            method:'POST',
            url:'material_save.action',
            dataType:'json',
            data:newMaterialsData
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //成功提示
                tip(document.getElementById('successTip_addMaterialForm'),'block',4000);
            }else if (data.status=='false'){
                console.info('物资添加失败，请重新添加!');
            }
        }).error(function (data, status, header, config) {
            //失败提示
            tip(document.getElementById('failTip_addMaterialForm','block'),4000);
        })
    };
    //表单检验
    this.checkForm=function (newMaterialsData) {
        //初始化
        $rootScope.$broadcast('defaultFormStatus',{name:false,totalCount:false,storageLocation:false});
        //检验
        for(var i in newMaterialsData){
            if(!newMaterialsData[i].name||newMaterialsData[i].name&&newMaterialsData[i].name.length==0){
                $rootScope.$broadcast('newMD_name',true);
                return;
            }else if (!newMaterialsData[i].totalCount||newMaterialsData[i].totalCount&&newMaterialsData[i].totalCount.length==0){
                $rootScope.$broadcast('newMD_totalCount',true);
                return;
            }else if (!newMaterialsData[i].storageLocation||newMaterialsData[i].storageLocation&&newMaterialsData[i].storageLocation.length==0){
                $rootScope.$broadcast('newMD_storageLocation',true);
                return;
            }
        }
        //没问题，则发送请求
        return true;
    };
    /**
     *修改物资
     */
    this.corMaterials=function (correctedMaterialsData) {
        //初始化
        var temp={materials:[]},temp_cor,temp_default;
        //深层次拷贝，排除数据双向绑定的干扰
        temp_cor=deepCopy(correctedMaterialsData);
        temp_default=toCorCompare;
        //检查是否有修改
        for(var i =temp_cor[1];i--;){
            //优化：只访问一次
            var temp_i=temp_default[0][i];
            for(var j= len,len=temp_default[1];j--;){
                var temp_j=temp_cor[0][j];
                //如果对上号
                if(temp_i&&temp_j&&temp_i.id==temp_j.id){
                    //如果不对应，则加入到请求数据中
                    if(temp_i.name!==temp_j.name||temp_i.totalCount!==temp_j.totalCount||temp_i.storageLocation!==temp_j.storageLocation){
                        //删除lentCount和state属性
                        delete temp_j.lentCount;
                        delete temp_j.state;
                        temp.materials.push(temp_j);
                    }
                    //将已经对好号码的索引所对应的值删除
                    len--;
                }
            }
        }
        //如果没有任何实质性的修改，则return;
        if(temp.materials.length===0)return;
        //请求
        that.corMaterials_post(temp);
    };
    //修改请求
    this.corMaterials_post=function (materials) {
        $http({
            method:'POST',
            url:'material_update.action',
            dataType:'json',
            data:materials
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //修改成功
                $rootScope.$broadcast('corSuccess',true);
                //ng-change恢复默认值
                $rootScope.$broadcast('corMaterials_change','allFalse');
            }else if (data.status=='false'){
                console.info('修改失败，请再次点击修改按钮!');
            }
        }).error(function (data, status, header, config) {
            //由于网络原因，请求失败的提示
            $rootScope.$broadcast('corFail',true);
            //ng-change恢复默认值
            $rootScope.$broadcast('corMaterials_change','allFalse');
        })
    };
    /**
     *物资出借
     */
    //物资下拉框数据
    this.getAllMaterials=function (total) {
        //true参数相当于区别同一个请求不同用处的钥匙
        that.materialsList(total,1,true);
    };
    //出借请求
    this.materialsLend=function (materialsLendData) {
        //修改时间格式，此处两个时间的值是通过js获取，如果用model的话，用js来修改model是报错的，只有通过vale才能得到后台接口规定的时间格式
        materialsLendData.revertDate=document.getElementById('materialsBorrowDate').value.replace('T',' ');
        materialsLendData.borrowDate=document.getElementById('materialsRevertDate').value.replace('T',' ');
        $http({
            method:'POST',
            url:'material_lend.action',
            dataType:'json',
            data:JSON.stringify(materialsLendData)
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                console.info('出借成功!');
            }else if (data.status=='false'){
                console.log('出借失败，请重新出借!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //日期格式检验
    this.datetime_local=function (type,inpName) {
        var oMaterialsLendForm=document.getElementsByName('materialsLendForm')[0],len=oMaterialsLendForm[inpName].value.length;
        switch (type){
            case 'borrowData':return len==16? false:true;
            case 'revertData':return len==16? false:true;
        }
    };
    /**
     *出借记录
     */
    this.lendRecord=function (pageSize,pageNum) {
        $http({
            method:'POST',
            url:'material_log.action',
            data:JSON.stringify({
                "pageSize":pageSize,
                "pageNum":pageNum
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                $rootScope.$broadcast('lendRecord',[data.recordList,data.recordCount]);
            }else if (data.status=='false'){
                console.info('获取数据失败1');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        });
    };
    //时间格式处理
    this.datetimeHandle=function (datetime) {
        return datetime.slice(0,10);
    };
    /**
     *查询子记录详情
     */
    //进入详情页
    this.goDetailPage=function (id) {
        that.subLendRecord(id);
    };
    //请求
    this.subLendRecord=function (id) {
        $http({
            method:'POST',
            url:'material_child.action',
            data:JSON.stringify({
                'id':id
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                $rootScope.$broadcast('subRecord',data.data);
            }else if(data.status=='false')console.info('获取子记录失败!');
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };

    /**
     *归还物资
     */
    //弹窗
    this.returnWindow=function (id,count,revertCount) {
        //广播对应的id
        $rootScope.$broadcast('returnId',[id,count-revertCount]);
        //因为弹窗组件内部代码原因，如果阻止冒泡，则不会触发模态弹窗,所以用stopBubble来控制,保证即使冒泡到tr也不会执行事件函数
        setTimeout(function () {
            $rootScope.$broadcast('stopBubble',true);
        },1000)
    };
    //请求
    this.returnMaterial=function (materialData) {
        $http({
            method:'POST',
            url:'material_return.action',
            dataType:'json',
            data:materialData
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //重置提示
                $rootScope.$broadcast('returnSuccess',false,'success');
                //如果成功，模拟退出弹窗
                imitClick(document.getElementById('return_closeBtn'));
            } else if (data.status=='false')console.info('归还请求失败!');
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //退出弹窗后，清空
    $('.return-modal').on('hidden.bs.modal',function (e) {
        //model&&tip
        $rootScope.$broadcast('returnSuccess',false,'close');
        //value
        var e =e||window.event;
        (e.target||e.srcElement).getElementsByTagName('input')[0].value='';
    });
    /**
     * 删除物资
     */
    this.delMaterial=function (id) {
        $http({
            method:'POST',
            url:'material_delete.action',
            data:JSON.stringify({
                "id":id
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //重新请求当页
                $rootScope.$broadcast('delMateriaSuccess',true);
            }else if (data.status=='false'){
                console.info('删除失败，请再次点击删除图标!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };



    /**
     * 封装函数
     */
    //发送请求后的提示
    function tip(ele,css,time) {
        ele.style.display=css;
        var timer=setInterval(function () {
            ele.style.display='none';
            clearInterval(timer);
        },time)
    }
    //深层拷贝
    function deepCopy(copyThing, goal) {
        //没有定义gold就将拷贝产物定位为转化成空数组
        //最终想要转换成什么对象还是数组就由这里决定
        var goal = goal || [];
        //遍历
        for (var i in copyThing) {
            //如果拷贝过程中遇到拷贝物的属性是对象
            if (typeof copyThing[i] === 'object') {
                //如果是数组对象，goal[i]定义为一个空数组，然后递归；不然，就定义为一个对象，递归
                //目的是为了保持类型一模一样
                goal[i] = (copyThing[i].constructor === Array) ? [] : {};
                deepCopy(copyThing[i], goal[i]);
            } else {
                //如果属性值为字符串，则直接拷贝
                goal[i] = copyThing[i];
            }
        }
        return [goal,parseInt(i)+1];
    }
    //模拟点击
    function imitClick(ele) {
        //创建事件对象
        var event=document.createEvent('MouseEvents');
        //事件对象初始化
        event.initMouseEvent('click',true,true,document.defaultView,0,0,0,0,0,false,false,false,false,0,null);
        //触发事件
        ele.dispatchEvent(event);
    }
}]);