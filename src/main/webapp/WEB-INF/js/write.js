$(function(){
	//입력
	$('#writeBtn').click(function(){
		$('#nameDiv').empty();
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		
		if($('#name').val() == ''){
			$('#nameDiv').text('이름 입력');
			$('#name').focus();
			
		}else if($('#id').val() == ''){
			$('#idDiv').text('아이디 입력');
			$('#id').focus();
			
		}else if($('#pwd').val() == ''){
			$('#pwdDiv').text('비밀번호 입력');
			$('#pwd').focus();
		
		}else{
			$.ajax({
				type: 'post',
				url: '/chapter06_Web/user/write',
				data: $('#writeForm').serialize(), //변수=값&변수=값
				success: function(){
					alert('회원가입 완료');
					location.href = '/chapter06_Web/user/list';
				}, 
				error: function(e){
					console.log(e);
				}
			});
		}
	});
	
	//아이디 중복체크
	$('#id').focusout(function(){
		$('#idDiv').empty();
		
		if($('#id').val() == ''){
			$('#idDiv').text('먼저 아이디 입력');
			$('#id').focus();
			
		}else{
			$.ajax({
				type: 'post',
				url: '/chapter06_Web/user/isExistId',
				data: 'id=' + $('#id').val(), // {'id' : $('#id').val()}
				dataType: 'text',
				success: function(data){
					if(data == 'exist'){ //아이디 존재
						$('#idDiv').text('사용 불가능');
						$('#idDiv').css('color', 'red');
						
					}else if(data == 'non_exist') { //아이디 X
						$('#idDiv').text('사용 가능');
						$('#idDiv').css('color', 'blue');
					}
				},
				error: function(e){
					console.log(e);
				}
			});
			
		}
	});
	
});












