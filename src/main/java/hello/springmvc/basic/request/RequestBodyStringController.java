package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody={}", messageBody);

		response.getWriter().write("ok!");
	}

	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);
		responseWriter.write("ok!");
	}

	//HttpEntity : HTTP header, body 정보를 편리하게 조회한다. 요청파라미터를 조회하는 기능과 관계 없다!
	//HttpEntity 는 응답에도 사용 가능하다. 메세지 바디 정보 직접 반환, 헤더 정보 포함 가능, view 조회 가능!
	//HttpEntity 를 상속받은 RequestEntity, ResponseEntity 가 존재한다.
	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
		String messageBody = httpEntity.getBody();
		log.info("messageBody={}", messageBody);

		return new HttpEntity<>("ok!");
	}

	//@RequestBody, @ResponseBody
	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
		log.info("messageBody={}", messageBody);

		return "ok!";
	}
}
