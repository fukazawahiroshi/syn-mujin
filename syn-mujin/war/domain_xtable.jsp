<%@page contentType="text/html, charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

ドメイン登録画面
<form name="addform">
<div style="margin-top:30;margin-bottom:30"><span style="margin-left:50px">
<u>ID(email):</u><input type="text" name="emailfield" size=50>
<u>password:</u><input type="text" name="password" size=50>
<input type="button" onclick="submitAddForm()" value="追加"></div>
</form>

<div id="domains"></div>

<script type="text/javascript" src="js/jquery.xtable-1.1.js"></script>

<script>
function deleteDomain(id){
	$.ajax({
		type:"post",
		url: "api",
		data:"deleteDomain="+id,
		success: function(d){
			$('#domains').xTable('reload');
		}
	});
}
function submitAddForm(){
	alert('処理が完了すると、一覧にドメインIDが追加されます。少々お待ちください。');
	$(document.addform).ajaxSubmit({
		url: "api",
	    success: function(data){
	        if(!data) alert('è¿½å ã§ãã¾ããã§ããã');
	        else $('#domains').xTable('reload');
	    },
	});
}
<%// create object config %>
var config = {
	height:400,
	title:'Domains',
	toolbar:{align:'right',
		buttons:[{text:'Reload',
			icon:'refresh',
		 	script:function(e,id){
				$(e).click(function(){
					$('#'+id).xTable('reload');
				});
			}
			}]},
	columns:[{header:'ドメイン管理者アドレス',width:420,name:'email',order:false},
		{header:'password',width:120,name:'password',order:false},
				{header:'削除',width:50,align:'center',
				data:function(obj,id,tr){
					var b = $('<input type=button value="削除"></input>');
					b
					.html('削除')
					.click(function(){
						deleteDomain(obj.email);
					});
					return b;
				}
		}],
	url:'xtable?table=domain',
	type:'json',
	pakages:['remote'],
	pagination:{
		message:"Displaying employee %s - %s of %s",
		record_per_page:20
	},
	loading_message:'Loading...',
	order:{column:'domain',type:'ASC'}
};
$('#domains').xTable(config);
</script>
