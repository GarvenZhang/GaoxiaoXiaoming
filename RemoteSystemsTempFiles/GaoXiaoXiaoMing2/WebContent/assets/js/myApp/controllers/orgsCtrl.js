/**
 * Created by Govern on 2016/10/1.
 */
angular.module('xiaoMing').controller('orgsInSameUnivCtrl',['$scope','orgsService',function ($scope, orgsService) {
    //保存当前作用域
    var that=this;
    /**
     *本机构基本信息
     */
    this.myOrgData=[];
    orgsService.getMyOrgData();
    $scope.$on('myOrgData',function (event, data) {
        that.myOrgData=data;
    });
    /**
     *获取同校组织列表
     */
    //页数配置
    this.pageConfig={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    //动态变化页数与日志数据
    var sameOrgsList=function () {
        var pageNum=that.pageConfig.currentPage,
            pageSize=that.pageConfig.itemsPerPage;
        orgsService.getSameOrgsList(pageNum,pageSize);
    };
    //监听页数改变以实时改变每页日志条数
    $scope.$watch('conf.currentPage+conf.itemsPerPage',sameOrgsList);
    //获取数据
    this.orgsList=[];
    $scope.$on('sameOrgsList',function (event, data) {
        that.orgsList=data[1];
        that.pageConfig.totalItems=data[0];
        console.log(that.orgsList);
    });
    //隔行变色
    this.colorChange=orgsService.colorChange;
}]);