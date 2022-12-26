package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 */

@Slf4j
@Controller
public class RequestBodyJsonController {

	private ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/request-body-json-v1")
	public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBod={}",messageBody);
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
		log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

		response.getWriter().write("ok!");
	}

	@ResponseBody
	@PostMapping("/request-body-json-v2")
	public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

		log.info("messageBod={}",messageBody);
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
		log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

		return "ok!";
	}

	//@RequestBody 생략시 @ModelAttribute 가 자동으로 붙기 때문에 파라미터값을 처리하지 못한다
	@ResponseBody
	@PostMapping("/request-body-json-v3")
	public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

		log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

		return "ok!";
	}

	@ResponseBody
	@PostMapping("/request-body-json-v4")
	public String requestBodyJsonV4(HttpEntity<HelloData> helloDataHttpEntity) throws IOException {

		HelloData helloData = helloDataHttpEntity.getBody();
		log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

		return "ok!";
	}

	//JSON -> obj -> JSON
	@ResponseBody
	@PostMapping("/request-body-json-v5")
	public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

		log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

		return helloData;
	}
}
