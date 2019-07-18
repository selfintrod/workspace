//一般直接写在一个js文件中
layui.define(['transfer','layer','element','table'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,transfer=layui.transfer
  ,element=layui.element
  ,table=layui.table;

  
 function getRoles(tableName)
 {
	//ajax获取所有角色，初始化select
	  $.ajax(
               {
                   type: 'GET',
                   url: "http://localhost:8080/ksm/admin/getRoles",
                   dataType: 'json',  
                   async : true,
                   success:function(data)
	              {	                   	
	                var html=new String();                 	
                   	html+='<div class="layui-form-item" id="rolesbox" style="width: 80%;width: 80%;margin-top: 20px;margin-left:-30px;">';
                   	html+='<label class="layui-form-label"><i class="layui-icon layui-icon-group" style="font-size: 24px; color:#FFB800;"></i></label>';
                   	html+='<div class="layui-input-block" 1style="width: 80%;">';
                   	
                   	var temp=new String();
                   	$.each(data, function(index,item) {
	                		html+='<input type="checkbox" lay-skin="primary" name="regAttr" value="'+item.roleId+'" title="'+item.roleName+'">';	                	     		
	                });	  
                   	html+='</div>';
                   	html+='</div>';                   	
                   	$("#"+tableName).append(html);
                   	form.render('checkbox');                  	              	   	                	 
	                    },	               
	                    error:function()
	                    {
	                	  layer.msg('获取角色列表失败');//请求出错处理
	                    },
               });
 }
  
  
table.render({
     elem: '#userTable'
    ,url:"http://localhost:8080/ksm/admin/showUsers"
    ,title: '员工数据表'
    ,toolbar: '#userToolBar'
    ,id:'userTable'   		
    ,cols: [[
       {type: 'checkbox', fixed: 'left'} 
      ,{field:'userName', title:'用户名', width:120}
      ,{field:'passWord', title:'密码', width:200}
      ,{field:'id', title:'ID', width:120,unresize: true, sort: true}
      ,{field:'roleName', title:'角色', width:80, 
    	  templet: function(d){
    		  var roleNames=new String;
    		//  var ksmRole=JSON.parse(d.userRoles);
    		  for(let i=0;i<d.userRoles.length;i++)
    			  roleNames=roleNames+d.userRoles[i].roleName+' ';
    		  //$.each(d.ksmRoles,function(){
    			 // roleNames=d.roleNames+ksmRoles.roleName;
    		  //})
    		  return roleNames}
       }
     // ,{field:'roleId', title:'角色id', width:120, templet: function(d){
		 // var roleId=new String;
  		//  var ksmRole=JSON.parse(d.userRoles);
  		//  for(let i=0;i<d.userRoles.length;i++)
  			//  roleId=roleId+d.userRoles[i].roleId+';';
  		  //$.each(d.ksmRoles,function(){
  			 // roleNames=d.roleNames+ksmRoles.roleName;
  		  //})
  		 // return roleId}}
     // ,{field:'email', title:'邮箱', width:150, edit: 'text', templet: function(res){
      //  return '<em>'+ res.email +'</em>'
      
      //,{field:'sex', title:'性别', width:80, edit: 'text', sort: true}
      //,{field:'joinTime', title:'加入时间', width:120}
    ,{fixed: 'right', title:'操作', toolbar: '#userBar'}
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
  table.on('edit(userTable)', function(obj){
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
                    url: "http://localhost:8080/ksm/admin/update",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'userName' : data.userName,
	                    'passWord':data.passWord,
	                    'userId':data.id,
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
  table.on('toolbar(userTable)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
    //此处为添加用户页面
      case 'addUser':
    	getRoles('userRegist');
        var index=layer.open(
            	{
              	  title: '员工注册',
              	  type:1,
              	  btn:['注册','取消'],
              	  area:['450px','300px'],
              	  skin:'layui-layer-lan',
              	  shade: 0,
              	  shadeClose:true,
              	  content: $('#userRegist'),//.html(),  
              	  success:function(index,layero)
              	  {
              		  
              	  },
              	  yes: function(index,layero)
              	  {
              		  var str=new Array();
              		  $("input[name='regAttr']:checked").each(function (index, item) {            	        
              	        str.push($(this).val());            	        
              	    });             		 
              		  //按钮【按钮一】的回调
              		  var userName=$("#userName").val();
              		  var passWord=$("#passWord").val();
              		  var id=$("#id").val();
              		  var roleId=str;          		  
              		  if(userName.length===0||id.length===0|passWord.length===0)
              			  {
              		  layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/saveUser",
              	                    dataType: 'json',  
              	                    async : true,
              	                    data: { 
              	                    	'userName' :userName,
              	                    	'passWord':passWord,
              	                    	'userId':id,
              	                    	'roleId':JSON.stringify(roleId),
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
              	                error:function(data)
              	                {
              	                	  layer.msg('添加失败');//请求出错处理
              	                	  layer.confirm(JSON.stringify(data));
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
        $('#rolesbox').remove();
      break;    
    }});
    //监听行工具事件
   table.on('tool(userTable)', function(obj){
                     var data = obj.data;
                      //console.log(obj)
           switch(obj.event){
             case 'del':
             layer.confirm('确定删除员工'+data.userName, function(index){
             $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/deleteUser",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'userId' : data.id,
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
    	getRoles('userEdit');
    	var index=layer.open(
            	{
              	  title: '员工编辑',
              	  type:1,
              	  btn:['确定','取消'],
              	  area:['450px','300px'],
              	  skin:'layui-layer-lan',
              	  shade:0,
              	  shadeClose:true,
              	  content: $('#userEdit'),
              	  yes: function(index, layero)
              	  {
              		var str=new Array();
            		  $("input[name='regAttr']:checked").each(function (index, item) {            	        
            	        str.push($(this).val());            	        
            	    });   
              		    //按钮【按钮一】的回调
              		var userName=$("#userNameEdit").val();
          		    var passWord=$("#passWordEdit").val();
          		    var userId=data.id;
          		    var roleId=str;
              		  if(userName.length===0||id.length===0|passWord.length===0)
              			  {
              		         layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/saveUser",
              	                    dataType: 'json',  
              	                    async : true,
              	                    data: { 
              	                    	'userName' :userName,
              	                    	'passWord':passWord,
              	                    	'userId':userId,
              	                    	'roleId':JSON.stringify(roleId)
              	                    	//'arrayList' : JSON.stringify(row)  //把数据转换为json格式字符串
              	                },
              	                success:function(req)
              	                {
              	                	  layer.msg('修改成功');//请求成功时处理 
              	                	  table.reload('userTable');
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
    	$('#rolesbox').remove();
    	break;    
}});
});