<%@ page pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach items="${xjlist}" var="xjbean" varStatus="stas">
	<div class="tagContent selectTag flowmargin-top" id="${xjbean.taskType}${xjbean.taskId}Div" style="display: none;">
		<div class="mian02">
			<div class="workform_list">
			    <label>完成情况:</label>
			    <span>&nbsp;${xjbean.workIsFinish}</span>
			    <div class="clear"></div>
			</div>
			<div class="workform_list">
			    <label><font color="red">*</font>耗时:</label>
			    <span>
			        &nbsp;${xjbean.costTime}分钟
			    </span>
			    <div class="clear"></div>
			</div>
<div class="workform_list">
   <label> 保修状态:</label> 
   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
   <div class="clear"></div>
</div>
			<div class="workform_list">
			    <label>任务内容:</label>
			    <span>
			        &nbsp;${xjbean.taskContent}
			    </span>
			    <div class="clear"></div>
			</div>
			<div class="workform_list">
			    <label><font color="red">*</font>巡检类型:</label>
			    <span>&nbsp;${xjbean.installAndRemoveDeviceModel.firstType}</span>
			    <c:if test="${xjbean.installAndRemoveDeviceModel.secondType!=''}">
			    	<span>&nbsp;${xjbean.installAndRemoveDeviceModel.secondType}</span>
			    </c:if>
			    <div class="clear"></div>
			</div>
			<div class="workform_list" id="xjdivcontentemark" style="display:none">
			    <label>类型说明:</label>
			    <span>
			        &nbsp;${xjbean.installAndRemoveDeviceModel.explain}
			    </span>
			    <div class="clear"></div>
			</div>
			<div class="workform_list" id="xjdivcontentremark">
			    <label> 巡检内容如下:</label>
			    <div class="clear"></div>
			</div>
			<c:choose>
				<c:when test="${null != xjbean.installAndRemoveDeviceModel.sjTypeDetails && fn:length(xjbean.installAndRemoveDeviceModel.sjTypeDetails) > 0}">
					<c:forEach items="${xjbean.installAndRemoveDeviceModel.sjTypeDetails}" var="sjDetail" varStatus="stas">
					       <c:if test="${sjDetail.sjTypeid }"></c:if>
						<div class="workform_list">
						    <label> ${sjDetail.sjTypeid }:</label>
						    <span>
						    	<c:choose>
								    <c:when test="${'软件检查' == sjDetail.sjTypeid }">
								    	${sjDetail.sjContext }
							    	</c:when>
							    	<c:when test="${'硬件检查' == sjDetail.sjTypeid }">
								        ${sjDetail.sjContext }
							    	</c:when>
							    	<c:when test="${'环境检查' == sjDetail.sjTypeid }">
							        	${sjDetail.sjContext }
							    	</c:when>
							    	<c:when test="${'其他' == sjDetail.sjTypeid }">
								        ${sjDetail.sjContext }
							    	</c:when>
						    	</c:choose>
						    </span>
						    <div class="clear"></div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
				
				<div class="workform_list">
					    <label> 软件检查:</label>
					    <span>
					    	&nbsp;
					    </span>
					    <div class="clear"></div>
					</div>
					<div class="workform_list">
					    <label> 硬件检查:</label>
					    <span>
					        &nbsp;
					    </span>
					    <div class="clear"></div>
					</div>
					<div class="workform_list">
					    <label> 环境检查:</label>
					    <span>
					        &nbsp;
					    </span>
					    <div class="clear"></div>
					</div>
					<div class="workform_list">
					    <label> 其他:</label>
					    <span>
					        &nbsp;
					    </span>
					    <div class="clear"></div>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="workform_list">
			    <label><font color="red">*</font>工作描述:</label> 
			    <span>
			       &nbsp;${xjbean.workRemark}
			    </span>
			    <div class="clear"></div>
			</div>
			<fieldset class="fieldset_style">
				<legend class="legend_font">
					<span id="task_replace_button_inspection${xjbean.taskId}" class="task_replace">
					    <span id="inspectionspare${xjbean.taskId}" class="task_replace_button_unselected"  onclick="XJspareshow('${xjbean.taskId}')">模块替换</span>&nbsp;
					    <span id="inspectionelement${xjbean.taskId}" class="task_replace_button_unselected" onclick="XJelementshow('${xjbean.taskId}')">零件替换</span>
					</span>
				</legend>
				<div class="record_div" id="inspection1${xjbean.taskId}" style="display: none">
				    <c:forEach items="${xjbean.importModel}" var="taskspare">
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
				<div class="record_div" id="inspection2${xjbean.taskId}" style="display: none">
				    <c:forEach items="${xjbean.subModel}" var="taskelement">
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
	</div>
</c:forEach>
