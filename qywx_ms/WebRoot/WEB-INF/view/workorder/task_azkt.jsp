<%@ page language="java" pageEncoding="utf-8"%>
<div class="mian02">
	<div class="workform_list">
		<label>完成情况:</label>
		<span> 
			<input type="radio" name="${taskbean.taskType}finish" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 
			<input type="radio" name="${taskbean.taskType}finish" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
		</span>
		<div class="clear"></div>
		<script type="text/javascript">
			stateAndInputs[stateAndInputs.length] = {
				inputName : '${taskbean.taskType}finish',
				state : '${taskbean.workIsFinish}',
				taskType : '${taskbean.taskType}'
			};
		</script>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>耗时(分钟):</label> <span> <input
			type="number" name="AZKTtime" class="noBlank" id="AZKTtime" oninput="OnInput(event)"
			onpropertychange="OnPropChanged(event)" value="${taskbean.costTime}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
	   <label> 保修状态:</label> 
	   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
	   <div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>任务:</label> <span> ${taskbean.taskContent} </span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>工作描述:</label> <span> <textarea
				name="AZKTworkRemark" class="noBlank" id="AZKTworkRemark">${taskbean.workRemark}</textarea>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>客户:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTcustom">
				<c:forEach items="${customerList}" var="custombean">
					<option value="${custombean.defaultId },${custombean.defaultName }"
					<c:if test="${custombean.defaultId==taskbean.installAndRemoveDeviceModel.customerNameId}">selected="selected"</c:if>>
						${custombean.defaultName }</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>网点名称:</label> <span> <input type="text" class="noBlank"
			name="AZKTbranchName" id="AZKTbranchName"
			value="${taskbean.installAndRemoveDeviceModel.branchName}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>安装类型:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKTinstallType">
				<c:forEach
					items="${taskbean.taskAttributeOption.intallType.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.intallType.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>安装方式:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKTinstallModels">
				<c:forEach
					items="${taskbean.taskAttributeOption.installModel.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.installModel.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>省份:</label> <span> <select
			class="taskOrder_inputwidth noBlank" onchange="loadCity(this)"
			id="AZKTprovinces" name="AZKTprovinces">
				<option value="">请选择省份</option>
				<c:forEach items="${ provinces}" var="province" varStatus="statu">
					<option
						<c:if test="${taskbean.installAndRemoveDeviceModel.province== province}">selected=true</c:if>
						value="${ statu.count},${ province}">${ province}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>城市:</label> <span> 
		 <select class="taskOrder_inputwidth noBlank" name="AZKTcitys" id="AZKTcitys">
	        <option value="${ taskbean.installAndRemoveDeviceModel.cityId},${ taskbean.installAndRemoveDeviceModel.city}">
	        ${ taskbean.installAndRemoveDeviceModel.city}</option>
	      </select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>装机属性:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKTinstallPropertys">
				<c:forEach
					items="${taskbean.taskAttributeOption.installProperty.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.installProperty.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<%-- <div class="workform_list">
		<label><font color="red">*</font>ATM管理员:</label> <span> <input type="text" class="noBlank"
			name="AZKTATMManager" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.ATMManager}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>ATM管理员电话:</label> <span> <input type="text" class="noBlank"
			name="AZKTATMManagerTel" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.ATMManagerTel}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>网点负责人电话:</label> <span> <input type="text"
			name="AZKTbranchPrincipalTel" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.branchPrincipalTel}" />
		</span>
		<div class="clear"></div>
	</div> --%>
	<div class="workform_list">
		<label><font color="red">*</font>操作系统:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKToperationSystems">
				<c:forEach
					items="${taskbean.taskAttributeOption.operationSystem.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.operationSystem.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>系统版本:</label> <span> <input type="text" class="noBlank"
			name="AZKTosVersion" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.osVersion}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>ATMC名称:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKTATMCNs">
				<c:forEach
					items="${taskbean.taskAttributeOption.ATMCName.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.ATMCName.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>ATMC版本:</label> <span> <input type="text" class="noBlank"
			name="AZKTATMCVersion" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.ATMCVersion}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>ATMC跨平台SP:</label> <span> <input type="text" class="noBlank"
			name="AZKTATMCSpVersion" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.ATMCSpVersion}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>加密方式:</label> <span> <select
			class="taskOrder_inputwidth noBlank" name="AZKTencryptModels">
				<c:forEach
					items="${taskbean.taskAttributeOption.encryptMode.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.encryptMode.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>ATM号:</label> <span> <input type="text" class="noBlank"
			name="AZKTATMNumber" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.ATMNumber}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>银行号:</label> <span> <input type="text"
			name="AZKTbankNumber" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.bankNumber}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>支行终端号:</label> <span> <input type="text"
			name="AZKTbankTerminalNumber" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.bankTerminalNumber}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>网络连接协议:</label> <span> <input type="text"
			name="AZKTnetProtocol" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.netProtocol}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>本机ip:</label> <span> <input type="text"
			name="AZKTLocalIP" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.localIP}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>p端ip:</label> <span> <input type="text" name="AZKTpip"
			id="textfield2" value="${taskbean.installAndRemoveDeviceModel.pip}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>网关:</label> <span> <input type="text" name="AZKTgateway"
			id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.gateway}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>子掩码:</label> <span> <input type="text"
			name="AZKTsubnetMask" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.subnetMask}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>日均交易金额:</label> <span> <select
			class="taskOrder_inputwidth" name="AZKTdailyAverageAmounts">
				<c:forEach
					items="${taskbean.taskAttributeOption.dayAerageSum.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.dayAerageSum.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>日均交易笔数:</label> <span> <select
			class="taskOrder_inputwidth" name="AZKTdailyAverageNums">
				<c:forEach
					items="${taskbean.taskAttributeOption.dayAerageTime.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.dayAerageTime.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>灰尘:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTdusts">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentDust.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentDust.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>温度:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTtemperatures">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentTemperature.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentTemperature.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>湿度:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKThumiditys">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentHumidity.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentHumidity.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>供电:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTpowerSupplys">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentPowerSupply.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentPowerSupply.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>防雨:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTwaterproofs">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentRainDefence.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentRainDefence.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>日晒:</label> <span> <select class="taskOrder_inputwidth noBlank"
			name="AZKTsolarizations">
				<c:forEach
					items="${taskbean.taskAttributeOption.environmentSunshine.optionValue}"
					var="beans">
					<option
						<c:if test="${taskbean.taskAttributeOption.environmentSunshine.defaultId== beans.optionId }">selected=true</c:if>
						value="${ beans.optionId},${ beans.optionName}">${
						beans.optionName}</option>
				</c:forEach>
		</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>安装地址:</label> <span> <input type="text" class="noBlank"
			name="AZKTinstallAddress" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.installAddress}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>参考费用:</label> <span> <input type="text" class="noBlank"
			name="AZKTreferenceCharge" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.referenceCharge}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>参考线路:</label> <span> <input type="text" class="noBlank"
			name="AZKTconsultWay" id="textfield2"
			value="${taskbean.installAndRemoveDeviceModel.consultWay}" />
		</span>
		<div class="clear"></div>
	</div>
		<%-- <input type="text" class="noBlank"
			name="AZKTequipmentChargeName" id="AZKTequipmentChargeName"
			value="${taskbean.installAndRemoveDeviceModel.equipmentChargeName}" /> --%>
	<%-- <div class="workform_list">
		<label><font color="red">*</font>设备责任人:</label> <span> 
			<select class="taskOrder_inputwidth" id="AZKTequipmentChargeName" name='AZKTequipmentChargeName' >
		    	<option value=""></option>
		    	<c:forEach items="${togetherPersonList}" var="item">
					<option
						<c:if test="${taskbean.installAndRemoveDeviceModel.equipmentChargeName==item.name}">selected=true</c:if>
						value="${item.name }">${item.name}</option>
				</c:forEach>
			</select>
		</span>
		<div class="clear"></div>
	</div> --%>
	<br/>
	<div class="task_replace">
		<span id="azktTaskMove" class="modal_ok" style="background: #4286f5; color: #fff;width: 33%">移除任务</span>
		<div class="clear"></div>
	</div>
</div>