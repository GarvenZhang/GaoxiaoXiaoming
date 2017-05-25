angular.module('xiaoMing').controller('mainCtrl', ['$rootScope', '$scope','userService', function($rootScope, $scope, userService){
    var self = this;

    //用户信息
    this.user = getCookie("USER_INFO");

    //未读消息
    this.unreadList = {};
    userService.getUnread();
    $scope.$on("getUnread", function(state, data){
        self.unreadList = data;
    });

    //皮肤
    this.skinList = userService.skinList;
    this.setSkin = userService.setSkin;
    this.setSkin();
    
    //注销
    this.logout = userService.logout;

    /**
     *申请加入（新）组织
     */
    this.joinOrg=userService.joinOrg;
    //cookie获得默认值
    this.defaultData=self.user.role==='ORG_USER'? {
        university:self.user.university,
        campus:self.user.campus,
        realName:self.user.realName,
        phoneNumber:self.user.phoneNumber,
        role:self.user.role
    }:null;
    //想父级广播事件
    //$scope.$emit('univ',self.user.university);
    //获取已加入的组织列表
    userService.joineOrgs();
    this.orgs=[];
    $scope.$on('orgs',function (event, data) {
        self.orgs=data;
    });
    //切换组织
    this.changeOrg=userService.changeOrg;
    //获取组织列表
    this.orgsData=[];
    userService.getOrgsData();
    $scope.$on('orgsData',function (event, data) {
        self.orgsData=data;
    });
    //获取部门列表
    this.depart=[];
    this.getDeparData=userService.getDeparData;
    $scope.$on('depart',function (event, data) {
        self.depart=data;
    });
    //提交验证初始化
    this.submitted=false;
    //清空model
    $scope.$on('resetModel',function (event, data) {
        self.joinOrgData=data;
    });

    // 修复闪退bug
    this.showApplyData=userService.showApplyData;
    //手动触发下拉菜单出现
    this.clickDropdown=userService.clickDropdown;
    /**
     *提示性div样式：display：none
     */
    this.defaultHide=userService.defaultHide;
    /**
     *text模拟number框：兼容
     */
    this.onlyNum=userService.onlyNum;
    /**
     *自动切换焦点
     */
    this.autoChangeFocus=userService.autoChangeFocus;

}]);