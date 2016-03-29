<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addrName = String.valueOf(request.getParameter("addrName"));
	String longitude = String
			.valueOf(request.getParameter("longitude"));
	String latitude = String.valueOf(request.getParameter("latitude"));
	request.setAttribute("addrName", addrName);
	request.setAttribute("longitude", longitude);
	request.setAttribute("latitude", latitude);
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?type=quick&ak=pNwGHcggmOgWCnAspAkRLsyo&v=1.0"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=pNwGHcggmOgWCnAspAkRLsyo"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

#golist {
	display: none;
}

@media ( max-device-width : 780px) {
	#golist {
		display: block !important;
	}
}
</style>

<title>地图导航</title>
<script type="text/javascript">
	// 百度地图API功能
	window.onload = function() {
		var map = new BMap.Map("allmap");
		var lng = 116.417854;
		var lat = 39.923978;
		var point = new BMap.Point(lng, lat);
		var myGeo = new BMap.Geocoder();
		map.centerAndZoom(point, 15);
		map.addControl(new BMap.ZoomControl());
		var addresst = '${addrName}';
		var elngt = '${longitude}';
		var elatt ='${latitude}';
		var address = "";
		var elng = "";
		var elat = "";
		if (null != addresst && "" != (addresst)) {
			address = '${addrName}';
		} else if (null != elngt && null != elatt) {
			elng = elngt;
			elat =  elatt;
			myGeo.getLocation(new BMap.Point(elng, elat), function(result) {
				if (result) {
					address = result.address;
				}
			});
		} else {
			alert("定位失败,请重新加载");
			return;
		}
		//=========================
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r) {
			if (this.getStatus() == BMAP_STATUS_SUCCESS) {
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				lat = r.point.lat;
				lng = r.point.lng;
				//====================================
				var regionStart = "";
				// 根据坐标得到地址描述    
				myGeo.getLocation(new BMap.Point(lng, lat), function(result) {
					if (result) {
						regionStart = result.address;
					}				
					//=======================================				
					var opts = {
						width : 380, // 信息窗口宽度
						height : 15, // 信息窗口高度
						title : "点击加载路径从这儿到" // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow(address, opts); // 创建信息窗口对象
					map.openInfoWindow(infoWindow, r.point); //开启信息窗口                
					var mk = new BMap.Marker(r.point);
					map.addOverlay(mk);
					map.panTo(r.point);
					var regionEndNo = address.indexOf("市");
					var StartAddressNo = regionStart.indexOf("市");
					var regionStartR = regionStart.substring(0, StartAddressNo);
					var regionEnd = address.substring(0, regionEndNo);
					mk.addEventListener("click", function() {
						/*start|end：（必选）
						{name:string,latlng:Lnglat}
						opts:
						mode：导航模式，固定为
						BMAP_MODE_TRANSIT、BMAP_MODE_DRIVING、
						BMAP_MODE_WALKING、BMAP_MODE_NAVIGATION
						分别表示公交、驾车、步行和导航，（必选）
						region：城市名或县名  当给定region时，认为起点和终点都在同一城市，除非单独给定起点或终点的城市
						origin_region/destination_region：同上
						 */
						var start = {
							latlng : new BMap.Point(lng, lat),
							name : regionStart
						}
						var end = {
							name : address
						}
						var opts = {
							mode : BMAP_MODE_DRIVING,
							origin_region : regionStartR,
							destination_region : regionEnd
						}
						var ss = new BMap.RouteSearch();
						ss.routeCall(start, end, opts);
					});
								});

				

			} else {
				alert('定位失败' + this.getStatus());
			}
		}, {
			enableHighAccuracy : true
		})

	}

	function getPosition(lng, lat) {
		var myGeo = new BMap.Geocoder();
		// 根据坐标得到地址描述    
		myGeo.getLocation(new BMap.Point(lng, lat), function(result) {
			if (result) {
				//alert(result);
				return result.address;
				//alert(result.address);
			}
		});
	}
</script>
</head>
<body>

	<div id="allmap"></div>
</body>
</html>

