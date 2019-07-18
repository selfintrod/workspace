layui.define(['transfer','layer','element','table'], function(){
  var $ = layui.$
  ,layer = layui.layer
  ,element=layui.element
;
  
getMenus();
console.log('菜单的dom为'+$('#menus').html());
 

/*$.ajaxSetup({
	  type:'post',
	  complete:function(xhr, ts){ //XMLHttpRequest, textStatus
	      var sessionStatus = xhr.getResponseHeader('sessionstatus');
	      if(sessionStatus == 'timeout') {
	          alert('太长时间没有操作，请重新登录！');
	          window.top.location.href = '/login.html';
	      }
	  }
});*/

function getMenus()
 {
	//ajax获取所有菜单，初始化menu
	  $.ajax(
               {
                   type: 'GET',
                   url: "http://localhost:8080/ksm/admin/showMenuTree",
                   dataType: 'json',  
                   async : true,
                   success:function(data)
	              {	  
                	   var type='layui-nav layui-layout-left';
                	   var menus=data.data;
              	       var html=showMenus(menus,type);
                   	  //$("#"+"logo").after(html); 
                   	
                   //	$(".layui-layout-left").css("class", "zorder:999;");
                   //	
                   	//element.render('nav');
              	     $("#"+"menus").append(html);
              	   element.init('nav');
              	  // console.log('菜单被添加的dom'+html);
	                    },	               
	                    error:function()
	                    {
	                	  layer.msg('获取角色列表失败');//请求出错处理
	                    },
               });
 }
	  
	  
  //递归函数，递归输出菜单
 function showMenus(data,type)
 {           	   
      //if(data!=null&&data!=undefined)
 //{
      var html=new String;              	
      var child=data;
      var childMenu;
      var StringType='layui-nav';
      html+='<ul class="'+type+'">';                   	
     $.each(child, function(index,item) {
              html+='<li class="layui-nav-item"><a href="http://localhost:8080/ksm'+item.url+'">'+item.menuName+'</a>';
               if(item.children!=undefined&&item.children!=null)
                  {
            	       html+='<dl class="layui-nav-child">'; 
    	               childMenu=item.children;
                       $.each(childMenu, function(index,childitem) {
                                    			
	                      html+='<dd><a href="http://localhost:8080/ksm'+childitem.url+'">'+childitem.menuName+'</a>';	                      
	                    if(childitem.children.length!=0)
	                        {	     
	                    	
	                           html+=showMenus(childitem.children,StringType);
	                        }
	                    html+='</dd>';
	                    });
                       html+='</dl></li>';
                  };
           });
     //}
      return html+='</ul>';
  }; 
  
  
});