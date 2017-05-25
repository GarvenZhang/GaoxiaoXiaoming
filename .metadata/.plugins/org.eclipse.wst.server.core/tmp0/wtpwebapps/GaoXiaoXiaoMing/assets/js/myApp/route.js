angular.module('xiaoMing').config(['$stateProvider','$httpProvider', function($stateProvider,$httpProvider){
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

    $stateProvider
    //member
        .state('main.work',{
            //href跳转到对应的页面
            url:'/work',
            //页面模板
            templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/work.html',
            controller:'workCtrl',
            controllerAs:'work',
            resolve:{
                ngrendedDrt:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/ngRended.js')
                }],
                workS:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/workService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/workCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.work.detail',{
            url:'/detail/:projectData',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/work_detail.html',
            controller:'workDetailCtrl',
            controllerAs:'detail',
            resolve:{
                detailS:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/detailService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/detailCtrl.js')
                    })
                }]
            }
        })
        .state('main.inform',{
            url:'/inform',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/inform.html',
            controller:'informCtrl',
            controllerAs:'inform',
            resolve:{
                ngrendedDrt:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/ngRended.js')
                }],
                leaveS:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/infoService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/infoCtrl.js');
                    })
                }
                ]
            }
        })
        .state('main.doc_m',{
            url:'/doc_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/doc.html',
            controller:'docCtrl',
            controllerAs:'doc',
            resolve:{
                orgsS:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/docService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/docCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.enroll',{
        url:'/enroll',
        templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/enroll.html',
        resolve:{
            ngRendedDrt:['$ocLazyLoad',function ($ocLazyLoad) {
                return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/ngRended.js') ;
            }],
            getInputValueDirective:['$ocLazyLoad',function ($ocLazyLoad) {
                return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/getInputValue.js') ;
            }],
            loadLogService:[
                '$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/enrollServiceM.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/enrollCtrlM.js');
                    });
                }]
            }
        })
        .state('main.leave',{
            url:'/leave',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/desk/leave.html',
            controller:'leaveCtrl',
            controllerAs:'leave',
            resolve:{
                ngRendedDrt:['$ocLazyLoad',function ($ocLazyLoad) {
                   return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/ngRended.js')
                }],
                leaveS:['$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/leaveService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/leaveCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.info_o',{
            url:'/info_o',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/organization/info.html',
            controller:'info_oCtrl',
            controllerAs:'info_o',
            resolve:{
                info_oS:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/info_oService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/info_oCtrl.js')
                    })
                }]
            }
        })
        .state('main.contact',{
            url:'/contact',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/organization/contact.html',
            // controller:'contactNowCtrl',
            // controllerAs:'contact',
            resolve:{
                loadLogService:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/contactServiceM.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/contactCtrlM.js');
                        });
                    }]
            }
        })
        .state('main.freetime_m',{
            url:'/freetime_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/organization/freetime.html',
            //controller :
        })
        .state('main.invite_m',{
            url:'/invite_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/organization/invite.html',
            controller : 'inviteCtrlM',
            controllerAs:'inviteM',
            resolve:{
                loadLogService:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/inviteServiceM.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/inviteCtrlM.js');
                        });
                    }]
            }
        })
        .state('main.info_m',{
            url:'/info_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/account/info.html',
            controller:'userInfoCtrl',
            controllerAs:'userInfo',
            resolve:{
                uInfoService:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/userInfoService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/userInfoCtrl.js')
                    });
                }]
            }
        })
        .state('main.message',{
            url:'/message',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/account/message.html',
            controller :'sMessageCtrl',
            controllerAs:'sMessage',
            resolve:{
                sMessageS:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/sMessageService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/sMessageCtrl.js')
                    })
                }]
            }
        })
        //修改密码
        .state('main.password_m',{
            url:'/password_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/account/password.html',
            controller:'passwordCtrl',
            controllerAs:'password',
            resolve:{
                passwordMService:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/passwordService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/passwordCtrl.js')
                    })
                }]
            }
        })
        .state('main.log_m',{
            url:'/log_m',
            templateUrl : '/GaoXiaoXiaoMing/pages/member/account/log.html',
            controller : "logMCtrl",
            controllerAs : "logs",
            resolve: {
                loadLogMService: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/logService.js').then(function(){
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/logMCtrl.js')
                    });
                }]
            }
        })
        //admin
        .state('main.staff',{
            url:'/staff',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/staff.html',
            resolve:{
                staffS:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/staffService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/staffCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.materials',{
            url:'/materials',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/materials.html',
            controller:'materialsCtrl',
            controllerAs:'materials',
            resolve:{
                materialsS:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/materialsService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/materialsCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.doc_a',{
            url:'/doc_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/doc.html',
            //controller :
        })
        .state('main.freetime_a',{
            url:'/freetime_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/freetime.html',
            //controller :
        })
        .state('main.organizations',{
            url:'/organizations',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/organizations.html',
            controller:'orgsInSameUnivCtrl',
            controllerAs:'orgs',
            resolve:{
                orgsS:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/orgsService.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/orgsCtrl.js');
                        })
                    }
                ]
            }
        })
        .state('main.statistics',{
            url:'/statistics',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/statistics.html',
            //controller :
        })
        .state('main.invite_a',{
            url:'/invite_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/organization_admin/invite.html',
            controller : 'inviteCtrlA',
            controllerAs:'inviteA',
            resolve:{
                loadLogService:[
                    '$ocLazyLoad',function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/inviteServiceA.js').then(function () {
                            return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/inviteCtrlA.js');
                        });
                    }]
            }
        })
        .state('main.org_trends',{
            url:'/org_trends',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/message/org_trends.html',
            //controller :
        })
        .state('main.sys_message',{
            url:'/sys_message',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/message/sys_message.html',
            controller :'sMessageCtrl',
            controllerAs:'sMessage',
            resolve:{
                sMessageS:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/sMessageService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/sMessageCtrl.js')
                    })
                }]
            }
        })
        .state('main.letter',{
            url:'/letter',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/message/letter.html',
            //controller :
        })
        .state('main.info_a',{
            url:'/info_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/account_admin/org_info.html',
            controller:'orgInfoCtrl',
            controllerAs:'orgInfo',
            resolve:{
                loadInfoAService:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/orgInfoService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/orgInfoCtrl.js')
                    })
                }]
            }
        })
        .state('main.password_a',{
            url:'/password_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/account_admin/password_admin.html',
            controller:'passwordCtrl',
            controllerAs:'password',
            resolve:{
                passwordAService:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/passwordService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/passwordCtrl.js')
                    })
                }]
            }
        })
        .state('main.log_a',{
            url:'/log_a',
            templateUrl : '/GaoXiaoXiaoMing/pages/admin/account_admin/log.html',
            controller : "logACtrl",
            controllerAs : "logA",
            resolve:{
                loadLogAService:['$ocLazyLoad',function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/logAService.js').then(function () {
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/logACtrl.js')
                    })
                }]
            }

        })
        //other
        //主页
        .state('main.home',{
            url:'/home',
            templateUrl : '/GaoXiaoXiaoMing/pages/other/home.html',
            controller: "homeCtrl",
            controllerAs : "home",
            resolve: {
                loadLogMService: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/logService.js').then(function(){
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/homeCtrl.js')
                    });
                }],
            }
        })
        //登陆页
        .state('login',{
            url:'/login',
            templateUrl : '/GaoXiaoXiaoMing/login.html',
            controller: "loginCtrl",
            controllerAs : "login",
            resolve: {
                loadUserService: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/userService.js').then(function(){
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/loginCtrl.js')
                    });
                }],
            }
        })
        //注册页
        .state('regist',{
            url:'/regist',
            templateUrl : '/GaoXiaoXiaoMing/regist.html',
            controller: "registCtrl",
            controllerAs : "register",
            resolve: {
                loadGetCodeDrt: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/getCodeDirective.js')
                }],
                loadLogMService: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/registService.js').then(function(){
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/registCtrl.js')
                    });
                }],
            }
        })
        .state('enter',{
            url:'',
            onEnter: function($state) {
                $state.go('login');
            }
        })
        .state('main',{
            url:'/main',
            templateUrl : '/GaoXiaoXiaoMing/main.html',
            controller: "mainCtrl",
            controllerAs : "main",
            cache: false,
            resolve: {
                loadSideBar: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/directives/sideBarDirective.js?');//+Math.random()
                }],
                loadLogMService: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/services/userService.js').then(function(){
                        return $ocLazyLoad.load('/GaoXiaoXiaoMing/assets/js/myApp/controllers/mainCtrl.js')
                    });
                }]
            }
        })

}]);