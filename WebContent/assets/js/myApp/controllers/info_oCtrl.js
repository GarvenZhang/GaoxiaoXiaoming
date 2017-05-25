/**
 * Created by Govern on 2016/10/1.
 */
angular.module('xiaoMing').controller('info_oCtrl',['$scope','info_oService',function ($scope, info_oService) {
    var that=this;
    /**
     *提供默认数据
     */
    this.defaultData=[];
    info_oService.getDefaultData();
    $scope.$on('getDefault',function (event,data) {
        that.defaultData=data;
    });
}]);