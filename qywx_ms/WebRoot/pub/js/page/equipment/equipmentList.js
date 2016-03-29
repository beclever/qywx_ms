  /** @include "common.js"; */
  
  /*
  document.addEventListener('DOMContentLoaded',function(){
    setTimeout(function(){
      utils.onload(selectSaEquipmentList());
    },200);
  },false);
  */
  $(function(){
  	utils.onload(selectSaEquipmentList);
  	selectSaEquipmentList(true);
  });
  
  	//业务菜单返回导航菜单
	function backNavMenu(){
		window.android_backNav.exitActivity();
	}
	
  /**
   * 获取备件信息
   */
  function selectSaEquipmentList(isReload){
    var serialNumber = $("#equipmentSerialNumber").val();//设备序列号
    var customerName = $("#equipmentCustomerName").val();//客户名称
    var branchName = $("#equipmentBranchName").val();//网点名称
    var installAddress = $("#equipmentInstallAddress").val();//安装地址
    var ATMNumber = $("#equipmentATMNumber").val();//ATM号码
    var pageNum=$("#pageNum").val();

    //清除 设备序列号 条件
    if(serialNumber =='设备序列号'){ serialNumber ='';}
    if(customerName =='客户名称'){customerName ='';}				//清除 客户名称 条件
    if(branchName =='网点名称'){branchName ='';}				//清除 网点名称 条件
    if(installAddress =='安装地址'){installAddress='';}		//清除 安装地址 条件
    if(ATMNumber=='ATM号'){ATMNumber='';}					//清除 ATM号 条件
    var index = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
    utils.updateList(basePath+"/cp/equipment/pagelist.do",{serialNumber:serialNumber,customerName:customerName
      ,branchName:branchName,installAddress:installAddress,ATMNumber:ATMNumber,formtype:"qywx"},function(dataList){
      var html="";
     if(isReload) $('#thelist').html("");       //清空之前的数据 
      $(dataList).each(function(i,data){
        if(null!=data){
          var url=basePath+"/cp/equipment/detail.do?serialNumber="+data.serialNumber+"&dateTime="+new Date().getTime();
          html+="<div class='new_title02'>"+
                 "<div class='new_title02_arrow'>&nbsp;</div>"+
                  "<a href='"+url+"'>"+
                      "<samp>"+data.customsName+"</samp>"+
                      "<span>安装地址："+getNoNullValue(data.installAddress)+"</span>"+
                      "<span>序列号："+getNoNullValue(data.serialNumber)+"/ATM号："+getNoNullValue(data.atmNumber)+"</span>"+
                  "</a>"+
                 "<div class='clear'></div>"+
              "</div>";
        }
      });
      $('#thelist').append(html);
      $("#pageNum").val(Number(pageNum)+1);
      layer.close(index);
    },null,isReload);
  }
  
  	//当数据为空是，不显示
	function getNoNullValue(str){
		var result="";
		if(str !=null){
			result = str;
		}
		return result;
	}

	//初始化加载
	$(document).ready(function() {
		pressSerialNumber();
		pressBranchName();
		pressInstallAddress();
		pressATMNumber();
	});
	
	//设备序列号 填写时将字体改为 黑色
	function pressSerialNumber(){
	    var serialNumber = $("#equipmentSerialNumber").val();//设备序列号
	    if(!serialNumber){
	    	$("#spanSerialNumber").hide();
	    }else{
	    	$("#spanSerialNumber").show();
	    }
	}
		
	//客户名称 填写时将字体改为 黑色
	function pressCustomerName(){
		var customerName = $("#equipmentCustomerName").val();//客户名称
		
		if(!customerName){
			$("#spanCustomerName").hide();
		}else{
			$("#spanCustomerName").show();
		}
	}
	
	//网点名称 填写时将字体改为 黑色
	function pressBranchName(){
	    var branchName = $("#equipmentBranchName").val();//网点名称

	    if(!branchName){
	    	$("#spanBranchName").hide();
	    }else{
	    	$("#spanBranchName").show();
	    }
	}
	
	//安装地址 填写时将字体改为 黑色
	function pressInstallAddress(){
	    var installAddress = $("#equipmentInstallAddress").val();//安装地址

	    if(!installAddress){
	    	$("#spanInstallAddress").hide();
	    }else{
	    	$("#spanInstallAddress").show();
	    }
	}
	
	//ATM号码 填写时将字体改为 黑色
	function pressATMNumber(){
	    var ATMNumber = $("#equipmentATMNumber").val();//ATM号

	    if(!ATMNumber){
	    	$("#spanATMNumber").hide();
	    }else{
	    	$("#spanATMNumber").show();
	    }
	}
	
	//清除 设备序列号 条件
	function cleanSerialNumber(){
		$("#equipmentSerialNumber").val("");
		$("#spanSerialNumber").hide();
	}	
	
	//清除 客户名称 条件
	function cleanCustomerName(){
		$("#equipmentCustomerName").val("");
		$("#spanCustomerName").hide();
	}
	
	//清除 网点名称 条件
	function cleanBranchName(){
		$("#equipmentBranchName").val("");
		$("#spanBranchName").hide();
	}	
	
	//清除 安装地址 条件
	function cleanInstallAddress(){
		$("#equipmentInstallAddress").val("");
		$("#spanInstallAddress").hide();
	}
	
	//清除 ATM号 条件
	function cleanATMNumber(){
		$("#equipmentATMNumber").val("");
		$("#spanATMNumber").hide();
	}