/**
 * Created by Govern on 2016/10/20.
 */
angular.module('xiaoMing').service('sMessageService',['$rootScope','$http','$state',function ($rootScope, $http, $state) {
    var that=this;
    /**
     *获取系统消息
     */
    this.getSMessage=function (sMessage) {
        $http({
            method:'POST',
            url:'systemMessage_list.action',
            dataType:'json',
            data:sMessage
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('sMessage',[data.recordList,data.recordCount]);
            }else if (data.status=='false'){
                console.info('获取失败,请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试')
        })
    };

    //获取申请列表
    this.peData={};
    this.getApplyMessage=function (sMessage) {
        $http({
            method:'POST',
            url:'joinApply_list.action',
            dataType:'json',
            data:sMessage
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('applyMessage',[data.recordList,data.recordCount]);
            }else if (data.status=='false'){
                console.info('获取失败,请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试')
        })
    };
    //申请弹窗获取数据
   this.checkInfo=function (name,position,department,id,handle,agree) {
       that.peData={
           'name':name,
           'position':position,
           'department':department,
           'id':id,
           'handle':handle,
           'agree':agree
       };
       $rootScope.$broadcast('applyData',that.peData);
   };

    // 修复闪退bug
    this.showApplyData=function () {
        $('#chosen_per').modal('show');
    };

   //处理申请
    this.dealApply=function (id,agree,reason) {
        $http({
            method:'POST',
            url:'joinApply_handle.action',
            data:JSON.stringify({
                'id':id,
                'agree':agree,
                'reason':reason
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){

                $rootScope.$broadcast('agOrRe',data);
            }else if (data.status=='false'){
                console.info('获取失败,请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试')
        })
    };
}]);