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
	        <%-- <select class="taskOrder_inputwidth" name="AZKTcustom" disabled="disabled">
	            <c:forEach items="${customerList}" var="custombean">
	            	<option
	                        <c:if test="${custombean.defaultName== azktCustomerName }">selected=true</c:if>
	                        value="${custombean.defaultId },${custombean.defaultName }"> ${custombean.defaultName }</option>
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
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装类型:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTinstallType" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.intallType.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.intallType.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.installType}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装方式:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTinstallModels" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.installModel.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.installModel.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.installModel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>省份:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" onchange="loadCity(this)" id="azktcity" name="AZKTprovinces" disabled="disabled">
	         	<option value=""></option>
	            <c:forEach items="${ provinces}" var="province" varStatus="statu">
					<option <c:if test="${taskbean.installAndRemoveDeviceModel.province== province}">selected=true</c:if>
						value="${ statu.count},${ province}">${ province}</option>
				</c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.province}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>城市:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTcitys" disabled="disabled">
	        	<option value="${ taskbean.installAndRemoveDeviceModel.cityId},${ taskbean.installAndRemoveDeviceModel.city}">
				${ taskbean.installAndRemoveDeviceModel.city}</option>
	        </select> --%>
	        ${ taskbean.installAndRemoveDeviceModel.city}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>装机属性:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTinstallPropertys" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.installProperty.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.installProperty.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
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
	        <%-- <select class="taskOrder_inputwidth" name="AZKToperationSystems" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.operationSystem.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.operationSystem.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
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
	        <%-- <select class="taskOrder_inputwidth" name="AZKTATMCNs" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.ATMCName.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.ATMCName.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
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
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>加密方式:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTencryptModels" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.encryptMode.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.encryptMode.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.encryptModel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atm号:</label>
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
	        <%-- <select class="taskOrder_inputwidth" name="AZKTdailyAverageAmounts" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.dayAerageSum.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.dayAerageSum.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.dailyAverageAmount}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日均交易笔数:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTdailyAverageNums" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.dayAerageTime.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.dayAerageTime.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.dailyAverageNum}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>灰尘:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTdusts" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentDust.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentDust.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.dust}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>温度:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTtemperatures" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentTemperature.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentTemperature.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.temperature}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>湿度:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKThumiditys" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentHumidity.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentHumidity.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.humidity}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>供电:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTpowerSupplys" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentPowerSupply.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentPowerSupply.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.powerSupply}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>防雨:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTwaterproofs" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentRainDefence.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentRainDefence.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.waterproof}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日晒:</label>
	    <span>
	        <%-- <select class="taskOrder_inputwidth" name="AZKTsolarizations" disabled="disabled">
	            <c:forEach items="${taskbean.taskAttributeOption.environmentSunshine.optionValue}" var="beans">
	                <option
	                        <c:if test="${taskbean.taskAttributeOption.environmentSunshine.defaultId== beans.optionId }">selected=true</c:if>
	                        value="${ beans.optionId},${ beans.optionName}">${ beans.optionName}</option>
	            </c:forEach>
	        </select> --%>
	        ${taskbean.installAndRemoveDeviceModel.solarization}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装地址:</label>
	    <span>
	       ${taskbean.installAndRemoveDeviceModel.installAddress}
	    </span>
	    <div class="clear"></div>
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
	<%-- <div class="workform_list">
		<label><font color="red">*</font>设备责任人:</label>
		<span>
			<select class="taskOrder_inputwidth" name="AZKTequipmentChargeName" id="AZKTequipmentChargeName" disabled="disabled">
				<c:forEach items="${workbean.followUser.optionValue}" var="flowbean">
					<option value="${flowbean.optionName }" <c:if test="${flowbean.optionName == taskbean.installAndRemoveDeviceModel.equipmentChargeName}">selected="selected"</c:if>> ${flowbean.optionName}</option>
				</c:forEach>
			</select>
		</span>
		<div class="clear"></div>
	</div> --%>
</div>