/**
 * Created by jm_hello on 2016/9/14.
 */
angular.module('xiaoMing')
    .controller('staffCtrlNow',['$scope','staffService',function ($scope,staffService) {
    /**
     *
     *初始化
     */
    //保存当前的作用域
    var that=this;
    //初始化总人数
    this.recordCount=0;
    //建立一个空数组contactNowDataList，用来储存用户输入的数据，也方便与后台对接
    var contactNowDataList=[];

    /**
     *
     * 页面配置
     */
    this.pageNowConfig={
        //当前的页数
        currentPage:1,
        //每一页有的item数
        itemsPerPage:10,
        //每一页可供选择的数目
        perPageOptions:[10,20,30]
    };

    this.otherNowInfo={
        sort:1,
        edition:''
    };

    /**
     *
     * 数据的变化——动态变化页数与通讯录的数据
     */
    var getContactNowData=function () {
        var pageNum=that.pageNowConfig.currentPage,
            pageSize=that.pageNowConfig.itemsPerPage,
            sort=that.otherNowInfo.sort,
            edition=that.otherNowInfo.edition;
        //调用contactService服务里的getContactData方法，将赋值好的数据传递回给后台
        staffService.getContactNowData(pageNum,pageSize,sort,edition);
    };

    /**
     *
     * 监听数据
     */
    $scope.$watch('conf.currentPage+conf.itemsPerPage',getContactNowData);
    //与后台$broadcast传过来的数据对接,回调函数里的data指的就是contactService里的contactData
    $scope.$on('getContactNowDataA',function (event,data) {
        that.contactNowDataList=data.data;
        //总人数赋值（当届的页面）。这里要用到that才能让数据在页面显示，总人数就等于有多少行，这里并不需要用到service里的recordCount
        that.recordCount=that.pageNowConfig.totalItems=data.total;
    });
    //下载通讯录
   // this.uploadingFiles=staffService.uploadingFiles;
}])
    .controller('staffOld',['$scope','staffService',function ($scope,staffService) {
        /**
         *
         *初始化
         */
        //保存当前的作用域
        var that=this;
        //初始化总人数
        this.recordCount=0;
        //建立一个空数组contactNowDataList，用来储存用户输入的数据，也方便与后台对接
        var contactOldDataList=[];
        var allEdition=[];

        /**
         *
         * 页面配置
         */

        this.pageOldConfig={
            //当前的页数
            currentPage:1,
            //每一页有的item数
            itemsPerPage:10,
            //每一页可供选择的数目
            perPageOptions:[10,20,30]
        };
        this.otherOldInfo={
            sort:1,
            edition:'2012'
        };

        /**
         *
         * 数据的变化——动态变化页数与通讯录的数据
         */
        var getContactOldData=function () {
                var pageNum=that.pageOldConfig.currentPage,
                    pageSize=that.pageOldConfig.itemsPerPage,
                    sort=that.otherOldInfo.sort,
                    edition=that.otherOldInfo.edition;
                //调用contactService服务里的getContactData方法，将赋值好的数据传递回给后台
                staffService.getContactOldData(pageNum,pageSize,sort,edition);
            };
        /**
         *
         * 监听数据
         */
        $scope.$watch('conf.currentPage+conf.itemsPerPage',getContactOldData);

        //与后台$broadcast传过来的数据对接,回调函数里的data指的就是contactService里的contactData
        $scope.$on('getContactDataOldA',function (event,data) {
            //总人数赋值（往届的页面）
            that.pageOldConfig.totalItems=data.total;
            that.contactOldDataList=data.data;
        });

        //哪一届（往届的页面）
         this.editionA=this.otherOldInfo.edition;
        //下载通讯录
        // this.downloadAddress=contactService.downloadAddress(that.pageConfig.edition);
        staffService.getEdition();
        $scope.$on('getEdition',function (event,data) {
            that.allEdition=data;
            $scope.editionData=data[0];
        });
    }])
    .controller('saveData',['$scope','staffService',function ($scope,staffService) {
        var that=this;
        var allEdition=[];
        this.pageOldConfig={
            //当前的页数
            currentPage:1,
            //每一页有的item数
            itemsPerPage:10,
            //每一页可供选择的数目
            perPageOptions:[10,20,30]
        };
        this.otherOldInfo={
            sort:1
        };

        this.changeEdition=function (editionModel) {
            var pageNum=that.pageOldConfig.currentPage,
                pageSize=that.pageOldConfig.itemsPerPage,
                sort=that.otherOldInfo.sort;
            //console.log('edition:'+edition);
            //调用contactService服务里的getContactData方法，将赋值好的数据传递回给后台
            staffService.getContactOldData(pageNum,pageSize,sort,editionModel);
        };

        staffService.getEdition();
        $scope.$on('getEdition',function (event,data) {
            that.allEdition=data;
            $scope.editionData=data[0];
        });
    }]);
