/**
 * Created by Govern on 2016/10/10.
 */
angular.module('xiaoMing').service('detailService',['$rootScope','$state','$http','$location',function ($rootScope, $state, $http,$location) {
    //保存当前作用域
    var that=this ;
    //返回到我的工作
    this.backToMyWork=function () {
        $location.path('/main/work');
    };
    /**
     *项目动态
     */
    this.projectDynamic=function (data) {
        $http({
            method:'POST',
            url:'dynamicState_list.action',
            dataType:'json',
            data:JSON.stringify(data)
        }).success(function (data, status, header, config) {
            if(data.status==='true'){
                $rootScope.$broadcast('dynamicData',{recordList:data.recordList,pageCount:data.pageCount});
            }else if (data.status=='false'){
                console.info('获取失败，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
}]);