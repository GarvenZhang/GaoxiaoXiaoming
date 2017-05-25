/**
 * Created by Govern on 2016/9/18.
 */
angular.module('xiaoMing').controller('leaveCtrl',['$scope','leaveService',function ($scope, leaveService) {
    //保存当前作用域
    var that=this;
    /**
     *请求人员列表
     */
    this.pplList=[];
    this.getPplList=leaveService.getPplList();
    $scope.$on('getPplList',function (event, data) {
        that.pplList=data;
    });
    //jq插件初始化
    this.rended=leaveService.rended;
    /**
     *请假申请
     */
    this.postAbsenceApply=leaveService.postAbsenceApply;
    /**
     *请求查询列表
     */
    //得到数据
    this.absenceData_P=[];
    this.recordCount_P='';
    $scope.$on('getAbsencedata_P',function (event, data) {
        that.absenceData_P=data.recordList;
        that.recordCount_P=data.recordCount;
    });
    //获取得到第一页请假内容
    leaveService.getAbsenceData(7,1,'published');
    //滚动事件，下拉得到更多
    document.getElementById('reportInv_wrap_P').onscroll=leaveService.getWaterful_P;
    //处理apply中的name
    this.showApplyName=leaveService.showApplyName;
    //请假结果的数据处理
    this.absenceResult=leaveService.absenceResult;
    /**
     * 处理请假请求
     */
    this.absenceData_R=[];
    this.recordCount_R='';
    $scope.$on('getAbsencedata_R',function (event, data) {
        that.absenceData_R=data.recordList;
        that.recordCount_R=data.recordCount;
    });
    //获取得到第一页请假内容
    leaveService.getAbsenceData(7,1,'received');
    //滚动事件，下拉得到更多
    document.getElementById('reportInv_wrap_R').onscroll=leaveService.getWaterful_R;
    /**
     *提交申请
     */
    //提交处理
    this.handleAbsenceRequest=leaveService.handleAbsenceRequest;
    //得到value值
    this.getValue=leaveService.getValue;
    //正则验证datetime-local
    this.datetimeCheck=leaveService.datetimeCheck;
    //表单检验
    this.checkAbsenceApply=leaveService.checkAbsenceApply;
    //清空
    this.reset=leaveService.reset;
}]);