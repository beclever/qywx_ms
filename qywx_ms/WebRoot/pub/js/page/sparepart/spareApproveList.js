$(function() {
	pull("down");//初始化加载数据
});
//上下拉事件
function pull(type) {
	if (type == "up") {
		//layer.msg('上拉');
		var pageNum = $('#pageNum').val();
		listApproveSpare(pageNum);
	} else if (type == "down") {
		//var pageNum = 1;//页码置于1
		$('#pageNum').val(1);
		var pageNum = $('#pageNum').val();
		//layer.msg('下拉');
		$('#approveList').empty();
		listApproveSpare(pageNum) ;
	}
}


function listApproveSpare(pageNum) {
	var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
	$.ajax({
				type : "post",
				url : basePath+"/cp/sparepart/approveSpareList.do",
				data:{pageNum:pageNum},
				dataType : 'json',
				cache : false,
				success : function(dataResult) {
					layer.close(index);
					if(dataResult.status==1){
						if(!dataResult.warbbean){
							if($('#pageNum').val()=='1'){
								layer.msg('暂无数据');
							}
							layer.msg('暂无更多数据');
							return;
						}
						
						$('#pageNum').val(pageNum/1+1);
						var html = "";
						$(dataResult.warbbean).each(
							function(i, data) {
								if (null != data) {//detailType=1 工程师工单详情
									var url = basePath+ "/cp/sparepart/approveSpareDetail.do?applyId=" + data.applyId + "&hasSerialNumber="+data.hasSerialNumber+"&borrowNum="+data.borrowNum ;
									if(data.hasSerialNumber=='Y'){
										url = url +"&serialNumbers="+data.serialNumbers ;						
									}
									html+="<div class='new_title02'>"+
						             "<div class='new_title02_arrow'>&nbsp;</div>"+
						              "<a  href='"+url+"'>"+
						                  "<span>物料编码："+data.materialCode+"</span>"+
						                  "<span>物料名称："+data.materialName+"</span>"+
						                  "<span>物料级别："+data.moduleLevel+"</span>"+
						                  "<span>借用人："+data.borrowerName+"</span>"+
						                  "<span>数量："+data.borrowNum+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓位：好件位</span>"+
						              "</a>"+
						              "<div class='clear'></div>"+
						              "</div>";
									
								}
							});
						$('#approveList').append(html);
						//document.getElementById("approveList").innerHTML = html;
					}else{
						layer.close(index);
						layer.alert("接口出错，请重试！");
					}
					
				},
				error : function(data) {
					layer.alert(data.status);
				}
			});
}
