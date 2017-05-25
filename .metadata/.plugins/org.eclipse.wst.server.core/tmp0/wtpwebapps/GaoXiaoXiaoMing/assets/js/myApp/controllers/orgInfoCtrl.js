/**
 * Created by Govern on 2016/9/12.
 */
angular.module('xiaoMing').controller('orgInfoCtrl',['$scope','orgInfoService',function ($scope, orgInfoService) {
    //保存当前作用域
    var that =this ;
    //存储后台数据与view接应
    this.userData={};
    //初次请求数据
    orgInfoService.getUserData ();
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
    this.changeSchool=orgInfoService.changeSchool;
    //校区列表
    this.campusList=[];
    $scope.$on('getCampus',function (event, data) {
        that.campusList=data;
    });
    //获取图片
    this.imgUrl='';
    $scope.$on('getImgUrl',function (event, data) {
        that.userData.logoUrl=data;
        console.log(that.userData.logoUrl);

    });
    document.getElementById('or_logo').onchange=orgInfoService.getImgData;
    /**
     * 动态触发ajax发送数据给后台
     */
    //动态监听并修改数据
    this.initData=orgInfoService.initData;
    this.newData=orgInfoService.newData;
    //获取下拉框option数据的value（即id）
    this.getId=orgInfoService.getId;
    //获取输入框value
    this.getValue=orgInfoService.getValue;
    //检验修改
    this.correctedData=orgInfoService.correctedData;
    this.correctedId=orgInfoService.correctedId;
    this.defaultShow=orgInfoService.defaultShow;
    this.checkPhoneNum=orgInfoService.checkPhoneNum;
    this.checkBlankString=orgInfoService.checkBlankString;
    $scope.$on('getOnOrOff',function (event, data) {
        that.onOrOff=data;
    })

}]);