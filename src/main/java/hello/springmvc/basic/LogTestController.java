package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
//return 하는 string이 httpbody에 그대로 반환된다.
@RestController
public class LogTestController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest(){
		String name = "Spring";

		System.out.println("name = " + name);

		//중요도에 따른 로그 등급.. trace와 debug는 평상시에 출력되지 않는다.
		//application.properties 에서 보려고 하는 로그의 등급을 표시할 수 있다.
		//설정으로 로그를 상황에 맞게 조절할 수 있다.
		//sout 이 아닌 log 는 파일로 남길 수 있고 분할 등이 가능하다.
		//trace < debug < info < warn < error
		log.trace("trace log={}", name);
		log.debug("debug log={}", name);
		log.info(" info log={}", name);
		log.warn("warn log={}", name);
		log.error("error log={}", name);
		//java에서 +를 사용하면 사용하지 않는 trace에서도 쓸모없는 연산이 발생한다.

		return "ok";
	}


}
