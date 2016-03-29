/**
 * 工单的附件处理
 */

var latitude = null;
var longitude = null;

//判断是否微信浏览器
function is_weixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}
//第一次单击的时间（用于iscroll.js引起的点击一次触发两次问题）
var t1 = null;
function myclick(){
	if (t1 == null){
		t1 = new Date().getTime();
	}else{		
		var t2 = new Date().getTime();
		if(t2 - t1 < 500){
			t1 = t2;
			return;
		}else{
			t1 = t2;
		}
	}
	/*自己的代码*/
}

//附件添加
function addImgs(){
	if(!is_weixin())
	{
		alert("请使用微信浏览器！");
		return;
	}
	if (t1 == null){
		t1 = new Date().getTime();
	}else{		
		var t2 = new Date().getTime();
		if(t2 - t1 < 1000){
			t1 = t2;
			return;
		}else{
			t1 = t2;
		}
	}
	chooseImage();
}
//微信返回图片对象
var images = { localId: [], serverId: [] };
var imageList = [];

//用于统计图片个数
var countImg = 0;

//拍照或从手机相册中选图接口
function chooseImage(){
	wx.chooseImage({
		sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有'original', 
	    sourceType: ['album'], // 可以指定来源是相册还是相机，默认二者都有, 'camera'
	    success: function (res) {
	    	console.log("选择图片/拍照");
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        images.localId = localIds;
	        //上传图片
	        uploadImage();
	        
	    }
	});
}

/**
 * 功能：提供给外部接口，生成最终的html代码
 * 参数：imgList：附件列表；isShowDelete：是否显示删除按钮 (默认显示)
 */
var funDealtPreviewHtml = function(imgList,isShowDelete) {
	var html = '';
	if(isShowDelete === null || isShowDelete === "" || isShowDelete === undefined){
		isShowDelete = true;
	}
	$.each(imgList,function(i,n){
		imageList.push(n.path);
	});
	if(imageList.length > 0){
		$('.upload_box').show();
	}else{
		$('.upload_box').hide();
	}
	if(isShowDelete == true){
		$.each(imgList,function(i,n){
			countImg += 1;
			//var html = "";
			html += '<div id="uploadList_'+ n.imgId +'" class="upload_append_list">';
			html += '	<div class="file_bar">';
			html += '		<div style="padding:5px;">';
			//html += '			<p class="file_name">' + countImg + '</p>';
			html += '           <span class="file_del" data-index="'+n.imgId+'" imgPath="'+n.path+'" title="删除"></span>';   // 删除按钮的html
			html += '		</div>';
			html += '	</div><br/>';
			html += '		<div class="imgBox" style="width:'+90+'px;height:'+90+'px">';				
			html += '			<img id="uploadImage_'+n.imgId+'" class="upload_image" src="' + n.path + '" style="width:'+80+'px;height:'+70+'px" />';                                                                 
			html += '		</div>';
			html += '</div>';
	    });
	}else{
		$.each(imgList,function(i,n){
			countImg += 1;
			//var html = "";
			html += '<div id="uploadList_'+ n.imgId +'" class="upload_append_list">';
			html += '		<div class="imgBox" style="width:'+90+'px;height:'+90+'px">';				
			html += '			<img id="uploadImage_'+n.imgId+'" class="upload_image" src="' + n.path + '" style="width:'+80+'px;height:'+70+'px" />';                                                                 
			html += '		</div>';
			html += '</div>';
	    });
	}
	funAppendPreviewHtml(html,isShowDelete);
	prviewImg();
};

/**
 * 功能：处理生成的html代码
 * 参数：html：需要组装的html；isShowDelete：是否显示删除按钮 (默认显示)
 */
var funAppendPreviewHtml = function(html,isShowDelete){
	$(".add_upload").before(html);
	if(isShowDelete == true){
		// 绑定删除按钮
		funBindDelEvent();
	}
	$(".upload_append_list").find(".file_bar").addClass("file_hover");
};

//绑定删除按钮事件
var funBindDelEvent = function(){
	if($(".file_del").length>0){
		// 删除方法
		$(".file_del").click(function() {
			var imgId = $(this).attr("data-index");
			var imgPath = $(this).attr("imgPath");
			
			//询问框
			layer.confirm('确定要删除图片吗？', {
			    btn: ['确定','取消'], //按钮
			    shade: [0.6,'#666'] //显示遮罩
			}, function(){
				layer.msg('正在删除...', {shift: 5});
				deleteAttach(imgId,imgPath);
				return false;
			}, function(){
			    //layer.msg('取消么么哒', {shift: 6});
			});
		});
	}
};

//删除图片
var deleteAttach = function(imgId,imgPath){
	//正在加载效果层
    var index = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/deleteImage.do",
		data : {
			id : imgId, poNumber: $("#poNumber").val()
		},
		cache : false,
		success : function(result) {
			layer.close(index);
			//提示层
			layer.msg('删除成功！');
			$("#uploadList_" + imgId).fadeOut();
			countImg -= 1;
			$.each(imageList, function(i,n){
				if(n === imgPath){
					imageList.splice(i,1);
				}
			});
			if(imageList.length > 0){
				$('.upload_box').show();
			}else{
				$('.upload_box').hide();
			}
			prviewImg();
		},
		error : function(state) {
			layer.close(index);
			layer.msg('删除失败！');
		}
	});
}

//预览图片接口
function prviewImg(){
	if(!imageList || imageList.length == 0){
	return;
	}
	$(".upload_image").click(function(){
		if (t1 == null){
			t1 = new Date().getTime();
		}else{		
			var t2 = new Date().getTime();
			if(t2 - t1 < 500){
				t1 = t2;
				return;
			}else{
				t1 = t2;
			}
		}
		wx.previewImage({
		    current: this.src, // 当前显示图片的http链接
		    urls: imageList // 需要预览的图片http链接列表
		});
	});
	
}

//上传图片接口
function uploadImage(){
	if (images.localId.length == 0) {
      alert('请先选择图片');
      return;
    }
	//正在加载效果层
    var index = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
    var i = 0, length = images.localId.length;
    images.serverId = [];
    function upload() {
      wx.uploadImage({
        localId: images.localId[i],
        isShowProgressTips: 1, // 默认为1，显示进度提示
        success: function (res) {
          i++;
          images.serverId.push(res.serverId);
          if (i < length) {
            upload();
          }else{
        	  submitImg();
          }
        },
        fail: function (res) {
          alert("第"+(i+1)+"张图片上传失败，请检查！");
          layer.close(index);
        }
      });
    }
    upload();
}
function getLocation() {
	wx.getLocation({
		type : 'wgs84',
		success : function(res) {
			latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		}
	});
}

//提交添加图片
function submitImg(){
	if (t1 == null){
		t1 = new Date().getTime();
	}else{		
		var t2 = new Date().getTime();
		if(t2 - t1 < 1000){
			t1 = t2;
			return;
		}else{
			t1 = t2;
		}
	}
	var serverIdStr = images.serverId.join(',');
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/upload.do",
		dataType: 'json',
		data : {
			mediaId : serverIdStr, poNumber: $("#poNumber").val(),
			poNumber: $("#poNumber").val(),
			equipmentId: equipmentId,
			op:"save",
			workformId: $("#workformId").val(),
			latitude: latitude,
			longitude: longitude
		},
		success: function(imgs) {
	        //组织预览图片
	        funDealtPreviewHtml(imgs);
	        layer.closeAll('loading');
	        layer.msg('上传成功！');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("上传失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
		}
	});
}
//提交添加图片
function testsubmitImg(){
	var serverIdStr = "ORhKc_FayBijpXd5zjyIqTvdBWX2VuvJTzjP1qgZIyTE1h267S4gBCzJZ24lYBJk";
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/upload.do",
		dataType: 'json',
		data : {
			mediaId : serverIdStr, poNumber: $("#poNumber").val(),
			poNumber: $("#poNumber").val(),
			equipmentId: equipmentId,
			op:"save",
			workformId: $("#workformId").val(),
			latitude: latitude,
			longitude: longitude
		},
		success: function(imgs) {
	        layer.closeAll('loading');
	        layer.msg('上传成功！');
	        //组织预览图片
	        funDealtPreviewHtml(imgs);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("上传失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
		}
	});
}