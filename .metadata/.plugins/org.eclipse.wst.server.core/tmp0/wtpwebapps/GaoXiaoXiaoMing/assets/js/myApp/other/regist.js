;(function($){
    
//更改账户类型    
$('.regist-role button').click(function(){
    $('.regist-wrap form').trigger("reset");
    $('.regist-role button').removeClass('active');
    $(this).addClass("active");
    $('.regist-wrap').removeClass('active');
    var idx = $(this).index();
    $('.regist-wrap').eq(idx).addClass("active");
});

//表单校验
$('#regist-member').validate({
    rules: {
        "loginName": {
            minlength: 3,
            required: true
        },
        "phone-member": {
            rangelength: [11,11],
            required: true
        },
        "pwd-member": {
            minlength: 6,
            required: true
        },
        "confirmPwd-member": {
            equalTo: '#pwd-member',
            required: true
        },
        "msg-code-M": {
            required: true
        },
        "school-member": {
            required: true
        },
        "campus-member": {
            required: true
        },
    },
    messages: {
        "loginName": {
            minlength: '用户名至少3个字符',
            required: '请输入用户名'
        },
        "phone-member": {
            rangelength: '请输入11位的手机号码',
            required: '请输入11位的手机号码'
        },
        "pwd-member": {
            minlength: '密码至少6位',
            required: '请输入密码'
        },
        "confirmPwd-member": {
            equalTo: '两次输入不一致',
            required: '请再输入一次密码'
        },
        "msg-code-M": {
            required: '请输入验证码'
        },
        "school-member": {
            required: '请选择学校'
        },
        "campus-member": {
            required: '请选择校区'
        }
    }
});
$('#regist-admin').validate({
    rules: {
        "acc-admin": {
            minlength: 3,
            required: true
        },
        "pwd-admin": {
            minlength: 6,
            required: true
        },
        "confirmPwd-admin": {
            equalTo: '#pwd-admin',
            required: true
        },
        "contact-admin": {
            rangelength: [11,11],
            required: true
        },
        "msg-code-A": {
            required: true
        },
        "org-name": {
            required: true
        },
        "school-admin": {
            required: true
        },
        "campus-admin": {
            required: true
        }
    },
    messages: {
        "acc-admin": {
            minlength: '帐号长度至少为3个字母、数字',
            required: '请填写帐号'
        },
        "pwd-admin": {
            minlength: '密码至少6位',
            required: '请输入密码'
        },
        "confirmPwd-admin": {
            equalTo: '两次输入不一致',
            required: '请再输入一次密码'
        },
        "contact-admin": {
            rangelength: '请输入11位的手机号码',
            required: '请输入11位的手机号码'
        },
        "msg-code-A": {
            required: '请输入验证码'
        },
        "org-name": {
            required: '请输入组织全称'
        },
        "school-admin": {
            required: '请选择学校'
        },
        "campus-admin": {
            required: '请选择校区'
        }
    },
    debug: true
}); 


//请求：获取校区列表
// function getCampus(schoolId){
//     $.post(
//         "http://"+window.location.host+'/GaoXiaoXiaoMing/university_campus.action',
//         JSON.stringify({
//            id: schoolId 
//         })
//     ).done(function(data){
//         data.campusList.each(function(idx){
//             $('.select-sch').append('<option value="'+data.campusList[idx].id+'">'+data.campusList[idx].name+'</option>');
//         });
//     }).fail(function(){
//         console.log('服务繁忙，请重试');
//     })
// }



// //请求：注册
// function register(){
//     $.post(
//         "http://"+window.location.host+'/GaoXiaoXiaoMing/user_register.action',
//         JSON.stringify({
//             "loginName":"admin",
//             "password":"admin",
//             "phoneNumber":"11111111111"
//         })
//     ).done(function(data){
//         console.log(arguments);
//     }).fail();
// }

// register();
    
})(jQuery);