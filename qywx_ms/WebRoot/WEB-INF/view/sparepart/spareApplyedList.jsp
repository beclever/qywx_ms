<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已借备件</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />


<style>
.subject font {
	color: #333;
}

#wrapper {
	position:absolute; z-index:1;
	top:0em; bottom:2px; 
	width:100%;
	background:#aaa;
	overflow:auto;
}

.addspare{
    color: #fff;
	border:0px;
	padding:0.4em 0.4em;
	text-align:center;
	font-size:1em;
	background:#3694E1;
	-moz-border-radius:3px;
    -webkit-border-radius:3px;
    border-radius:3px;
    float:right;
    width:40px;
}
#wrapper{background:none;}
</style>
	
	
<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>


<script type="text/javascript">
	
	function pull(type) {
	}

	
	function initList(){
		var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
		$.ajax( {  
	        type : "post",  
	        url : "${ctx_path }/cp/sparepart/applyedList.do",
			//data:{pageNum:pageNum},
			dataType: 'json',
			cache:false,
			success : function(dataResult) {  
				//if(dataResult.status=="0"){
					layer.close(index);
					var html = "" ;
					$(dataResult.data.wsBorrowDetailInfoBeans).each(function(i,data){
						if(null!=data) {
							/* html+="<div class='new_title02'>"+
				             "<div class='new_title02_arrow'>&nbsp;</div>"+
				              "<a onclick=\"onItemClick(\'"+data.borrowId+"\',\'"+data.materialCode+"\',\'"+data.materialName+"\',\'"+data.sparepartNum+"\',\'"+data.storeId+"\',\'"+data.storeName+"\',\'"+data.borrowName+"\',\'"+data.serialNumber+"\',\'"+data.hasSerial+"\',\'"+data.status+"\',\'"+data.borrowTime+"\')\">"+
				                  "<samp>" +data.materialName+"</samp>"+
				                  "<span>数量："+data.sparepartNum+"<br/>"+
				                  "编号-"+data.materialCode+"<br/>"+
				                  "借用时间："+data.borrowTime + "</span>"+
				             "</a>"+
				              "<div class='clear'></div>"+
				          "</div>"; */
				          html += "<div class=\"gdlb_list\">"+
							"<h3 class=\"font_black\">"+formatData(data.materialName)+"</h3>"+
							"<span class=\"gdlb_info\"> 数量："+data.sparepartNum+"<br />"+
								"编号："+formatData(data.materialCode)+"<br /> 借用时间："+formatData(data.borrowTime)+"<br />"+
							"</span> <a onclick=\"onItemClick(\'"+data.borrowId+"\',\'"+data.materialCode+"\',\'"+data.materialName+"\',\'"+data.sparepartNum+"\',\'"+data.storeId+"\',\'"+data.storeName+"\',\'"+data.borrowName+"\',\'"+data.serialNumber+"\',\'"+data.hasSerial+"\',\'"+data.status+"\',\'"+data.borrowTime+"\')\"><span class=\"query_arrow\"></span></a>"+
						"</div>";
							
						}
		 			}); 
					document.getElementById("applyedList").innerHTML = html ;
					document.getElementById("person_desc_id").innerHTML = "借用人："+dataResult.data.userName+"<br />";
					document.getElementById("department_desc_id").innerHTML = "借用部门："+dataResult.data.borrowDep+"<br />";
					document.getElementById("count_desc_id").innerHTML = "已借备件：共"+dataResult.data.total+"条<br />";
				//}else{
				//	alert(dataResult.errMsg);
			//	}
				
	    	},
			error:function(data){
				layer.close(index);
			}
	    });
	}
	
	$(function(){
		initList();
	});
	
	//格式化数据：当参数为null，undefined时，返回空字符串
	var formatData = function(p){
		return !p? "":p;
	}
	
	function onItemClick(borrowId,materialCode,materialName,sparepartNum,storeId,storeName,borrowName,serialNumber,hasSerial,status,borrowTime){
		
		var data = {} ;
		data.borrowId=borrowId;
		data.materialCode=materialCode;
		data.materialName=materialName;
		data.sparepartNum=sparepartNum;
		data.storeId=storeId;
		
		data.storeName=storeName;
		data.borrowName=borrowName;
		data.serialNumber=serialNumber;
		
		data.hasSerial=hasSerial;
		data.status=status;
		
		data.borrowTime=borrowTime;
		var params = JSON.stringify(data);
		
		//alert(params);
		
		var str = encodeURI($.trim(params)) ;
		window.location.href="${ctx_path}/cp/sparepart/applyedSpareDetail.do?data="+str ;

	}
	
</script>
</head>
<body style="background: #ffffff;">
	<div class="samian" style="background:#F6F6F6;">
    
		<div class="spare_replace_user">
			<span  id="person_desc_id">借用人：<br/></span>
			<span  id="department_desc_id">借用部门：<br />
			</span> <span  id="count_desc_id">已借备件：共0条<br /></span>
			<div class="clear"></div><div class="clear"></div>
		</div>
	</div>
	<div class="wrapper" id="wrapper" style="top:7.5em;">
		<div id="scroller">
			<div id="applyedList"></div>
		</div>
	</div>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
</body>
</html>