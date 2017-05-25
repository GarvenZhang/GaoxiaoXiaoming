angular.module('xiaoMing')
.directive('ngRended',function(){
    return {
        restrict: 'A',
        link: function(scope,element,attr){
            if(scope.$last == true){
                scope.$eval(attr.ngRended);
            }
        }
    }
})