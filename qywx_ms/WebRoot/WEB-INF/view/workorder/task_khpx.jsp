<%@ page language="java" pageEncoding="utf-8"%>
<div class="mian02" >
	<div class="workform_list">
	    <label>完成情况:</label>
	    <span> 
			<input type="radio" name="${taskbean.taskType}finish" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 
			<input type="radio" name="${taskbean.taskType}finish" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
		</span>
	    <div class="clear"></div>
	    <script type="text/javascript">
	    stateAndInputs[stateAndInputs.length] = {inputName:'${taskbean.taskType}finish',state:'${taskbean.workIsFinish}',taskType:'${taskbean.taskType}'}; 
    	</script>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>耗时(分钟):</label>
	    <span>
	        <input type="number" name="PXtime" id="PXtime"
	               onKeyUp="actualTaskTotalTime(this)" value="${taskbean.costTime}"/>
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
	        <textarea name="PXworkRemark"  id="PXworkRemark">${taskbean.workRemark}</textarea>
	    </span>
	    <div class="clear"></div>
	</div>
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button_client" class="task_replace">
			    <span id="clientspare" class="task_replace_button_unselected">模块替换</span>&nbsp;
			    <span id="clientelement" class="task_replace_button_unselected">零件替换</span>
			    <!-- <span id="khpxTaskMove" class="task_replace_button_unselected">移除任务</span> -->
			</div>
		</legend>
		<div class="record_div" id="client1" style="display: none">
		    <a class="logistics_add" onclick="oldspareDialog('client1')">
		     	 <img class="logistics_add_icon" src="${ctx_path}/pub/images/icon_add.png"/>添加
		    </a>
		    <c:forEach items="${taskbean.importModel}" var="taskspare">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
		                <input type='hidden' value="${taskspare.oldSerialName}" name="client1oldSerialName"/>
		                <input type='hidden' value="${taskspare.oldSerialNumber}" name="client1oldSerialNumber"/>
		                <input type='hidden' value="${taskspare.oldMaterialCode}" name="client1oldMaterialCode"/>
		                <input type='hidden' value="${taskspare.oldHardwareVersion}" name="client1oldHardwareVersion"/>
		                <input type='hidden' value="${taskspare.oldSoftwareVersion}" name="client1oldSoftwareVersion"/>
		                <input type='hidden' value="${taskspare.equipmentConfigId}" name="client1equipmentConfigId"/>
		                <input type='hidden' value="${taskspare.newSerialName}" name="client1newSerialName"/>
		                <input type='hidden' value="${taskspare.newMaterialCode}" name="client1newMaterialCode"/>
		                <input type='hidden' value="${taskspare.newSerialNumber}" name="client1newSerialNumber"/>
		                <input type='hidden' value="${taskspare.newHardwareVersion}" name="client1newHardwareVersion"/>
		                <input type='hidden' value="${taskspare.newSoftwareVersion}" name="client1newSoftwareVersion"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskspare.newSerialName}</span>
		             <input class="logistics_close" type='image' src='css/sa/images/delete.png' onclick='WSSpare(this)'/>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="client2" style="display: none">
		    <a class="logistics_add"  onclick="newspareDialog('client2')">
		       	<img class="logistics_add_icon" src="${ctx_path}/pub/images/icon_add.png"/>添加
		    </a>
		    <c:forEach items="${taskbean.subModel}" var="taskelement">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
		                <input type='hidden' value="${taskelement.newSerialName}" name="client2newSerialName"/>
		                <input type='hidden' value="${taskelement.oldSerialName}" name="client2oldSerialName"/>
		                <input type='hidden' value="${taskelement.quantity}" name="client2nums"/>
		                <input type='hidden' value="${taskelement.newMaterialCode}" name="client2newMaterialCode"/>
		                <input type='hidden' value="${taskelement.oldMaterialCode}" name="client2oldMaterialCode"/>
		                <input type='hidden' value="${taskelement.newSoftwareVersion}" name="client2newSoftwareVersion"/>
		                <input type='hidden' value="${taskelement.newHardwareVersion}" name="client2newHardwareVersion"/>
		                <input type='hidden' value="${taskelement.useMethod}" name="client2useMethod"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
		            <span><font color='blue'>数量:</font>${taskelement.quantity}</span>
		             <input class="logistics_close" type='image' src='css/sa/images/delete.png' onclick='WSSpare(this)'/>
		        </div>
		    </c:forEach>
		</div>
	</fieldset>
	<br/>
	<div class="task_replace">
		<span id="khpxTaskMove" class="modal_ok" style="background: #4286f5; color: #fff;width: 33%">移除任务</span>
		<div class="clear"></div>
	</div>
</div>