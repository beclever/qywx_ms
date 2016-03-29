<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="mian02">
	<div class="workform_list">
		<label>完成情况：</label> 
		<span>&nbsp;${gradebean.workIsFinish}</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>耗时:</label> 
		<span>&nbsp;${gradebean.costTime}分钟</span>
		<div class="clear"></div>
	</div>
<div class="workform_list">
   <label> 保修状态:</label> 
   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
   <div class="clear"></div>
</div>
	<div class="workform_list">
		<label>任务：</label> 
		<span>&nbsp;${gradebean.taskContent } </span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>工作描述:</label> 
		<span>&nbsp;${gradebean.workRemark}</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>升级前状态:</label> 
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>操作系统:</label> 
		<span>&nbsp;${gradebean.beforSjModel.operationSystem }</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label style="width:45%;">操作系统版本:</label> 
		<span style="width:55%;">&nbsp;${gradebean.beforSjModel.osVersion}</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label>ATMC名称:</label> 
		<span>&nbsp;${gradebean.beforSjModel.atmCName }</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label>ATMC版本:</label> 
		<span>&nbsp;${gradebean.beforSjModel.atmCVersion}</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label style="width:45%;">跨平台sp版本:</label> 
		<span style="width:55%;">&nbsp;${gradebean.beforSjModel.atmCSpVersion}</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>升级后状态:</label>
		<div class="clear"></div> 
	</div>
	<div class="workform_list">
		<label>操作系统:</label> 
        <span>&nbsp;${gradebean.afterSjModel.operationSystem }</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label style="width:45%;">操作系统版本:</label> 
		<span style="width:55%;">&nbsp;${gradebean.afterSjModel.osVersion}</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label>ATMC名称:</label> 
		<span>&nbsp;${gradebean.afterSjModel.atmCName }</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label>ATMC版本:</label> 
		<span>&nbsp;${gradebean.afterSjModel.atmCVersion}</span>
		<div class="clear"></div>
	</div><div class="workform_list">
		<label style="width:45%;">跨平台sp版本:</label> 
		<span style="width:55%;">&nbsp;${gradebean.afterSjModel.atmCSpVersion}</span>
		<div class="clear"></div>
	</div>
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<span id="task_replace_button_sj${gradebean.taskId}"class="task_replace"> 
				<span id="UGS${gradebean.taskId}" 
					class="task_replace_button_unselected">模块替换</span> 
				<span id="UGE${gradebean.taskId}"
					class="task_replace_button_unselected">零件替换</span>
			</span>
		</legend>
		<div class="record_div" id="DivUGS${gradebean.taskId}" style="display: none">
			<c:forEach items="${gradebean.importModel}" var="taskspare">
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
		<div class="record_div" id="DivUGE${gradebean.taskId}"
			style="display: none">
			<c:forEach items="${gradebean.subModel}" var="taskelement">
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
	</fieldset>
</div>