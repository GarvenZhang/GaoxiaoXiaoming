angular.module('xiaoMing')
.service("registService", ["$rootScope","$http", "$state", function($rootScope, $http, $state, $interval){
    //获取学校
    this.getSchool = function(){
        $http({
            method: "POST",
            url: "university_list.action"
        }).success(function(res, status, headers, config){
            if(res.status == "true"){
                $rootScope.$broadcast("getSchools", res.data);   
            }else{
                console.info("服务器繁忙");
            }
        }).error(function(res, status, headers, config){
            console.error("未知错误");
        });
    };
    
    //获取校区
    this.getCampus = function(schoolId){
        console.log(schoolId);
        $http({
            method: "POST",
            url: "university_campus.action",
            data: JSON.stringify({
                id: schoolId
            })
        }).success(function(res, status, headers, config){
            if(res.status == "true"){
                $rootScope.$broadcast("getCampus", res.data);
            }else{
                console.info("服务器繁忙");
            }
        }).error(function(res, status, headers, config){
            console.error("未知错误");
        });
    };
    
    //获取手机验证码
    this.getCode = function(phoneNum){
        if(!phoneNum || phoneNum.length!=11){
            console.error("手机号码有误");
            return
        }
        $http({
            method: "POST",
            url: "user_registerCode.action",
            data: JSON.stringify({
                phoneNumber: phoneNum
            })
        }).success(function(res, status, headers, config){
            if(res.status == "true" ){
                console.log("验证码已发送"); 
            }else{
                console.info("服务器繁忙");
            }
        }).error(function(res, status, headers, config){
            console.error("未知错误");
        });
    };
    
    //成员用户注册
    this.registMember = function(member){
        var data = {
            loginName: "",
            password: "",
            phoneNumber: "",
            campusId: "",
            code: ""
        };
        for(var key in data){
            if(!member[key]){
                console.info("no such key");
                return
            }
        }
        if($('select.error').length>0||$('input.error').length>0){
            return
        }
        data = {
            loginName: member.loginName,
            password: $.md5(member.password),
            phoneNumber: member.phoneNumber,
            campusId: member.campusId,
            code: member.code
        };
        $http({
            method: "POST",
            url: "user_register_user.action",
            dataType:"json",
            data: JSON.stringify(data)
        }).success(function(res, status, headers, config){
            if("true" == res.status){ 
                alert("注册成功,返回登陆页面");
                $state.go("login");
            }else if(res.status == "false" && res.error == "code"){
                console.info("验证码错误");
            }else{
                console.info("服务器繁忙");
            }  
        }).error(function(res, status, headers, config){
            console.info("未知错误");
        });
    };
    
    //管理员用户注册
    this.registAdmin = function(member){
        var data = {
            loginName: "",
            password: "",
            phoneNumber: "",
            campusId: "",
            orgName: "",
            code: ""
        };
        for(var key in data){
            if(!member[key]){
                console.info("no such key");
                return
            }
        }
        if($('select.error').length>0||$('input.error').length>0){
            return
        }
        data = {
            loginName: member.loginName,
            password: $.md5(member.password),
            phoneNumber: member.phoneNumber,
            campusId: member.campusId,
            orgName: member.orgName,
            code: member.code
        };
        $http({
            method: "POST",
            url: "user_register_org.action",
            dataType:"json", 
            data: JSON.stringify(data)
        }).success(function(res, status, headers, config){
            if("true" == res.status){ 
                alert("注册成功,返回登陆页面");
                $state.go("login");
            }else if(res.status == "false" && res.error == "code"){
                console.info("验证码错误");
            }else{
                console.info("服务器繁忙");
            }  
        }).error(function(res, status, headers, config){
            console.info("未知错误");
        });
    };
    
    
}]);