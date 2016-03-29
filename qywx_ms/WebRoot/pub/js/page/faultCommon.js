var saveFaultInfos = function(args1,args2){
    $.ajax({
        type:"post",
        url:basePath+"/cp/fault/saveFaultInfos.do",
        dataType:"text",
        data:{arg1:args1,arg2:args2},
       
        success:function(data){
        	if(data=='success'){
        		window.location.href = basePath+'/cp/fault/faultIndex.do';
        	}
        },
        error:function(xhr,type,exception){
        	alert(xhr+type+exception);
        }
    });
    

}