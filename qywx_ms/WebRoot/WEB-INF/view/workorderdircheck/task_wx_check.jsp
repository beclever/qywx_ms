<%@ page pageEncoding="utf-8" language="java" %>
<div class="mian02">
	 <div class="workform_list">
	    <label>完成情况:</label>
	    <span> 
			<input type="radio" disabled="disabled" name="${taskbean.taskType}finish" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 
			<input type="radio" disabled="disabled" name="${taskbean.taskType}finish" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
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
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button" class="task_replace">
			    <span id="faultlocationpath" class="task_replace_button_unselected">故障部位</span>
			    <span id="maintainspare" class="task_replace_button_unselected">模块替换</span>
			    <span id="maintainelement" class="task_replace_button_unselected">零件替换</span>
			    <div class="clear"></div>
			</div>
		</legend>
		<div class="record_div" id="maintain1" style="display: none">
		    <c:forEach items="${taskbean.deviceHitch}" var="taskfault">
		        <div class='task_box02'>
		            <span><font color='blue'>故障部位:</font>${taskfault.problemPartRemark}
		                <input type='hidden' value="${taskfault.problemPartId}" name="maintain1problemPartId"/>
		                <input type='hidden' value="${taskfault.problemPartCode}" id="maintain1problemPartCode"
		                       name="maintain1problemPartCode"/>
		                <input type='hidden' value="${taskfault.problemCodeId}" name="maintain1problemCodeId"/>
		                <input type='hidden' value="${taskfault.troubleCode}" name="maintain1troubleCode"/>
		                <input type='hidden' value="${taskfault.problemReasonId}" name="maintain1problemReasonId"/>
		                <input type='hidden' value="${taskfault.troubleReasonCode}" name="maintain1troubleReasonCode"/>
		                <input type='hidden' value="${taskfault.problemMethodId}" name="maintain1problemMethodId"/>
		                <input type='hidden' value="${taskfault.processCode}" name="maintain1processCode"/>
		            </span>
		            <%-- <span><font color='blue'>故障描述:</font>${taskfault.troubleCode}</span>
					<span><font color='blue'>故障原因:</font>${taskfault.troubleReasonCode}</span>
					<span><font color='blue'>处理方法:</font>${taskfault.processCode}</span> --%>
					<span><font color='blue'>故障描述:</font>${taskfault.troubleRemark}</span>
					<span><font color='blue'>故障原因:</font>${taskfault.troubleReasonRemark}</span>
					<span><font color='blue'>处理方法:</font>${taskfault.processRemark}</span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="maintain2" style="display: none">
		    <c:forEach items="${taskbean.importModel}" var="taskspare">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
		                <input type='hidden' value="${taskspare.oldSerialName}" name="maintain2oldSerialName"/>
		                <input type='hidden' value="${taskspare.oldSerialNumber}" name="maintain2oldSerialNumber"/>
		                <input type='hidden' value="${taskspare.oldMaterialCode}" name="maintain2oldMaterialCode"/>
		                <input type='hidden' value="${taskspare.oldHardwareVersion}" name="maintain2oldHardwareVersion"/>
		                <input type='hidden' value="${taskspare.oldSoftwareVersion}" name="maintain2oldSoftwareVersion"/>
		                <input type='hidden' value="${taskspare.equipmentConfigId}" name="maintain2equipmentConfigId"/>
		                <input type='hidden' value="${taskspare.newSerialName}" name="maintain2newSerialName"/>
		                <input type='hidden' value="${taskspare.newMaterialCode}" name="maintain2newMaterialCode"/>
		                <input type='hidden' value="${taskspare.newSerialNumber}" name="maintain2newSerialNumber"/>
		                <input type='hidden' value="${taskspare.newHardwareVersion}" name="maintain2newHardwareVersion"/>
		                <input type='hidden' value="${taskspare.newSoftwareVersion}" name="maintain2newSoftwareVersion"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskspare.newSerialName}</span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="maintain3" style="display: none">
		    <c:forEach items="${taskbean.subModel}" var="taskelement">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
		                <input type='hidden' value="${taskelement.newSerialName}" name="maintain3newSerialName"/>
		                <input type='hidden' value="${taskelement.oldSerialName}" name="maintain3oldSerialName"/>
		                <input type='hidden' value="${taskelement.quantity}" name="maintain3nums"/>
		                <input type='hidden' value="${taskelement.newMaterialCode}" name="maintain3newMaterialCode"/>
		                <input type='hidden' value="${taskelement.oldMaterialCode}" name="maintain3oldMaterialCode"/>
		                <input type='hidden' value="${taskelement.newSoftwareVersion}" name="maintain3newSoftwareVersion"/>
		                <input type='hidden' value="${taskelement.newHardwareVersion}" name="maintain3newHardwareVersion"/>
		                <input type='hidden' value="${taskelement.useMethod}" name="maintain3useMethod"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
		            <span><font color='blue'>数量:</font>${taskelement.quantity}</span>
		        </div>
		    </c:forEach>
		</div> 
		<div class="clear"></div>   
	</fieldset>
	

</div>