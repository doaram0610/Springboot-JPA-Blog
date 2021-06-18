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
		$("#btn-update").on("click", ()=>{	//function(){} 대신 ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서이다.
			this.update();
		});		
/*		
		$("#btn-login").on("click", ()=>{	//function(){} 대신 ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서이다.
			this.login();
		});
*/
	},	
	save:function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
			
		//console.log(data);
		
		//ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청
		//ajax호출시 default 가 비동기 호출
		$.ajax({
			type:"POST",
			url:"/blog/auth/joinProc",
			data:JSON.stringify(data),	//data는 자바스크립트오브젝트이고  JSON.stringify(data)는 json 데이터이다. (http body데이터)
			contentType: "application/json;charset=utf-8",	// body데이터가 어떤 타입인지
			dataType:"json"	//요청을 서버로 해서 응답이 왔을 때 json 문자열로 보낼 경우 javascript object 로 변경해준다.			
		}).done(function(resp){						
			console.log(resp);
			console.log("회원가입이 완료되었다");	
			alert("회원가입이 완료되었다");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
	},
	update:function(){
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
			
		//console.log(data);

		$.ajax({
			type:"PUT",
			url:"/blog/user",
			data:JSON.stringify(data),	//data는 자바스크립트오브젝트이고  JSON.stringify(data)는 json 데이터이다. (http body데이터)
			contentType: "application/json;charset=utf-8",	// body데이터가 어떤 타입인지
			dataType:"json"	//요청을 서버로 해서 응답이 왔을 때 json 문자열로 보낼 경우 javascript object 로 변경해준다.			
		}).done(function(resp){						
			console.log(resp);
			console.log("회원정보가 수정되었다");	
			alert("회원정보가 수정되었다");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
	}	
/*	,	
	login:function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
			
		//console.log(data);
		
		//ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청
		//ajax호출시 default 가 비동기 호출
		$.ajax({
			type:"POST",
			url:"/blog/api/user/login",
			data:JSON.stringify(data),	//data는 자바스크립트오브젝트이고  JSON.stringify(data)는 json 데이터이다. (http body데이터)
			contentType: "application/json;charset=utf-8",	// body데이터가 어떤 타입인지
			dataType:"json"	//요청을 서버로 해서 응답이 왔을 때 json 문자열로 보낼 경우 javascript object 로 변경해준다.			
		}).done(function(resp){						
			console.log(resp);
			console.log("로그인이 완료되었다");	
			alert("로그인이 완료되었다");		
			location.href = "/blog";
		}).fail(function(error){
			console.log(error);
			alert(JSON.stringify(error));
		}); 
	}
*/	
}

index.init();