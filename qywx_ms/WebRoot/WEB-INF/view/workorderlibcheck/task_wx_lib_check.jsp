<%@ page pageEncoding="utf-8" language="java" %>
<div class="mian02">
	 <div class="workform_list">
	    <label>完成情况:</label>
	    <span>&nbsp;${taskbean.workIsFinish}</span>
		<div class="clear"></div>
	</div>
	 <div class="workform_list">
	    <label><font color="red">*</font>耗时:</label>
	    <span>
	       &nbsp;${taskbean.costTime}分钟
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
	        &nbsp;${taskbean.taskContent}
	    </span>
	    <div class="clear"></div>
	</div>
	 <div class="workform_list">
	    <label><font color="red">*</font>工作描述:</label>
	    <span>
	       &nbsp;${taskbean.workRemark}
	    </span>
	    <div class="clear"></div>
	</div>
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<span id="task_replace_button" class="task_replace">
			    <span id="faultlocationpath" class="task_replace_button_unselected">故障部位</span>
			    <span id="maintainspare" class="task_replace_button_unselected">模块替换</span>
			    <span id="maintainelement" class="task_replace_button_unselected">零件替换</span>
			</span>
		</legend>
		<div class="record_div" id="maintain1" style="display: none">
		    <c:forEach items="${taskbean.deviceHitch}" var="taskfault">
		        <div class='task_box02'>
		            <span><font color='blue'>故障部位:</font>${taskfault.problemPartRemark}</span>
		            <span><font color='blue'>故障描述:</font>${taskfault.troubleRemark}</span>
		            <span><font color='blue'>故障原因:</font>${taskfault.troubleReasonRemark}</span>
		            <span><font color='blue'>处理方法:</font>${taskfault.processRemark}</span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="maintain2" style="display: none">
		    <c:forEach items="${taskbean.importModel}" var="taskspare">
		        <div class='task_box02'>
		            <span><font color='blue'>原模块:</font>${taskspare.oldSerialName}</span>
		            <span><font color='blue'>物料编码:</font>${taskspare.oldMaterialCode}</span>
		            <span><font color='blue'>条码编码:</font>${taskspare.oldSerialNumber}</span>
		            <span><font color='blue'>新模块:</font>${taskspare.newSerialName}</span>
		            <span><font color='blue'>物料编码:</font>${taskspare.newMaterialCode}</span>
		            <span><font color='blue'>条码编码:</font>${taskspare.newSerialNumber}</span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="maintain3" style="display: none">
		    <c:forEach items="${taskbean.subModel}" var="taskelement">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料名称:</font>${taskelement.oldSerialName}</span>
		            <span><font color='blue'>物料编码:</font>${taskelement.oldMaterialCode}</span>
		            <span><font color='blue'>新物料名称:</font>${taskelement.newSerialName}</span>
		            <span><font color='blue'>物料编码:</font>${taskelement.newMaterialCode}</span>
		            <span><font color='blue'>使用方法:</font>${taskelement.useMethod}</span>
		            <span><font color='blue'>使用数量:</font>${taskelement.quantity}</span>
		        </div>
		    </c:forEach>
		</div> 
		<div class="clear"></div>   
	</fieldset>
	

</div>