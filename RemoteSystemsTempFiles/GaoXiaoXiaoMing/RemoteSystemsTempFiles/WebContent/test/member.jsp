<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/xiaoming/resources/js/jQuery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="/xiaoming/resources/js/jQuery.json.js"></script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>å±:</td>
				<td><input type="text" id="edition"></td>
			</tr>
		</table>
		<input type="button" value="æ¥è¯¢" id="list_btn" />
	</div>

	<div>
        <form action="/xiaoming/org_user/member_importFromTable.action" enctype="multipart/form-data" method="post">
            <s:file name="file"/>
            <br/>
            <s:submit value="Upload" />
        </form>
	</div>
	<%=request.getParameter("file") %>
	<hr>
	<div id="test_list"></div>

	<script type="text/javascript">
		$("#list_btn").click(
				function() {
					$.ajax({
						type : "post",
						url : "/xiaoming/org_user/member_list.action",
						contentType : "application/json; charset=utf-8",
						data : $.toJSON({
							"pageSize" : 5,
							"pageNum" : 1,
							"edition" : $("#edition").val()
						}),
						dataType : "json",
						success : function(returnData, status) {
							if ("success" == status) {
								$("#test_list").append(
										'<p>' + JSON.stringify(returnData)
												+ "</p>");
							}
						}
					})
				});
	</script>
</body>
</html>