/**
 * 사용자등록
 * 회원가입시 ajax 를 사용하는 이유 2가지
 *	1. 요청에 대한 응답을 html 이 아닌 Data(json)으로 받기 위해-> 이렇게 하면 웹과 앱 두군데이서 모두 호출이 가능하다.
 *	2. 비동기통신(순서에 상관없이)을 하기 위해서이다. 순차적으로 프로세스를 처리하는 것이 아니라 동시에 여러가지 작업을 호출하여 처리할 수 있다.
 */
 let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{	//function(){} 대신 ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서이다.
			this.save();
		});
		$("#btn-delete").on("click", ()=>{	//function(){} 대신 ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서이다.
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{	//function(){} 대신 ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서이다.
			this.update();
		});		
	},	
	save:function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type:"POST",
			url:"/blog/api/board",
			data:JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType:"json"			
		}).done(function(resp){						
			console.log(resp);
			console.log("글이 저장되었습니다.");	
			alert("글이 저장되었습니다.");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
	},	
	deleteById:function(){
		let id = $("#id").text();
		
		$.ajax({
			type:"DELETE",
			url:"/blog/api/board/"+id,
			dataType:"json"			
		}).done(function(resp){						
			console.log(resp);
			console.log("삭제되었습니다.");	
			alert("삭제되었습니다.");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
	},
	update:function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type:"PUT",
			url:"/blog/api/board/"+id,
			data:JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType:"json"			
		}).done(function(resp){						
			console.log(resp);
			console.log("글이 수정되었습니다.");	
			alert("글이 수정되었습니다.");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
		
	}	
}

index.init();