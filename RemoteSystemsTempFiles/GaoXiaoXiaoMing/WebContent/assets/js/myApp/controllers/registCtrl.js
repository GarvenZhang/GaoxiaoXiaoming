angular.module('xiaoMing').controller('registCtrl', ['$scope','registService', function($scope, registService){
    var self = this;
    
    //学校校区
    registService.getSchool();   
    this.schoolList = [];
    this.campusList = [];
    this.getCampus = registService.getCampus;
    $scope.$on("getSchools", function(state, data){
        self.schoolList = data;
    });
    $scope.$on("getCampus", function(state, data){
        self.campusList = data;
    });
    
    //获取手机验证码
    this.getCode = registService.getCode;
    
    //成员用户注册
    this.user_member = {};
    this.registMember = registService.registMember;
    
    //管理员用户注册
    this.user_admin = {};
    this.registAdmin = registService.registAdmin;
    
}]);