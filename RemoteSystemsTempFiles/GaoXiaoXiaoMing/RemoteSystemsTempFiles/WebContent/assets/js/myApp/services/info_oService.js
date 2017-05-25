/**
 * Created by Govern on 2016/10/1.
 */
angular.module('xiaoMing').service('info_oService',['$rootScope','$state','$http',function ($rootScope, $state, $http) {
    var that=this;
    /**
     *提供默认数据
     */
    this.getDefaultData=function () {
        $http({
            method:'POST',
            url:'organization_info.action'
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('getDefault',data);
                //onOrOff处理
                that.onOrOff(data.public);
            }else if(data.status=='false'){
                console.info('获取失败，请重试！');
            }
        }).error(function (data, status, header, config) {
            console.info('网络繁忙，请稍后再试！');
        })
    };
    //onOrOff的处理
    this.onOrOff=function (onOrOff) {
        onOrOff===true?document.getElementById('onOrOff').parentNode.className='switch-animate switch-on':document.getElementById('onOrOff').parentNode.className='switch-animate switch-off';
    };
}]);