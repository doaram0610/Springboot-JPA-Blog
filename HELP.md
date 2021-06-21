# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


### 유트부 강좌 : Springboot - 나만의 블로그 만들기 총 73강
스프링부트 강좌 65 강 (블로그 프로젝트) -->> 요거 부터 듣기
	
### 추가했던 library 6개 
Spring Boot DevTool : 자동재시작
Lombok : Vo 의 getter, setter  생략시켜두 되도록 가능
Spring Data JPA : DB 를 만들때 이걸 사용한다 (14강 참조)
MySQL Driver : DB 연결
Spring Security : 보안관련 라이브러리
Spring Web : 어노테이션 사용가능하도록 하며 톰캣을 탑재하고 있다

pom.xml 을 수정하면 저장하는 동시에 다운로드+빌드를 함께 한다.

### https://github.com/
	
0. git Repository 최초 생성후 
	git init
	git add .
	git commit -m "환경세팅완료 v1"
	git remote add origin 주소
	git push origin master	


1. 연결 : 최초 한번만 한다 그다음 부턴 pull -> push
	git init
	git remote add origin 주소



2. 가져오기 올리기
	push 자료 올리기
	pull 자료 가져오기
	순서 : pull -> push

	<pull>
		git pull origin master


	<push>
		-- 상태 체크
		git status
		
		-- 모든파일을 tracking
		git add .
		
		-- commit 달기
		git commit -m "환경세팅완료 v1"
		
		-- 실제로 git 에 push
		git push origin master	
	
3. config설정
	git config --list	
	
	
### lombok 설정하기
.jar 파일이 있는 위치에서 git 실행 하고(없으면 cmd 창에서 실행) java -jar lombok.jar 를 실행 > 이클립스 실행 .exe 파일을 지정하고 인스톨 한다.	

### yaml 설정 : https://getinthere.tistory.com/20
스페이스 두칸,  콜론 뒤에 스페이스 한칸
web.xml, root-context.xml, servlet-contet.xml 을 모두 합친것 = application.yml


### JPA 사용하기  https://getinthere.tistory.com/23
JPA = ORM = Object 가 테이블로 만드어지는 기술

-- 유저이름@아이피주소
create user 'cos'@'%' identified by 'jang1234';

-- ON DB이름.테이블명
-- TO 유저이름@아이피주소
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
CREATE DATABASE blog CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use blog;



### library 생성 위치 변경 하기
원하는 위치에 (기본 C:\Users\사용자명\.m2) settings.xml을 생성한다.
eclipse-Window-Preferences-Maven-User Settings에 생성한 settings.xml 파일 경로로 변경한다.

<localRepository>repository 폴더 생성 할 경로</localRepository>

<?xml version="1.0"?>

<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/SETTINGS/1.0.0">

<localRepository>D:/Dev/eGovFrameDev-3.7.0-64bit/maven_repository_3_7/repository</localRepository>

</settings>



### bootstrap
https://www.w3schools.com/bootstrap4/default.asp


### 이클립스 단축키
Ctrl + Shift + F : 들여쓰기 정렬

### 스프링 시작
1. 톰캣시작
2. web.xml
3. context.xml -> DB연결 테스트	
* requset -> web.xml (DB연결, 트랜젝션시작) -> 필터 -> 스트링컨테이너(Controller(응답시 트랜젝션종료,DB연결세션종료) -> Service -> Repository) -> 영속성컨텍스트(데이터를 갖고 있음) -> DB	
	
### security taglib
https://www.baeldung.com/spring-security-taglibs  접속해서 jsp include 형식 복사해온다
docs.spring.io/spring-security/site/docs/3.0x/reference-access.html	security session 이용 방법

※ 시큐리티로그인 실행 순서
jsp(/blog/auth/loginProc) 요청 
-> SecurityConfig(loginProcessingUrl 설정 확인) 
-> UserDetailsService를 implements 받은 Service 에서 username 확인
-> UserRepository.findByUsername 를 이용해 db값 추출(PrincipalDetail Vo 사용)
-> SecurityConfig(passwordEncoder 설정 확인)에서 요청한 password 값을 해쉬암호화해서 db에서 가져온 값과 비교한다.
-> 세션값에 유저정보 저장하고 성공페이지로 이동 (세션 성공적으로 생성)
-> loginForm.jsp > SecurityConfig > PrincipalDetailService > PrincipalDetail > UserRepository > PrincipalDetailService > SecurityConfig 

### 글쓰기 화면
글쓰기 화면의 본문 html 편집기 코드 다운로드 : https://summernote.org/

### 선생님 github
https://github.com/codingspecialist

### 스프링작동 원리
톰캣 
-> 필터 : 요청이 올때 걸러낸다.
-> 디스패처 
-> Controller : @Controller 는  화면으로 보여줄때, @RestController는 데이터로 보낼때 사용한다. 이때는 jdbc연결만 한다. 
-> Service : 이걸 굳이 만들어서 사용하는 이유는 여기서 트랜잭션이 시작/종료되도록 해서 컨트롤러에서 요청처리는 하고 있어도 트랜잭션을 유지 하지 않아서 속도개선을 해준다.
-> repository(JPA)
-> 영속성 컨택스트 - DataSource - DB

ViewResolver
세션
인터셉터 : 권한을 체크하는 함수(함수 실행전/후에 어떤 처리를 한다. 관리자 권한 체크, 로그인 여부 체크 등...)

### 카카오 API

인증 -> 토큰

#### 인증
웹서버 : http://localhost:8000
클라이언트 REST API 키 : 9fb8744cbc91ff1c85fd1641698b3d84

카카오로그인요청 콜백주소 : http://localhost:8000/blog/auth/kakao/callback
카카오한테 받는 거 :  profile(필수), email(선택)


* 카카오로그인 호출 URL
GET /oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code HTTP/1.1
Host: kauth.kakao.com

GET /oauth/authorize?client_id=9fb8744cbc91ff1c85fd1641698b3d84&redirect_uri=http://localhost:8000/blog/auth/kakao/callback&response_type=code
Host: https://kauth.kakao.com/oauth/authorize?client_id=9fb8744cbc91ff1c85fd1641698b3d84&redirect_uri=http://localhost:8000/blog/auth/kakao/callback&response_type=code

#### 토큰
* 토큰 발급 주소
POST /oauth/token HTTP/1.1
Host: kauth.kakao.com
Content-type: application/x-www-form-urlencoded;charset=utf-8  -->> 요건 key=value 형태로 데이터를 보내라하는 말

https://kauth.kakao.com/oauth/token
grant_type=authorization_code
client_id=9fb8744cbc91ff1c85fd1641698b3d84
redirect_uri=http://localhost:8000/blog/auth/kakao/callback
code=필수아님, 동적인 값임

* 토큰을 통한 사용자 정보 조회
GET/POST /v2/user/me HTTP/1.1
Host: https://kapi.kakao.com/v2/user/me
Authorization: Bearer {ACCESS_TOKEN}
Content-type: application/x-www-form-urlencoded;charset=utf-8



