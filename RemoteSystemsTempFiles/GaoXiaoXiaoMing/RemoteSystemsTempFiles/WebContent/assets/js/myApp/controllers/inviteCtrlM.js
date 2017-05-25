/**
 * Created by jm_hello on 2016/9/16.
 */
angular.module('xiaoMing').controller('inviteCtrlM',['$scope','inviteServiceM',function ($scope,inviteServiceM) {
    /*
     *变量初始化
     */

    //保存作用域
    var that=this;
    //保存表格内要显示的数据初始化
    var inviteList=[];
    var hasJoinedPeople=[];

    //全部人数
    var recordNum=0;
    //组织信息
    var organizationData=[];

    /*
     * 页面配置
     */
    this.pageConfig = {
        //当前页数
        currentPage: 1,
        //每一页数据条数
        itemsPerPage: 10,
        //每页数据条数选择
        perPageOptions: [10, 20, 30]
    };

    this.otherInfo={
        sort:1,//排列顺序
        edition:'2013' //sql中只有2013以下的
    };

    /**
     * 前端与后端的数据对接
     */
    var getInviteData=function () {
        var pageNum=that.pageConfig.currentPage,
            pageSize=that.pageConfig.itemsPerPage,
            sort=that.otherInfo.sort,
            edition=that.otherInfo.edition;
           // a=1;
        //将数据传到后端
        inviteServiceM.getInviteData(pageNum,pageSize,sort,edition);
    };


    //监控conf.currentPage+conf.itemsPerPage的变化
    $scope.$watch('conf.currentPage+conf.itemsPerPage',getInviteData);

    //监听inviteService的$broadcast传来的数据，实现将后台的数据转接给前端
    $scope.$on('getInviteDataA',function (event,data) {
        that.recordNum=that.pageConfig.totalItems=data.total;
        that.inviteList=data.data;
        that.hasJoinedPeople=data.hasJoinedPeople;
    });


    //状态转换
    this.transformJoinState=inviteServiceM.getJoinedState;
    inviteServiceM.getOrganizationData();
    $scope.$on('getOrganizationData',function (event,data) {
       that.organizationData=data;
    });
}]);
