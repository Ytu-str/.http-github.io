layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#test',
    limit :10,
    id :"test",
    url:'/du',
    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    page :true
    ,cols: [
    	[
 
     {
		field :'ti',
		title :'题目and答案',
		width :800
		
     },{
    	 filed :'id',
    	 title :'id',
    		width :100
     }
      
    ]
    ],
  });
});
