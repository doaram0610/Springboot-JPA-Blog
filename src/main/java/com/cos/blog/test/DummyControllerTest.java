package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired	//의존성 주입
	private UserRepository userRepository;
	

	//http://localhost:8000/blog/dummy/user
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {	//@RequestBody는 json형태로 전달된 파라미터를 받는다
		
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.";
		}
		return "삭제되었습니다. id : "+id;
	}
	
	//http://localhost:8000/blog/dummy/user
	//더티체킹은 캐시에 담아뒀다가 변경이 있으면 실제 데이터를 변경해주는 걸 말한다. 
	@Transactional	//함수종료시에 자동 커밋시킨다.save 함수를 호출하지 않아도 데이터가 업데이트된다. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) {	//@RequestBody는 json형태로 전달된 파라미터를 받는다
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		//요청한 id 의 결과값이 없는 경우 처리
		//아래 findById를 이용해서 데이터를 가져올 때 영속화가 된다. 영속석컨택스트를 이용해 캐시에 보관된다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		//위 데이터를 가져온 후 아래 함수값만 입력하면 @Transactional을 통해 커밋되면서 자동으로 수정된다.
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		//save 함수는 데이터가 있으면 update 없으면 insert 를 해준다.
//		userRepository.save(user);
		return user;
	}
	
	//전체 목록
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> list() {
	
		return userRepository.findAll();
	}
	
	//페이지목록
	//http://localhost:8000/blog/dummy/user/page
	//http://localhost:8000/blog/dummy/user/page?page=0 (페이지번호 0 부터 시작된다)
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC)Pageable pageable) {
	
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		if(pagingUsers.isLast()) {
			//마지막 페이지 일경우 분기 처리 
		}
		List<User> users =  pagingUsers.getContent();
		return users;
	}

	//상세화면
	//{id}주소로 파라메터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//존재하지 않는 id 검색시 리턴값 처리
		/*
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("없는 사용자 아이디 입니다.");
			}
		});
		*/
		//람다식 처리방법(결과는 위와 동일)
		User user = userRepository.findById(id).orElseThrow(()-> {
				return new IllegalArgumentException("없는 사용자 아이디 입니다.");
		});
		
		//스프링 프로젝트인 경우 user 오브젝트를 json(Gson 라이브러리)으로 변환해서 리턴해야 하지만
		//스프링부트 프로젝트는 자동으로 MessageConvertor 가 Jackson라이브러리를 호출해서 json 형태로 변환해서 리턴해준다.
		return user;
	}
	
	//회원가입
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {	//User객체는 form 태그로 전달된 파라미터를 받는다
		
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("roles: "+user.getRole());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었다.";
	}
}

