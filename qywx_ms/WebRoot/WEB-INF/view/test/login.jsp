<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<style type="text/css">
html {
height: 100%;
}

body {
height: 100%;
padding: 0;
margin: 0;
}

#container {
width: 100%;
height: 320px;
margin-top:100px;
text-align:center;
}
</style>
</head>
<body>
<div id="container">
登录帐号：<input type="text" id="loginName"><br>
手机号码：<input type="text" id="telephone"><br>
<input type="button" onclick="login()" value="验证">
</div>
<script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function login(){
	$.ajax( {    
	    url:'${ctx_path}/cp/docheck.do',// 跳转到 action    
	    data:{    
	             loginName : $('#loginName').val(),    
	             telephone : $('#telephone').val()
	               
	    },    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {    
	        if(data.errcode =="true" ){    
	            alert("验证成功！");    
	        }else{    
	            alert(data.errmsg);    
	        }    
	     },    
	     error : function() {    
	          alert("异常！");    
	     }    
	}); 
}
</script>
</body>
</html>