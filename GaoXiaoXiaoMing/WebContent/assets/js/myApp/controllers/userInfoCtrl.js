/**
 * Created by Govern on 2016/9/13.
 */
angular.module('xiaoMing').controller('userInfoCtrl',['$scope','userInfoService',function ($scope, userInfoService) {
    //保存当前作用域
    var that =this ;
    //存储后台数据与view接应
    this.userData={};
    //初次请求数据
    userInfoService.getUserData ();
    //动态监听并接收数据
    $scope.$on('getUserData',function (event, data) {
        that.userData=data;
    });

    /**
     * 获得下拉框选项数据
     */
    //学校列表
    this.univList=[];
    $scope.$on('getUniv',function (event, data) {
        that.univList=data;
    });
    this.changeSchool=userInfoService.changeSchool;
    //校区列表
    this.campusList=[];
    $scope.$on('getCampus',function (event, data) {
        that.campusList=data;
    });
    //年级列表
    this.gradeList=[];
    $scope.$on('getGrade',function (event, data) {
        that.gradeList=data;
    });
    //当前机构部门列表
    this.departList=[];
    $scope.$on('getDepart',function (event, data) {
        that.departList=data;
    });

    /**
     * 动态触发ajax发送数据给后台
     */
    //动态监听并修改数据
    this.initData=userInfoService.initData;
    this.newData=userInfoService.newData;
    //获取下拉框option数据的value（即id）
    this.getId=userInfoService.getId;
    //获取输入框value
    this.getValue=userInfoService.getValue;
    //检验修改
    this.correctedData=userInfoService.correctedData;
    this.correctedId=userInfoService.correctedId;
    this.defaultShow=userInfoService.defaultShow;
    this.checkPhoneNum=userInfoService.checkPhoneNum;
    this.checkBlankString=userInfoService.checkBlankString;
}]);