$(function(){
	$.ajax({
		type: 'post',
		url: '/chapter06_Web/user/getUserList',
		data: 'pg=' + $('#pg').val(),
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
			//console.log(data.list[0].name);
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>', {
					align: 'center',
					text : items.name
				
				})).append( $('<td/>',{
					align: 'center'
					
					}).append( $('<a/>', { 
						href: '#', 
						text: items.id,
						class: 'subjectA'
					}))
				).append($('<td/>', {
				
					align: 'center',
					text : items.pwd
				})).appendTo($('#userListTable'));
				
			});//$.each
			
			//페이징 처리
			$('#userPagingDiv').html(data.userPaging.pagingHTML);
			
			//아이디 클릭했을 때
			$('.subjectA').click(function(){
				//alert($(this).text());
				//alert($(this).parent().prev().prop('tagName'));
				//alert('이름 = ' + $(this).parent().prev().text());
				
				location.href = '/chapter06_Web/user/updateForm?id=' + $(this).text() + '&pg=' + $('#pg').val();                  
				
			});
			
		},
		error: function(e){
			console.log(e);
		}
		
	});
});







