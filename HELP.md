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

####################################################################################
### 유트부 강좌 : Springboot - 나만의 블로그 만들기 총 73강
	스프링부트 강좌 7강(블로그 프로젝트) -->> 요거 부터 듣기
	
### 추가했던 library 6개 
	Spring Boot DevTool : 자동재시작
	Lombok : Vo 의 getter, setter  생략시켜두 되도록 가능
	Spring Data JPA : DB 를 만들때 이걸 사용한다 (14강 참조)
	MySQL Driver : DB 연결
	Spring Security : 보안관련 라이브러리
	Spring Web : 어노테이션 사용가능하도록 하며 톰캣을 탑재하고 있다
	
	pom.xml 을 수정하면 저장하는 동시에 다운로드+빌드를 함께 한다.

### https://github.com/
doaram0610 / infancy&79
	
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
	
	
	
	
### lombok 설정하기
.jar 파일이 있는 위치에서 git 실행 하고(없으면 cmd 창에서 실행) java -jar lombok.jar 를 실행 > 이클립스 실행 .exe 파일을 지정하고 인스톨 한다.	

### yaml 설정 : https://getinthere.tistory.com/20
스페이스 두칸,  콜론 뒤에 스페이스 한칸
web.xml, root-context.xml, servlet-contet.xml 을 모두 합친것 = application.yml


### JPA 사용하기  https://getinthere.tistory.com/23
JPA = ORM = Object 가 테이블로 만드어지는 기술





