/**
 * Created by Govern on 2016/10/8.
 */
angular.module('xiaoMing').controller('workCtrl',['$scope','workService',function ($scope, workService) {
    //保存当前作用域
    var that=this;
    /**
     *请求人员列表
     */
    this.pplList=[];this.pplList_cor=[];
    this.getPplList=workService.getPplList();
    $scope.$on('getPplList',function (event, data) {
        that.pplList=data;
        that.pplList_cor=data;
    });
    //jq插件初始化
    this.rended=workService.rended;
    /**
     *请求工作列表
     */
    this.workData=[];
    //所有
    $scope.$on('allWork',function (event, data) {
        that.workData=data.recordList;
        that.pageConfig.totalItems=data.total;
    });
    //我安排的工作
    $scope.$on('publishedWork',function (event, data) {
        that.workData=data.recordList;
        that.pageConfig.totalItems=data.total;

    });
    //别人安排给我的工作
    $scope.$on('receivedWork',function (event, data) {
        that.workData=data.recordList;
        that.pageConfig.totalItems=data.total;
    });
    //转换type
    this.type='all';
    this.changeType=function (type) {
        //当变换type类型的时候，currentPage变回1，itemsPerPage变回3
        that.pageConfig.currentPage=1;that.pageConfig.itemsPerPage=3;
        //将当前装填的type存起来，给分页插件用
        that.type=type;
        //发送请求
        workService.getWorkList({
            "pageSize":that.pageConfig.itemsPerPage,
            "pageNum":that.pageConfig.currentPage,
            "type":type
        },true);
    };
    //分页插件
    //页数配置初始化
    this.pageConfig={
        currentPage:1,
        itemsPerPage:3,
        perPageOptions:[3,6,9]
    };
    //动态变化页数与日志数据
    function workList() {
        workService.getWorkList({
            "pageSize":that.pageConfig.itemsPerPage,
            "pageNum":that.pageConfig.currentPage,
            "type":that.type
        });
    }
    //监听页数改变以实时改变每页日志条数
    $scope.$watch(function () {
        return that.pageConfig.currentPage+that.pageConfig.itemsPerPage;
    },workList,true);
    //已完成人数数据处理
    this.finishedPpl=workService.finishedPpl;
    //进度条处理
    this.progressHandle=workService.progressHandle;
    //工作完成状况处理
    this.workSitu=workService.workSitu;
    //名字处理
    this.nameHandle=workService.nameHandle;
    //从cookie中取得user名
    this.realName=document.cookie.realName;
    /**
     *完成工作
     */
    this.doneWork=workService.doneWork;
    this.selfDoneWork=workService.selfDoneWork;
    /**
     *安排工作
     */
    //初始化
    /*this.temp={
        project:{name:'',id:''}
    };*/
    this.assignList={
        project:{},
        assignList:[{members:[]}]
    };
    //添加
    this.addNewWork=function () {
        var timer=setInterval(function () {
            $scope.$apply(function () {
                that.assignList.assignList.push({members:[]})
            });
            clearInterval(timer);
        },100)
    };
    //删除
    this.delAssig=function (index) {
        var timer=setInterval(function () {
            $scope.$apply(function () {
                that.assignList.assignList.splice(index,1);
            });
            clearInterval(timer);
        },100)
    };
    //重置
    this.resetAssignList=workService.resetAssignList;
    $scope.$on('resetAssignList',function (event, data) {
        that.assignList=data;
    });
    //模拟点击，在页面进行转换项目名
    //如果是在页面上用ng-change的话，只能检测到输入时的真实改变值，而无法检测到赋值而来的值
    /*$scope.$watch(function(){return that.temp.project},function () {
        workService.imitateClick();
    },true);*/
    //pplId处理
    $scope.$on('pplListId',function (event, data) {
        //添加到model中,实时更新
        $scope.$apply(function () {
            that.assignList.assignList[data[1].parentNode.children[0].innerHTML].members=data[0];
        });
        //添加到view中
        data[1].parentNode.children[2].value=data[0];
    });
    //用于显示人员名称的model
    $scope.$on('pplName',function (event, data) {
        data[1].children[0].innerHTML=data[0];
    });
    //安排表单检验
    this.submitted=false;
    //工作安排数据请求发送
    this.pubWork=workService.pubWork;
    //deadline验证
    this.datetime_local=workService.datetime_local;
    /**
     * 修改工作
     */
    //进入修改页面
    this.toCorrectPage=workService.toCorrectPage;
    //传需要修改的数据
    this.correct=workService.correct;
    this.assigData=[];
    $scope.$on('assigData',function (event, data) {
        that.assigData  =data;
    });
    //pplId_cor处理
    $scope.$on('pplListId_cor',function (event, data) {
        //添加到model中,实时更新
        $scope.$apply(function () {
            that.assigData[data[1].parentNode.children[0].innerHTML].members=data[0];
        });
        //添加到view中
        data[1].parentNode.children[2].value=data[0];
    });
    //用于显示人员名称的model
    $scope.$on('pplName_cor',function (event, data) {
        data[1].children[0].innerHTML=data[0];
    });
    //修改表单检验
    this.submittedCor=false;
    //提交修改后的数据
    this.corWork=workService.corWork;
    /**
     *删除工作
     */
    this.delWork=workService.delWork;
    $scope.$on('deleted',function (event, data) {
        if(data==='true')workList();
    });
    /**
     *请求项目下拉框数据
     */
    this.project=[];
    workService.getProject();
    $scope.$on('project',function (event, data) {
        that.project=data;
    });
    /**
     *查看项目详情
     */
    this.toProjectDetail=workService.toProjectDetail;

    // 修复闪退bug
    this.showApplyData=workService.showApplyData;
    //获取目标点
    this.getTarget=workService.getTarget;
}]);