package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		log.info("username={}, age={}",username, age);

		response.getWriter().write("ok");
	}

	//RestController와 같이 ModelAndView를 찾는 것이 아니라 String 값을 그대로 반환한다
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
			@RequestParam("username") String memberName,
			@RequestParam("age") int memberAge
	){
		log.info("username={}, age={}",memberName, memberAge);

		return "ok!";
	}

	//변수 명이 같다면 생략 가능하다
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(
			@RequestParam String username,
			@RequestParam int age
	){
		log.info("username={}, age={}",username, age);

		return "ok!";
	}

	//단순 타입이라면 어노테이션도 생략 가능, 대신 요청 파라미터 이름과 같아야한다
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age){
		log.info("username={}, age={}",username, age);

		return "ok!";
	}


	//해당 요청 파라미터에 대한 필수여부 설정
	//required true 지만 파라미터가 없으면 bad request 인 400을 에러로 보낸다
	//int age 일때 파라미터를 보내지 않으면 null이 들어가야하지만 int에서 들어갈 수 없다
	//Integer age로 바꾸면 가능하다. primitive가 아닌 warp class를 사용한다.
	//null과 "" 는 다르다. username= 까지만 하면 빈 문자로 확인해서 ok가 출력된다.
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
			@RequestParam(required = true) String username,
			@RequestParam(required = false) Integer age
	){
		log.info("username={}, age={}",username, age);

		return "ok!";
	}

	//빈 문자의 경우에도 default가 실행된다.
	//default 설정 시 required=false 는 의미가 없다.
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
			@RequestParam(required = true, defaultValue = "guest") String username,
			@RequestParam(required = false) Integer age
	){
		log.info("username={}, age={}",username, age);

		return "ok!";
	}

	//MultiValueMap 은 파라미터 값을 list로 가진다
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap){
		log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

		return "ok!";
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v0")
	public String modelAttributeV0(@RequestParam String username,
	                               @RequestParam int age){
		HelloData helloData = new HelloData();
		helloData.setUsername(username);
		helloData.setAge(age);

		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

		return "ok!";
	}

	//HelloData의 setter를 통해서 입력(바인딩) 한다
	//parameter name을 토대로 username의 경우 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
	//type이 안맞는 경우 BindException이 발생한다.
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute HelloData helloData){

		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

		return "ok!";
	}

	//omit @ModelAttribute..
	//단순 타입,, String, int, Integer 는 RequestParam
	//이외 나머지 타입은 ModelAttribute 가 적용된다. (argument resolver 제외..)
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData){

		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

		return "ok!";
	}
}
