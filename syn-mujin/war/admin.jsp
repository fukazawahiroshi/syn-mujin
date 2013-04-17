<%@page import="com.google.appengine.api.NamespaceManager"%>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html charset="utf-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/jquery-ui-1.10.2.custom.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/main.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/x-table.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/x-table-icons.css" />
<title>Admin</title>
<script>
function setNeedToShow(show, key){
	$.ajax({
		type:"post",
		url: "api",
		data:"setNeedToShow="+show+"&key="+key,
		success: function(d){
			console.log(d);
		}
	});
}
</script>
</head>
<body>
<p style="text-align:center">無人受付システム<!-- <a href="_ah/login?continue=%2Fadmin">logout</a> -->
<!--a href="/admin?action=initWithApps">Google Appsテスト</a-->
userRole=${f:h(userRole)}
status=${f:h(status)}
Namespace=<%= NamespaceManager.get() %>
gmailAccount=<%= UserServiceFactory.getUserService().getCurrentUser().getEmail() %>
<%-- <c:if test="${companryStaffList!=null || fn:length(departmentList)>0 && userRole=='admin'}"> --%>
<%-- <c:if test="${companryStaffList!=null || fn:length(departmentList)>0 }"> --%>
<a href="/admin?action=initData">初期化</a>
<%-- </c:if> --%>
</p>
<%-- <c:if test="${userRole=='superadmin'}">
<%@ include file="domain_xtable.jsp"%>
</c:if>
 --%>
<%-- <c:if test="${companryStaffList==null && fn:length(departmentList)==0 && userRole=='admin'}">
<%//<li><a href="/admin?action=init">内部データで初期化します</a>%>
<%//<li><a href="javascript:$(':file').click()">csvをアップロードします</a>%>
<form name="initform" method="post" action="admin">
<input type="hidden" name="action" value="initWithApps">
google apps password: <input type="password" name="password"><input type="submit" value="初期化"></form>
<form name="form1" method="post" action="/FileUpload" enctype="multipart/form-data">
<input type="file" name="formFile" size="60" style="visibility:hidden" onChange="$(document.form1).submit()">
</form>
</c:if> --%>
<%-- <c:if test="${companryStaffList==null && fn:length(departmentList)==0 && userRole=='user'}">
データの準備ができていません。管理者に連絡してください。 --%>
<%-- </c:if> --%>
<%-- <c:if test="${empty status && fn:length(departmentList)>0}"> --%>
 <%-- <c:if test="${empty status && fn:length(departmentList)>0}"> --%>
 <form action="admin" name="menu">
<table width=60% style="margin:0 auto;text-align:center;border:3px white solid" id="menu">
<c:if test="${userRole=='user' &&  (empty status || status=='nodata')}">
<tr><td colspan=2 onclick="document.menu.status.value='emptable';document.menu.submit()">連絡先表示内容のメンテナンス</td></tr>
<tr><td colspan=2 onclick="document.menu.status.value='offtime';document.menu.submit()">オフタイム表示コンテンツメンテナンス</td></tr>
<tr><td width=30%>
<%-- <ul id="menuxx">
<li><a href="#">拠点▼</a>
<ul>
<c:forEach var="d" items="${locationList}" varStatus="status">
<li id="${status.index}"><a href="#">${f:h(d)}</a></li>
</c:forEach>
</ul>
</li>
</ul>
 --%>
<div id="accordion">
<div>
<h5><a href="#">拠点▼</a></h5>
<ul style="list-style:none">
<c:forEach var="d" items="${locationList}" varStatus="status">
<li id="${status.index}><a href="#">${f:h(d)}</a></li>
</c:forEach>
</ul>
</div>
</p>
</div></td><td onclick="location.href='/'">受付表示の開始</td></tr>
</c:if>
<input type="hidden" name="status">
<!--tr><td colspan=2>ユーザードメイン登録</td></tr-->
<%-- <c:if test="${userRole=='admin'}"> --%>
<c:if test="${userRole=='admin' && (empty status || status=='nodata')}">
<tr><td colspan=2 onclick="document.menu.status.value='domaincontrol';document.menu.submit()">ユーザードメイン登録</td></tr>
<tr><td colspan=2 onclick="document.menu.status.value='usercontrol';document.menu.submit()">ユーザー管理</td></tr>
</c:if>
</table>
</form>
<%-- </c:if> --%>


<c:if test="${status=='offtime'}">
<div style="width:700px;text-align:center;margin:0 auto">
<!--form action="api" method="post" name="offtime" enctype="multipart/form-data"-->
<form action="api" method="post" name="offtime">
<!--tr><td width=100 style='background-color:white;text-align:left'><u>ファイル名:</u></td><td><input type="file" name="videoFile">
<input type=button onclick="submitVideo()" value="Upload"></td></tr-->
<table><tr><td style="vertical-align:top;text-align:left"><u>メッセージ:</u><br><textarea cols=80 rows=3 name="setMessageText" id="setMessageText">${AdMessage}</textarea></td>
<td><input type=button onclick="$(document.offtime).ajaxSubmit();return false;" value="設定"></td></tr></table>
</form>
<div width=100% style="text-align:left"><u>プレビュー:</u></div>
<div style="margin:0 auto;text-align:center">
<div style="position:relative;top:0;border-radius:5px;font:bold 50% sans-serif;z-index:102;margin:0 auto;margin-top:20px;text-align:center;background-color:rgba(0,0,0,0.5);width:60%;color:white" id="message"></div>
<video id="video" style="position:relative;top:-50px;margin:0 auto;width:700px;height:450px" controls>
    <source src="chromeicon.mp4" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"' />
</video>
</div>
<form action="admin" name='xtable' style="text-align:right">
<input type=submit value="戻る">
</form>
<script>
$("#message").html($("#setMessageText").val().replace("\n","<br>"));
$("#setMessageText").bind("keyup change", function(){
	$("#message").html($("#setMessageText").val().replace("\n","<br>"));
});
</script>
</div>
</c:if>

<c:if test="${status=='usertable'}">
<%@ include file="admin_user_xtable.jsp"%>
<form action="admin" name='xtable' style="text-align:right">
<input type=submit value="戻る">
</form>
</c:if>

<c:if test="${status=='emptable'}">
<%@ include file="admin_xtable.jsp"%>
<form action="admin" name='xtable' style="text-align:right">
<input type=submit value="戻る">
</form>
</c:if>

<c:if test="${status=='domaintable'}">
<%@ include file="domain_xtable.jsp"%>
<form action="admin" name='xtable' style="text-align:right">
<input type=submit value="戻る">
</form>
</c:if>

<script>
function submitVideo(){
	$(document.offtime).ajaxSubmit();
}

$(function(){
	/* $("#menuxx").menu(
			{
				posiion:{
					my: "left top",
					at: "right-6 top+4"
				}
			}
	); */

	$("#accordion").accordion({
		header: "h5",
		collapsible:true,
		animated: 'bounceslide' ,
		autoHeight:false,
		active:4
	});
	$("li").live("click", function(){
		var $str = $(this).text();
		alert('拠点を'+$str+'とします。');
		$.ajax({
		    type: 'GET',
		    url: 'api',
		    data: {
		        location: $str
			},
			success: function(arr) {
				/* $("#accordion div div").text("拠点▼："+$str); */
				$("#accordion").accordion({
					active:4
				});

			}
		});
	});
});

</script>
</body>
</html>
