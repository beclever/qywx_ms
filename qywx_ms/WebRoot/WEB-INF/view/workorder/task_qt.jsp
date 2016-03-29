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
	        <input type="number"  name="QTtime" id="QTtime" onKeyUp="actualTaskTotalTime(this)" value="${taskbean.costTime}"/>
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	   <label> 保修状态:</label> 
	   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
	   <div class="clear"></div>
	</div>
	<div class="workform_list">
	   <label> 任务:</label> 
	   <span>${taskbean.taskContent}</span>
	   <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>工作描述:</label>
	    <span>
	        <textarea name="QTworkRemark" id="QTworkRemark">${taskbean.workRemark}</textarea>
	    </span>
	    <div class="clear"></div>
	</div>	
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button_other" class="task_replace">
			    <span id="otherspare" class="task_replace_button_unselected">模块替换</span>
			    <span id="otherelement" class="task_replace_button_unselected">零件替换</span>
			    <!-- <span id="qtTaskMove" class="task_replace_button_unselected">移除任务</span> -->
			    <div class="clear"></div>
			</div>
		</legend>
	
	<div class="record_div" id="other1" style="display: none">
	    <a class="logistics_add" onclick="oldspareDialog('other1')">
	       	<img class="logistics_add_icon" src="${ctx_path }/pub/images/icon_add.png"/>添加
	    </a>
	    <c:forEach items="${taskbean.importModel}" var="taskspare">
	        <div class='task_box02'>
	            <span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
	                <input type='hidden' value="${taskspare.oldSerialName}" name="other1oldSerialName"/>
	                <input type='hidden' value="${taskspare.oldSerialNumber}" name="other1oldSerialNumber"/>
	                <input type='hidden' value="${taskspare.oldMaterialCode}" name="other1oldMaterialCode"/>
	                <input type='hidden' value="${taskspare.oldHardwareVersion}" name="other1oldHardwareVersion"/>
	                <input type='hidden' value="${taskspare.oldSoftwareVersion}" name="other1oldSoftwareVersion"/>
	                <input type='hidden' value="${taskspare.equipmentConfigId}" name="other1equipmentConfigId"/>
	                <input type='hidden' value="${taskspare.newSerialName}" name="other1newSerialName"/>
	                <input type='hidden' value="${taskspare.newMaterialCode}" name="other1newMaterialCode"/>
	                <input type='hidden' value="${taskspare.newSerialNumber}" name="other1newSerialNumber"/>
	                <input type='hidden' value="${taskspare.newHardwareVersion}" name="other1newHardwareVersion"/>
	                <input type='hidden' value="${taskspare.newSoftwareVersion}" name="other1newSoftwareVersion"/>
	            </span>
	            <span><font color='blue'>新物料:</font>${taskspare.newSerialName}</span>
	            <input class="logistics_close" type='image' src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)'/>
	        </div>
	    </c:forEach>
	</div>
	<div class="record_div" id="other2" style="display: none">
	    <a class="logistics_add" onclick="newspareDialog('other2')">
	    	<img class="logistics_add_icon" src="${ctx_path }/pub/images/icon_add.png"/>添加
	    </a>
	    <c:forEach items="${taskbean.subModel}" var="taskelement">
	        <div class='task_box02'>
	            <span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
	                <input type='hidden' value="${taskelement.newSerialName}" name="other2newSerialName"/>
	                <input type='hidden' value="${taskelement.oldSerialName}" name="other2oldSerialName"/>
	                <input type='hidden' value="${taskelement.quantity}" name="other2nums"/>
	                <input type='hidden' value="${taskelement.newMaterialCode}" name="other2newMaterialCode"/>
	                <input type='hidden' value="${taskelement.oldMaterialCode}" name="other2oldMaterialCode"/>
	                <input type='hidden' value="${taskelement.newSoftwareVersion}" name="other2newSoftwareVersion"/>
	                <input type='hidden' value="${taskelement.newHardwareVersion}" name="other2newHardwareVersion"/>
	                <input type='hidden' value="${taskelement.useMethod}" name="other2useMethod"/>
	            </span>
	            <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
	            <span><font color='blue'>数量:</font>${taskelement.quantity}</span>
	             <input class="logistics_close" type='image' src='${ctx_path }/pub/images/images/delete.png' onclick='WSSpare(this)'/>
	        </div>
	    </c:forEach>
	</div>
	</fieldset>
	<br/><br/>
	<div class="task_replace">
		<span id="qtTaskMove" class="modal_ok" style="background: #4286f5; color: #fff;width: 33%">移除任务</span>
		<div class="clear"></div>
	</div>
</div>