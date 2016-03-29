<%@ page  pageEncoding="utf-8" language="java" %>
<div class="mian02">
	<div class="workform_list">
	    <label>完成情况:</label>
	    <span> 
			<input type="radio" disabled="disabled" readonly="readonly" name="${taskbean.taskType}finish" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 
			<input type="radio" disabled="disabled" readonly="readonly" name="${taskbean.taskType}finish" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
		</span>
	    <div class="clear"></div>
	    <script type="text/javascript">
	    stateAndInputs[stateAndInputs.length] = {inputName:'${taskbean.taskType}finish',state:'${taskbean.workIsFinish}',taskType:'${taskbean.taskType}'}; 
    	</script>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>耗时(分钟):</label>
	    <span>
	       ${taskbean.costTime}
	    </span>
	    <div class="clear"></div>
	</div>
<div class="workform_list">
   <label> 保修状态:</label> 
   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
   <div class="clear"></div>
</div>
	<div class="workform_list">
	    <label>任务:</label>
	    <span>
	        ${taskbean.taskContent}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>工作描述:</label>
	    <span>
	        ${taskbean.workRemark}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>客户:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth"  name="AZFZcustom" disabled="disabled">
	            <c:forEach items="${customerList}" var="custombean">
	                <option <c:if test="${taskbean.installAndRemoveDeviceModel.customerNameId==custombean.defaultId}">selected = selected</c:if> value="${custombean.defaultId },${custombean.defaultName }">
	                        ${custombean.defaultName }
	                </option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.customerName}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>网点名称:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.branchName}
	    </span><div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装类型:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.installType}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装方式:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.installModel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>省份:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.province}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>城市:</label>
	    <span>
	        ${ taskbean.installAndRemoveDeviceModel.city}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>装机属性:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.installProperty}
	    </span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label>atm管理员:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.ATMManager}
	    </span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label>atm管理员电话:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.ATMManagerTel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label>网点负责人电话:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.branchPrincipalTel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>操作系统:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.operationSystem}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>系统版本:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.osVersion}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc名称:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.ATMCName}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc版本:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.ATMCVersion}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc跨平台sp:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.ATMCSpVersion}
	    </span><div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>加密方式:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.encryptModel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc号:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.ATMNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>银行号:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.bankNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>支行终端号:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.bankTerminalNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>网络连接协议:</label>
	    <span>
	      ${taskbean.installAndRemoveDeviceModel.netProtocol}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>本机ip:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.localIP}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>p端ip:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.pip}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>网关:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.gateway}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>子掩码:</label>
	    <span>
	      ${taskbean.installAndRemoveDeviceModel.subnetMask}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日均交易金额:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.dailyAverageAmount}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日均交易笔数:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.dailyAverageNum}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>灰尘:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.dust}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>温度:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.temperature}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>湿度:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.humidity}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>供电:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.powerSupply}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>防雨:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.waterproof}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日晒:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.solarization}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装地址:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.installAddress}
	    </span><div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>参考费用:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.referenceCharge}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>参考线路:</label>
	    <span>
	        ${taskbean.installAndRemoveDeviceModel.consultWay}
	    </span>
	    <div class="clear"></div>
	</div>
</div>