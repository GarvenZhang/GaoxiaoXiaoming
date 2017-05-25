angular.module('xiaoMing')
//发送验证码60秒倒计时
.directive('getCodeBtnM',function($timeout, $interval){
    return { 
        restrict: 'EA',
        transclude: true, 
        scope: {
            "phoneNum": "=phoneNum",
            "getCode":  "&getCode",
        },
        link: function(scope, ele, attr){
            scope.isSent = false;
            scope.time = 60;
            scope.startTimer = function(){
                ele.find("a").addClass("hasSent");
                if(!scope.phoneNum || scope.phoneNum.length!=11 || scope.isSent != false)return
                scope.isSent = true;
                var itv = $interval(function(){
                    scope.time--;
                    if(scope.time<1){
                        $interval.cancel(itv);
                        scope.isSent = false;
                        scope.time = 60;
                        ele.find("a").removeClass("hasSent");
                    }
                }, 1000);
            };
        },
        template: '<a class="btn btn-primary">'+
                  '<span ng-click="getCode();startTimer()" ng-transclude ng-show="!isSent"></span>'+
                  '<span ng-show="isSent">{{time}}秒后重新获取</span>'+
                  '</a>',
    };    
});