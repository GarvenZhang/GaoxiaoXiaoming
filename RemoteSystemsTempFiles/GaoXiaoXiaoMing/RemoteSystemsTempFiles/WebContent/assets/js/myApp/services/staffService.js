/**
 * Created by jm_hello on 2016/9/14.
 */
angular.module('xiaoMing')
    .service('staffService',['$rootScope','$http','$state',function ($rootScope,$http,$state) {
    //初始化响应数据

    var contactNowData = {
        data: [],
        total: 1
    };
    var that=this;

    var contactOldData = {
        data: [],
        total: 1
    };
    var allEdition;

    //通过后端获取数据——当届
    this.getContactNowData = function (pageNum, pageSize, sort, edition) {
        $http({
            method: 'POST',
            url: 'member_list.action',
            //请求的数据只有pageNum、pageSize
            data: JSON.stringify({
                'pageNum': pageNum,
                'pageSize': pageSize,
                'sort': sort,
                'edition': edition
            })
            //status:200~299时
        }).success(function (data, status, header, config) {
            if (data.status == 'true' && data.recordList) {
                //每次请求成功，建立一个空数组，将原来请求成功的数据清除掉，保证不影响用户请求数据
                contactNowData.data = [];
                //将用户输入的数据于后台相关联，就是将用户的数据（即：data.contactDataList）保存到后台的数据库中（即：cotactData.data）
                //record是forEach()里的回调函数的第一个参数：遍历时当前的数据（即：data.recordList）
                angular.forEach(data.recordList, function (record) {
                    contactNowData.data.push({
                        name: record.name,
                        department: record.department,
                        position: record.position,
                        major: record.major,
                        grade: record.grade,
                        phoneNumber: record.phoneNumber
                    });
                });
                //总人数，将url请求的数据里的recordCount赋值给contactData.total
                contactNowData.total = data.recordCount;
                //将处理好的数据传递给子级$scope
                $rootScope.$broadcast('getContactNowDataA', contactNowData);
            } else if (data.status == 'false') {
                console.info('无法获取数据');
            }
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后再试！');
        })
    };

    //通过后端获取数据——往届
    this.getContactOldData = function (pageNum, pageSize, sort, edition) {
        $http({
            method: 'POST',
            url: 'member_list.action',
            //请求的数据只有pageNum、pageSize
            data: JSON.stringify({
                'pageNum': pageNum,
                'pageSize': pageSize,
                'sort': sort,
                'edition': edition
            })
            //status:200~299时
        }).success(function (data, status, header, config) {
            if (data.status == 'true' && data.recordList) {
                //每次请求成功，建立一个空数组，将原来请求成功的数据清除掉，保证不影响用户请求数据
                contactOldData.data = [];
                //将用户输入的数据于后台相关联，就是将用户的数据（即：data.contactDataList）保存到后台的数据库中（即：cotactData.data）
                //record是forEach()里的回调函数的第一个参数：遍历时当前的数据（即：data.recordList）
                angular.forEach(data.recordList, function (record) {
                    contactOldData.data.push({
                        name: record.name,
                        department: record.department,
                        position: record.position,
                        major: record.major,
                        grade: record.grade,
                        phoneNumber: record.phoneNumber
                    });
                });
                //总人数，将url请求的数据里的recordCount赋值给contactData.total
                contactOldData.total = data.recordCount;
                //将处理好的数据传递给子级$scope
                $rootScope.$broadcast('getContactDataOldA', contactOldData);
            } else if (data.status == 'false') {
                console.info('无法获取数据');
            }
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后再试！');
        })
    };

    //下拉列表与表格数据的关联
    this.getEdition=function () {
        $http({
            method:'POST',
            url:'organization_edition.action'
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                allEdition=data.data;
                $rootScope.$broadcast('getEdition', allEdition);
            }else if(data.status=='false'){
                console.info('无法获取数据!');
            }
        }).error(function (data,status,header,config) {
            console.error('服务器繁忙，请稍后再试！');
        })
    };
    //上传文件
    // this.uploadingFiles=function () {
    //     $http({
    //         method:'POST',
    //         url:'image_upload.action',
    //         transformRequest: angular.identity,
    //         headers: {'Content-Type':undefined}
    //
    //     }).success(function (res,status,header,config) {
    //         if(status=='true'){
    //             alert('上传成功！');
    //         }else if(data.status=='false'){
    //             console.info('无法上传!');
    //         }
    //     }).error(function (data,status,header,config) {
    //         console.error('服务器繁忙，请稍后再试！');
    //     })
    // }
}]);



    //下载通讯录
 //    this.downloadAddress=function (edition) {
 //        $http({
 //            method:"POST",
 //            url:"member_download.action",
 //            data:JSON.stringify({
 //                'edition':edition
 //            })
 //        }).success(function (data,status,header,config) {
 //            if(data.status=='true'){
 //                console.log('下载成功');
 //            }
 //        }).error(function (data,status,header,config) {
 //            console.error('服务器繁忙，请稍后再试！');
 //        });
 //    }
 // }]);

