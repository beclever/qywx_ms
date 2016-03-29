<%@ page language="java" pageEncoding="utf-8"%>
<div class="mian02">
	<div class="workform_list">
		<label>完成情况:</label> <span> <input type="radio"
			name="${taskbean.taskType}finish" value="是" <c:if test="${taskbean.workIsFinish=='是' || taskbean.workIsFinish == null}"> checked="checked"</c:if> />是 <input type="radio"
			name="${taskbean.taskType}finish" value="否" <c:if test="${taskbean.workIsFinish=='否'}"> checked="checked"</c:if> />否
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
			type="number" type="text" name="BYtime" id="BYtime"
			oninput="OnInput(event)" onpropertychange="OnPropChanged(event)"
			value="${taskbean.costTime}" />
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
	   <label> 保修状态:</label> 
	   <span>${taskbean.taskAttributeOption.serverStatus.defaultName}</span>
	   <div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>任务:</label> <span> ${taskbean.taskContent } </span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>工作描述:</label> <span> <textarea
				name="BYworkRemark" id="BYworkRemark">${taskbean.workRemark}</textarea>
		</span>
		<div class="clear"></div>
	</div>
	<fieldset class="fieldset_style">
		<legend class="legend_font">
			<div id="task_replace_button_goodmaintain" class="task_replace">
				<span id="goodmaintainspare" class="task_replace_button_unselected">模块替换</span>
				<span id="goodmaintainelement"
					class="task_replace_button_unselected">零件替换</span>
				<%-- <c:if test="${taskbean.isRemove =='true'}">
					<span id="byTaskMove" class="task_replace_button_unselected">移除任务</span>
				</c:if> --%>
			</div>
		</legend>
		<div class="record_div" id="goodmaintain1" style="display: none">
			<a class="logistics_add"
				onclick="oldspareDialog('goodmaintain1')">
				<img class="logistics_add_icon"
				src="${ctx_path}/pub/images/icon_add.png" />添加
			</a>
			<!-- ======================================================end ================================= -->

			<c:forEach items="${taskbean.importModel}" var="taskspare">
				<div class='task_box02'>
					<span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
						<input type='hidden' value="${taskspare.oldSerialName}"
						name="goodmaintain1oldSerialName" /> <input type='hidden'
						value="${taskspare.oldSerialNumber}"
						name="goodmaintain1oldSerialNumber" /> <input type='hidden'
						value="${taskspare.oldMaterialCode}"
						name="goodmaintain1oldMaterialCode" /> <input type='hidden'
						value="${taskspare.oldHardwareVersion}"
						name="goodmaintain1oldHardwareVersion" /> <input type='hidden'
						value="${taskspare.oldSoftwareVersion}"
						name="goodmaintain1oldSoftwareVersion" /> <input type='hidden'
						value="${taskspare.equipmentConfigId}"
						name="goodmaintain1equipmentConfigId" /> <input type='hidden'
						value="${taskspare.newSerialName}"
						name="goodmaintain1newSerialName" /> <input type='hidden'
						value="${taskspare.newMaterialCode}"
						name="goodmaintain1newMaterialCode" /> <input type='hidden'
						value="${taskspare.newSerialNumber}"
						name="goodmaintain1newSerialNumber" /> <input type='hidden'
						value="${taskspare.newHardwareVersion}"
						name="goodmaintain1newHardwareVersion" /> <input type='hidden'
						value="${taskspare.newSoftwareVersion}"
						name="goodmaintain1newSoftwareVersion" /> </span> <span><font
						color='blue'>新物料:</font>${taskspare.newSerialName}</span>
					<!--=====start===== modify by zt20141213 control the botton can not press  when the flow is return -->
					<%--<c:choose>
						<c:when test="${null !=isEnable && isEnable=='No'}">
						</c:when>
						<c:otherwise>--%>
							<!--  blow: the source code -->

							<input class="logistics_close" type='image'
								src='${ctx_path}/pub/images/delete.png' onclick='WSSpare(this)' />
						<%--</c:otherwise>
					</c:choose>--%>
					<!-- ======================================================end ================================= -->
				</div>
			</c:forEach>
		</div>
		<div class="record_div" id="goodmaintain2" style="display: none">
			<a class="logistics_add"
				onclick="newspareDialog('goodmaintain2')">
				<img class="logistics_add_icon"
				src="${ctx_path}/pub/images/icon_add.png" />添加
			</a>
			<!-- ======================================================end ================================= -->

			<c:forEach items="${taskbean.subModel}" var="taskelement">
				<div class='task_box02'>
					<span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
						<input type='hidden' value="${taskelement.newSerialName}"
						name="goodmaintain2newSerialName" /> <input type='hidden'
						value="${taskelement.oldSerialName}"
						name="goodmaintain2oldSerialName" /> <input type='hidden'
						value="${taskelement.quantity}" name="goodmaintain2nums" /> <input
						type='hidden' value="${taskelement.newMaterialCode}"
						name="goodmaintain2newMaterialCode" /> <input type='hidden'
						value="${taskelement.oldMaterialCode}"
						name="goodmaintain2oldMaterialCode" /> <input type='hidden'
						value="${taskelement.newSoftwareVersion}"
						name="goodmaintain2newSoftwareVersion" /> <input type='hidden'
						value="${taskelement.newHardwareVersion}"
						name="goodmaintain2newHardwareVersion" /> <input type='hidden'
						value="${taskelement.useMethod}" name="goodmaintain2useMethod" />
					</span> <span><font color='blue'>新物料:</font>${taskelement.newSerialName}</span>
					<span><font color='blue'>数量:</font>${taskelement.quantity}</span>
					<input class="logistics_close" type='image'
						src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)' />
					<!-- ======================================================end ================================= -->
				</div>
			</c:forEach>
		</div>
	</fieldset>
	<br/><br/>
	<div class="task_replace">
		<span id="byTaskMove" class="modal_ok" style="background: #4286f5; color: #fff;width: 33%">移除任务</span>
		<div class="clear"></div>
	</div>
</div>