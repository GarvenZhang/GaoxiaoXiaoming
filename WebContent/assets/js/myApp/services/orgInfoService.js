/**
 * Created by Govern on 2016/9/12.
 */
angular.module('xiaoMing')
    .service('orgInfoService',['$rootScope','$http','$state',function ($rootScope,$http,$state) {
    /**
     * 初始化
     */
    //保存当前作用域
    var that=this;
    //初始化contactName和phone/officeAddress/orgDescription/isPublic数据
    this.postData={
        name:'',
        phone:' ',
        contactName:' ',
        officeAddress:'',
        orgDescription:'',
        public:''
    };
    //备用于phone/contactName/officeAddress/orgDescription/isPublic的修改
    var postData={
        name:'',
        phone:' ',
        contactName:' ',
        officeAddress:'',
        orgDescription:'',
        public:''
    };
    //学校列表
    this.univList=[];
    //校区列表
    this.campusList=[];
    //年级列表
    this.gradeList=[];
    //部门列表
    this.departList=[];

    /**
     * 获得默认数据
     */
    //请求数据
    this.getUserData=function () {
        $http({
            method:'POST',
            url:'organization_info.action',
            cache:true
            //statue:200~299
        }).success(function (data, status, header, config) {
            //清空
            that.postData={};
            //获取数据
            if(data.status=='true'){
                //向下传播到各个子级
                $rootScope.$broadcast('getUserData',data);
                //获得contacts和phone数据
                postData.name=that.postData.name=data.name;
                postData.phone=that.postData.phone=data.phone;
                postData.contactName=that.postData.contactName=data.contactName;
                postData.officeAddress=that.postData.officeAddress=data.officeAddress;
                postData.orgDescription=that.postData.orgDescription=data.orgDescription;
                postData.public=that.postData.public=data.public;
                data.public==false?document.getElementById('public').parentNode.className='switch-off switch-animate':document.getElementById('public').parentNode.className='switch-on switch-animate';
                //保证在此数据获取成功的情况下再获取下拉框数据，以获得各下拉框的值
                that.getUniv();
                //获取数据失败时
            }else if(data.status=='false'){
                //$state.go('login');
                console.log('连接错误！');
            }
            //响应失败时
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后重试');
        })
    };

    /**
     * 获得下拉框选项数据
     */
    //获取学校列表
    this.getUniv=function () {
        $http({
            method:'POST',
            url:'university_list.action',
            cache:true
        }).success(function (data, status, header, config) {
            //接收数据
            if(data.status=='true'){
                $rootScope.$broadcast('getUniv',data.data);
                //联动校区
                that.getCampus(data.data.id);
                console.log(2);
            }
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后重试');
        })
    };
    //学校与校区联动
    this.changeSchool=function (newSchoolId) {
        that.getCampus(newSchoolId);
    };
    //获取校区列表
    this.getCampus=function (idNum) {
        $http({
            method:'POST',
            url:'university_campus.action',
            cache:true,
            data:JSON.stringify({
                "id":idNum
            })
        }).success(function (data, status, header, config) {
            //清空
            that.campusList=[];
            //接收数据
            if(data.status=='true'){
                that.campusList=data.data;
                $rootScope.$broadcast('getCampus',that.campusList);
                console.log(3);
            }else if(data.status=='false'){
                console.info('请求失败');
            }
        }).error(function (data, status, header, config) {
            console.error('服务器繁忙，请稍后重试');
        })
    };
    //上传图片
    this.getImgData=function (file) {
        //转化格式
        var formdata=new FormData();
        var fileObj=this.files;
        formdata.append('file',fileObj[0]);
        //发送请求
        $http({
            method:'POST',
            url:'organization_updateLogo.action',
            transformRequest:angular.identity,
            headers:{'Content-Type':undefined},
            dataType:'json',
            data:formdata
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('getImgUrl',data.url);
            }else if(data.status=='false'){
                console.info('上传失败');
                //o.parent().children("#or_logo").attr("src", data.url);
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };

    /**
     * 修改及检验
     */
    //ng-focus时复制的模板,用作对照
    var init;
    this.initData=function (initData) {
        init=initData;
    };
    //下拉框版：获取到当前input的value值
    this.getId=function (inputName,nameList) {
        var Id;
        var eleValue=document.getElementById(inputName).value;
        //用jq根据元素属性获取元素eleHtml
        var eleHtml=$('#'+inputName+' option[value$='+eleValue+']').html();
        //匹配及转换为id
        angular.forEach(nameList,function (value) {
            if(value.name==eleHtml.toString()){
                Id=value.id;
            }
            return;
        });
        return Id;
        //html中默认值一开始会返回NaN，不过不影响此处的运算
        // return value=(parseInt(document.getElementById(inputName).value)+1).toString();
    };
    //书写框版：获取到当前input的value值
    this.getValue=function (inputName) {
        return value = document.getElementById(inputName).value.toString();
    };
    this.onOrOff=true;
    //ng-blur时进行对照，若有修改，则发送请求
    this.newData=function (DataName,nameList,newData) {
        //开关
        var oneOfTwoKey=false;
        //查询对照post内容是否含有两个必要key
        //注意此处形参中value与key反了
        //nameList为1只是为了区别于下面的if
        if(nameList===1){
            angular.forEach(that.postData,function (value,key) {
                //如果有修改,即聚焦前与聚焦后值不相同,因为类型不同，所以全都转换为string型再对比
                //如果是两个必要key的其一
                //输入内容为NaN情况不能发送请求
                if(newData.toString()!==init.toString()&&key===DataName&&newData.toString()!=='NaN'){
                    that.postData[DataName]=newData.toString();
                    //发送修改
                    that.sendUserData(that.postData,DataName);
                    //表明是两种情况的第一种情况，则无需判断第二种情况
                    oneOfTwoKey=true;
                }
            });
        }
        //开关类型的检验及修改请求发送（是否公开）
        if(nameList===2){
            if(document.getElementById(newData).parentNode.className.indexOf('switch-off')){
                that.onOrOff='公开';
            }else if (document.getElementById(newData).parentNode.className.indexOf('switch-on')){
                that.onOrOff='不公开';
            }
            console.log(that.onOrOff);
            $rootScope.$broadcast('getOnOrOff',that.onOrOff);
            that.postData['public']=that.onOrOff;
            that.sendUserData(that.postData,'public');
            oneOfTwoKey=true;
        }
        //转化为json中每个对象所对应的id
        var newId=that.getId(DataName,nameList);

        //要发送修改请求的前提都是：前后值不一样
        //初始化(一定包含name/contactName和phone/officeAddress/orgDescription)
        if(oneOfTwoKey===false&&newId.toString()!==init.toString()){
            //加上可选的修改数据
            postData[DataName]=newId.toString();
            that.sendUserData(postData,DataName);
        }
    };
    //发送修改数据成功的检验
    var corrected;
    this.correctedData=function () {
        if(typeof corrected=='boolean'){
            return corrected;
        }
        return corrected;
    };
    //默认错误提示不显示
    var defaultShow;
    this.defaultShow=function () {
        switch (defaultShow){
            case 'univ':
                defaultShow='universityId';
                break;
            case 'camp':
                defaultShow='campusId';
                break;
            case 'pub':
                defaultShow='public';
                break;
        }
        return defaultShow;
    };
    //标识元素id
    var eleId;
    //当跳转页面时成功信息提示全部清掉
    window.onhashchange=function () {
        eleId=0;
    };
    //锁定当前元素，以对应
    this.correctedId=function () {
        return corrected===true?eleId:eleId=0;
    };
    //手机号码正则检验
    this.checkPhoneNum=function (phoneNum) {
        return new RegExp(/^1(3|5|7|8)\d{9}$/).test(phoneNum)?true:false;
    };
    //空字符检验
    this.checkBlankString=function (string) {
        return string.length!==0?true:false;
    };
    //动态发送数据
    //巧妙地将元素id与dataName即接口文档中的key同名，所以不用另建形参
    this.sendUserData=function (data,id) {
        $http({
            method:'POST',
            url:'organization_update.action',
            //data对象的格式
            dataType:'json',
            data:JSON.stringify(data)
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                console.info('修改成功');
                corrected=true;
                eleId=id;
            }else if(data.status=='false'){
                corrected=false;
                console.info('修改失败');
                switch (id){
                    case 'universityId':
                        defaultShow='univ';
                        break;
                    case 'campusId':
                        defaultShow='camp';
                        break;
                    case 'public':
                        defaultShow='pub';
                        break;
                }
            }
        }).error(function (data, status, header, config) {
            console.log('服务器繁忙，请稍后重试');
        })
    };

}]);