//一般直接写在一个js文件中
layui.define(['layer','element','table','tree'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,element=layui.element
  ,tree=layui.tree
  ,table=layui.table;
 var tempMenus=new Array();
 var Menus;
 
 

table.render({
    elem: '#roleTable'
    ,url:"http://localhost:8080/ksm/admin/getRoles2"
    ,title: '角色权限表'
    ,toolbar: '#roleToolBar'
    ,cols: [[
       {type: 'checkbox', fixed: 'left'} 
      ,{field:'roleName', title:'角色', width:120}      
      ,{field:'roleId', title:'角色ID', width:120,unresize: true, sort: true}
      ,{field:'description', title:'角色描述', width:240,}   
      ,{fixed: 'right', title:'操作', toolbar: '#roleBar', width:120}
      ]]
       ,page: true
       ,parseData: function(res){ //res 即为原始返回的数据
           return {
          "code": res.code, //解析接口状态
          "msg": res.msg, //解析提示文本
          "count": res.count, //解析数据长度
          "data": res.data.records //解析数据列表
        };
      }
   ,response: {
        statusName: 'code' //规定数据状态的字段名称，默认：code
        ,statusCode: 200 //规定成功的状态码，默认：0
        ,dataName: 'data' //规定数据列表的字段名称，默认：data
      } 

  });

 //监听单元格编辑
  table.on('edit(roleTable)', function(obj){
    var value = obj.value //得到修改后的值
    ,oldValue=$(this).prev().text()
    ,data = obj.data //得到所在行所有键值
    ,field = obj.field; //得到字段
    layer.confirm('确定将[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value+'?',{btn:['确定','取消']},
    		//确定按钮的回调
    		function(index){    		
                $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/roleUpdate",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'userName' : data.roleName,	                    
	                    'roleId':data.roleId,
                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
                },
                success:function(req)
                {
                	  layer.msg('修改成功');//请求成功时处理
                },
                //  complete:function()
                //  {
                //    		         //请求完成的处理
                //  },
                error:function()
                {
                	  layer.msg('修改失败');//请求出错处理
                },
                
                });
        layer.close(index);
    		},
    		//取消的回调
    		function(index)
            {
    			//layer.msg(oldValue);
    			var objTemp ={};
    			objTemp[field] =oldValue;
    			obj.update(objTemp);
            	layer.close(index);
            },
        
      );
    
  });
  
  
 //工具栏事件
  table.on('toolbar(roleTable)', function(obj){
    switch(obj.event){
      case 'addRole':
    	  $.ajax(
                  {
                      type: 'get',
                      url: "http://localhost:8080/ksm/admin/showMenuModels",
                      dataType: 'json',  
                      async : true,              
                      success:function(data)
                      {
                	        tree.render({
                		        elem: '#RRmenu'
                		       ,data: data.data
                		       ,showLine: false//是否开启连接线
                		       ,showCheckbox: true
                		       ,id: 'RRmenu'
                		       ,oncheck: function(obj){
                		    	     //测试被选节点
                		    	  var checkData = tree.getChecked('RRmenu');
                		    	  var tempMenus= [];
                		    	 
                		    	if(checkData.length!=0)
                		    		for(var i=0;i<checkData.length;i++)
                		    		{               		    			 
                		    			tempMenus.push.apply(tempMenus,getChildren(checkData[i]));
                		    		}
                		    	console.log(tempMenus);
                		    	Menus=tempMenus;              		    	
                		          // console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
                		         // console.log(obj.elem); //得到当前节点元素              		    
                		    	  }
                		   });
                   },
                  //  complete:function()
                  //  {
                  //    		         //请求完成的处理
                  //  },
                     error:function()
                      {
                  	    layer.msg('获取菜单列表失败');//请求出错处理
                      },                
                   });
                    
                   var index=layer.open(
            	 {
              	  title: '角色注册',
              	  type:1,
              	  btn:['注册','取消'],
              	  area:['450px','300px'],
              	  skin:'layui-layer-lan',
              	  shade:0,
              	  shadeClose:false,
              	  content: $('#roleRegist'),
              	  success(index,layero)
          	      {
          	       },
              	  yes: function(index, layero)//yes为按钮1的回调
              	  {
              		  var roleName=$("#RroleName").val();
              		  var roleId=$("#RroleId").val();
              		  var roleDes=$('#RroleDes').val();
              		  var menus=Menus.toString();
              		  if(roleName.length===0||roleId.length===0|roleDes.length===0)
              			  {
              		  layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/saveRole",
              	                    dataType: 'json',  
              	                    async : true,
              	                   data: { 
              	                    	'roleName' : roleName,
              	                    	'roleId':    roleId,
              	                    	'roleDes': roleDes,
              	                    	'menus': menus,
              	               },
              	                success:function(req)
              	                {
              	                	  layer.msg('添加成功');//请求成功时处理
              	                	  
              	                	layer.close(index);
              	                },
              	                //  complete:function()
              	                //  {
              	                //    		         //请求完成的处理
              	                //  },
              	                error:function()
              	                {
              	                	  layer.msg('添加失败');//请求出错处理
              	                	layer.close(index);
              	                },
              	                });
              			  };             		
              	  },
              		btn2: function(index, layero)
              	  {
              		    //按钮【按钮二】的回调
              		    layer.close(index);
              		    //return false 开启该代码可禁止点击该按钮关闭
              	  },
              	  
              	}); 
      break;   
    };
});
   //监听行工具事件
  table.on('tool(roleTable)', function(obj){
    var data = obj.data;
    //console.log(obj)
    switch(obj.event){
    
    case 'del':
      layer.confirm('确定删除角色['+data.roleName+']?', function(index){        
                
    	  $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/roleDelete",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'roleId' : data.roleId,
                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
                },
                success:function(req)
                {
                	  layer.msg('删除成功');
                	  obj.del();//请求成功时处理
                },
                //  complete:function()
                //  {
                //    		         //请求完成的处理
                //  },
                error:function()
                {
                	  layer.msg('删除失败');//请求出错处理
                },
                });
        layer.close(index);
      });
      break;
      
    case 'edit':   	     
    	$.ajax(
                {
                    type: 'get',
                    url: "http://localhost:8080/ksm/admin/showMenuModels",
                    dataType: 'json',  
                    async : true,              
                    success:function(data)
                    {
                	 //请求成功时处理
              	        tree.render({
              		        elem: '#REmenu'
              		       ,data: data.data
              		       ,showLine: false//是否开启连接线
              		       ,showCheckbox: true
              		       ,id: 'REmenu'
              		       ,oncheck: function(obj){
              		    	     //测试被选节点
              		    	  var checkData = tree.getChecked('REmenu');
              		    	  console.log(checkData); 
              		    	
              		          // console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
              		         // console.log(obj.elem); //得到当前节点元素              		    
              		    	  }
              		   });
              	        
              	        console.log('菜单初始化成功');
                 },
                //  complete:function()
                //  {
                //    		         //请求完成的处理
                //  },
                   error:function()
                    {
                	    layer.msg('获取菜单列表失败');//请求出错处理
                    },                
                 });
    	
    	var index=layer.open(
          	    {
            	  title: '角色编辑',
            	  type:1,
            	  btn:['确定','取消'],
            	  area:['600px','600px'],
            	  skin:'layui-layer-lan',
            	  shade:0,
            	  shadeClose:true,
            	  content: $('#roleEdit'),
            	  success(index,layero)
            	  {                   		  
            	  },
          	      yes: function(index, layero)
          	      {
          		    //按钮【按钮一】的回调
          		    var roleName=$("#roleName").val();
          		    var roleId=data.roleId;
          		    var roleDes=$("#EroleDes").val();
          		    if(roleName.length===0||roleDes.length===0)
          			  {
          		            layer.msg("重新填写");
          			  }
          			  else{$.ajax(
          	                {
          	                    type: 'GET',
          	                    url: "http://localhost:8080/ksm/admin/saveRole",
          	                    dataType: 'json',  
          	                    async : true,
          	                    data: { 
          	                    	'roleName' : roleName,
          	                    	'roleDes':roleDes,
          	                    	'roleId':roleId,
          	                    	//'menus': menus,
          	                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
          	                },
          	                success:function(req)
          	                {
          	                	  layer.msg('修改成功');//请求成功时处理
          	                	  
          	                	layer.close(index);
          	                },
          	                //  complete:function()
          	                //  {
          	                //    		         //请求完成的处理
          	                //  },
          	                error:function()
          	                {
          	                	  layer.msg('修改失败');//请求出错处理
          	                	layer.close(index);
          	                },
          	                });
          			  };             		
          	          },
          		   btn2: function(index, layero)
          	       {
          		    //按钮【按钮二】的回调
          		       layer.close(index);
          		    //return false 开启该代码可禁止点击该按钮关闭
          	       },          	             	    
                });
    	      break; 
    };
             //下拉列表监听，每当select选中值发生变化，此处回调，
            // form.on('select(test)', function(data){   
           //        console.log(data);
           //  var temp=$('#authRoles option:selected').text();
            //transfer.reload('auth', {
                         //        title: ['用户', temp]
                         //   });
                         //   });
                               
}); 
});

//递归函数，将树形结构转为一维数组
function getChildren(data)
{ 	
	var menus=new Array();
	if(data!=null&&data!=undefined)
	{
		var child=data;
		menus.push(child.id);
		if(child.children!=undefined)
		{
		for(var i=0;i<child.children.length;i++)
			{						
			menus.push.apply(menus,getChildren(child.children[i]));
			}	
		}
	}
	return menus;
};     