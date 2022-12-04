package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

}
