/**
 * Created by jm_hello on 2016/10/13.
 */
angular.module('xiaoMing')
    .directive('getValue',function(){
        return {
            restrict: 'AE',
            scope:{
                flavor:'='
            },
            template:'<input ng-model="flavor" id="inputV">'
        }
    });