/**
 * Created by Govern on 2016/9/14.
 */
angular.module('xiaoMing').service('infoService',['$rootScope','$http','$state',function ($rootScope, $http, $state) {
    //保存当前作用域
    var that = this;
    /**
     * 请求人员列表
     */
    var pplList;
    this.getPplList = function () {
        $http({
            method: 'POST',
            url: 'member_simpleList.action'
        }).success(function (data, status, header, config) {
            if (data.status == 'true') {
                $rootScope.$broadcast('getPplList', data.data);
                //储存给提交申请用
                pplList=data.data;
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
    };
    //闪退bug
    $('#chosenPpl').on('focus',function () {
        console.log('bbbbasd');
        $('#chosen_per').modal('show');
    });
    /**
     *请假申请
     */
    //申请请求
    this.postInfo=function (members,content) {
        $http({
            method:'POST',
            url:'message_save.action',
            data:JSON.stringify({
                "members": members,
                "content": content
            })
        }).success(function (data, header, status, config) {
            if(data.status=='true'){
                confirm('通知发布成功！');
            }else if (data.status=='false'){
                console.log('发布失败，请重新发布通知！');
            }
        }).error(function (data,header,status,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    //得到value值
    this.getValue=function (eleId) {
        return document.getElementById(eleId).value;
    };
    //发布通知
    this.checkAbsenceApply=function () {
        that.postInfo(that.getValue('chosenPplId').split(','),that.getValue('absenceReasonTxtA'));
    };
    //清空
    this.reset=function () {
        //模拟事件清空人员列表选项
        for(var i =0,aLi=document.getElementById('pplForm_inform').getElementsByTagName('li'),lenLi=aLi.length;i<lenLi;i++)
            for(var j =0,aPplId=document.getElementById('chosenPplId').value.split(','),len_aPplId=aPplId.length;j<len_aPplId;j++)
                if(aPplId[j]==aLi[i].children[1].lastElementChild.innerHTML)imitClick(aLi[i].children[0].lastElementChild);
        //清空value
        document.getElementById('chosenPplId').value=document.getElementById('absenceReasonTxtA').value=document.getElementById('chosenPpl').value='';
    };
    /**
     * 请求查询列表
     */
    //存总数据
    this.recordCount_P='';
    this.recordCount_R='';
    //请求数据
    this.getInfoData = function (pageSize,pageNum,type) {
        $http({
                method: 'POST',
                url: 'message_list.action',
                data: JSON.stringify({
                    "pageSize":pageSize,
                    "pageNum": pageNum,
                    "type": type
                })
            }).success(function (data, status, header, config) {
            //同一接口用于两个瀑布流
            if (data.status == 'true'&&type==0) {
                //临时声明一个传播的数据集合
                var temp_P=[];
                temp_P.push({
                    recordList:data.recordList,
                    recordCount:data.recordCount
                });
                $rootScope.$broadcast('getInfoData_P', temp_P[0]);
                //获得数据总数目
                console.log('p:'+data.recordList);
                that.recordCount_P=data.recordCount;
            }else if (data.status=='true'){
                //临时声明一个传播的数据集合
                var temp_R=[];
                temp_R.push({
                    recordList:data.recordList,
                    recordCount:data.recordCount
                });
                $rootScope.$broadcast('getInfoData_R',temp_R[0]);
                //获得数据数目
                that.recordCount_R=data.recordCount;
            }else if (data.status == 'false') {
                console.info('获取失败，请重新获取！')
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
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
    //滚动事件，下拉得到更多
    var x_p=0,x_r=0;
    //因为service.js文件载入一次，而controller.js文件在每次打开页面的时候都会重新载入，所以要在hash值变化的时候把x_p归零
    window.onhashchange=function () {
        x_p=x_r=0;
    };
    this.getWaterful_P=function() {
        //每次请求的数据数
        var count=7*(x_p+1);
        //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
        if((that.recordCount_P-count>=0||Math.abs(that.recordCount_P-count)<7)&&document.getElementById('reportInv_wrap_P').scrollTop+document.getElementById('reportInv_wrap_P').clientHeight>=document.getElementById('allHeightATime_P').clientHeight){
            //重新请求数据，在不改变当前页数的情况下，将一页中容纳的数据变多，便达到了瀑布流的效果
            that.getInfoData(count,1,0);
            x_p++;
        }
    };
    this.getWaterful_R=function() {
        //每次请求的数据数
        var count=7*(x_r+1);
        //当得到第一页&&当获取的页数不超过总页数&&当下拉到底
        if((that.recordCount_R-count>=0||Math.abs(that.recordCount_R-count)<7)&&document.getElementById('reportInv_wrap_R').scrollTop+document.getElementById('reportInv_wrap_R').clientHeight>=document.getElementById('allHeightATime_R').clientHeight){
            //重新请求数据，在不改变当前页数的情况下，将一页中容纳的数据变多，便达到了瀑布流的效果
            that.getInfoData(count,1,1);
            x_r++;
        }
    };
    /**
     *封装函数
     */
    //点击事件
    function imitClick(ele) {
        //创建事件对象
        var event=document.createEvent('MouseEvents');
        //事件对象初始化
        event.initMouseEvent('click',true, true, document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        //触发事件
        ele.dispatchEvent(event);
    }
}]);
