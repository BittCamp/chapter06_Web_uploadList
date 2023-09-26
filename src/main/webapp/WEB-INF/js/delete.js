//삭제 버튼
$('#deleteBtn').click(function(){
	if(confirm('삭제하시겠습니까?')){
		$.ajax({
			type: 'post',
			url: '/chapter06_Web/user/delete',
			data: {'id' : $('#id').val() },
			success: function(){
				alert('회원정보 삭제 완료');
				location.href = '/chapter06_Web/user/list';
			}, 
			error: function(e){
				console.log(e);
			}
		});
	}//if
	
});