angular.module('xiaoMing').controller('logMCtrl', ['$scope','logService', function($scope, logService){
    //保存当前作用域指针
    var self = this;
    //声明用于存放日志列表的空数组
    this.logList = [];
    //分页插件
    //页数配置
    this.pageConfig = {
        //当前页数
        currentPage: 1,
        //每一页数据条数
        itemsPerPage: 10,
        //每页数据条数选择
        perPageOptions: [10, 20, 30]
    };
    //从服务中获取日志数据
    var getLogList = function(){
            //当前页数
        var pageNum = self.pageConfig.currentPage,
            //每页数据条数
            pageSize = self.pageConfig.itemsPerPage;
        //传进改变后的当前页数和每一页的数据条数
        logService.getLogList(pageNum, pageSize);
    };
    //监听conf.currentPage+conf.itemsPerPage，有变化时触发getLogList
    $scope.$watch("conf.currentPage+conf.itemsPerPage", getLogList);
    //监听getLogListM事件，被触发后执行后面的函数
    $scope.$on("getLogListM", function(state, data){
        //传入resData
        self.pageConfig.totalItems = data.total;
        //日志数据
        self.logList = data.list;
        //控制台查看resData
        console.info(self.pageConfig.totalItems);
    });

}]);