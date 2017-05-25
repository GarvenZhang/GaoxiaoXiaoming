angular.module('xiaoMing').controller('loginCtrl', ['$scope','userService', function($scope, userService){
    //表单校验
    userService.formValidate();

    //登陆  
    this.login = userService.login;

    //用户信息未过期则自动跳转到首页
    userService.autoLogin();

}]);