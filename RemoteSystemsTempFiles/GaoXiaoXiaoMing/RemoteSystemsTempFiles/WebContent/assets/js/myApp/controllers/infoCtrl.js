/**
 * Created by Govern on 2016/9/14.
 */
angular.module('xiaoMing').controller('informCtrl',['$scope','infoService',function ($scope, infoService) {
    //保存当前作用域
    var that=this;
    /**
     *请求人员列表
     */
    this.pplList=[];
    this.getPplList=infoService.getPplList();
    $scope.$on('getPplList',function (event, data) {
        that.pplList=data;
    });
    //jq插件初始化
    this.rended=infoService.rended;
    /**
     *请假申请
     */
    this.postInfo=infoService.postInfo;
    /**
     *请求查询列表
     */
    //得到数据
    this.absenceData_P=[];
    this.recordCount_P='';
    $scope.$on('getInfoData_P',function (event, data) {
        that.absenceData_P=data.recordList;
        that.recordCount_P=data.recordCount;
    });
    //获取得到第一页请假内容
    infoService.getInfoData(7,1,0);
    //滚动事件，下拉得到更多
    document.getElementById('reportInv_wrap_P').onscroll=infoService.getWaterful_P;
    //处理apply中的name
    this.showApplyName=infoService.showApplyName;
    /**
     * 处理请假请求
     */
    this.absenceData_R=[];
    this.recordCount_R='';
    $scope.$on('getInfoData_R',function (event, data) {
        that.absenceData_R=data.recordList;
        that.recordCount_R=data.recordCount;
    });
    //获取得到第一页请假内容
    infoService.getInfoData(7,1,1);
    //滚动事件，下拉得到更多
    document.getElementById('reportInv_wrap_R').onscroll=infoService.getWaterful_R;
    /**
     *发布通知
     */
    //发布处理
    this.handleAbsenceRequest=infoService.handleAbsenceRequest;
    //得到value值
    this.getValue=infoService.getValue;
    //表单检验
    this.checkAbsenceApply=infoService.checkAbsenceApply;
    this.informErrorTip=false;
    //重置
    this.reset=infoService.reset;
}]);