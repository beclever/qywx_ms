/**
 * 弹出层：已经提供了弹出的标题，内容和关闭触发事件，确定触发事件
 * 引用与参数说明：需要传入一个对象格式为： 
 *  var box = jQuery.showDialog({ 
 * 		title :"弹出层标题", (可不添加，默认为：温馨提示)
 * 		msg : "弹出层内容", 
 * 		isFullHeight : false,是否全屏显示
 * 		hasDefault : true, 是否显示取消按钮（true显示，false不显示）
 * 		handlerFeforeClose : function(){ 
 * 			alert("取消事件"); 
 * 		}, 
 * 		primary : function(){
 * 			alert("确定事件"); 
 * 			确定方法是不关闭窗口的，如需关闭请添加：(box为弹出层返回对象)
 * 			if(box){
 *				box.data("close")();
 *			}
 * 		} 
 * });
 * 
 */

var _ISIE6 = !-[ 1, ] && !window.XMLHttpRequest;

// 显示或设置滚动条为默认值
jQuery.showScroll = function() {
	jQuery("body").css("overflow", "");
	$("html").eq(0).css("overflow", "");
};
// 隐藏滚动条
jQuery.hideScroll = function() {
	jQuery("body").css("overflow", "hidden");
	$("html").eq(0).css("overflow", "hidden");
};

jQuery.showDialog = function(data) {
	jQuery.hideScroll();// 隐藏滚动条
	var index = 99999;
	var d = {
		title : "温馨提示",
		msg : "",
		isFullHeight : false,
		hasDefault : true,
		duration : 0,
		handlerFeforeClose : null,
		primary : null
	};
	if (jQuery.isPlainObject(data)) {
		d = jQuery.extend(d, data)
		d.duration = _ISIE6 ? 0 : d.duration;
	}
	// 弹窗层
	var boxDiv = $("<div/>");
	// 遮罩层
	var maskDiv = $("<div/>").css({
		position : "fixed",
		zIndex : index,
		width : "100%",
		height : "100%",
		top : 0,
		left : 0,
		background : "rgba(0, 0, 0, 0.6)"
	});

	// 内容整体div
	var dialogDiv = $("<div/>").css({
		position : "fixed",
		zIndex : (index + 1),
		width : "85%",
		top : "50%",
		left : "50%",
		transform : "translate(-50%, -50%)",
		backgroundColor : "#FAFAFC",
		textAlign : "center",
		borderRadius : "6px",
	});
	
	// 标题div
	var titleDiv = $("<div/>").css({
		padding : "1.2em 20px 0.5em"
	}).append(
			"<strong  style='font-weight: 400;font-size: 18px;'>" + d.title
					+ "</strong>");// 弹窗标题

	// 内容div
	var msgDiv = $("<div/>").css({
		textAlign : "left",
		padding : "0 20px",
		fontSize : "14px",
		color : "#888",
		overflow: "auto",
		
	}).html(d.msg);// 自定义弹窗内容
	
	if(d.isFullHeight){
		dialogDiv.css({height : ($(window).height()-50)+"px"});
		msgDiv.css({height : (dialogDiv.height() - 100)+"px"});
	}
	
	// 按钮div
	var btnDiv = $("<div/>").css({
		display : "flex",
		fontSize : "18px",
		height : "42px",
		lineHeight : "42px",
		marginTop : "10px",
		position : "relative",
		borderRadius : "0 0 6px 6px"
	});

	// 关闭
	var close = function(fn) {
		maskDiv.fadeOut(d.duration);
		boxDiv.fadeOut(d.duration, function() {
			boxDiv.remove();
			maskDiv.remove();
			jQuery.showScroll();// 显示滚动条
			if (jQuery.isFunction(fn)) {
				fn();
			}
		});
	};
	boxDiv.data("close",close);
	// 按钮样式
	var btnCss = {
		display : "block",
		color : "#3CC51F",
		textDecoration : "none",
		cursor : "pointer",
		borderRadius : "0 0 6px 6px",
		width : "50%",
		'float' : "left"
	};

	// 取消按钮
	var defaultBtn = $("<a/>").css(btnCss).css("color", "#353535").html("取消")
			.click(function() {
				close(d.handlerFeforeClose);
			});

	// 确定按钮
	var primaryBtn = $("<a/>").css(btnCss).html("确定").click(function() {
		d.primary();
	});

	// 是否添加取消按钮
	if (d.hasDefault) {
		btnDiv.append(defaultBtn);
	}else{
		primaryBtn.css({width : "100%"});
	}
	btnDiv.append(primaryBtn);

	// 按钮悬浮添加背景颜色
	btnDiv.find("a").hover(function() {
		$(this).css("backgroundColor", "#EEEEEE");
	}, function() {
		$(this).css("backgroundColor", "#FAFAFC");
	});

	dialogDiv.append(titleDiv).append(msgDiv).append(btnDiv);
	boxDiv.append(maskDiv).append(dialogDiv);

	jQuery("body").append(boxDiv);
	
	return boxDiv;
};
