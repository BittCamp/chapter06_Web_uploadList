$(function(){
	$.ajax({
		type: 'post',
		url: '/chapter06_Web/user/getUpload_list_Ajax',
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			console.log(data[0].seq)
			
			/*
			$.each(data, function(index, item){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: item.seq
					
				})).append($('<td/>',{					
					align: 'center',
					
					}).append($('<img/>', {
						src: '../storage/' + item.image1,
						style: 'width: 70px; height: 70px;'
					}))
				
				).append($('<td/>',{
					align: 'center',
					text: item.imageName
				})).appendTo($('#imageListTable'));     
				
				//실제위치 C:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\chapter06_Web\WEB-INF\storage 실제위치에 파일있어야 됨.
			});
			*/
			
			var result;
			$.each(data, function(index, item){
				result = `<tr>` +
						 `<td align="center">` + item.seq + `</td>` + 
						 `<td align="center">` +
						 	`<img src="../storage/` + item.image1 + `" style="width: 70px; height: 70px;">` +  
						 `</td>` + 
						 `<td align="center">` + item.imageName + `</td>` +
						 `</tr>`;
						 
				$('#imageListTable').append(result);						 
			});
		},
		error: function(e){
			console.log(e);
		}
	});
});




