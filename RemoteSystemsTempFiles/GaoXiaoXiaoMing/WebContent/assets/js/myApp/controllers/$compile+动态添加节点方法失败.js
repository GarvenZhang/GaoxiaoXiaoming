/**
 * Created by Govern on 2016/9/18.
 */
angular.module('xiaoMing').service('leaveService',['$rootScope','$state','$http','$compile',function ($rootScope, $state, $http,$compile) {
    //保存当前作用域
    var that = this;

    /**
     * 请求人员列表
     */
    this.getPplList = function () {
        $http({
            method: 'POST',
            url: 'member_simpleList.action'
        }).success(function (data, status, header, config) {
            if (data.status == 'true') {
                $rootScope.$broadcast('getPplList', data.data);
                console.log(data);
            } else if (data.status == 'false') {
                console.info('连接上后台但是获取不了数据');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //插件初始化（解决jq插件与ng-repeat冲突的bug）
    this.rended = function () {
        $('.skin-flat input').each(function () {
            var self = $(this);
            self.iCheck({
                checkboxClass: 'icheckbox_flat-blue'
            });
        });
        console.log(1111);
    };
    /**
     *请假申请
     */

    /**
     * 请求查询列表
     */
    //临时applys容器
    that.applys = [];
    //存当前页数
    this.pageCount='';
    //请求数据
    this.getAbsenceData = function (pageSize,pageNum,type) {
        $http({
            method: 'POST',
            url: 'absence_list.action',
            data: JSON.stringify({
                "pageSize":pageSize,
                "pageNum": pageNum,
                "type": type
            })
        }).success(function (data, status, header, config) {
            if (data.status == 'true' && data.recordList) {
                $rootScope.$broadcast('getAbsencedata', data.recordList);
                //获得总页数
                that.pageCount=data.pageCount;
                console.log('that.pageCount'+that.pageCount);
            } else if (data.status == 'false') {
                console.info('获取失败，请重新获取！')
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    //获取得到第一页请假内容
    that.getAbsenceData(7,1,'published');

    //apply名字的数据处理
    this.showApplyName = function (applys) {
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
    this.absenceResult=function (applys) {
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
    var oAaa=document.getElementById('aaa');
    this.getMoreData=function (pageSize,pageNum,type) {
        //二次请求
        that.getAbsenceData(pageSize,pageNum,type);

        var el=$compile('<div  ng-repeat="data in leave.absenceData" class="list-group">'+
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
            '</a><p ng-bind-html=""></p>'+
            '</div>')($rootScope);

        //js创建DOM
        oAaa.append(el);

    };
    //获取得到第一页请假内容
    var pageNum=1,key=false;
    this.getAbseData=function () {
        if(key===false){
            that.getAbsenceData(7,pageNum,'published');
            key=true;
        }
    }();
    //滚动事件，下拉得到更多
    document.getElementById('reportInv_wrap').onscroll=function () {
        //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
        console.log(document.getElementById('reportInv_wrap').scrollTop+document.getElementById('reportInv_wrap').clientHeight>=document.getElementById('allHeightATime').clientHeight);
        if(key===true&&pageNum<=that.pageCount&&document.getElementById('reportInv_wrap').scrollTop+document.getElementById('reportInv_wrap').clientHeight>=document.getElementById('allHeightATime').clientHeight){
            that.getMoreData(7,pageNum++,'published');
        }
    };






    /*//监听，实现瀑布流效果
     document.getElementById('reportInv').addEventLister('scroll',function () {
     console.log(00000);
     },false);
     EventUtil.addHandler(document.getElementById('reportInv'),'scroll',function () {
     console.log(666);
     });*/



    /**
     * 处理请假请求
     */

}]);