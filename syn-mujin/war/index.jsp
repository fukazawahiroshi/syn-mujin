<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html charset="UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width" />
<script src="js/jquery-1.9.1.min.js"></script>
<link href="css/main.css" rel="stylesheet" type="text/css">
<!--script>
$(window).bind('resize load', function(){
        $("html").css("zoom" , $(window).width()/320 );
});
</script-->
<title>Home</title>
</head>
<body width="100%" onload="window.scrollTo(0,1);">

<%// 検索方法選択 %>
<div id="selectMethod" style="position:absolute;width:100%">
<div id="initialSubList">
<ul style="floate:left">
<li onclick='pushPage($("#pageInitial"))'>五十音検索
<li onclick='showOrganization()'>部署検索
</ul>
</div>
</div>

<%// イニシャル検索 %>
<%// top page %>
<div id="pageInitial" style="display:none;position:absolute;width:100%">
<div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div>
<div id="initialList">
<script src="js/keyboard.js"></script>
<!--
<ul><li><div class="topInitialCell" onclick="showInitialList('あ')">あ</div><li><div class="topInitialCell" onclick="showInitialList('い')">い</div><li><div class="topInitialCell" onclick="showInitialList('う')">う</div><li><div class="topInitialCell" onclick="showInitialList('え')">え</div><li><div class="topInitialCell" onclick="showInitialList('お')">お</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('か|が')">か</div><li><div class="topInitialCell" onclick="showInitialList('き|ぎ')">き</div><li><div class="topInitialCell" onclick="showInitialList('く|ぐ')">く</div><li><div class="topInitialCell" onclick="showInitialList('け|げ')">け</div><li><div class="topInitialCell" onclick="showInitialList('こ|ご')">こ</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('さ|ざ')">さ</div><li><div class="topInitialCell" onclick="showInitialList('し|じ')">し</div><li><div class="topInitialCell" onclick="showInitialList('す|ず')">す</div><li><div class="topInitialCell" onclick="showInitialList('せ|ぜ')">せ</div><li><div class="topInitialCell" onclick="showInitialList('そ|ぞ')">そ</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('た|だ')">た</div><li><div class="topInitialCell" onclick="showInitialList('ち|ぢ')">ち</div><li><div class="topInitialCell" onclick="showInitialList('つ|づ')">つ</div><li><div class="topInitialCell" onclick="showInitialList('て|で')">て</div><li><div class="topInitialCell" onclick="showInitialList('と|ど')">と</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('な')">な</div><li><div class="topInitialCell" onclick="showInitialList('に')">に</div><li><div class="topInitialCell" onclick="showInitialList('ぬ')">ぬ</div><li><div class="topInitialCell" onclick="showInitialList('ね')">ね</div><li><div class="topInitialCell" onclick="showInitialList('の')">の</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('は|ば|ぱ')">は</div><li><div class="topInitialCell" onclick="showInitialList('ひ|び|ぴ')">ひ</div><li><div class="topInitialCell" onclick="showInitialList('ふ|ぶ|ぷ')">ふ</div><li><div class="topInitialCell" onclick="showInitialList('へ|べ|ぺ')">へ</div><li><div class="topInitialCell" onclick="showInitialList('ほ|ぼ|ぽ')">ほ</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('ま')">ま</div><li><div class="topInitialCell" onclick="showInitialList('み')">み</div><li><div class="topInitialCell" onclick="showInitialList('む')">む</div><li><div class="topInitialCell" onclick="showInitialList('め')">め</div><li><div class="topInitialCell" onclick="showInitialList('も')">も</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('や')">や</div><li><div class="topInitialCell" onclick="showInitialList('')"></div><li><div class="topInitialCell" onclick="showInitialList('ゆ')">ゆ</div><li><div class="topInitialCell" onclick="showInitialList('')"></div><li><div class="topInitialCell" onclick="showInitialList('よ')">よ</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('ら')">ら</div><li><div class="topInitialCell" onclick="showInitialList('り')">り</div><li><div class="topInitialCell" onclick="showInitialList('る')">る</div><li><div class="topInitialCell" onclick="showInitialList('れ')">れ</div><li><div class="topInitialCell" onclick="showInitialList('ろ')">ろ</div></ul>
<ul><li><div class="topInitialCell" onclick="showInitialList('わ')">わ</div><li><div class="topInitialCell" onclick="showInitialList('')"></div><li><div class="topInitialCell" onclick="showInitialList('')"></div><li><div class="topInitialCell" onclick="showInitialList('')"></div><li><div class="topInitialCell" onclick="showInitialList('')"></div></ul>
-->
</div>
</div>
<%// リスト %>
<div id="pageInitial2" style="display:none;position:absolute">
<div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div>
<div id="initialSubList">
<ul id="empNameLines" style="floate:left">
</ul>
</div>
</div>
<%// 個別データ表示 %>
<div id="pageInitial3" style="display:none;position:absolute">
<div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div>
<div id="profileDetail">
<p class="profileLine" id="nameHolder"></p>
<p class="profileLine" id="kaneNameHolder"></p>
<p class="profileLine" id="deptHolder"></p>
<p class="profileLine" id="phoneHolder"></p>
<p style="text-align:center;"><img id="photoHolder" width=100 height=100></p>
</div>
</div>

<%// 組織階層ブラウズ %>
<%// level 1 (部) %>
<div id="pageOrganization" style="display:none;position:absolute;width:100%;overflow:hidden">
<div style="width:300%">
<div style="width:50px;position:fixed;float:left" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div>
<div id="deptSublist" style="left:100px">
<ul id="Departments">
<c:forEach var="d" items="${departmentList}">
	<li onclick="showSection('${f:h(d)}')">${f:h(d)}
</c:forEach>
</ul>
</div>
<!--/div-->
<%// level 2 (課) %>
<!--div id="pageOrganization2" style="display:none;position:absolute;width:50%"-->
<!--div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div-->
<div id="deptSublist">
<ul id="Sections">
</ul>
</div>
<!--/div-->
<%// level 3 (係) %>
<!--div id="pageOrganization3" style="display:none;position:absolute;width:50%"-->
<!--div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div-->
<div id="deptSublist">
<ul id="Subsections">
</ul>
</div>
</div>
</div>

<%// level 4 (スタッフ) %>
<div id="pageOrganization4" style="display:none;position:absolute;width:50%">
<div style="width:50px;position:fixed" onclick='popPage()'><div class="arrow_box_l" style="width:0px;height:100px;left:50px"></div></div>
<div id="initialSublist">
<ul id="EmpList">
</ul>
</div>
</div>

<%// movie %>
<div class="video-container" style="text-align:center;display:none;position:absolute;width:100%;height:100%;z-index:100;background-color:#ffffff" onclick="stopMovie()" id="video-container">
<div id="adMessage" style="position:relative;top:5%;border-radius:10px;font:bold 150% sans-serif;z-index:102;margin:0 auto;margin-top:20px;text-align:center;background-color:rgba(0,0,0,0.5);width:60%;color:white"></div>
<video id="video" style="position:absolute;z-index:-1;top:0px;width:100%;height:100%;margin:0">
    <source src="chromeicon.mp4" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"' />
</video>
</div>

<%// スクリプト %>
<script>
<%// jqueryオブジェクト page管理用スタック %>
var stack=new Array();
stack.push($("#selectMethod"));
<%// 半分push用 stack %>
var hstack=new Array();
<%// departmentに属さないcomapnyStaffを取得とセット %>
var companyStaff=${companyStaffList};
var next=$("#Departments").children("li:first");
for(s in companyStaff){
	var staff=companyStaff[s];
	$("<li><div onclick=showEmp("+JSON.stringify(staff)+")>"+fullname(staff)+"</div></li>").insertBefore(next);
}
<%// 動画タイマーのセット %>
var videoInterval=30000000;
$("#adMessage").html("${AdMessage}".replace("\n","<br>"));
var movieTimer=setTimeout(function(){
	playMovie();
}, videoInterval);
$("*").bind("click", function(){
	clearTimeout(movieTimer);
	stopMovie();
	movieTimer=setTimeout(function(){
		playMovie();
	}, videoInterval);
});
document.getElementById('video').load();
<%// background %>
<%//$("#selectMethod").css("background-image", "url(p5250104.jpg)");
//$("#selectMethod").css("opacity", 0.5);%>
<%// js functions %>
<%// video再生 %>
function playMovie(){
	var vc=$("#video-container");
	vc.css("height", window.height+"px")
		.css("top", "0px");
	vc.fadeIn();
	var v=document.getElementById('video');
	v.loop=true;
	v.play();
	//v.webkitRequestFullScreen();
	popToRoot(false);
}
function stopMovie(){
	var v=document.getElementById('video');
	v.pause();
	v.currentTime=0;
	$("#video-container").hide();
}
<%// ホームをポップする %>
function popToRoot(needShow){
	if(stack.length==1) return;
	from=stack.pop();
	to=stack.slice(0)[0];
	stack=new Array();
	stack.push(to);
	if(needShow){
		to.css("left", -1*document.width+"px");
		from.animate({
			left:document.width+"px"
		},function(){
			from.hide();
		});
		to.show();
		to.animate({
			left:"0px"
		});
	} else {
		to.css("left", "0px");
		to.show();
		from.css("left", document.width+"px");
		from.hide();
	}
}
<%// ページをスタックからポップする %>
function popPage(){
	from=stack.pop();
	to=stack.slice(stack.length-1)[0];
	to.css("left", -1*document.width+"px");
	from.animate({
		left:document.width+"px"
	},function(){
		from.hide();
	});
	to.show();
	to.animate({
		left:"0px"
	});
}

<%// ページをスタックにプッシュする %>
function pushPage(to){
	from=stack.slice(stack.length-1)[0];
	stack.push(to);
	to.css("left", document.width+"px");
	from.animate({
		left:-1*document.width+"px"
	},function(){
		from.hide();
	});
	to.show();
	to.animate({
		left:"0px"
	});
}

<%// ページをスタックにプッシュする ページ半分 %>
function pushHalf(one){
	right=hstack.slice(hstack.length-1)[0];
	left=(hstack.length>1)? hstack.slice(hstack.length-2)[0]:null;
	hstack.push(one);
	if(left!=null){
		<%// left -> hide %>
		left.animate({
			left:-1*400+"px"
		},function(){
			left.hide();
		});
	}
	<%// right -> left %>
	right.css("float","left");
	right.animate({
	});
	<%// new one -> right %>
	one.css("left", document.width+"px");
	one.css("top", one.css("top")*-1+"px");
	one.show();
	one.css("float","left");
	one.animate({
		float:left
	});
}
<%// full name生成 %>
function fullname(emp){
	return emp.sname+" "+emp.fname+" ("+emp.kanaSname+" "+emp.kanaFname+")";
}
<%// スタッフリストを表示 %>
function showEmpList(dept, section, subsection){
	$.ajax({
		url: "api?getEmpList="+subsection+"&dept="+dept+"&sect="+section,
		dataType:'json',
		success: function(d){
			$("#EmpList").empty();
			for(x in d){
				var p=d[x];
				$("<li><div onclick='showEmp("+JSON.stringify(p)+")'>"+fullname(p)+"</div></li>").appendTo($("#EmpList"));
			}
			pushPage($("#pageOrganization4"));
		}
	});
}
<%// subsection（係）とsectionスタッフのリストを表示 %> 
function showSubsection(dept, section){
	$.ajax({
		url: "api?getSubsections="+section+"&dept="+dept,
		dataType:'json',
		success: function(d){
			$("#Subsections").empty();
			for(x in d){
				var p=d[x];
				if(typeof(p)=="object"){
					<%// オブジェクトならsectionスタッフリスト %>
					var next=$("#Subsections").children("li:first");
					if(next.length==0){
						for(y in p){
							$("<li><div onclick='showEmp("+JSON.stringify(p[y])+")'>"+fullname(p[y])+"</div></li>").appendTo($("#Subsections"));
						}
					} else {
						for(y in p){
							$("<li><div onclick='showEmp("+JSON.stringify(p[y])+")'>"+fullname(p[y])+"</div></li>").insertBefore(next);
						}
					}
				} else {
					$("<li><div onclick='showEmpList(\""+dept+"\", \""+section+"\", "+JSON.stringify(p)+")'>"+p+"</div></li>").appendTo($("#Subsections"));
				}
			}
			pushPage($("#pageOrganization3"));
		}
	});
}
<%// section （課）とdepartmentスタッフのリストを表示 %>
function showSection(dept){
	$.ajax({
		url: "api?getSections="+dept,
		dataType:'json',
		success: function(d){
			$("#Sections").empty();
			for(x in d){
				var p=d[x];
				if(typeof(p)=="object"){
					<%// オブジェクトならdepartmentスタッフリスト %>
					var next=$("#Sections").children("li:first");
					if(next.length==0){
						for(y in p){
							$("<li><div onclick='showEmp("+JSON.stringify(p[y])+")'>"+fullname(p[y])+"</div></li>").appendTo($("#Sections"));
						}
					} else {
						for(y in p){
							$("<li><div onclick='showEmp("+JSON.stringify(p[y])+")'>"+fullname(p[y])+"</div></li>").insertBefore(next);
						}
					}
				} else {
					$("<li><div onclick='showSubsection(\""+dept+"\", "+JSON.stringify(p)+")'>"+p+"</div></li>").appendTo($("#Sections"));
				}
			}
			hstack.push($('>#Detartments').parent());
			pushHalf($('>#Sections').parent());
//			console.log($('#Sections').parent().attr();
//			pushPage($("#pageOrganization2"));
		}
	});
}
<%// スタッフ詳細 %>
function showEmp(obj){
	$("#nameHolder").text(obj.sname+" "+obj.fname);
	$("#kaneNameHolder").text(obj.kanaSname+" "+obj.kanaFname);
	$("#deptHolder").text(obj.department+" "+obj.section+" "+obj.subsection);
	$("#phoneHolder").text(obj.birthday);
	$("#photoHolder").attr("src", obj.image);
	pushPage($("#pageInitial3"));
}
<%// 五十音によるスタッフリストの表示 %>
function showInitialList(pattern){
	$.ajax({
		url: "api?getEmpByInitial="+pattern,
		dataType:'json',
		success: function(d){
			$("#empNameLines").empty();
			for(x in d){
				var p=d[x];
				$("<li><div class='empNameCell' onclick='showEmp("+JSON.stringify(p)+")'>"+fullname(p)+"</div>").appendTo($("#empNameLines"));
			}
			pushPage($("#pageInitial2"));
		}
	});
}
function showOrganization(){
	var w=$('#Departments').css("width");
	console.log(w);
	$('#Departments').parent()
		.css("left", (document.width-w)/2);
	pushPage($("#pageOrganization"));
}

<%// ページの初期化 不要？ %>
$("#pageInitial2")
	.css("left",document.width+"px")
	.css("width",document.width+"px")
	.css("top","0px");
$("#pageInitial3")
	.css("left",document.width+"px")
	.css("width",document.width+"px")
	.css("top","0px");
</script>
</body>
</html>
