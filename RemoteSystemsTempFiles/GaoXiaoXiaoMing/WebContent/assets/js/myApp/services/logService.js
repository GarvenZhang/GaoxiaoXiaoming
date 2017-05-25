angular.module('xiaoMing')
.service("logService", ["$rootScope","$http", "$state", function($rootScope, $http, $state){
    //初始化响应数据
    var resData = {
        list: [],
        total: 1
    };
    //请求：获取日志列表
    this.getLogList = function(pageNum, pageSize){
        //发送请求
        $http({
            method : "POST",
                url : 'dynamicState_list.action',
            //转换为json，动态变化当前页码和当前页中日志数据
            data : JSON.stringify({
                "pageSize":pageSize,
	            "pageNum":pageNum
            })
            //status:200~299时
        }).success(function(res, status, headers, config){
            //成功响应并且接收到日志数据时
            if(res.recordList && res.status == "true"){
                //清空存放日志数据的数组
                resData.list = [];
                //用result将响应对象中的recordList存起来
                var result = res.recordList;
                //遍历json中的每一条数据
                for(var i in result){
                    //数据筛选处理
                    resData.list.push({
                        //日期数据分割
                        date: result[i].time.split(" ")[0],
                        //时间数据分割
                        time: result[i].time.split(" ")[1],
                        //描述内容存放
                        event: result[i].description
                    });
                }
                //页总数
                resData.total = res.recordCount;
                //广播事件给所有下级
                $rootScope.$broadcast("getLogListM", resData);
                //接收失败时
            }else if(res.status == "false" && res.error == "noLogin"){
                //转到login页面
                $state.go('login');
            }
            //非成功响应的处理
        }).error(function(res, status, headers, config){
            console.error("服务器繁忙，请稍后重试");
        });
    };
}]);