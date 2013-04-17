<%@page contentType="text/html, charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<%//名前検索 %>
<table>
	<tr>
		<td>姓</td>
		<td><input type='text' style="border:1px solid #CCC;" id="src_sname"/></td>
		<td>名</td>
		<td><input type='text' style="border:1px solid #CCC;" id="src_fname"/></td>
		<td valign="middle"><button onclick="search();">Search</button> </td>
	</tr>
</table>
<%//部署検索 %>
<table>
	<tr>
		<td>部</td>
		<td>
		  <select name="department" onchange="deptChanged(this)" id="deptSelection">
		   <option value="" selected>選択
<c:forEach var="d" items="${departmentList}">
<option value="${f:h(d)}">${f:h(d)}
</c:forEach>
		  </select>
		</td>
		<!-- section -->
		<td style="display:none" id="sectSelectionColumn">課</td>
		<td style="display:none">
		  <select name="section" onchange="sectChanged(this)" id="sectSelection">
		   <!-- insert -->
		  </select>
		</td>
		<!-- sub section -->
		<td style="display:none" id="subsectSelectionColumn">係</td>
		<td style="display:none">
		  <select name="subsection" onchange="subsectChanged(this)" id="subsectSelection">
		   <option value="" selected>選択
		   <!-- insert -->
		  </select>
		</td>
	</tr>
</table>
<%// xtable object %>
<div id="employees"></div>

<%// sctript %>
<script type="text/javascript" src="js/jquery.xtable-1.1.js"></script>
<script>
<%// 表示非表示設定 %>
function setNeedToShowId(show, id){
	$.ajax({
		type:"post",
		url: "api",
		data:"setNeedToShow="+show+"&id="+id,
		success: function(d){
			console.log(d);
		}
	});
}
<%// subsectionセレクタが更新 %>
function subsectChanged(obj){
	var conditions = {
		department:$("#deptSelection").val(),
		section:$("#sectSelection").val(),
		subsection:obj.value
	};
	$('#employees').xTable('conditions',conditions);
	$('#employees').xTable('reload');
}
<%// sectionセレクタが更新 %>
function sectChanged(obj){
	var conditions = {
		department:$("#deptSelection").val(),
		section:obj.value
	};
	$('#employees').xTable('conditions',conditions);
	$('#employees').xTable('reload');
		$("#subsectSelection").empty();
		$("#subsectSelectionColumn").hide();
		$("#subsectSelectionColumn").next().hide();
	if(obj.value==""){
		return;
	}
	$.ajax({
		type:"post",
		url: "api",
		dataType: "json",
		data:"getSubsections="+obj.value+"&dept="+$("#deptSelection").val(),
		success: function(d){
			$("#subsectSelection").empty();
		    $("<option value='' selected>選択</option>").appendTo($("#subsectSelection"));
			for(str in d){
				$("<option value="+d[str]+">"+d[str]+"</option>").appendTo($("#subsectSelection"));
			}
			$("#subsectSelectionColumn").show();
			$("#subsectSelectionColumn").next().show();
		}
	});	
}
<%// departmentセレクタが更新 %>
function deptChanged(obj){
	var conditions = {
		department:obj.value
	};
	$('#employees').xTable('conditions',conditions);
	$('#employees').xTable('reload');
		$("#sectSelection").empty();
		$("#sectSelectionColumn").hide();
		$("#sectSelectionColumn").next().hide();
		$("#subsectSelection").empty();
		$("#subsectSelectionColumn").hide();
		$("#subsectSelectionColumn").next().hide();
	if(obj.value==""){
		return;
	}
	$.ajax({
		type:"post",
		url: "api",
		dataType: "json",
		data:"getSections="+obj.value,
		success: function(d){
			$("#sectSelection").empty();
		    $("<option value='' selected>選択</option>").appendTo($("#sectSelection"));
			for(str in d){
				$("<option value="+d[str]+">"+d[str]+"</option>").appendTo($("#sectSelection"));
			}
			$("#sectSelectionColumn").show();
			$("#sectSelectionColumn").next().show();
		}
	});	
}
<%// create object config %>
var config = {
	height:400, 
	title:'Employees', 
	toolbar:{align:'right',
		buttons:[{text:'Reload',
			icon:'refresh',
		 	script:function(e,id){
				$(e).click(function(){
					$('#'+id).xTable('reload');
				});
			}
			}]},
	columns:[{header:'姓',width:120,name:'sname',order:false},
		{header:'名',width:120,name:'fname',order:false},
		{header:'姓（よみ）',width:120,name:'kanaSname',order:true},
		{header:'名（よみ）',width:120,name:'kanaFname',order:true},
		{header:'部',width:150,name:'department',align:'center',order:false},
		{header:'課',width:150,name:'section',align:'center',order:false},
		{header:'係',width:150,name:'subsection',align:'center',order:false},
		{header:'内線',width:100,name:'birthday',align:'center',order:false},
		{header:'表示',width:50,align:'center',
			data:function(obj,id,tr){
				var b = $('<input type=checkbox'+((obj.needToShow)? ' checked ':'')+'></input>');
				b
				.html('Edit')
				.click(function(){
					setNeedToShowId(this.checked, obj.key.id);
				});
				return b;
			}
		}],
	url:'xtable',
	type:'json',
	pakages:['remote'],
	pagination:{
		message:"Displaying employee %s - %s of %s",
		record_per_page:20
	},
	loading_message:'Loading...',
	order:{column:'kanaSname',type:'ASC'}
};
$('#employees').xTable(config);

<%// 名前search function.%>
function search(){
	if($('#src_sname').val()!="" || $('#src_fname').val()!=""){
		var conditions = {
			sname:$('#src_sname').val(),
			fname:$('#src_fname').val()
		};
		$('#employees').xTable('conditions',conditions);
	}
	$('#employees').xTable('reload');
}
</script>
