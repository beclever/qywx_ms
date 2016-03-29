function sendOldspare() {
	var serialNumber = $("#serialNumber").val();
	var divname = $("#divname").val();
	var workformId = $('#workformId').val();
	var poNumber = $('#poNumber').val();
	window.location.href = basePath
			+ '/cp/module/oldsparepath.do?serialNumber=' + serialNumber
			+ "&divname=" + divname + "&workformId="+workformId + "&poNumber="+poNumber;
}

function sendnewspare() {

	var oldSerialName = $("#oldSerialName").val();
	var oldSerialNumber = $("#oldSerialNumber").val();
	var oldMaterialCode = $("#oldMaterialCode").val();
	var oldHardwareVersion = $("#oldHardwareVersion").val();
	var oldSoftwareVersion = $("#oldSoftwareVersion").val();
	var equipmentConfigId = $("#equipmentConfigId").val();
	var poNumber = $("#poNumber").val();
	
	var divname = $("#divname").val();

	if (oldSerialName == "") {
		$("#oldSerialName").val()
		return;

	} else {
		var oldSerialName = $("#oldSerialName").val();
		var oldSerialNumber = $("#oldSerialNumber").val();
		var oldMaterialCode = $("#oldMaterialCode").val();
		var oldHardwareVersion = $("#oldHardwareVersion").val();
		var oldSoftwareVersion = $("#oldSoftwareVersion").val();
		var equipmentConfigId = $("#equipmentConfigId").val();
		var poNumber = $("#poNumber").val();
		var workformId = $('#workformId').val();
		var divname = $("#divname").val();
		var serialNumber = $("#serialNumber").val();
		if (oldSerialName == "") {
			$("#oldSerialName").val();
			parent.layer.msg('请先选择原模块');
			return;

		} else {
			//oldSerialName = encodeURI(encodeURI(oldSerialName));
			window.location.href = basePath
					+ '/cp/module/newsparepath.do?oldSerialName='
					+ oldSerialName + "&oldSerialNumber=" + oldSerialNumber
					+ "&oldMaterialCode=" + oldMaterialCode
					+ "&oldHardwareVersion=" + oldHardwareVersion
					+ "&oldSoftwareVersion=" + oldSoftwareVersion + "&divname="
					+ divname + "&equipmentConfigId=" + equipmentConfigId
					+ "&oldModuleType=" + oldModuleType+"&poNumber="+poNumber+"&workformId="+workformId+"&serialNumber="+serialNumber;
		}
	}
}

function sendWorkspareOK() {
	var divname = $("#divname").val();
	var oldSerialName = $("#oldSerialName").val();
	var oldSerialNumber = $("#oldSerialNumber").val();
	var oldMaterialCode = $("#oldMaterialCode").val();
	var oldHardwareVersion = $("#oldHardwareVersion").val();
	var oldSoftwareVersion = $("#oldSoftwareVersion").val();
	var equipmentConfigId = $("#equipmentConfigId").val();
	var newSerialName = $("#newSerialName").val();
	var newMaterialCode = $("#newMaterialCode").val();
	var newSerialNumber = $("#newSerialNumber").val();
	var newHardwareVersion = $("#newHardwareVersion").val();
	var newSoftwareVersion = $("#newSoftwareVersion").val();
	var poNumber = $('#poNumber').val();
	if (oldMaterialCode == "") {
		layer.alert("原模块'中物料编码'的不能为空");
		return;
	}
	if (newMaterialCode == "") {
		layer.alert("新模块'中物料编码'的不能为空");
		return;
	}
	newModuleType  = newModuleType.replace(/\s+/g,"");
	oldModuleType=oldModuleType.replace(/\s+/g,"");
	if (newModuleType != oldModuleType) {
		
		layer.alert("'原模块'中物料类型和'新模块'中的物料类型要一致!");
		return;
	} else {
		// modify by zt 20150319
		// modify by Rache 20150604
		var args = divname + "-,-" + oldSerialName + "-,-" + oldSerialNumber + "-,-"
				+ oldMaterialCode + "-,-" + oldHardwareVersion + "-,-"
				+ oldSoftwareVersion + "-,-" + equipmentConfigId + "-,-"
				+ newSerialName + "-,-" + newMaterialCode + "-,-" + newSerialNumber
				+ "-,-" + newHardwareVersion + "-,-" + newSoftwareVersion;

		window.location.href = basePath + '/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='
				+ poNumber + "&showInfoargs=" + args+"&typeInfo=workOrderspare";
	}
}
function sendWorkspareCancel() {
	var poNumber = $('#poNumber').val();
	window.location.href = basePath + '/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber=' + poNumber;
}
