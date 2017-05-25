/**
 * Created by Govern on 2016/9/27.
 */
angular.module('xiaoMing')
    .directive('myApply',function () {
        return {
            restrict:'E',
            template:'<div ng-repeat="data in leave.absenceData" class="list-group">'+
                    '<a class="list-group-item">'+
                    '<p ng-bind="data.content" class="list-group-item-heading"></p>'+
                    '<div class="row leave-date">'+
                    '<div class="col-sm-12">'+
                    '<span class="pull-left">请假时间:&nbsp;&nbsp;</span>'+
                    '<span ng-bind="data.absenceTime">&nbsp;</span> 到 <span ng-bind="data.endTime"> </span>'+
                    '</div>'+
                    '</div>'+
                    '<div class="row">'+
                    '<div class="col-sm-10">'+
                    '<span class="label pull-left">To</span>'+
                    '<span ng-bind="showApplyName(data.applys)"  class="sent-to col-sm-10" title="{{showApplyName(data.applys)}}"></span>'+
                    '</div>'+
                    '<div class="col-sm-2 pull-right">'+
                    '<p ng-bind="absenceResult(data.applys)"></p>'+
                    '</div>'+
                    '</div>'+
                    '</a>'+
                    '</div>',
            replace:true,
            scope:{
                getAbsenceData:'='
            },
            link:function (scope, iElement, iAttrs, controller) {
                var getAbsenceData=scope.getAbsenceData;
                //apply名字的数据处理
                scope.showApplyName = function (applys) {
                    //保存当前作用域
                    var self = this;
                    //保存名字的string变量
                    this.nameStr = '';
                    //indexOf兼容ie9
                    if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
                        //原型创造indexOf
                        Array.prototype.indexOf = function (el) {
                            for (var i = 0, n = this.length; i < n; i++) {
                                if (this[i] === el) {
                                    return i;
                                }
                            }
                            return -1;
                        };
                        //条件分支语句的{}不是一个作用域，所以此处的return也就是整个函数的作用域中的return
                        return getApplyName(applys);
                    }else{
                        //ie9以上及其他浏览器
                        return getApplyName(applys);
                    }
                    //名字处理
                    function getApplyName() {
                        angular.forEach(applys, function (apply) {
                            //最后一次循环的时候逗号去掉
                            self.nameStr += applys.indexOf(apply) == applys.length - 1 ? apply.name : apply.name + ' , ';
                        });
                        return self.nameStr;
                    }
                };
                //请假结果的数据处理
                scope.absenceResult=function (applys) {
                    var result='',num=0;
                    angular.forEach(applys,function (apply) {
                        apply.agree==true?num++:null
                    });
                    if(num==applys.length){
                        result='(都)已批准';
                    }else if(num==0){
                        result='(都)不批准';
                    }else{
                        result='('+num+'/'+applys.length+')';
                    }
                    return result;
                };

                //下拉到底实现瀑布流效果
                var oAllHeightATime=document.getElementById('allHeightATime');
                //滚动事件，下拉得到更多
                document.getElementById('reportInv_wrap').onscroll=function () {
                    //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
                    console.log(document.getElementById('reportInv_wrap').scrollTop+document.getElementById('reportInv_wrap').clientHeight>=document.getElementById('allHeightATime').clientHeight);
                    if(key===true&&pageNum<=that.pageCount&&document.getElementById('reportInv_wrap').scrollTop+document.getElementById('reportInv_wrap').clientHeight>=document.getElementById('allHeightATime').clientHeight){
                        oAllHeightATime.innerHTML+='<my-apply></my-apply>';
                        getAbsenceData(7,2,'published');
                    }
                };
            }
        }
    });