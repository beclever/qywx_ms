/*$(function() {
	pull("down");//初始化加载数据
});*/
//上下拉事件
function pull(type) {
	if (type == "up") {
		var pageNum = $('#pageNum').val();
		//layer.msg(pageNum);
		var materialName = $('#materialName').val();
		//getPageData(pageNum);
		querySpare(pageNum,materialName);
	} else if (type == "down") {
		var pageNum = 1;//页码置于1
		$('#pageNum').val(1);
		var pageNum = $('#pageNum').val();
		var materialName = $('#materialName').val();
		//layer.msg(pageNum);
		$('#spareList').empty();
		//getPageData(pageNum);
		querySpare(pageNum,materialName);
	}
}


function querySpare(){
	var pageNum = $('#pageNum').val();
	var materialName = $('#materialName').val();
	//var searchText = $.trim($("materialName").value) ;
	if(materialName==""){
		layer.msg("请输入查询条件");
		return;
	}
	var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
	$.ajax( {  
        type : "post",  
        url : basePath+"/cp/sparepart/pageSparelistByParam.do",
        data:{pageNum:pageNum,materialName:materialName},
		dataType: 'json',
		cache:false,
		success : function(dataResult) {  
			if(dataResult.length==0){
				if($('#pageNum').val()==1)
					layer.msg("暂无数据");
				else
					layer.msg("暂无更多数据");
			}
			layer.close(index);
			var html = "" ;
			$('#pageNum').val(pageNum/1+1);
			$(dataResult).each(function(i,data){
				if(null!=data) {//detailType=1 工程师工单详情
					
					 var url = basePath+"/cp/sparepart/sparepartDetail.do?materialCode="+data.materialCode+"&hasSerialNumber="+data.hasSerialNumber+"&storeId="+data.storeId;
					 html+="<div class='new_title02'>"+
		             "<div class='new_title02_arrow'>&nbsp;</div>"+
		              "<a href='"+url+"'>"+
		                  "<samp >" +data.materialName+"</samp>"+
		                  "<span>物料编号："+data.materialCode+"</span>"+
		                  "<span>库存数量："+data.stockNum+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓位：好件位</span>"+
		             "</a>"+
		              "<div class='clear'></div>"+
		          "</div>";
					
				}
 			}); 
		//	document.getElementById("spareList").innerHTML = html ;
			$('#spareList').empty();
			$('#spareList').append(html);
    	},
		error:function(data){
			layer.close(index);
			layer.msg("查询出错");
		}
    });
}

function cleanMaterialName(){
	$('#materialName').val("");
	document.getElementById("spanMaterialName").style.display = 'none';
}


/**
 * 控制删除按钮显示/消失
 */
function getDeleteBnt(){
	var display = document.getElementById("spanMaterialName").style.display;
    var materialName = $('#materialName').val();
    if(materialName=='' || materialName==null){
    	document.getElementById("spanMaterialName").style.display = 'none';
    }else{
    	document.getElementById("spanMaterialName").style.display = 'inline';
    }
}
