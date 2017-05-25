/**
 * Created by Govern on 2016/9/9.
 */
angular.module('xiaoMing').controller('logACtrl',['$scope','logAService',function ($scope,logAService) {
    //保存当前作用域
    var that=this;
    //创造一个空数组，用来存放日志数据
    this.logList=[];
    //页数配置
    this.pageConfig={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    //动态变化页数与日志数据
    var LogData=function () {
        var pageNum=that.pageConfig.currentPage,
            pageSize=that.pageConfig.itemsPerPage;
        logAService.getLogData(pageNum,pageSize);
    };
    //监听页数改变以实时改变每页日志条数
    $scope.$watch('conf.currentPage+conf.itemsPerPage',LogData);
    //从服务中传入后端传来的日志数据
    $scope.$on('getLodDataA',function (event, data) {
        that.logList=data.data;
        that.pageConfig.totalItems=data.total;
    });
}]);
