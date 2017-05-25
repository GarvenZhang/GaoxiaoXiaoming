/**
 * Created by Govern on 2016/9/18.
 */
angular.module('xiaoMing').service('leaveService',['$rootScope','$state','$http',function ($rootScope, $state, $http) {
    //保存当前作用域
    var that = this;
    /**
     * 请求人员列表
     */
    var pplList;
    this.getPplList = function () {
        $http({
            method: 'POST',
            url: 'member_simpleList.action'
        }).success(function (data, status, header, config) {
            if (data.status == 'true') {
                $rootScope.$broadcast('getPplList', data.data);
                //储存给提交申请用
                pplList=data.data;
            } else if (data.status == 'false') {
                console.info('连接上后台但是获取不了数据');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //插件初始化（解决jq插件与ng-repeat冲突的bug）
    this.rended = function () {
        $('.skin-flat input').each(function () {
            var self = $(this);
            self.iCheck({
                checkboxClass: 'icheckbox_flat-blue'
            });
        });
    };

    /**
     *请假申请
     */
    //申请请求
    this.postAbsenceApply=function (members,content,absenceTime,endTime) {
        console.log('members---:'+members);
        console.log('members:'+ typeof members);
        $http({
            method:'POST',
            url:'absence_save.action',
            data:JSON.stringify({
                "members": members,
                "content": content,
                "absenceTime": absenceTime,
                "endTime": endTime
                })
            }).success(function (data, header, status, config) {
                if(data.status=='true'){
                    confirm('提交申请成功！');
                }else if (data.status=='false'){
                    console.log('提交失败，请重新提交申请！');
                }
            }).error(function (data,header,status,config) {
                console.error('网络繁忙，请稍后再试！');
            })
    };
    //得到value值
    this.getValue=function (eleId) {
        return document.getElementById(eleId).value;
    };
    //datetime-local正则验证
    this.datetimeCheck=function (value) {
        return value.length===16?false:true;
    };
    //提交申请
    this.checkAbsenceApply=function () {
       that.postAbsenceApply(that.getValue('chosenPplId').split(','),that.getValue('absenceReasonTxtA'),that.getValue('leave-start').replace('T',' '),that.getValue('leave-end').replace('T',' '));
    };
    //清空
    this.reset=function () {
        //模拟事件清空人员列表选项
        for(var i =0,aLi=document.getElementById('pplForm_leave').getElementsByTagName('li'),lenLi=aLi.length;i<lenLi;i++)
            for(var j =0,aPplId=document.getElementById('chosenPplId').value.split(','),len_aPplId=aPplId.length;j<len_aPplId;j++)
                if(aPplId[j]==aLi[i].children[1].lastElementChild.innerHTML)imitClick(aLi[i].children[0].lastElementChild);
        //清空value
        document.getElementById('chosenPplId').value=document.getElementById('absenceReasonTxtA').value=document.getElementById('chosenPpl').value=document.getElementById('leave-start').value=document.getElementById('leave-end').value='';
    };
    /**
     * 请求查询列表
     */
    //存总数据
    this.recordCount_P='';
    this.recordCount_R='';
    //请求数据
    this.getAbsenceData = function (pageSize,pageNum,type) {
        $http({
                method: 'POST',
                url: 'absence_list.action',
                data: JSON.stringify({
                    "pageSize":pageSize,
                    "pageNum": pageNum,
                    "type": type
                })
            }).success(function (data, status, header, config) {
            //同一接口用于两个瀑布流
            if (data.status == 'true'&&type=='published') {
                //临时声明一个传播的数据集合
                var temp_P=[];
                temp_P.push({
                    recordList:data.recordList,
                    recordCount:data.recordCount
                });
                $rootScope.$broadcast('getAbsencedata_P', temp_P[0]);
                //获得数据总数目
                that.recordCount_P=data.recordCount;
            }else if (data.status=='true'){
                //临时声明一个传播的数据集合
                var temp_R=[];
                temp_R.push({
                    recordList:data.recordList,
                    recordCount:data.recordCount
                });
                $rootScope.$broadcast('getAbsencedata_R',temp_R[0]);
                //获得数据数目
                that.recordCount_R=data.recordCount;
            }else if (data.status == 'false') {
                console.info('获取失败，请重新获取！')
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    //apply名字的数据处理
    this.showApplyName = function (applys) {
        //保存当前作用域
        var self = this;
        //保存名字的string变量
        this.nameStr = '';
        //indexOf兼容ie9
        if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
            //原型创造indexOf
            Array.prototype.indexOf = function (el) {
                for (var i = 0, n = this.length; i < n; i++) {
                    if (this[i] === el) {
                        return i;
                    }
                }
                return -1;
            };
            //条件分支语句的{}不是一个作用域，所以此处的return也就是整个函数的作用域中的return
            return getApplyName(applys);
        }else{
            //ie9以上及其他浏览器
            return getApplyName(applys);
        }
        //名字处理
        function getApplyName() {
            angular.forEach(applys, function (apply) {
                //最后一次循环的时候逗号去掉
                self.nameStr += applys.indexOf(apply) == applys.length - 1 ? apply.name : apply.name + ' , ';
            });
            return self.nameStr;
        }
    };
    //请假结果的数据处理
    this.absenceResult=function (applys) {
        var result='',num=0;
        angular.forEach(applys,function (apply) {
            apply.agree==true?num++:null
        });
        if(num==applys.length){
            result='(都)已批准';
        }else if(num==0){
            result='(都)不批准';
        }else{
            result='('+num+'/'+applys.length+')';
        }
        return result;
    };
    //滚动事件，下拉得到更多
    var x_p=0,x_r=0;
    //因为service.js文件载入一次，而controller.js文件在每次打开页面的时候都会重新载入，所以要在hash值变化的时候把x_p归零
    window.onhashchange=function () {
        x_p=x_r=0;
    };
    this.getWaterful_P=function() {
        //每次请求的数据数
        var count=7*(x_p+1);
        //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
        if((that.recordCount_P-count>=0||Math.abs(that.recordCount_P-count)<7)&&document.getElementById('reportInv_wrap_P').scrollTop+document.getElementById('reportInv_wrap_P').clientHeight>=document.getElementById('allHeightATime_P').clientHeight){
            //重新请求数据，在不改变当前页数的情况下，将一页中容纳的数据变多，便达到了瀑布流的效果
            that.getAbsenceData(count,1,'published');
            x_p++;
        }
    };
    this.getWaterful_R=function() {
        //每次请求的数据数
        var count=7*(x_r+1);
        //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
        if((that.recordCount_R-count>=0||Math.abs(that.recordCount_R-count)<7)&&document.getElementById('reportInv_wrap_R').scrollTop+document.getElementById('reportInv_wrap_R').clientHeight>=document.getElementById('allHeightATime_R').clientHeight){
            //重新请求数据，在不改变当前页数的情况下，将一页中容纳的数据变多，便达到了瀑布流的效果
            that.getAbsenceData(count,1,'received');
            x_r++;
        }
    };
    /**
     * 处理请假请求
     */
    //请求
    this.handleAbsenceRequest=function (e,id, agree) {
        var e =e||window.event,oAgOrRe=e.target.parentNode.parentNode.children[2];
        //初始化结果
        var result;
        $http({
            method:'POST',
            url:'absence_handle.action',
            data:JSON.stringify({
                "id":id,
                "agree":agree
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //dom样式改变
                oAgOrRe.style.display='block';
                e.target.parentNode.style.display='none';
                    if(agree){
                    oAgOrRe.children[1].style.display='none';
                }else{
                    oAgOrRe.children[0].style.display='none';
                }
                console.log(oAgOrRe.style);
            }else if (data.status=='false'){
                console.info('请求失败，请再次点击 拒绝/同意 按钮！');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    /**
     *封装函数
     */
    //冒泡排序算法：最新消息靠前
    function bubbleSort(arr) {
        //外循环：遍历数组中的每一个位置
        for(var i =0,len=arr.length;i<len;i++){
            //内循环：进行比较
            for(var j =0,len0=arr.length-1;j<len0;j++){
                //交换
                if(arr[j+1].absenceTime.replace(/-/g,'').replace(' ','').replace(':','')>arr[j].absenceTime.replace(/-/g,'').replace(' ','').replace(':','')){
                    //临时
                    var temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return  arr;
    }
    //模拟事件
    function imitClick(ele) {
        //创建事件对象
        var event=document.createEvent('MouseEvents');
        //事件对象初始化
        event.initMouseEvent('click',true, true, document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        //触发事件
        ele.dispatchEvent(event);
    }
}]);