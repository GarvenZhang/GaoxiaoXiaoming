/**
 * Created by jm_hello on 2016/9/16.
 */
angular.module('xiaoMing').service('inviteServiceM',['$rootScope','$http','$state',function ($rootScope,$http,$state) {
    /*
    *数据初始化
    */
    //储存数据
    var inviteData={
        data:[],
        total:1,
        hasJoinedPeople:[]
    };
    var that=this;
    var organizationData=[];
    /*
    *计算人数
    */
    //判断是否加入——0：表示未加入， 1：表示已加入
    this.getJoinedState=function (stateData) {
        var state=['未加入','已加入'];
        return state[stateData];
    };

    /*
    *数据请求
     */

    this.getInviteData=function (pageNum,pageSize,sort,edition) {
        $http({
            method:'POST',
            url:'member_list.action',
            data:JSON.stringify({
                'pageNum': pageNum,
                'pageSize': pageSize,
                'sort': sort,
                'edition': edition
            })
        }).success(function (data,status,header,config) {
            if(data.status == 'true'&& data.recordList){
                //每次请求，清空原数组内的数据,不然会出现数据累加，从而出现Bug
                inviteData.data=[];
                //遍历数据库里的recordList里的数据，record指的就是当前遍历的数据
                angular.forEach(data.recordList,function (record) {
                    //把record里的数据存储到 inviteData.data这个已经设置好的空数组里
                    inviteData.data.push({
                        name:record.name,
                        department:record.department,
                        position:record.position,
                        phoneNumber:record.phoneNumber,
                        state:record.state
                    });
                });
                //总人数，将url请求的数据里的recordCount赋值给inviteData.total
                inviteData.total=data.recordCount;
                inviteData.hasJoinedPeople=data.joinCount;
                //向子级$scope传递数据
                $rootScope.$broadcast('getInviteDataA',inviteData);
            }else if(data.status=='false'){
                console.info('由于网络问题，暂时无法获取数据+');
            }
        }).error(function (data,status,header,config) {
            console.error('服务器繁忙，请稍后再试！');
        });
    };

    //请求组织数据
    this.getOrganizationData=function () {
        $http({
            method:'POST',
            url:'member_getInfo.action'
        }).success(function (data,status,header,config) {
                if(data.status=='true'){
                    organizationData=data.organization;
                    $rootScope.$broadcast('getOrganizationData',organizationData)
                }else{
                    console.info('无法获取信息');
                }
            }).error(function (data,status,header,config) {
                console.error('服务器繁忙，请稍后再试！');
            })
    }
}]);