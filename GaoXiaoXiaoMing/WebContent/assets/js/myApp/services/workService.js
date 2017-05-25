/**
 * Created by Govern on 2016/10/8.
 */
angular.module('xiaoMing').service('workService',['$rootScope','$state','$http',function ($rootScope, $state, $http) {
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
     *请求工作列表
     */
    //请求
    this.getWorkList=function (postData,clickType) {
        $http({
            method:'POST',
            url:'assignment_list.action',
            dataType:'json',
            data:JSON.stringify(postData)
        }).success(function (data, status, header, config) {
            if(data.status=='true'&&postData.type=='all'){
                //临时存储
                var temp_allWork={recordList:data.recordList,total:data.recordCount};
                //所有
                $rootScope.$broadcast('allWork',temp_allWork);
                //当转换类型的时候，模拟点击事件
                if(clickType)imitateClick_pageOne();
            }else if (data.status=='true'&&postData.type=='published'){
                //临时存储
                var temp_publishedWork={recordList:data.recordList,total:data.recordCount};
                //我安排的工作
                $rootScope.$broadcast('publishedWork',temp_publishedWork);
                if(clickType)imitateClick_pageOne();
            }else if (data.status=='true'&&postData.type=='received'){
                //临时存储
                var temp_receivedWork={recordList:data.recordList,total:data.recordCount};
                //别人安排给我的工作
                $rootScope.$broadcast('receivedWork',temp_receivedWork);
                if(clickType)imitateClick_pageOne();
            }else if (data.status=='false'){
                console.info('无法获取工作数据，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //已完成人数数据处理
    this.finishedPpl=function (exercisers) {
        //存储已完成的人数数据
        var temp=0;
        //计算器
        angular.forEach(exercisers,function (exerciser) {
            if(exerciser.finished===true){
                temp++;
            }
        });
        return temp;
    };
    //进度条处理
    this.progressHandle=function (finished,total) {
        return {
            'min-width':'20px',
            'width':(finished/total)*100+"%"
        };
    };
    //完成状况处理:已延期xx天/剩余xx天/已完成
    this.workSitu=function (exercisers,finished,total,deadline) {
        //已完成
        if(finished===total)return' 已完成';
        //已延期&&剩余
        for(var i in exercisers){
            if(exercisers[i].finished===false&&exercisers[i].delay>0)return '已延期'+exercisers[i].delay+'天';
            if(exercisers[i].finished===false&&exercisers[i].delay<0)return '还剩下'+Math.abs(exercisers[i].delay)+'天';
        }
    };
    //名字处理
    this.nameHandle=function (exercisers,cor) {
        //临时储存:0放name1放id
        var temp=[[],[]];
        //遍历
        angular.forEach(exercisers,function (value, key) {
            temp[0].push(value.name);
            temp[1].push(value.id);
        });
        //一定要返回一个string类型，因为ng-bind这些内置接收的是表达式，即要么字符串，要么函数，而直接返回数组，就带有[],则会进行逻辑运算而报错
        return cor==='true'?temp:temp[0].toString();
    };
    /**
     *完成工作
     */
    this.doneWork=function (id) {
        $http({
            method:'POST',
            url:'assignment_finish.action',
            data:JSON.stringify({
                "id":id
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //重新请求当页数据
                $rootScope.$broadcast('deleted','true');
            }else if (data.status=='false'){
                console.info('无法执行此按钮，请再按一次!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    var realName=document.cookie.realName;
    this.selfDoneWork=function (exercisers) {
        var key=false;
        for(var i in exercisers){
            if(exercisers[i].finished==true&&exercisers[i].name==realName)key=true;
        }
        return key;
    };
    /**
     *安排工作
     */
    //安排工作判断是否为新工作
    this.pubWork=function (workData) {
        var temp=[];
        //遍历查找是否为新工作
        var isNewWork=function () {
            //如果不是新项目，附上id
            for(var i in projectList){
                if(projectList[i].name==workData.project.name){
                    workData.project.id=projectList[i].id;
                    temp.push(workData);
                    return;
                }
            }
            //如果是，且id存在，去掉id
            var o=0;
            for(var j in projectList){
                if(projectList[j].name!==workData.project.name){
                    workData.project.id=projectList[j].id;
                    o++;
                }
            }
            if(o===projectList.length&&workData.project.id)workData.project.id='';
            temp.push(workData)
        }();
        that.pubOrCorWork(temp);
    };
    //请求
    this.pubOrCorWork=function (workData) {
        console.log(workData);
        $http({
            method:'POST',
            url:'assignment_save.action',
            dataType:'json',
            data:JSON.stringify(workData)
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //安排成功或者返回
                console.log('工作安排成功!');
            }else if(data.status=='false'){
                console.info('安排（修改）失败，请重新进行操作!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };

    //获取目标点
    var pubModal_target;
    this.getTarget=function (e) {
        console.log(e);
        var ev=e||window.event;
            pubModal_target=(ev.target||ev.srcElement).parentNode;
    };
    // 修复闪退bug
    this.showApplyData=function () {
        console.log(1);
        $('#chosen_per').modal('show');
    };
    //存储点击时的元素
    var target_pub,target_cor,pplId_pub,pplId_cor;
    $('.choose-staff').on('show.bs.modal',function (e) {
        //要获得event并且有兼容性，必须要先单获取event
        var e=e||window.event;
        console.log(pubModal_target);
        console.log(pubModal_target.className.indexOf('pubModal'));
        //用同一个模态弹窗，那么就要区别性地传数据下去,分别在标签上加点区别性的属性即可，比如class
        if(pubModal_target.className.indexOf('pubModal')>-1)pplListFormHandle(e,'pplListForm',pplId_pub,'target_pub');
        if(pubModal_target.className.indexOf('corModal')>-1)pplListFormHandle(e,'pplListForm',pplId_cor,'target_cor');

    });
    //人员名称处理
    //将选中的任选显示到文本框中
    $('.choose-staff').on("hidden.bs.modal",function () {
        console.log(target_pub);
        if(target_pub&&target_pub.className.indexOf('pubModal')>-1)pplListFormViewAndModel('.staff_name','.pplId',target_pub,'pplName','pplListId','pplId_pub','#pplListForm');
        if(target_cor&&target_cor.className.indexOf('corModal')>-1)pplListFormViewAndModel('.staff_name','.pplId',target_cor,'pplName_cor','pplListId_cor','pplId_cor','#pplListForm');

    });
    //deadline验证
    this.datetime_local=function (only,index) {
        switch (only){
            case 'false':return document.getElementsByClassName('deadline'+index)[0].value.length==16?false:true;
            case 'true':return correctWorkForm.deadline.value.length>=16?false:true;
        }
    };
    //重置AssignList
    this.resetAssignList=function () {
        $rootScope.$broadcast('resetAssignList',{ project:{},assignList:[{members:[]}]});
    };
    /**
     *修改工作
     */
    //进入修改页面及返回
    this.toCorrectPage=function (id) {
        switch(id){
            case 'myWorkWrap':document.getElementById('myWorkWrap').className='ng-scope hidePage';document.getElementById('correctWorkWrap').className='showPage';
                break;
            case 'correctWorkWrap':document.getElementById('correctWorkWrap').className='hidePage';document.getElementById('myWorkWrap').className='showPage ng-scope';
                break;
        }
    };
    //修改工作的数据
    this.correct=function (projectName,members,content,deadline) {
        //存id
        pplId_cor=members[1];
        //传给model
        $rootScope.$broadcast('assigData',[{projectName:projectName,members:members[0],content:content,deadline:new Date(deadline)}]);
    };
    //修改工作请求
    this.corWork=function (corWorkData) {
        //对象中存在对象，进行深拷贝
        var corWorkData_c={};
        corWorkData_c=deepCopy(corWorkData[0]);
        console.log(corWorkData_c);
        //deadline格式处理
        corWorkData_c.deadline=correctWorkForm.deadline.value.replace('T',' ').substring(0,16);
        $http({
            method:"POST",
            url:'assignment_update.action',
            dataType:'json',
            data:corWorkData_c
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                //DOM操作提示成功
                console.log('修改成功!')
            }else if(data.status=='false'){
                console.info('修改失败!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    /**
     *删除工作
     */
    this.delWork=function (id) {
        $http({
            method:'POST',
            url:'assignment_delete.action',
            data:JSON.stringify({
                "id":id
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                //重新请求当页数据
                $rootScope.$broadcast('deleted','true');
            }else if (data.status=='false'){
                console.info('删除失败，请重新删除!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    /**
     *请求项目下拉框数据
     */
    //给发送安排请求时用于检查是否有对应的id
    var projectList;
    this.getProject=function () {
        $http({
            method:'GET',
            url:'organization_projects.action'
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                projectList=data.data;
                $rootScope.$broadcast('project',data.data);
            }else if (data.status=='false'){
                console.info('获取失败，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    /**
     *查看项目详情
     */
    this.toProjectDetail=function (projectId,projectName,content,publishDate,deadline,publisher,nameHandle) {
        var projectData=[projectId,projectName,content,publishDate,deadline,publisher,nameHandle];
        //跳转页面
        $state.go('main.work.detail',{projectData:projectData});
    };
    //点击事件模拟
    function imitateClick_pageOne() {
        //因为插件的算法原因，当itemsPerPage为6/9而currentPage不为1的时候，点击切换类型，虽然curentPage和itemsPerPage初始化了，但是页码数还没有切换，必须要点击一下页码位置进行触发，插件内部相关计算程序才会重新运作
        //而如果用模拟事件，则必须要等到新的数据渲染完毕之后才能进行，不然的话会与ng的内部循环相冲突而卡死，导致报错
        //所以延迟100ms进行，对于此处，0.1s的延迟对于客户来说完全懵过去，不会被察觉
        var timer=setInterval(function () {
            imitateClick(document.getElementsByClassName('pagination pull-left ng-scope')[0].getElementsByTagName('li')[1]);
            clearInterval(timer);
        },100);
    }
    /*//给页面用
    this.imitateClick=function () {
        var timer=setInterval(function () {
            if(document.getElementById('proNameChangeBtn')!==null)imitateClick(document.getElementById('proNameChangeBtn'));
            clearInterval(timer);
        },100);
    };*/
    //封装模拟点击事件
    function imitateClick(ele) {
        //创建事件对象
        var event=document.createEvent('MouseEvents');
        //初始化事件对象
        event.initMouseEvent('click',true, true, document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        //触发事件
        ele.dispatchEvent(event);
    }
    //封装深拷贝
    function deepCopy(copyThing, goal) {
        //没有定义gold就将拷贝产物定位空对象
        var goal = goal || {};
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
        return goal;
    }
    //封装人员列表：清空与赋值
    function pplListFormHandle(event,formId,pplId,target) {
        //传出去，保存当前事件对象，给hidden.bs.modal事件用
        target==='target_pub'?target_pub=pubModal_target:target_cor=pubModal_target;
        console.log('pplId:'+pplId);
        //因为用的是同一个弹窗，所以每次在弹出前要将弹窗中checked的项与所看到的相同，若本来没有的（即点击触发的那个input没有人员名称的），就要清空弹窗
        //因为有可能人员列表中有相同名字的时候，就不会唯一匹配了，而每个人员的id是唯一的，所以要用id来匹配
        //已有的人员名称id
        //如果不是首次用人员列表，遍历清空上次的checked记录
        if(pplId&&pplId.length!==0){
            for(var i =0,pplListLi=document.getElementById(formId).getElementsByTagName('li'),len=pplListLi.length;i<len;i++){
                for(var l =0,len0=pplId.length;l<len0;l++){
                    if(pplListLi[i].children[1].children[2].innerHTML==pplId[l]){
                        //一定要找对有click事件的那个元素
                        console.log(pplId[l]);
                        imitateClick(pplListLi[i].children[0].children[1]);
                    }
                }
            }
        }
        //如果已经在人员列表选了人员，遍历查找并附上checked
        if(pubModal_target.parentNode.children[2].value.length!==0){
            for(var k =0;k<len;k++){
                for(var j =0,oValue=pubModal_target.parentNode.children[2].value.split(','),len_value=oValue.length;j<len_value;j++){
                    if(pplListLi[k].children[1].children[2].innerHTML==oValue[j]){
                        imitateClick(pplListLi[k].children[0].children[1]);
                    }
                }
            }
        }
    }
    //人员列表可视化与model处理
    function pplListFormViewAndModel(nameInp,idInp,target,nameView,nameModel,pplId,formId){
        var str = "",idArr=[];
        $(formId+' .checked').each(function(idx){
            if(idx < $(formId+' .checked').length-1){
                str += $(this).parent().find(nameInp).html() + "、";
                idArr.push($(this).parent().find(idInp).html());
            }else{
                str = str + $(this).parent().find(nameInp).html();
                idArr.push($(this).parent().find(idInp).html());
            }
        });
        console.log('str:'+str);
        console.log('idArr:'+idArr);
        //因为show.bs.modal和hidden.bs.modal两个事件一定是连贯发生的，所以不用担心target的值传来这个事件的执行函数内部的时候会不同
        //显示人员名称
        $rootScope.$broadcast(nameView,[str,target]);
        //改变人员名称对应的model
        $rootScope.$broadcast(nameModel,[idArr,target]);
        //（相对与下次打开弹窗的时候）记录上次的checked信息
        pplId==='pplId_pub'?pplId_pub=idArr:null;
    }


}]);