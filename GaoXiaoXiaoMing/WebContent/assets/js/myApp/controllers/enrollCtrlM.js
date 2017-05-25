/**
 * Created by jm_hello on 2016/10/5.
 */
angular.module('xiaoMing')
    .controller('enrollCtrlM',['$scope','enrollServiceM',function ($scope,enrollServiceM) {
    var that=this;
    /**
     *
     * 请求人员列表
     */
    this.ppListData=[];
    //调用获取人员列表
    enrollServiceM.getPeopleList();
    //监听数据变化
    $scope.$on('getPListData',function (event,data) {
        that.ppListData=data;
    });
    //jq插件初始化
    this.rended=enrollServiceM.rended;

    /**
     *
     * 发起活动报名
     */

    //发起报名

    this.applyData={};
    //发起报名按钮
    this.makeSureEnroll=enrollServiceM.makeSureEnroll;
    //提示框出现与隐藏的控制按钮
    this.enrolled=false;

    //为ng-model赋值
    //为选择人员的model赋值
    $scope.$on('idArr',function (event,data) {
        that.applyData.member=data;
    });
    //为信息项的model赋值，在用户点击“发起报名”按钮时，监听
    $scope.$on('infosArr',function (event,data) {
        that.applyData.infos=data;
    });


    //模拟信息输入框，监听
    $scope.$on('info',function (event,data) {
        var timer=setInterval(function () {
            $scope.$apply(function () {
                that.applyData.info=data;
            });
            clearInterval(timer);
        },1);
    });

    //未跳转页面和跳转页面，发起活动报名model清空
    $scope.$on('clearApply2',function (event,data) {
        that.applyData=data;
    });


    /**
     *我的报名
     */

    //可报名的活动
    this.canEnrollData_C=[];
    enrollServiceM.askEnrollmentList(7,1,1);
    $scope.$on('askEnrollmentData_C',function (event,data) {
        that.canEnrollData_C=data;
    });
    //报名按钮
    this.showList={};
    this.show=enrollServiceM.show;
    this.showData=[];
    $scope.$on('showData',function (event, data) {
        that.showData=data;
    });
    //监听可报名活动的数据
    this.myEnData={};
    $scope.$on('myEnData',function (event,data) {
        that.myEnData.name=data.name;
        that.myEnData.content=data.content;
        that.myEnData.title=data.title;
        that.myEnData.id=data.id;
        console.log('data:'+JSON.stringify(data));
    });
    //返回按钮
    this.hide=enrollServiceM.hide;

    //我发起的报名
    this.canEnrollData_M=[];
    enrollServiceM.askEnrollmentList(7,1,0);
    $scope.$on('askEnrollmentData_M',function (event,data) {
        that.canEnrollData_M=data;
    });

    //获得value值
    this.getVal=enrollServiceM.getVal;
    //分割时间
    this.splitTime=enrollServiceM.splitTime;

    //瀑布流
    document.getElementById('canEnrollOut_wrap').onscroll=enrollServiceM.getWaterful_C;
    document.getElementById('enrollOut_wrap').onscroll=enrollServiceM.getWaterful_M;
    /**
     * 处理活动信息
     */
    this.makeSureEnrollment=enrollServiceM.makeSureEnrollment;
        //控制警告框
    this.submitted=false;
}]);