/**
 * Created by Govern on 2016/9/11.
 */
angular.module('xiaoMing').service('logAService',['$rootScope','$http','$state',function ($rootScope, $http, $state) {
    //存放后端数据的空数组
    var logData={
        data:[],
        total:1
    };
    //通过接口获取后端数据
    this.getLogData=function (pageNum, pageSize) {
        $http({
            method:'POST',
            url:'dynamicState_list_org.action',
            data:JSON.stringify({
                "pageNum":pageNum,
                "pageSize":pageSize
            })
            //status:200~299
        }).success(function (data,status,header,config) {
            /*console.log(data);
            console.log(status);
            console.log(header);
            console.log(config);*/
            if(data.status=='true'&&data.recordList){
                logData.data = [];
                //后端数据处理并添加到logData.data
                angular.forEach(data.recordList,function (record) {
                    logData.data.push({
                        date:record.time.split(' ')[0],
                        time:record.time.split(' ')[1],
                        event:record.description
                    })
                });
                //logData.total数据处理
                logData.total=data.recordCount;
                //将处理好的数据传给所有子级scope
                $rootScope.$broadcast('getLodDataA',logData);
            }else if (data.status=='false'){
                    //跳转到login页面
                    $state.go('login');
            }
        //响应失败
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后再试！');
        })
    }
}]);