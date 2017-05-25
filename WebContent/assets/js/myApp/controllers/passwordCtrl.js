/**
 * Created by jm_hello on 2016/9/9.
 */
angular.module('xiaoMing').controller('passwordCtrl',['$scope','passwordService',function ($scope,passwordService) {
    //管理员用户密码
    this.user_admin_psw={};
    this.a_Password=passwordService.modifyAdminPassword;

    //成员用户密码
    this.user_member_psw={};
    this.m_Password=passwordService.modifyMemberPassword;
    //重置密码
    // var $obj=$scope;
    // this.resetPsw=passwordService.resetPsw($obj);
    this.resetPsw=function () {
        $scope.modifyPwd.$setPristine();
    };
}]);
