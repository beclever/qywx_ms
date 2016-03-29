var utils={};
	utils.getJson=function(url,params){
	  var result=null;
	  $.ajax({
	    url:url,
	    dataType:'json',
	    data:params,
	    async:false,
	    success:function(res){
	      result=res;
	    },
	    complete:function(xhr,textStatus){
	      /*if(xhr.readyState=="4"){
	        alert(xhr.responseText);
	      }*/
	      xhr=null;
	    },
	    error:function(xhr,textStatus,errorThrown){
	      // $("#ajaxMessage").text($(this).text()+" out!")
	      alert("ajax error:"+textStatus);
	      xhr=null;
	    }    
	  });
	  return result;
	};
  
/**將String類型解析為Date類型.    
  parseDate('2006-1-1') return new Date(2006,0,1)    
  parseDate(' 2006-1-1 ') return new Date(2006,0,1)    
  parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16)    
  parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16);    
  parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254)    
  parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254)    
  parseDate('不正確的格式') retrun null    
*/     
utils.stringToDate=function(str){
  if(typeof str == 'string'){      
    var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);      
    if(results && results.length>3)      
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]));       
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);      
    if(results && results.length>6)      
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]));       
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);      
    if(results && results.length>7)      
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]),parseInt(results[7]));       
  }      
  return null;      
};

	/**防止多次重复点击*/
	utils.onclick=function(expression,fn){
	  $(expression).click(function(){
	    var $element=$(this);
	    if($element.attr('disabled')==undefined||$element.attr('disabled')!='disabled'){
	      $element.attr("disabled","true");
	      fn.apply(this,arguments);
	    }
	    else{
	      layer.msg("请耐心等待，不要重复提交！");
	    }
	    setTimeout( function() {$element.removeAttr('disabled');}, 5000);
	  });
	};
	
	/** 上拉动作* */
	function pullUpAction(callback){
	  setTimeout(function(){
	    //获取数据
	    callback&&callback();
	    //刷新列表
	    myScroll.refresh();
	  },1000);
	}
	
	/**
	 * 手势上推加载数据
	 * @return
	 */
	var myScroll,pullUpEl,pullUpOffset;
	function onload(callback){
	  pullUpEl=document.getElementById("pullUp");
	  pullUpOffset=pullUpEl.offsetHeight;
	  myScroll=new iScroll('wrapper',{
        vScrollbar:true,
	    useTransition:true,
	    checkDOMChanges : true,// 是否自动检测内容变化
	    onRefresh:function(){
	      if(pullUpEl.className.match('loading')){
	        pullUpEl.className='';
	        pullUpEl.querySelector('.pullUpLabel').innerHTML='上拉加载更多...';
	      }
	    },
	    onScrollMove:function(){
	      if(this.y<(this.maxScrollY-5)&&!pullUpEl.className.match('flip')){
	        pullUpEl.className='flip';
	        pullUpEl.querySelector('.pullUpLabel').innerHTML='松开刷新...';
	      }else if(this.y>(this.maxScrollY+5)&&pullUpEl.className.match('flip')){
	        pullUpEl.className='';
	        pullUpEl.querySelector('.pullUpLabel').innerHTML='上拉加载更多...';
	        this.maxScrollY=pullUpOffset;
	      }
	    },
	    onScrollEnd:function(){
	      if(pullUpEl.className.match('flip')){
	        pullUpEl.className='loading';
	        pullUpEl.querySelector('.pullUpLabel').innerHTML='数据加载中...';
	        pullUpAction(callback);
	      }
	    }
	  });
	  setTimeout(function(){
	    document.getElementById('wrapper').style.left='0';
	  },800);
	  
	}
	utils.onload=onload;
	document.addEventListener('touchmove',function(e){
	  e.preventDefault();
	},false);
	document.addEventListener('DOMContentLoaded', function () { try{setTimeout(loaded, 200);}catch(e){} }, false);

	
	
	utils.updateList=function(listurl,param,successfn,errorfn,isReload){
    if(isReload){
      $('#pageNum').val(1);
      myScroll.refresh();
    }
	  var pageNum=parseInt($('#pageNum').val())||1;//页码
	  var index = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
	  $.ajax({
	    type:"POST",
	    url:listurl,
	    data:$.extend({pageNum:pageNum},param),
	    dataType:'json',
	    cache:false,
	    success:function(dataList){
	      successfn&&successfn(dataList);
	      layer.close(index);
	      //增加页数
        if(dataList&&dataList.length>0){
          $('#pageNum').val(pageNum+1);
        }else{
        	if(isReload){
        		layer.msg("暂无数据");
        	}else{
        		layer.msg("暂无更多数据");
        	}
        	$('#pageNum').val(pageNum+1);
        }
	    },
	    error:errorfn||function(data){
	    	layer.close(index);
        	if(isReload){
        		$('#thelist').html("");       //清空之前的数据 
        		layer.msg("暂无数据");
        	}else{
        		layer.msg("暂无更多数据");
        	}
	    }
	  });
	}