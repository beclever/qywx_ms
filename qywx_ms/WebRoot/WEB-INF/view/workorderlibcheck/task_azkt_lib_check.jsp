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
	<div class="workform_list">
	    <label>客户:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.customerName}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>网点名称:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.branchName}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装类型:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.installType}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装方式:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.installModel}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label><font color="red">*</font>省份:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.province}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>城市:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.city}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>装机属性:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.city}</span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label>atm管理员:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.installProperty}</span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label style="width:50%">atm管理员电话:</label>
	    <span style="width:50%">&nbsp;${taskbean.installAndRemoveDeviceModel.ATMManagerTel}</span>
	    <div class="clear"></div>
	</div>
	<div style="display:none" class="workform_list">
	    <label style="width:50%">网点负责人电话:</label>
	    <span style="width:50%">&nbsp;${taskbean.installAndRemoveDeviceModel.branchPrincipalTel}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>操作系统:</label>
	    <span>&nbsp;${taskbean.installAndRemoveDeviceModel.operationSystem}</span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>系统版本:</label>
	    <span>
	       &nbsp;${taskbean.installAndRemoveDeviceModel.osVersion}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc名称:</label>
	    <span>
	    	&nbsp;${taskbean.installAndRemoveDeviceModel.ATMCName}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atmc版本:</label>
	    <span>
	       &nbsp;${taskbean.installAndRemoveDeviceModel.ATMCVersion}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label style="width:45%">atmc跨平台sp:</label>
	    <span style="width:55%">
	       &nbsp;${taskbean.installAndRemoveDeviceModel.ATMCSpVersion}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>加密方式:</label>
	    <span>
	    	&nbsp;${taskbean.installAndRemoveDeviceModel.encryptModel}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>atm号:</label>	
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.ATMNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>银行号:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.bankNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label style="width:38%">支行终端号:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.bankTerminalNumber}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label style="width:45%">网络连接协议:</label>
	    <span style="width:55%">
	        &nbsp;${taskbean.installAndRemoveDeviceModel.netProtocol}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>本机ip:</label>
	    <span>
	       &nbsp;${taskbean.installAndRemoveDeviceModel.localIP}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>p端ip:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.pip}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>网关:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.gateway}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>子掩码:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.subnetMask}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label style="width:45%">日均交易金额:</label>
	    <span style="width:55%">
	    	&nbsp;${taskbean.installAndRemoveDeviceModel.dailyAverageNum}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label style="width:45%">日均交易笔数:</label>
	    <span style="width:55%">
	    	&nbsp;${taskbean.installAndRemoveDeviceModel.dailyAverageAmount}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>灰尘:</label>
	    <span>
	     &nbsp;${taskbean.installAndRemoveDeviceModel.dust}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>温度:</label>
	    <span>
	    &nbsp;${taskbean.installAndRemoveDeviceModel.temperature}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>湿度:</label>
	    <span>
	    &nbsp;${taskbean.installAndRemoveDeviceModel.humidity}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>供电:</label>
	    <span>
	    &nbsp;${taskbean.installAndRemoveDeviceModel.powerSupply}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>防雨:</label>
	    <span>
	    &nbsp;${taskbean.installAndRemoveDeviceModel.waterproof}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>日晒:</label>
	    <span>
	    &nbsp;${taskbean.installAndRemoveDeviceModel.solarization}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>安装地址:</label>
	    <span>
	       &nbsp;${taskbean.installAndRemoveDeviceModel.installAddress}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>参考费用:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.referenceCharge}
	    </span>
	    <div class="clear"></div>
	</div>
	<div class="workform_list">
	    <label>参考线路:</label>
	    <span>
	        &nbsp;${taskbean.installAndRemoveDeviceModel.consultWay}
	    </span>
	    <div class="clear"></div>
	</div>
</div>