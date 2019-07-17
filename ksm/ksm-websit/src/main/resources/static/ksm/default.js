//一般直接写在一个js文件中
layui.define(['layer','element','table','tree'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,element=layui.element
  ,table=layui.table
  ,tree=layui.tree;

  $.ajax(
            {
                type: 'GET',
                url: "http://localhost:8080/ksm/admin//showMenuTree",
                dataType: 'json', 
                xhrFields: {
                    withCredentials: true
                  },
                  crossDomain: true, 
                async : true,              
            success:function(data)
            {
            	var menus=data.data;
            	  $("body").append(txt1,txt2,txt3);  
            	
            	  
            	layer.close(index);
            },
            //  complete:function()
            //  {
            //    		         //请求完成的处理
            //  },
            error:function()
            {
            	  layer.msg('失败');//请求出错处理
            	layer.close(index);
            },
            });
  
  


});

$.ajaxSetup({
	  type:'post',
	  complete:function(xhr, ts){ //XMLHttpRequest, textStatus
	      var sessionStatus = xhr.getResponseHeader('sessionstatus');
	      if(sessionStatus == 'timeout') {
	          alert('太长时间没有操作，请重新登录！');
	          window.top.location.href = '/login.html';
	      }
	  }
	});