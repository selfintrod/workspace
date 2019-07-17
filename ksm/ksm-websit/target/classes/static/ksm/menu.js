//一般直接写在一个js文件中
layui.define(['layer','element','table','tree'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,element=layui.element
  ,table=layui.table
  ,tree=layui.tree;
var pmenu;
table.render({
    elem: '#menuTable'
    ,url:"http://localhost:8080/ksm/admin/showMenus"
    ,title: '菜单表'
    ,toolbar: '#menuToolBar'
    ,cols: [[
       {type: 'checkbox', fixed: 'left'} 
       ,{field:'id', title:'ID', width:120, sort: true}
      ,{field:'menuName', title:'菜单名', width:120, edit: 'text'}
      ,{field:'menuDes', title:'菜单描述', width:120, edit: 'text'} 
      ,{field:'url', title:'地址', width:120, edit: 'text'}     
      ,{field:'parentId', title:'上级菜单', width:120, 
       }    
    ,{fixed: 'right', title:'操作', toolbar: '#menuBar', width:80}
    ]]
    ,page: true
    ,parseData: function(res){ //res 即为原始返回的数据
        return {
          "code": res.code, //解析接口状态
          "msg": res.msg, //解析提示文本
          "count": res.count, //解析数据长度
          "data": res.data //解析数据列表
        };
      }
   ,response: {
        statusName: 'code' //规定数据状态的字段名称，默认：code
        ,statusCode: 200 //规定成功的状态码，默认：0
        ,dataName: 'data' //规定数据列表的字段名称，默认：data
      } 

  });

 //监听单元格编辑
  table.on('edit(menuTable)', function(obj){
    var value = obj.value //得到修改后的值
    ,oldValue=$(this).prev().text()
    ,field = obj.field//得到字段
    ,data = obj.data //得到所在行所有键值
    ,fieldString=field.toString();//获得String类型字段名   
    layer.confirm('确定将菜单[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value+'?',{btn:['确定','取消']},
    		//确定按钮的回调
    		function(index){    		
                $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/saveMenu",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'menuName' : data.menuName,
	                    'menuUrl':data.url,
	                    'menuDes':data.menuDes,
	                    'parentId':data.parentId,                    
	                    'menuId':data.id,
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
  table.on('toolbar(menuTable)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'addMenu':
    	  $.ajax(
                  {
                      type: 'get',
                      url: "http://localhost:8080/ksm/admin/showMenuModels",
                      dataType: 'json',  
                      async : true,
                     
                      	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
                 
                  success:function(data)
                  {
                  	 //请求成功时处理
                	  tree.render({
                		    elem: '#parentMenu'
                		    ,data: data.data
                		    ,showLine: false//是否开启连接线
                		    ,showCheckbox: true
                		    ,id: 'parentMenu'
                		    ,oncheck: function(obj){
                		    	//测试被选节点
                		    	var checkData = tree.getChecked('parentMenu');
                		    	console.log(checkData);
                		    	
                		    console.log(obj.data); //得到当前点击的节点数据
                		    if(obj.checked==true)
                		    { 
                		    	pmenu=obj.data.id
                		    }
                		    if(obj.checked==false)
                		    	{
                		    	pmenu=0
                		    	}
                		   // console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
                		   // console.log(obj.elem); //得到当前节点元素
                		    
                		    //pmenu=data[0].children.id;
                		    console.log('父菜单id为'+pmenu);
                		    	  }
                		  });
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
    	
        var index=layer.open(
            	{
              	  title: '新增菜单',
              	  type:1,
              	  btn:['确定','取消'],
              	  area:['800px','550px'],
              	  skin:'layui-layer-lan',
              	  shade: 0,
              	  shadeClose:true,
              	  content: $('#menuReg'),
              	  yes: function(index, layero)
              	  {
              		    //按钮【按钮一】的回调
              		var menuName=$("#menuName").val();
            		  var menuId=$("#menuId").val();
            		  var menuUrl=$("#menuUrl").val();
            		  var menuDes=$('#menuDes').val();         		          		           		
              		  if(menuName.length===0||menuId.length===0|menuUrl.length===0)
              			  {
              		  layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/saveMenu",
              	                    dataType: 'json',  
              	                    async : false,
              	                    data: { 
              	                    	'menuName' : menuName,
              	                    	'menuId':menuId,
              	                    	'menuUrl':menuUrl,
              	                    	'menuDes':menuDes,
              	                    	'parentId':pmenu,
              	                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
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
  table.on('tool(menuTable)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('确定删除菜单 ['+data.menuName+']?', function(index){
        
        $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/deleteMenu",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'menuId' : data.id,
                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
                },
                success:function(req)
                {
                	  if(req.code==200)//请求成功时处理
                	{
                		  layer.msg('删除成功');
                		  obj.del();
                	}
                	  else
                		 {
                		  layer.msg(req.msg);
                		 } 
                	  
                },
                //  complete:function()
                //  {
                //    		         //请求完成的处理
                //  },
                error:function(res)
                {
                	  layer.msg('删除失败！'+' 原因：'+res.msg);//请求出错处理               	  
                },
                });
        layer.close(index);
      });
  }
});
  
  function menuModelInit()
  {
	  var datatemp;
	   $.ajax(
               {
                   type: 'get',
                   url: "http://localhost:8080/ksm/admin/showMenuModels",
                   dataType: 'json',  
                   async : true,
                  
                   	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
              
               success:function(data)
               {
               	 //请求成功时处理
            	   layer.msg(JSON.stringify(data));
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
	   return datatemp
  }
  
  
});