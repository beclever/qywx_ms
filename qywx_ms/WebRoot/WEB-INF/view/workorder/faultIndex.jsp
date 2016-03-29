<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>故障部位</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>
     <div class="window_mian" id="box">
    <input type="hidden" value="${serialNumber}" id="serialNumber">
    <input type="hidden" value="${divname}" id="divname">
    <input type="hidden" value="${equipmentModel }" id="equipmentModel">
         <div class="workform_list">
         	<div class="taskOrder_add_btn">
         		<label class="blue"><font color="red">*</font>故障部位:</label>
         		<div onclick="sendfaultlocate()">
             	<input type="text" readonly="readonly" size="15" name="problemPartCode"  
             		value="${problemPartCode}" id="problemPartCode" taskName="故障部位">
             	<span ><img src="${ctx_path }/pub/images/icon_window.png" /></span>
             	</div>
             </div>
             <div class="clear"></div>
             <input type="hidden" value="${problemPartId}" id="problemPartId">
         </div>
         <div class="workform_list">
         	<div class="taskOrder_add_btn">
	             <label class="blue"><font color="red">*</font>故障描述:</label>
	             <div onclick="sendfaultremark()">
	             	<input type="text" size="15" readonly="readonly" name="troubleCode"  
	             		value="${troubleCode}" id="troubleCode" taskName="故障描述">
					<span ><img src="${ctx_path }/pub/images/icon_window.png" /></span>
				</div>
             </div>
             <div class="clear"></div>
             <input type="hidden" id="problemCodeId"  value="${problemCodeId}">
         </div>
         <div class="workform_list">
         	 <div class="taskOrder_add_btn">
	             <label class="blue"><font color="red">*</font>故障原因:</label>
	             <div onclick="sendfaultreason()">
	             	<input type="text" size="15" onclick="sendfaultreason()" readonly="readonly" name="troubleReasonCode" 
	             		value="${troubleReasonCode}" id="troubleReasonCode"  taskName="故障原因">
	             <span ><img src="${ctx_path }/pub/images/icon_window.png" /></span>
	             </div>
             </div>
             <div class="clear"></div>
             <input type="hidden" id="problemReasonId"  value="${problemReasonId}">
         </div>
         <div class="workform_list">
         	 <div class="taskOrder_add_btn">
         	 <div onclick="sendProcess()">
	             <label class="blue"><font color="red">*</font>处理方法:</label>
	             	<input type="text" size="15" onclick="sendProcess()" readonly="readonly" name="processCode" 
	             		value="${processCode}" id="processCode" taskName="处理方法">
	             <span ><img src="${ctx_path }/pub/images/icon_window.png" /></span>
	             </div>
             </div>
             <div class="clear"></div>
             <input type="hidden" id="problemMethodId"  value="${problemMethodId}">
         </div>
         <div class="box" align="center">
             <input type="button" class="window_btn" value="确定" onclick="sendFaultOK()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <input type="button" class="window_btn"  value="取消" onclick="cancelFault()">
         </div>
   </div>
    <script type="text/javascript">
    var basePath = "${ctx_path}";
    var index = parent.layer.getFrameIndex(window.name);
  //给父页面传值
    $('#transmit').on('click', function(){
    	parent.layer.msg('字段不为空');
        parent.$('#parentIframe').text('我被改变了');
        parent.layer.close(index);
    });
	 function cancelFault(){
		  parent.layer.close(index);
	 }
	function sendFaultOK(){
	
		//传值
	  var obj = $('#divname').val();
	  var problemPartId = $('#problemPartId').val();
	  var problemPartCode = $('#problemPartCode').val();
	  var problemCodeId = $('#problemCodeId').val();
	  var troubleCode = $('#troubleCode').val();
	  var problemReasonId = $('#problemReasonId').val();
	  var troubleReasonCode = $('#troubleReasonCode').val();
	  var problemMethodId = $('#problemMethodId').val();
	  var processCode = $('#processCode').val();
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
	  loadFaultLocation(obj, problemPartId, problemPartCode, problemCodeId, troubleCode, problemReasonId, troubleReasonCode, problemMethodId, processCode);
	  parent.layer.close(index);
	  
	}
	
	// 故障传值
	function loadFaultLocation(obj, problemPartId, problemPartCode, problemCodeId, troubleCode, problemReasonId, troubleReasonCode, problemMethodId, processCode) {
		var htmlstr = "<div class='task_box02'><span><font color='blue'>故障部位:</font><input type='hidden' value='" + problemPartId + "' name='" + obj + "problemPartId'/>" +
	        "<input type='hidden' value='" + problemPartCode + "' name='" + obj + "problemPartCode'/>" +
	        "<input type='hidden' value='" + problemCodeId + "' name='" + obj + "problemCodeId'/>" +
	        "<input type='hidden' value='" + troubleCode + "' name='" + obj + "troubleCode'/>" +
	        "<input type='hidden' value='" + problemReasonId + "' name='" + obj + "problemReasonId'/>" +
	        "<input type='hidden' value='" + troubleReasonCode + "' name='" + obj + "troubleReasonCode'/>" +
	        "<input type='hidden' value='" + problemMethodId + "' name='" + obj + "problemMethodId'/>" +
	        "<input type='hidden' value='" + processCode + "' name='" + obj + "processCode'/>"
	        + problemPartCode
	        + "</span><span><font color='blue'>故障描述:</font>"
	        + troubleCode
	        + "</span><span><font color='blue'>故障原因:</font>" + troubleReasonCode + "</span><span><font color='blue'>处理方法:</font>" + processCode + "</span><a class='logistics_close' onclick=WSSpare(this)><img src='${ctx_path}/pub/images/delete.png'/></a></div>";
	        parent.$("#" + obj).append(htmlstr);
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
    </script>  
  </body>
</html>
