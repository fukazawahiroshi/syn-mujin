<%@page contentType="text/html, charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<form name="addform">
<div style="margin-top:30;margin-bottom:30"><span style="margin-left:50px"><u>ID(email):</u><input type="text" name="emailfield" size=50>
<input type="button" onclick="submitAddForm()" value="追加"></div>
</form>

<%// xtable object %>
<div id="users"></div>

<%// create object config %>
<script type="text/javascript" src="js/jquery.xtable-1.1.js"></script>
<script>
function deleteUser(id){
	$.ajax({
		type:"post",
		url: "api",
		data:"deleteUser="+id,
		success: function(d){
			$('#users').xTable('reload');
		}
	});
}
function submitAddForm(){
	$(document.addform).ajaxSubmit({
		url: "api?adduser="+document.addform.emailfield.value,
	    success: function(data){
	        if(!data) alert('è¿½å ã§ãã¾ããã§ããã');
	        else $('#users').xTable('reload');
	    },
	});
}
var config = {
	height:400,
	title:'Users',
	toolbar:{align:'right',
		buttons:[{text:'Reload',
			icon:'refresh',
		 	script:function(e,id){
				$(e).click(function(){
					$('#'+id).xTable('reload');
				});
			}
			}]},
	columns:[/*{header:'No',width:30,align:'left',data:function(obj, id, tr){
				var b=$('<span></span>');
				b.html($("#x-table-pagination-page-index-users").val()-0+tr[0].sectionRowIndex);
				return b;
			}
		},*/
		{header:'ユーザーID',width:300,name:'email',order:false},
		{header:'組織',width:120,name:'organization',order:false,align:'center'},
		{header:'設定拠点',width:120,name:'location',order:false,align:'center'},
		{header:'名前',width:120,name:'name',order:false,align:'center'},
		{header:'ドメイン',width:120,name:'domain',order:false,align:'center'},
		{header:'削除',width:50,align:'center',
			data:function(obj,id,tr){
				if(obj.email!="${adminEmail}"){
					var b = $('<input type=button value="削除"></input>');
					b
					.html('削除')
					.click(function(){
						deleteUser(obj.email);
					});
					return b;
				}
			}
		}],
	url:'xtable?table=user',
	type:'json',
	pakages:['remote'],
	pagination:{
		message:"Displaying users %s - %s of %s",
		record_per_page:20
	},
	loading_message:'Loading...',
	order:{column:'num',type:'ASC'}
};
$('#users').xTable(config);
</script>
