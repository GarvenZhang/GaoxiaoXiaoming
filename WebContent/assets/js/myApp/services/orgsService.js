/**
 * Created by Govern on 2016/10/1.
 */
angular.module('xiaoMing').service('orgsService',['$rootScope','$state','$http',function ($rootScope, $state, $http) {
    var that=this;
    /**
     *本机构基本信息
     */
    this.getMyOrgData=function () {
        $http({
            method:'POST',
            url:'organization_info.action'
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('myOrgData',data);
            }else if (data.status=='false'){
                console.info('获取失败，请重新获取！');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    /**
     *获取同校组织列表
     */
    //获取数据列表
    this.getSameOrgsList=function (pageNum,pageSize) {
        $http({
            method:'POST',
            url:'organization_list.action',
            data:JSON.stringify({
                "pageNum": pageNum,
                "pageSize": pageSize
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'&&data.recordList){
                //临时储存
                var temp=[data.recordCount,data.recordList];

                $rootScope.$broadcast('sameOrgsList',temp);
            }else if (data.status=='false'){
                console.info('获取失败，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请重新登陆!')
        })
    };
    //active-disabled-suspended顺序转换（只为美观）
    this.colorChange=function (index) {
        //频率为3的颜色转换
        switch(index%3){
            case 0:return 'status-metro status-suspended';
            case 1:return 'status-metro status-active';
            case 2:return 'status-metro status-disabled';
        }
    }
}]);