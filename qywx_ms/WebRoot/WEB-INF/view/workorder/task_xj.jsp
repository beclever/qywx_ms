<%@ page language="java" pageEncoding="utf-8"%>
<div class="mian02">
	<div class="workform_list" >
	    <label>完成情况:</label>
	    <span> 
			<input type="radio" name="${taskbean.taskType}finish${taskbean.taskId}" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 
			<input type="radio" name="${taskbean.taskType}finish${taskbean.taskId}" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
		</span>
	    <div class="clear"></div>
	    <script type="text/javascript">
	    stateAndInputs[stateAndInputs.length] = {inputName:'${taskbean.taskType}finish${taskbean.taskId}',state:'${taskbean.workIsFinish}',taskType:'${taskbean.taskType}${taskbean.taskId}'}; 
    	</script>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>耗时(分钟):</label>
	    <span>
	        <input type="number"  name="XJ${taskbean.taskId}time" id="XJ${taskbean.taskId}time"  oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" value="${taskbean.costTime}"/>
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
	        ${taskbean.taskContent}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>巡检类型:</label>
	    <span>
	        <select class="taskOrder_inputwidth" onchange="xjselectInfo(this,${taskbean.taskId})" name="firstType${taskbean.taskId}" id="XJ${taskbean.taskId}types">
	            <option>请选择类型</option>
	            <option <c:if test="${taskbean.installAndRemoveDeviceModel.firstType=='合同约定巡检'}">selected=true</c:if>>
	                合同约定巡检
	            </option>
	            <option <c:if test="${taskbean.installAndRemoveDeviceModel.firstType=='客户要求巡检'}">selected=true </c:if>>
	                客户要求巡检
	            </option>
	        </select>
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list" id="xjdivcontent${taskbean.taskId}" 
	style="display:<c:if test="${taskbean.installAndRemoveDeviceModel.firstType=='客户要求巡检'}">visibility </c:if><c:if test="${taskbean.installAndRemoveDeviceModel.firstType!='客户要求巡检'}">none </c:if>">
	    <label> &nbsp; &nbsp;</label>
	    <span>
	        <select class="taskOrder_inputwidth" name="secondType${taskbean.taskId}">
	            <option></option>
	            <option
	                    <c:if test="${taskbean.installAndRemoveDeviceModel.secondType=='节前巡检'}">selected=true</c:if> >节前巡检
	            </option>
	            <option <c:if test="${taskbean.installAndRemoveDeviceModel.secondType=='项目保障巡检'}">selected=true</c:if>>
	                项目保障巡检
	            </option>
	            <option <c:if test="${taskbean.installAndRemoveDeviceModel.secondType=='专项安全巡检'}">selected=true</c:if>>
	                专项安全巡检
	            </option>
	        </select>
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list" id="xjdivcontentemark${taskbean.taskId}" style="display:<c:if test="${taskbean.installAndRemoveDeviceModel.firstType=='客户要求巡检'}">visibility </c:if><c:if test="${taskbean.installAndRemoveDeviceModel.firstType!='客户约定巡检'}">none </c:if>">
	    <label>类型说明:</label>
	    <span>
	        <input type="text" name="explain${taskbean.taskId}" id="explain${taskbean.taskId}" value="${taskbean.installAndRemoveDeviceModel.explain}"/>
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list" id="xjdivcontentremark${taskbean.taskId}">
	    <label> 巡检内容</label>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label> 软件检查:</label>
	    <span>
	        <input type="text" name="softcheck${taskbean.taskId}" id="softcheck${taskbean.taskId}" value="${taskbean.installAndRemoveDeviceModel.sjTypeDetails[0].sjContext}"/><!-- ${softcheck} -->
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label> 硬件检查:</label>
	    <span>
	        <input type="text" name="hardcheck${taskbean.taskId}" id="hardcheck${taskbean.taskId}" value="${taskbean.installAndRemoveDeviceModel.sjTypeDetails[1].sjContext}"/><!-- ${hardcheck} -->
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label> 环境检查:</label>
	    <span>
	        <input type="text" name="enviromentcheck${taskbean.taskId}" id="enviromentcheck${taskbean.taskId}" value="${taskbean.installAndRemoveDeviceModel.sjTypeDetails[2].sjContext}"/><!-- ${enviromentcheck} -->
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label> 其他:</label>
	    <span>
	        <input type="text" name="othercheck${taskbean.taskId}" id="othercheck${taskbean.taskId}" value="${taskbean.installAndRemoveDeviceModel.sjTypeDetails[3].sjContext}"/><!-- ${othercheck} -->
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>工作描述:</label> 
	    <span>
	        <textarea name="XJworkRemark${taskbean.taskId}"  id="XJ${taskbean.taskId}workRemark">${taskbean.workRemark}</textarea>
	    </span>
	    <div class="clear"></div>
	</div>
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button_inspection${taskbean.taskId}" class="task_replace">
			    <span id="inspectionspare${taskbean.taskId}" class="task_replace_button_unselected" onclick="XJspareshow('${taskbean.taskId}')">模块替换</span>&nbsp;
			    <span id="inspectionelement${taskbean.taskId}" class="task_replace_button_unselected" onclick="XJelementshow('${taskbean.taskId}')">零件替换</span>
			    <span id="xjTaskMove${taskbean.taskId}" onclick="removeXJtask('${taskbean.taskId}')" class="task_replace_button_unselected">移除任务</span>
			</div>
		</legend>
		<div class="record_div" id="inspection1${taskbean.taskId}" style="display: none">
		    <a class="logistics_add" onclick="oldspareDialog('inspection1${taskbean.taskId}')">
		       	<img class="logistics_add_icon" src="${ctx_path }/pub/images/icon_add.png"/>添加
		     </a>
		    <c:forEach items="${taskbean.importModel}" var="taskspare">
		        <div class='task_box02'>
		            <span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
		                <input type='hidden' value="${taskspare.oldSerialName}" name="inspection1${taskbean.taskId}oldSerialName"/>
		                <input type='hidden' value="${taskspare.oldSerialNumber}" name="inspection1${taskbean.taskId}oldSerialNumber"/>
		                <input type='hidden' value="${taskspare.oldMaterialCode}" name="inspection1${taskbean.taskId}oldMaterialCode"/>
		                <input type='hidden' value="${taskspare.oldHardwareVersion}" name="inspection1${taskbean.taskId}oldHardwareVersion"/>
		                <input type='hidden' value="${taskspare.oldSoftwareVersion}" name="inspection1${taskbean.taskId}oldSoftwareVersion"/>
		                <input type='hidden' value="${taskspare.equipmentConfigId}" name="inspection1${taskbean.taskId}equipmentConfigId"/>
		                <input type='hidden' value="${taskspare.newSerialName}" name="inspection1${taskbean.taskId}newSerialName"/>
		                <input type='hidden' value="${taskspare.newMaterialCode}" name="inspection1${taskbean.taskId}newMaterialCode"/>
		                <input type='hidden' value="${taskspare.newSerialNumber}" name="inspection1${taskbean.taskId}newSerialNumber"/>
		                <input type='hidden' value="${taskspare.newHardwareVersion}" name="inspection1${taskbean.taskId}newHardwareVersion"/>
		                <input type='hidden' value="${taskspare.newSoftwareVersion}" name="inspection1${taskbean.taskId}newSoftwareVersion"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskspare.newSerialName}</span>
		            <span class='logistics_close' onclick='WSSpare(this)'><img src="${ctx_path }/pub/images/delete.png"/></span>
		        </div>
		    </c:forEach>
		</div>
		<div class="record_div" id="inspection2${taskbean.taskId}" style="display: none">
		    <a class="logistics_add" onclick="newspareDialog('inspection2${taskbean.taskId}')">
		    	<img class="logistics_add_icon" src="${ctx_path }/pub/images/icon_add.png"/>添加
		    </a>
		    <c:forEach items="${taskbean.subModel}" var="taskelement">
		        <div class='task_box02' id='aaa'>
		            <span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
		                <input type='hidden' value="${taskelement.newSerialName}" name="inspection2${taskbean.taskId}newSerialName"/>
		                <input type='hidden' value="${taskelement.oldSerialName}" name="inspection2${taskbean.taskId}oldSerialName"/>
		                <input type='hidden' value="${taskelement.quantity}" name="inspection2${taskbean.taskId}nums"/>
		                <input type='hidden' value="${taskelement.newMaterialCode}" name="inspection2${taskbean.taskId}newMaterialCode"/>
		                <input type='hidden' value="${taskelement.oldMaterialCode}" name="inspection2${taskbean.taskId}oldMaterialCode"/>
		                <input type='hidden' value="${taskelement.newSoftwareVersion}" name="inspection2${taskbean.taskId}newSoftwareVersion"/>
		                <input type='hidden' value="${taskelement.newHardwareVersion}" name="inspection2${taskbean.taskId}newHardwareVersion"/>
		                <input type='hidden' value="${taskelement.useMethod}" name="inspection2${taskbean.taskId}useMethod"/>
		            </span>
		            <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
		            <span><font color='blue'>数量:</font>${taskelement.quantity}</span>
		            <span class='logistics_close' onclick='WSSpare(this)'><img src="${ctx_path }/pub/images/delete.png"/></span>
		        </div>
		    </c:forEach>
		</div>
	</fieldset>
</div>
