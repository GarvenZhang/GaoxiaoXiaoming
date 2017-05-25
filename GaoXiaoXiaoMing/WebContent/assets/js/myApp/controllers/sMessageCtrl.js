/**
 * Created by Govern on 2016/10/20.
 */
angular.module('xiaoMing').controller('sMessageCtrl',['$scope','sMessageService',function ($scope, sMessageService) {
    var that=this;
    /**
     *获取系统消息
     */
    //分页插件初始化
    this.pageConfig={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    //监听页数改变以实时改变每页日志条数
    $scope.$watch(function () {
        return that.pageConfig.currentPage+that.pageConfig.itemsPerPage;
    },workList,true);
    //请求
    function workList() {
        sMessageService.getSMessage({
            "pageSize":that.pageConfig.itemsPerPage,
            "pageNum":that.pageConfig.currentPage
        });
    }
    //接收
    this.sMessageList=[];
    $scope.$on('sMessage',function (event, data) {
        that.pageConfig.totalItems=data[1];
        that.sMessageList=data[0];
    });
    // 修复闪退bug
    this.showApplyData=sMessageService.showApplyData;
    /**
     * 获取申请列表
     */
    //分页插件初始化
    this.pageApplyConfig={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    //监听页数改变以实时改变每页日志条数
    $scope.$watch(function () {
        return that.pageApplyConfig.currentPage+that.pageApplyConfig.itemsPerPage;
    },applyData,true);
    //请求
    function applyData() {
        sMessageService.getApplyMessage({
            "pageSize":that.pageApplyConfig.itemsPerPage,
            "pageNum":that.pageApplyConfig.currentPage
        });
    }
    //接收
    this.applyMessageList=[];
    $scope.$on('applyMessage',function (event, data) {
        that.pageApplyConfig.totalItems=data[1];
        that.applyMessageList=data[0];
    });
    //申请弹窗
    this.per={};
    this.checkInfo=sMessageService.checkInfo;
    $scope.$on('applyData',function (event,data) {
        that.per.name=data.name;
        that.per.department=data.department;
        that.per.position=data.position;
        that.per.id=data.id;
        that.per.handle=data.handle;
        that.per.agree=data.agree;
    });
    // sMessageService.agreeOrRefuse();
    this.dealApply=sMessageService.dealApply;
    this.agOrRe={};
    $scope.$on('agOrRe',function (event,data) {
        //对象里在添加对象的话，如果没有先新建这个对象，是会报错的
        that.agOrRe[data.id]={};
        var agOrge_obj=that.agOrRe[data.id];
        agOrge_obj.handle=data.handle;
        agOrge_obj.agree=data.agree;
        agOrge_obj.id=data.id;
    });
}]);