function querySpare() {

	var materialName = $('#materialName').val();
	if(materialName.trim()==""){
		layer.msg('请输入查询内容！');
		return ;
	}
	var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
	$
			.ajax({
				type : "post",
				url : basePath+"/cp/sparepart/pageSparelistByParam.do",
				data:{materialName:materialName},
				dataType : 'json',
				cache : false,
				success : function(dataResult) {
					var html = "";
					layer.close(index);
					$(dataResult)
							.each(
									function(i, data) {
										if (null != data) {//detailType=1 工程师工单详情
											var url = basePath+"/cp/sparepart/applyingSpareDetail.do?materialCode="
													+ data.materialCode
													+ "&hasSerialNumber="
													+ data.hasSerialNumber
													+ "&materialName="
													+ data.materialName
													+ "&stockNum="
													+ data.stockNum
													+ "&storeName="
													+ data.storeName
													+ "&storeId="
													+ data.storeId;
											
											var countInputId = "countId" +i;
											
											html += "<div class='new_title02'>"
													+ "<a>"
													+ "<samp >"
													+ data.materialName
													+ "</samp>"
													+"<div style='width:60%'>"
													+ "<span>库存数量："
													+ data.stockNum
													+ "</span>"
													+ "<div style=''>"
														+ "<span>借用数量："
														+ "<input type='text' id='"+countInputId+"' style='width:30px' />"  
														+ "<div class='addspare'  onclick='applySpare(\""+data.storeId+"\",\""+ data.materialCode+"\", \""+ data.stockNum+"\", \""+countInputId+"\""+")'>添加</div>"
														+ "</div>"
													+ "</div>"
													+ "</a>"
													+ "<div class='clear'></div>"
													+ "</div>";

										}
									});
					$('#searchDesc').html("搜索结果：共"+dataResult.length+"条");
					$('#thelist').empty(html);
					$('#thelist').html(html);
					
				},
				error : function(data) {
					layer.close(index);
					layer.msg('系统繁忙，请重试！');
				}
			});
}
// storeId,materialCode

function applySpare(storeId,materialCode,stockNum,countInputId){

	var borrowNum = document.getElementById(countInputId).value ;
	if (null == borrowNum || "" == borrowNum) {
		layer.msg("请填写借用数量！");
		return false;
	}
	if (parseInt(borrowNum) > parseInt(stockNum)) {
		layer.msg("借用数量大于库存！");
		return false;
	}

	//验证是否是数字
	re = /^\d+\.?\d*$/
	var str = borrowNum;
	if (!re.test(str)) {
		len = str.length;
		str1 = str.substr(0, len - 1);
		layer.msg("只能输入数字,请重新输入");
		obj.value = "";
		obj.focus();
		return false;
	}
	var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
	$.ajax({
		type : "post",
		url : basePath+"/cp/sparepart/spareApply.do",
		dataType : "text",
		data : {
			borrownum : borrowNum,
			storeId : storeId,
			materialCode : materialCode,
		},
		success : function(data) {
			layer.close(index);
			if(data.indexOf("true")>=0){
				layer.msg("申请成功！");
				window.location.href=basePath+"/cp/ouath/sparepart/applyingSpareList.do";
			}else{
				layer.alert("申请失败！"+data);
			}
			//alert(data);
		},
		error : function(data) {
			//alert(xhr + type + exception);
			layer.close(index);
			layer.msg(data);
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

