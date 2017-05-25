/**
 * Created by Govern on 2016/10/10.
 */
angular.module('xiaoMing').controller('workDetailCtrl',['$scope','detailService','$stateParams',function ($scope,detailService,$stateParams) {
    //保存当前作用域
    var that =this ;
    //获取url中传过来的projectData参数(有http协议可知，url中全部是字符串，所有还有用split处理一下)
    this.projectData=$stateParams.projectData.split(',');
    //返回到我的工作
    this.backToMyWork=detailService.backToMyWork;
    /**
     *获取项目动态
     */
    //初始化
    this.dynamicData=[];
    this.pageCount=1;
    var pageNum=1;
    this.firstTime=function(){
        detailService.projectDynamic({
            "pageSize":5,
            "pageNum":pageNum,
            "projectId":that.projectData[0]
        });
        pageNum++;
    }();
    //换页
    this.projectDynamic=function (projectId) {
        //初始化当前页数
        detailService.projectDynamic({
            "pageSize":5,
            "pageNum":pageNum,
            "projectId":projectId
        })
    };
    $scope.$on('dynamicData',function (event, data) {
        that.dynamicData=data.recordList;
        that.pageCount=data.pageCount;
        //判断
        pageNum<that.pageCount?pageNum++:pageNum=1;
    })

}]);