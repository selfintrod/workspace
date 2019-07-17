//一般直接写在一个js文件中
layui.define(['layer','element','table'], function(){
  var $ = layui.$
  var layer = layui.layer
  ,form = layui.form
  ,element=layui.element
  ,table=layui.table;

table.render({
    elem: '#userTable'
    ,url:"http://localhost:8080/ksm/admin/showUsers"
    ,title: '员工数据表'
    ,toolbar: '#editToolBar'
    ,cols: [[
       {type: 'checkbox', fixed: 'left'} 
      ,{field:'userName', title:'用户名', width:120, edit: 'text'}
      ,{field:'passWord', title:'密码', width:200, edit: 'text'}
      ,{field:'id', title:'ID', width:120,unresize: true, sort: true}
      ,{field:'roleName', title:'角色', width:120, 
    	  templet: function(d){
    		  var roleNames=new String;
    		//  var ksmRole=JSON.parse(d.userRoles);
    		  for(let i=0;i<d.userRoles.length;i++)
    			  roleNames=roleNames+d.userRoles[i].roleName+';';
    		  //$.each(d.ksmRoles,function(){
    			 // roleNames=d.roleNames+ksmRoles.roleName;
    		  //})
    		  return roleNames}
       }
    ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:80}
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
      case 'addUser':
        var index=layer.open(
            	{
              	  title: '员工注册',
              	  type:1,
              	  btn:['注册','取消'],
              	  area:['450px','300px'],
              	  skin:'layui-layer-lan',
              	  shade: [0.5, '#000'],
              	  shadeClose:true,
              	  content: $('#userRegist'),
              	  yes: function(index, layero)
              	  {
              		    //按钮【按钮一】的回调
              		  var userName=$("#userName").val();
              		  var passWord=$("#passWord").val();
              		  var id=$("#id").val();
              		  if(userName.length===0||id.length===0|passWord.length===0)
              			  {
              		  layer.msg("重新填写");
              			  }
              			  else{$.ajax(
              	                {
              	                    type: 'GET',
              	                    url: "http://localhost:8080/ksm/admin/save",
              	                    dataType: 'json',  
              	                    async : true,
              	                    data: { 
              	                    	'userName' : userName,
              	                    	'passWord':passWord,
              	                    	'userId':id,
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
  table.on('tool(userTable)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('确定删除员工'+data.userName, function(index){
        
        $.ajax(
                {
                    type: 'GET',
                    url: "http://localhost:8080/ksm/admin/delete",
                    dataType: 'json',  
                    async : true,
                    data: { 
                    	'userName' : data.userName,
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