window.onpopstate = function() {
	var url = basePath + "/cp/equipment/detail.do?serialNumber=" + serialNumber
			+ "&dateTime=" + new Date().getTime();
	window.history.replaceState("", "", url);
};

function backDetail() {
	location.href = basePath + "/cp/equipment/detail.do?serialNumber="
			+ serialNumber + "&dateTime=" + new Date().getTime();
}

// 第一次单击的时间（用于iscroll.js引起的点击一次触发两次问题）
var t1 = null;
function myclick() {
	if (t1 == null) {
		t1 = new Date().getTime();
	} else {
		var t2 = new Date().getTime();
		if (t2 - t1 < 1000) {
			t1 = t2;
			return;
		} else {
			t1 = t2;
		}
	}
	/* 自己的代码 */
}

// 判断是否微信浏览器
function is_weixin() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

// 附件添加
function addImgs() {
	if (!is_weixin()) {
		layer.open({
			content : "请使用微信浏览器！",
			title : '温馨提示',
			closeBtn : false,
			yes : function(index) {
				layer.close(index); // 一般设定yes回调，必须进行手工关闭
				backDetail();
			}
		});
	} else {
		myclick();
		chooseImage();
	}
}

// 微信返回图片对象
var images = {
	localId : [],
	serverId : []
};

function getLocation() {
	wx.getLocation({
		type : 'wgs84',
		success : function(res) {
			latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		}
	});
}

// 拍照或从手机相册中选图接口
function chooseImage() {
	wx.chooseImage({
		count: 5, // 默认9
	    sizeType: ['compressed'],
	    sourceType: ['album'], 
		success : function(res) {
			console.log("选择图片/拍照");
			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			images.localId = localIds;
			// 上传图片
			uploadImage();

		}
	});
}

/**
 * 功能：提供给外部接口，生成最终的html代码 参数：imgList：附件列表；
 */
function funDealtPreviewHtml(id, urls) {
	var html = '';
	var ids = id.split(",");
	$.each(ids, function(i, id) {
		html += '<div id="uploadList_' + id + '" class="upload_append_list">';
		html += '	<div class="file_bar file_hover">';
		html += '		<div style="padding:5px;">';
		html += '           <span class="file_del" onclick="deleteImage(' + id
				+ ');" title="删除"></span>';
		html += '		</div>';
		html += '	</div>';
		html += '		<div class="imgBox" style="width:90px;height:80px">';
		html += '			<img id="uploadImage_' + id
				+ '" class="upload_image" onclick="prviewImg($(this));" src="'
				+ rootUrl + urls[i] + '" style="width:80px;height:80px" />';
		html += '		</div>';
		html += '</div>';
		imageList.push(rootUrl + urls[i]);
	});
	$(".add_upload").append(html);
}

// 绑定删除按钮事件
var deleteImage = function(id) {
	// 询问框
	layer.confirm('确定要删除图片吗？', {
		btn : [ '确定', '取消' ], // 按钮
		shade : [ 0.6, '#666' ]
	// 显示遮罩
	}, function() {
		layer.msg('正在删除...', {
			shift : 5
		});
		deleteAttach(id);
		return false;
	}, function() {
	});
};

// 删除图片
var deleteAttach = function(id) {
	// 正在加载效果层
	var index = layer.load(2, {
		shade : [ 0.6, '#666' ]
	});// 0.6透明度的灰色背景
	$.ajax({
		type : "post",
		url : basePath + "/cp/equipment/deleteImage.do",
		data : {
			id : id,
		},
		cache : false,
		success : function(result) {
			layer.close(index);
			if ("true" == result.returnResult) {
				// 提示层
				layer.msg('删除成功！');
				$("#uploadList_" + id).fadeOut();
				var imagePath = rootUrl + result.imageUrl;
				$.each(imageList, function(i, n) {
					if (n === imagePath) {
						imageList.splice(i, 1);
					}
				});
			} else {
				layer.msg(result.msg);
			}
		},
		error : function(state) {
			layer.close(index);
			layer.msg('删除失败！');
		}
	});
};

// 预览图片接口
function prviewImg(the) {
	if (!imageList || imageList.length == 0) {
		return;
	}
	if (t1 == null) {
		t1 = new Date().getTime();
	} else {
		var t2 = new Date().getTime();
		if (t2 - t1 < 500) {
			t1 = t2;
			return;
		} else {
			t1 = t2;
		}
	}
	wx.previewImage({
		current : the.attr("src"), // 当前显示图片的http链接
		urls : imageList
	// 需要预览的图片http链接列表
	});

}

// 上传图片接口
var uploadImageIndex;
function uploadImage() {
	if (images.localId.length == 0) {
		layer.msg('请先选择图片');
		return;
	}
	// 正在加载效果层
	uploadImageIndex = layer.load(2, {
		shade : [ 0.6, '#666' ]
	});// 0.6透明度的灰色背景
	var i = 0, length = images.localId.length;
	images.serverId = [];
	function upload() {
		wx.uploadImage({
			localId : images.localId[i],
			isShowProgressTips : 1, // 默认为1，显示进度提示
			success : function(res) {
				i++;
				images.serverId.push(res.serverId);
				if (i < length) {
					upload();
				} else {
					submitImg();
				}
			},
			fail : function(res) {
				layer.msg("第" + (i + 1) + "张图片上传失败，请检查！");
				layer.close(uploadImageIndex);
			}
		});
	}
	upload();
}

// 提交添加图片
function submitImg() {
	var serverIdStr = images.serverId.join(',');
	$.ajax({
		type : "post",
		url : basePath + "/cp/equipment/upload.do",
		data : {
			op : "save",
			equipmentId : equipmentId,
			latitude : latitude,
			longitude : longitude,
			mediaId : serverIdStr
		},
		cache : false,
		success : function(data) {
			// 组织预览图片
			if ("true" == data.returnResult) {
				funDealtPreviewHtml(data.ids, data.imagUrl);
			}
			layer.close(uploadImageIndex);
			layer.msg(data.msg);
		},
		error : function(state) {
			layer.close(uploadImageIndex);
			layer.msg('上传失败！');
		}
	});
}
