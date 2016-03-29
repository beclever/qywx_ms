var keys0 = ['divname', 'equipmentModel', 'problemPartId', 'problemPartCode', 'problemCodeId', 'troubleCode', 'problemReasonId', 'troubleReasonCode', 'problemMethodId', 'processCode'];
var pageCacheKey = ['faultLocationPageKey'];
var formCacheValuePrefix = 'var cacheJson = {';
var cacheForm = function () {
    var values = formCacheValuePrefix;
    keys0.each(function (value, index) {
        if (value.trim().length == 0) return;
        values += value + ":'" + $('#' + value).val() + "'" + (index + 1 == keys0.length ? '};' : ',');
    })
}
var retrieveForm = function () {
    PageCache.retrieveInDefaultKey(function (msg) {
        eval(msg);
        for (var key in cacheJson) {
            $('#' + key).val(cacheJson[key]);
        }
    });
}
var retrieve = function () {
    retrieveForm();
    PageCache.retrieve(pageCacheKey, function (value) {
        var values = value.split('&');
        for (var i = 0; i < 2; i++) {
            $('#' + values[i]).val(values[i + 2]);
        }
    })
}

//故障部位
function sendfaultlocate() {
    var equipmentModel = $("#equipmentModel").val();
    window.location.href = basePath + '/cp/fault/faultInfo.do?equipmentModel=' + equipmentModel;
}

//故障描述
function sendfaultremark() {
	var problemPartId = $('#problemPartId').val();
    if (!problemPartId || problemPartId.length == 0){
    	parent.layer.msg('请先选择故障部位');
    	return;
    }
    window.location.href = basePath + '/cp/fault/faultRemark.do?problemPartId=' + problemPartId;
}
//故障原因
function sendfaultreason() {
    window.location.href = basePath + '/cp/fault/faultReason.do';
}
//处理方法
function sendProcess() {
    window.location.href = basePath + '/cp/fault/faultProcess.do';
}

//保存
function sendFaultOK() { 
	  var obj = $('#divname').val();
	  var equipmentModel = $('#equipmentModel').val();
	  var problemPartId = $('#problemPartId').val();
	  var problemPartCode = $('#problemPartCode').val();
	  var problemCodeId = $('#problemCodeId').val();
	  var troubleCode = $('#troubleCode').val();
	  var problemReasonId = $('#problemReasonId').val();
	  var troubleReasonCode = $('#troubleReasonCode').val();
	  var problemMethodId = $('#problemMethodId').val();
	  var processCode = $('#processCode').val();
	  var poNumber=$('#poNumber').val();
	  if(problemPartCode==""||problemPartCode.length==0){
		  parent.layer.msg('故障部位不能为空');
		  return;
	  }
	  if(troubleCode==""||troubleCode.length==0){
		  parent.layer.msg('故障描述不能为空');
		  return;
	  }
	  if(troubleReasonCode==""||troubleReasonCode.length==0){
		  parent.layer.msg('故障原因不能为空');
		  return;
	  }
	  if(processCode==""||processCode.length==0){
		  parent.layer.msg('处理方法不能为空');
	      return;
	  }
    
    //================================
	  var divname = $('#divname').val();
	  var equipmentModel = $('#equipmentModel').val();
	  var problemPartId = $('#problemPartId').val();
	  var problemPartCode = $('#problemPartCode').val();
	  var problemCodeId = $('#problemCodeId').val();
	  var troubleCode = $('#troubleCode').val();
	  var problemReasonId = $('#problemReasonId').val();
	  var troubleReasonCode = $('#troubleReasonCode').val();
	  var problemMethodId = $('#problemMethodId').val();
	  var processCode = $('#processCode').val();
	  var poNumber=$('#poNumber').val();
	  var args = "&divname="+divname 
	  +"&problemPartId="+problemPartId 
	  +"&problemPartCode="+encodeURIComponent(encodeURIComponent(problemPartCode)) 
	  +"&problemCodeId="+problemCodeId 
	  +"&troubleCode="+encodeURIComponent(encodeURIComponent(troubleCode))  
	  +"&problemReasonId="+problemReasonId 
	  +"&troubleReasonCode="+encodeURIComponent(encodeURIComponent(troubleReasonCode))  
	  +"&problemMethodId="+problemMethodId 
	  +"&processCode="+encodeURIComponent(encodeURIComponent(processCode)) 
	  + "&equipmentModel="+equipmentModel;
	  window.location.href = basePath + '/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber=' + poNumber+args+"&isExistFault="+1;
}

//取消
function cancelFault() {
	  var poNumber=$('#poNumber').val();
	  window.location.href = basePath + '/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='+poNumber;

}