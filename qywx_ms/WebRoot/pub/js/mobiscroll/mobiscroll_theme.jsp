<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<script src="${ctx_path }/pub/js/mobiscroll/js/mobiscroll.core.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx_path }/pub/js/mobiscroll/js/mobiscroll.frame.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx_path }/pub/js/mobiscroll/js/mobiscroll.scroller.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx_path }/pub/js/mobiscroll/js/mobiscroll.util.datetime.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx_path }/pub/js/mobiscroll/js/mobiscroll.datetimebase.js"
	type="text/javascript" charset="utf-8"></script>

<link href="${ctx_path }/pub/js/mobiscroll/css/mobiscroll.frame.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx_path }/pub/js/mobiscroll/css/mobiscroll.scroller.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	/**
	 * 日历控件，日期与时间(限制当前时间与当前之后时间)
	 *
	 * the：输入框对象
	 * dateFormat：日期格式(不传为默认：yy-mm-dd)
	 * timeFormat：时间格式(不传为默认：HH:ii:ss)
	 */
	function showDateTime(the, dateFormat, timeFormat) {
		$("#cont_datetime").show();
		the.mobiscroll().datetime({
			mode : 'scroller',
			display : 'bottom',
			lang : 'zh',
			minDate : new Date(),
			dateFormat : (dateFormat ? dateFormat : "yy-mm-dd"),
			timeFormat : (timeFormat ? timeFormat : "HH:ii:ss")
		});
	}

	/**
	 * 日历控件，日期与时间（可选所有时间）
	 *
	 * the：输入框对象
	 * dateFormat：日期格式(不传为默认：yy-mm-dd)
	 * timeFormat：时间格式(不传为默认：HH:ii:ss)
	 */
	function showAllDateTime(the, dateFormat, timeFormat) {
		$("#cont_datetime").show();
		the.mobiscroll().datetime({
			mode : 'scroller',
			display : 'bottom',
			lang : 'zh',
			dateFormat : (dateFormat ? dateFormat : "yy-mm-dd"),
			timeFormat : (timeFormat ? timeFormat : "HH:ii:ss")
		});
	}

	/**
	 * 日历控件，只有时间
	 *
	 * the：输入框对象
	 * timeFormat：时间格式(不传为默认：HH:ii:ss)
	 */
	function calendarOnlyTime(the, timeFormat) {
		$("#cont_datetime").show();
		the.mobiscroll().time({
			mode : 'scroller',
			display : 'bottom',
			lang : 'zh',
			timeFormat : (timeFormat ? timeFormat : "HH:ii:ss"),
			timeWheels : "HHiiss"
		});
	}

	/**
	 * 日历控件，只有日期
	 *
	 * the：输入框对象
	 * dateFormat：日期格式(不传为默认：yy-mm-dd)
	 */
	function calendarOnlyDate(the, dateFormat) {
		$("#cont_datetime").show();
		the.mobiscroll().date({
			mode : 'scroller',
			display : 'bottom',
			lang : 'zh',
			minDate : new Date(),
			dateFormat : (dateFormat ? dateFormat : "yy-mm-dd"),
		});
	}
	/**
	 * 日历控件，只有日期
	 *
	 * the：输入框对象
	 * dateFormat：日期格式(不传为默认：yy-mm-dd)
	 */
	function calendarDate(the, dateFormat) {
		$("#cont_datetime").show();
		the.mobiscroll().date({
			mode : 'scroller',
			display : 'bottom',
			lang : 'zh',
			dateFormat : (dateFormat ? dateFormat : "yy-mm-dd"),
		});
	}
</script>
</head>
<body>
	<div data-role="fieldcontain" class="demo-cont" id="cont_datetime"></div>
</body>
</html>
