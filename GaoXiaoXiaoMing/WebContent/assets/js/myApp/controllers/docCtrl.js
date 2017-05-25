/**
 * Created by jm_hello on 2016/11/4.
 */
angular.module('xiaoMing').controller('docCtrl',['$scope','docService',function ($scope,docService) {
    //初始化变量
    var that=this;
    this.allDocs=[];
    //获取文件夹
    docService.getAllFolder(1);
    //监控文件数据，修改信息，如果要在修改完信息后，马上在页面显示，可以用同一个model
    $scope.$on('getAllDocs',function (event,data) {
        //这里需要开启定时器
        // 并且用到$apply，为了从Angular框架的外部让表达式在Angular上下文内部执行，即触发脏检查$digest,保证数据一旦修改，就会得到通知，从而使数据在页面上自动更新
        var timer=setInterval(function () {
            $scope.$apply(function () {
                that.allDocs=data;
            });
            clearInterval(timer);
        },1);
    });
    //显示弹框
    this.showModal=docService.showModal;

    //确定修改信息
    this.makeSureModificaiton=docService.makeSureModificaiton;
    //删除文件
    this.deleteDoc=docService.deleteDoc;

    //当用户有输入修改的信息时，按确认，弹框才消失
    this.makeModalDispear=docService.makeModalDispear;
}]);