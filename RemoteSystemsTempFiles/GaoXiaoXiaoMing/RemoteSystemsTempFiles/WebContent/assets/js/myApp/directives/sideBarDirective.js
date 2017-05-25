angular.module('xiaoMing')
//根据用户权限显示不同的侧栏
.directive('sideBar',['$rootScope', function($rootScope) {
    if(localStorage.getItem("role")=="ORG_ADMIN"){
        return { 
            restrict: 'E', 
            templateUrl: 'adminSideBar', 
            replace: true 
        };
    }else{
        return { 
            restrict: 'E', 
            templateUrl: 'memberSideBar', 
            replace: true 
        };
    }
}]);