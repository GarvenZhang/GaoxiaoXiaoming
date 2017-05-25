<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="resources/js/jQuery.json.js"></script>
<script type="text/javascript" src="resources/js/jQuery.md5.js"></script>
<title>Insert title here</title>
</head>
<body>
Index!
<input id="loginName" type="text" name="loginName"/>
<input id="password" type="password" name="password" />
<!-- 
<input id="captcha" type="text" name="code" />
<img id="verify" src="captcha.action">
-->
<input id="login" type="button" value="登陆" />

<p id="p"></p>
<script type="text/javascript">
var u = {
		"loginName":"",
		"password":"",
		"code":""
};

$(function()  
		{  
		    $("#login").click(function()  
		    {  
				u.loginName = $("#loginName").val();
				//u.code = $("#captcha").val();
				u.password = $.md5($.md5($("#password").val()) + u.code);
		        $.ajax(  
		        {  
		            type:"post",  
		            url:"user_login.action",  
		            contentType: "application/json; charset=utf-8",
		            data:$.toJSON(u),
		            dataType:"json", 
		            success:function(returnData,status)  
		            {  
		                if("success" == status)  
		                {  
		                    $("#p").append(JSON.stringify(returnData));  
		                }  
		            }  
		        });   
		    });  
		});  

$(function () {
    //点击图片更换验证码
    $("#verify").click(function(){
        $(this).attr("src","captcha.action?timestamp="+new Date().getTime());
    });
});
</script>
</body>
</html>