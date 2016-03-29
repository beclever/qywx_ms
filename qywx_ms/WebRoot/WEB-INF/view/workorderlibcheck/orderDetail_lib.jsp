<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="mian02">
	<div class="workform_list">
		<label>纸质工单：</label> <span>${requestScope.baseInfo.iniPaperCode }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>工单编号：</label>${requestScope.baseInfo.poNumber }<span></span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>序列号：</label> <span>${requestScope.baseInfo.serialNumber }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>整机型号：</label> <span id="equipmentModel">${requestScope.baseInfo.equipmentModel }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>客户名称：</label> <span id="customerName">${requestScope.baseInfo.customerName }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>网点名称：</label> <span id="branchName">${requestScope.baseInfo.branchName }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>安装地址：</label> <span id="installAddress">${requestScope.baseInfo.installAddress }</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label>工程师：</label> <span>${requestScope.baseInfo.engineerName }</span>
		<div class="clear"></div>
	</div>
</div>