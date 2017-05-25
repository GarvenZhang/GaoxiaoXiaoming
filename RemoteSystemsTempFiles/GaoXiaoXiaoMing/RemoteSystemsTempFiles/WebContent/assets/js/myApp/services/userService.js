angular.module('xiaoMing')
.service("userService", ["$rootScope","$http", "$state", function($rootScope, $http, $state){
    var self = this;
    
    //表单校验
    this.formValidate = function(){
        if($.fn.validate){
            $('#login-form').validate({
                rules: {
                    "account": {
                        required: true
                    },
                    "password": {
                        required: true
                    },
                    //  randomCode: {
                    //      required: true
                    //  }
                },
                messages: {
                    "account": {
                        required: '请输入帐号'
                    },
                    "password": {
                        required: '请输入密码'
                    },
                    // "randomCode": {
                    //     required: '请再输入一次密码'
                    // }
                },
                debug: true
            });   
        }
    };
    
    //登陆
    this.login = function(loginName, password, rdCode){
        var form = {
            loginName: "",
            code: "",
            password: "" //暂定
        };
        /*暂时没有code*/
        if(!rdCode){
            rdCode = "";
        }
        /*暂时没有code*/
        if(!loginName||!password){
            return
        }
        form = {
            loginName: loginName,
            code: rdCode,
            password: $.md5(password) //暂定
        };
        $http({
            method: "POST",
            url: "user_login.action",
            dataType:"json", 
            data: JSON.stringify(form)
        }).success(function(res, status, headers, config){
            if("true" == res.status){  
                //设置1小时cookie
                var deadline = new Date();
                deadline.setHours(deadline.getHours() + 1);
                document.cookie= "USER_INFO="+JSON.stringify(res)+";expires="+deadline.toGMTString();
                //保存用户信息到全局作用域
                $rootScope.user = res;
                //在localStorage保存用户类型
                localStorage.setItem('role',res.role);
                $state.go("main.home");
            }else if(res.status == "false" && res.error == "accountpassword"){
                console.error("密码错了锁嗨");
            }  
        }).error(function(res, status, headers, config){
            console.error("未知错误");
        });
    };

    //用户信息未过期则不需要登陆自动跳转到首页
    this.autoLogin = function(){
        var userInfo = getCookie("USER_INFO");
        if(userInfo != null){
            $state.go("main.home");
        }
    };
    
    //注销
    this.logout = function(){
        $http({
            method: "POST",
            url: "user_logout.action"
        }).success(function(res, status, headers, config){
             removeCookie("USER_INFO");
             $state.go("login",{},{reload:true});
             window.location.reload();
        }).error(function(res, status, headers, config){
            console.error("未知错误");
        });
    };
    
    //获取未读消息
    this.getUnread = function(org_id){
        var data = {id: ""};
        if(org_id){
            data.id = org_id;
        }
        $http({
            method : "POST",
            url : 'unread_query.action',
            data : JSON.stringify(data)
        }).success(function(res, status, headers, config){
            if(res.status == "true"){
                $rootScope.$broadcast("getUnread", res);
            }else if(res.status == "false" && res.error == "noLogin"){
                removeCookie("USER_INFO");
                $state.go('login');
            }
        }).error(function(res, status, headers, config){
            console.error("服务器繁忙，请稍后重试");
        });
    };

    //保存皮肤设定
    this.skinList = [
        "url('assets/img/bg5.jpg')no-repeat center center fixed",
        "url('assets/img/bg2.jpg')no-repeat center center fixed",
        "url('assets/img/bg.jpg')no-repeat top center fixed",
        "url('assets/img/giftly.png')repeat",
        "#2c3e50",
        "url('assets/img/bg3.png')repeat",
        "url('assets/img/bg8.jpg')no-repeat center center fixed",
        "url('assets/img/bg9.jpg')no-repeat center center fixed",
        "url('assets/img/bg10.jpg')no-repeat center center fixed",
        "url('assets/img/bg11.jpg')no-repeat center center fixed",
        "url('assets/img/bg12.jpg')no-repeat center center fixed",
        "url('assets/img/bg13.jpg')repeat"
    ];
    this.setSkin = function(id){
        var userInfo = getCookie("USER_INFO");
        if(!userInfo)return
        if(!id && arguments.length == 0){
            var curBg = localStorage.getItem("custom_bg_"+userInfo.phoneNumber);
            if(curBg){
                $("body").css({
                    "background": self.skinList[curBg] 
                }); 
            }else{
                $("body").css({
                    "background": self.skinList[2] 
                });
            }
        }else{
            localStorage.setItem("custom_bg_"+userInfo.phoneNumber, id);
            $("body").css({
                "background": self.skinList[id]
            });
        }
    };

    /**
     *申请加入（新）组织
     */
    //获取已加入的组织列表
    this.joineOrgs=function () {
        $http({
            method:'POST',
            url:'userInfo_memberList.action',
            data:JSON.stringify({
                'pageSize':5,
                'pageNum':1
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('orgs',data.data);
                if(data.data.length===0){
                    //如果用户没有加入过任何组织，则默认打开弹窗进行申请
                    //模拟点击事件
                    imitateClick('showApplyPage');
                }
            }else if (data.status=='false'){
                console.info('获取失败，请重新获取!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //切换组织
    this.changeOrg=function (id) {
        $http({
            method:'POST',
            url:'user_changeMember.action',
            data:JSON.stringify({
                "id":id
            })
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                console.info('切换成功!');
            }else if (data.status=='false'){
                console.info('切换失败，请重新切换!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //获取组织列表
    this.getOrgsData=function () {
        $http({
            method:'POST',
            url:'university_org.action',
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('orgsData',data.data);
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //获取部门列表
    this.getDeparData=function (orgId) {
        $http({
            method:'GET',
            url:'department_list.action?id='+orgId
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                $rootScope.$broadcast('depart',data.data);
            }else if (data.status=='false'){
                console.info('获取失败，请稍后再试!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //提交申请
    this.joinOrg=function (applyInfo,close,second,successTip) {
        console.log(applyInfo);
        $http({
            method:'POST',
            url:'joinApply_apply.action',
            dataType:'json',
            data:applyInfo
        }).success(function (data, status, header, config) {
            if(data.status=='true'){
                postSuccessTip(close,second,successTip);
            }else if (data.status=='false'){
                console.info('申请失败，请重新申请!');
            }
        }).error(function (data, status, header, config) {
            console.error('网络繁忙，请稍后再试!');
        })
    };
    //提交成功时延迟3s作为提示
    function postSuccessTip(close,second,successTip) {
        //初始化为3s
        var num=2;
        //出现提示:因为是在1000ms之后才开始运行内部代码，所以要初始化为3
        document.getElementById(second).innerHTML=3;
        document.getElementById(successTip).style.display='inline';
        //倒数3s
        var timer=setInterval(function () {
            document.getElementById(second).innerHTML=num;
            num--;
            if(num===-1){
                //模拟点击事件
                imitateClick(close);
                //提交成功的提示消失
                document.getElementById(successTip).style.display='none';
                //关闭定时器
                clearInterval(timer);
                document.getElementById(second).innerHTML=3;
            }
        },1000);

    }
    //监听弹窗，关闭后重置表单
    $('.org-apply').on('hidden.bs.modal',function () {
        //表单重置：内容
        var temp={};
        $rootScope.$broadcast('resetModel',temp);
        //模拟点击事件：重置错误提示
        imitateClick('resetBtn');
    });
    //模拟点击事件封装
    function imitateClick(id) {
        //创建重置对象
        var event=document.createEvent('MouseEvents');
        //初始化事件对象
        event.initMouseEvent('click',true, true, document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        //触发事件
        document.getElementById(id).dispatchEvent(event);
    }

    // 修复闪退bug
    this.showApplyData=function () {
        $('#org_apply').modal('show');
    };
    var  oJoinedOrgs;
    //显示或隐藏下拉菜单
    this.clickDropdown=function (e) {
        //获取元素
        oJoinedOrgs=document.getElementById('joinedOrgs');
        //兼容事件对象
        var ev=e||window.event;
        //阻止冒泡
        if( ev.stopPropagation){
            ev.stopPropagation();
        }else{
            e.cancelBubble=true;
        }
        oJoinedOrgs.style.display='block';
    };
    //点击文档任意地方，使下拉菜单消失
    document.onclick=function () {
       if(oJoinedOrgs){
           oJoinedOrgs.style.display='none';
       }
    };


    /**
     *实时时钟
     */
    var timer_clock=setInterval(function () {
        if(document.getElementById('meridiem')){
            clock();
            clearInterval(timer_clock);
        }
    },500);
    function clock() {
        var toDate=new Date(),oDocument=document,oMeridiem=oDocument.getElementById('meridiem'),oSec=oDocument.getElementById('sec'),oMin=oDocument.getElementById('min'),
            oHour=oDocument.getElementById('hour'),oDate=oDocument.getElementById('Date');
        //初始化
        oSec.innerHTML=toDate.getSeconds();
        oMin.innerHTML=toDate.getMinutes();
        oHour.innerHTML=toDate.getHours();
        toDate.getHours()>12?oMeridiem.innerHTML='PM':oMeridiem.innerHTML='AM';
        var monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        var dayNames = ["Sun, ", "Mon, ", "Tue, ", "Wed, ", "Thu, ", "Fri, ", "Sat, "];
        oDate.innerHTML=dayNames[toDate.getDay()]+' '+new Date().getDate()+' '+monthNames[toDate.getMonth()]+' '+new Date().getFullYear();
        //实时变换
        setInterval(function () {
            var newDate=new Date();
            //减少js与html连接，优化
            oSec.innerHTML=newDate.getSeconds();
            if(newDate.getSeconds()==0){
                oMin.innerHTML=newDate.getMinutes();
                if(newDate.getMinutes()==59){
                    oSec.innerHTML=newDate.getHours();
                    if(newDate.getHours()==13){
                        oMeridiem.innerHTML='PM';
                    }else if(newDate.getHours()==0){
                        oMeridiem.innerHTML='AM';
                    }
                }
            }
        },1000);
    }

    /**
     * 只有将tip的style设为display：none才能够在刚打开页面的时候不会先出现tip（ng-show里面的表达式因为加载的原因表现为延迟）
     * 放在此处，各个子页面都能用
     */
    this.defaultHide=function (id) {
        //三目运算符中判断条件的运算时间
        setTimeout(function () {
            document.getElementById(id).removeAttribute('style');
        },500);
        return true;
    };

    /**
     *用text模拟number框：兼容
     */
    this.onlyNum=function (e) {
        var e=e||window.event;
        //取得键码并兼容
        var charCode=e.charCode||e.keyCode;
        //如果输入的不是基本键或者数字键，则阻止冒泡
        if(!/\d/.test(String.fromCharCode(charCode))&&charCode>9){
            if(e.preventDefault){
                e.preventDefault();
            }else{
                e.returnValue=false;
            }
        }
    };
    /**
     *自动切换焦点
     */
    var tempPreCode;
    this.autoChangeFocus=function (e,nextInp) {
        var e=e||window.event;
        var charCode=e.charCode||e.keyCode;
        var target=e.target||e.srcElement;
        //达到符合要求的长度则自动换焦
        if(target.value.length==target.maxLength&&target.value.length==!tempPreCode){
            console.log(document.getElementById(nextInp));
            document.getElementById(nextInp).focus();
            //保存上一次的长度
            tempPreCode=target.value.length;
        }
    }
}]);