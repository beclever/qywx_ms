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
	        <input type="number"  name="ZWtime" id="ZWtime"  oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" value="${taskbean.costTime}"/>
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
	        <textarea name="ZWworkRemark" id="ZWworkRemark">${taskbean.workRemark}</textarea>
	    </span>
	    <div class="clear"></div>
	</div>
	<!--
	<input name="" class="btn3 line_height2" type="button" value="模块替换" id="accountspare"/>&nbsp;
	<input name="" class="btn3 line_height2" type="button" value="零件替换" id="accountelement"/>
	-->
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button_account" class="task_replace">
			    <span id="accountspare" class="task_replace_button_unselected">模块替换</span>&nbsp;
			    <span id="accountelement" class="task_replace_button_unselected">零件替换</span>
			    <!-- <span id="cwclTaskMove" class="task_replace_button_unselected">移除任务</span> -->
			</div>
		</legend>
		<div class="record_div" id="account1" style="display: none">
		    <a class="logistics_add"  onclick="oldspareDialog('account1')">
		       <img class="logistics_add_icon" src="${ctx_path}/pub/images/icon_add.png"/>添加
		    </a>
		    <c:forEach items="${taskbean.importModel}" var="taskspare">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
		                <input type='hidden' value="${taskspare.oldSerialName}" name="account1oldSerialName"/>
		                <input type='hidden' value="${taskspare.oldSerialNumber}" name="account1oldSerialNumber"/>
		                <input type='hidden' value="${taskspare.oldMaterialCode}" name="account1oldMaterialCode"/>
		                <input type='hidden' value="${taskspare.oldHardwareVersion}" name="account1oldHardwareVersion"/>
		                <input type='hidden' value="${taskspare.oldSoftwareVersion}" name="account1oldSoftwareVersion"/>
		                <input type='hidden' value="${taskspare.equipmentConfigId}" name="account1equipmentConfigId"/>
		                <input type='hidden' value="${taskspare.newSerialName}" name="account1newSerialName"/>
		                <input type='hidden' value="${taskspare.newMaterialCode}" name="account1newMaterialCode"/>
		                <input type='hidden' value="${taskspare.newSerialNumber}" name="account1newSerialNumber"/>
		                <input type='hidden' value="${taskspare.newHardwareVersion}" name="account1newHardwareVersion"/>
		                <input type='hidden' value="${taskspare.newSoftwareVersion}" name="account1newSoftwareVersion"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskspare.newSerialName}</span>
		            <span class='logistics_close' onclick='WSSpare(this)'><img src="${ctx_path }/pub/images/delete.png"/></span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="account2" style="display: none">
		    <a class="logistics_add" onclick="newspareDialog('account2')">
		  		<img class="logistics_add_icon" src="${ctx_path}/pub/images/icon_add.png"/>添加
		    </a>
		    <c:forEach items="${taskbean.subModel}" var="taskelement">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
		                <input type='hidden' value="${taskelement.newSerialName}" name="account2newSerialName"/>
		                <input type='hidden' value="${taskelement.oldSerialName}" name="account2oldSerialName"/>
		                <input type='hidden' value="${taskelement.quantity}" name="account2nums"/>
		                <input type='hidden' value="${taskelement.newMaterialCode}" name="account2newMaterialCode"/>
		                <input type='hidden' value="${taskelement.oldMaterialCode}" name="account2oldMaterialCode"/>
		                <input type='hidden' value="${taskelement.newSoftwareVersion}" name="account2newSoftwareVersion"/>
		                <input type='hidden' value="${taskelement.newHardwareVersion}" name="account2newHardwareVersion"/>
		                <input type='hidden' value="${taskelement.useMethod}" name="account2useMethod"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
		            <span><font color='blue'>数量:</font>${taskelement.quantity}</span>
		            <span class='logistics_close' onclick='WSSpare(this)'><img src="${ctx_path }/pub/images/delete.png"/></span>
		        </div>
		    </c:forEach>
		</div>
	</fieldset>
	<br/>
	<div class="task_replace">
		<span id="cwclTaskMove" class="modal_ok" style="background: #4286f5; color: #fff;width: 33%">移除任务</span>
		<div class="clear"></div>
	</div>
</div>