//一般直接写在一个js文件中
layui.define(['layer','element','table'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,element=layui.element
  ,table=layui.table;

table.render({
    elem: '#menuTable'
    ,url:"http://localhost:8080/ksm/admin/showMenus"
    ,title: '菜单表'
    ,toolbar: '#editToolBar'
    ,cols: [[
       {type: 'checkbox', fixed: 'left'} 
       ,{field:'id', title:'ID', width:120, sort: true}
      ,{field:'menuName', title:'菜单名', width:120, edit: 'text'}
      ,{field:'menuDes', title:'菜单描述', width:120, edit: 'text'} 
      ,{field:'url', title:'地址', width:120, edit: 'text'}     
      ,{field:'parentId', title:'上级菜单', width:120, 
       }    
    ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:80}
    ]]
    ,page: true
    ,parseData: function(res){ //res 即为原始返回的数据
        return {
          "code": res.code, //解析接口状态
          "msg": res.msg, //解析提示文本
          "count": res.count, //解析数据长度
          "data": res.data.menus //解析数据列表
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
    ,data = obj.data //得到所在行所有键值
    ,field = obj.field //得到字段
    ,fieldString=field.toString();
    layer.confirm('确定将[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value+'?',{btn:['确定','取消']},
    		//确定按钮的回调
    		function(index){    		
                $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/menuUpdate",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	fieldString: value,	                    
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
        var index=layer.open(
            	{
              	  title: '新增菜单',
              	  type:1,
              	  btn:['注册','取消'],
              	  area:['450px','300px'],
              	  skin:'layui-layer-lan',
              	  shade: [0.5, '#000'],
              	  shadeClose:true,
              	  content: '<div class="layui-form-item" style="width: 80%;width: 80%;margin-top: 10px;margin-left:-30px;">'
         +'<label class="layui-form-label"><i class="layui-icon layui-icon-username" style="font-size: 32px; color:#FFB800;"></i></label>'
          +'<div class="layui-input-block" style="width: 80%;">'
            +'<input type="text" name="roleName" id="roleName" required lay-verify="required" autocomplete="off" placeholder="请输入角色名" class="layui-input">'   
          +'</div>'
        +'</div>'
        +'<div class="layui-form-item" style="width: 80%;margin-top: 30px;margin-left:-30px;">'
         +'<label class="layui-form-label"><i class="layui-icon layui-icon-password" style="font-size: 32px; color: #FFB800;"></i></label>'        
       +'</div>'
       +'<div class="layui-form-item" style="width: 80%;width: 80%;margin-top: 20px;margin-left:-30px;">'
         +'<label class="layui-form-label"><i class="layui-icon layui-icon-username" style="font-size: 32px; color:#FFB800;"></i></label>'
          +'<div class="layui-input-block" style="width: 80%;">'
            +'<input type="text" name="id" id="id" required lay-verify="required" autocomplete="off" placeholder="请输入角色ID" class="layui-input">'    
          +'</div>'
        +'</div>',
              	  yes: function(index, layero)
              	  {
              		    //按钮【按钮一】的回调
              		  var menuName=$("#menuName").val();
              		  var menuId=$("#id").val();
              		  var menuUrl=$("#Url").val();
              		  var menuDes=$('#menuDes').val();
              		  var parentId=$('#parentId').val();
              		  if(menuName.length===0||id.length===0|Url.length===0)
              			  {
              		  layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/saveMenu",
              	                    dataType: 'json',  
              	                    async : true,
              	                    data: { 
              	                    	'menuName' : menuName,
              	                    	'menuId':menuId,
              	                    	'menuUrl':menuUrl,
              	                    	'menuDes':menuDes,
              	                    	'parentId':parentId,
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
      case 'authen':
        var data = checkStatus.data;
        layer.msg('授权：'+ data.length + ' 个');
      break;    
    };
});
   //监听行工具事件
  table.on('tool(menuTable)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('确定删除菜单'+data.menuName, function(index){
        
        $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/menuDelete",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'menuId' : data.id,
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
  }
});
});