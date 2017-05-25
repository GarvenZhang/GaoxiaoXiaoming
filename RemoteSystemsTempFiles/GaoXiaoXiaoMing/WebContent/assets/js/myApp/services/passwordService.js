/**
 * Created by jm_hello on 2016/9/9.
 */
angular.module('xiaoMing').service('passwordService',['$rootScope','$http','$state',function ($rootScope,$http,$state) {
    //管理员修改密码
    this.modifyAdminPassword=function (member) {
        //数据初始化
        var data={
            password:'',
            newPassword:''
        };
        //遍历数据:保证用户填完所有数据
        for(var key in data){
            if(!member[key]){
                console.info("no such key");
                return;
            }
        }
        //验证表单
        if($('input.error').length>0){
            return;
        }
        //密码赋值并加密
        data={
            password:$.md5(member.password),
            newPassword:$.md5(member.newPassword)
        };
        //获取密码
        $http({
            method:'POST',
            url:'user_updatePassword.action',
            dataType:"json",
            data:JSON.stringify(data)
        }).success(function (res,status,header,config) {
            if(res.status=='true'){
                alert('修改密码成功');
                location.reload();
                // console.log('data:'+data);//[object Object]
                // console.log('status:'+status);//200
                // console.log('header:'+header);//function (c){a||(a=Xc(b));return c?(c=a[z(c)],void 0===c&&(c=null),c):a}
                // console.log('config:'+config);//[object Object]
            }else{
                alert('密码错误，请重新输入密码');
            }
        }).error(function (res,status,headers,config) {
            console.error('未知错误');
        });
    };

    //成员修改密码
    this.modifyMemberPassword=function (member) {
        console.log(1);
        //数据初始化
        var data={
            password:'',
            newPassword:''
        };
        //遍历数据
        for(var key in data){
            if(!member[key]){
                console.info("no such key");
                return
            }
        }
        data={
            password:$.md5(member.password),
            newPassword:$.md5(member.newPassword)
        };
        //获取密码
        $http({
            method: 'POST',
            url:'user_updatePassword.action',
            data:JSON.stringify(data)
        }).success(function (res,status,headers,config) {
            if(res.status=='true'){
                alert('修改密码成功');
                location.reload();
            }else{
                alert('密码错误，请重新输入密码');
            }
        }).error(function (res,status,headers,config) {
            console.error('未知错误');
        })
    };
    //密码重置
    // this.resetPsw=function (obj) {
    //   obj.modifyPwd.$setPristine();
    // };
}]);