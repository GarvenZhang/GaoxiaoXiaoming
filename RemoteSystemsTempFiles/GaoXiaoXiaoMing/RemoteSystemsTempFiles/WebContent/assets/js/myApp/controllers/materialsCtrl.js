/**
 * Created by Govern on 2016/10/6.
 */
angular.module('xiaoMing').controller('materialsCtrl',['$scope','materialsService',function ($scope, materialsService) {
    //保存当前作用域\
    var that=this;
    /**
     *物资表
     */
    //分页插件初始化
    this.pageConfig={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    var getMaterialsList=function () {
        var pageSize=that.pageConfig.itemsPerPage,
            pageNum=that.pageConfig.currentPage;
        materialsService.materialsList(pageSize,pageNum);
    };
    //动态监听分页变化
    $scope.$watch(function () {
        return that.pageConfig.currentPage+that.pageConfig.itemsPerPage;
    },getMaterialsList);
    //动态接收后台数据
    this.materialsList=[];
    $scope.$on('materialsList',function (event, data) {
        that.materialsList=[data.recordList,data.total];
        that.pageConfig.totalItems=data.total;
    });
    /**
     * 添加物资
     */
    //添加物资btn初始化
    this.materialsBtn=true  ;
    //inp组btn初始化
    this.inpBtn_materialForm=true;
    //添加物资请求
    this.newMaterials={materials:[{}]};
    this.addMaterials=function () {
        materialsService.addMaterials(that.newMaterials);
    };
    //批量添加
    this.moreAdd=function () {
        that.newMaterials.materials.push({})
    };
    //删除添加
    this.delMaterial_add=function (index) {
        that.newMaterials.materials.splice(index,1);
    };
    //表单检验
    this.checkForm=materialsService.checkForm;
    this.newMaterialsForm={name:false,totalCount:false,storageLocation:false};
    //接收默认值
    $scope.$on('defaultFormStatus',function (event, data) {
        that.newMaterialsForm=data;
    });
    //接收检验值
    $scope.$on('newMD_name',function (event, data) {
        that.newMaterialsForm.name=data;
    });
    $scope.$on('newMD_totalCount',function (event, data) {
        that.newMaterialsForm.totalCount=data;
    });
    $scope.$on('newMD_storageLocation',function (event, data) {
        that.newMaterialsForm.storageLocation=data;
    });
    /**
     *修改物资
     */
    //检查是否有修改
    this.material_name=false;this.material_totalCount=false;this.material_storageLocation=false;
    //提交修改修改请求后ng-change恢复默认
    $scope.$on('corMaterials_change',function (event, data) {
        that.material_name=false;
        that.material_totalCount=false;
        that.material_storageLocation=false;
    });
    //修改请求
    this.corMaterials=materialsService.corMaterials;
    //初始化修改按钮
    //修改成功
    this.corSuccess=false;
    $scope.$on('corSuccess',function (event, data) {
        that.corSuccess=data;
    });
    //修改失败
    this.corFail=false;
    $scope.$on('corFail',function (event, data) {
        that.corFail=data;
    });
    /**
     *物资出借
     */
    //用于储存ng-model值，方便一键提交，代替了之前getValue的方法
    this.materialsLendData={};
    this.materialsLend=materialsService.materialsLend;
    //取得物资下拉框数据
    this.materialsSelectData=[];
    $scope.$on('materialsSelectData',function (event, data) {
        that.materialsSelectData=data;
    });
    this.getAllMaterials=materialsService.getAllMaterials;
    //如果有错误，提交表单的时候统一将错误信息显示
    this.$submitted=false;
    this.signupForm=function () {
        materialsLendForm.$valid?materialsService.materialsLend(that.materialsLendData):that.submitted=true;
    };
    //出借数量超额提示
    this.maxCount=0;
    this.lendMaxCount=function (id,data) {
        //由于ng-options不能取到x的值，所以必须要手动遍历之后获取
        var len=data.length-1;
        do{
            if(id==data[len].id){that.maxCount=parseInt(data[len].totalCount)-parseInt(data[len].lentCount);break;}
        }while(len--)
    };
    //表单检验
    this.datetime_local=materialsService.datetime_local;
    /**
     * 删除物资
     */
    this.delMaterial=materialsService.delMaterial;
    $scope.$on('delMateriaSuccess',function (event,data) {
        getMaterialsList();
    });
    /**
     *出借记录
     */
    //出借记录
    this.pageConfig_record={
        currentPage:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30]
    };
    var lendRecordPost=function () {
        var pageSize=that.pageConfig_record.itemsPerPage,
            pageNum=that.pageConfig_record.currentPage;
        materialsService.lendRecord(pageSize,pageNum);
    };
    $scope.$watch(function () {
        return that.pageConfig_record.currentPage+that.pageConfig_record.itemsPerPage;
    },lendRecordPost);
    this.lendRecordData=[];
    $scope.$on('lendRecord',function (event, data) {
        that.pageConfig_record.totalItems=data[1];
        that.lendRecordData=data[0];
    });
    //时间格式处理
    this.datetimeHandle=materialsService.datetimeHandle;
    /**
     * 查询子记录详情
     */
    //进入详情页
    this.goDetailPage=materialsService.goDetailPage;
    this.recordDetailShow=true;
    $scope.$on('recordDetailShow',function (event, data) {
        that.recordDetailShow=data;
    });
    //详情页数据
    this.subRecord=[];
    $scope.$on('subRecord',function (event, data) {
        that.subRecord=data;
    });
    /**
     * 归还物资
     */
    //打开弹窗
    this.returnWindow=materialsService.returnWindow;
    //打开弹窗时阻止冒泡
    this.stopBubble=true;
    $scope.$on('stopBubble',function (event, data) {
        that.stopBubble=data;
    });
    //接收id
    $scope.$on('returnId',function (event, data) {
        that.returnNum.id=data[0];
        that.returnNum.ableToReturnNum=data[1];
    });
    //请求数据
    this.returnNum={};
    this.returnMaterial=materialsService.returnMaterial;
    //表单验证
    this.returnError=false;
    $scope.$on('returnSuccess',function (event, data) {
        that.returnError=data;
        //重新获取当页记录数据
        if(data==='success')lendRecordPost();
        if(data==='close')that.returnNum={};
    });
    /**
     * 删除物资记录
     */



}]);