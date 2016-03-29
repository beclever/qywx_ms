
// function :备件申请
//date:2013-10-14

var ff = 1;
var mc = 1;

// 定义全局函数

WSTogle = function(obj) {

	var parentid = obj.id;
	var mmid = parentid.substr(3);
	$("#mt" + mmid).toggle();
	if (ff == 1) {
		$("#img" + mmid).attr("src", basePath + "/pub/images/arrow2.png");
		ff = 2;
	} else if (ff == 2) {
		$("#img" + mmid).attr("src", basePath + "/pub/images/arrow.png");
		ff = 1;
	}

};

WSChildTogle = function(obj) {
	var parid = obj.id;

	var mmid = parid.substr(2);

	$(obj).next("div").toggle();

	if (mc == 1) {

		$("#" + parid + " #tt" + mmid).attr("src", basePath + "/pub/images/arrow2.png");
		mc = 2;
	} else if (mc == 2) {
		$("#" + parid + " #tt" + mmid).attr("src", basePath + "/pub/images/arrow.png");
		mc = 1;
	}

};

// $(function(){
// $('.folder').click(function(){
// $(this).next('div').toggle();
// });
// })
