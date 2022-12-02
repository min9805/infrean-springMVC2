package hello.springmvc.basic.requestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
	public String helloBasic(){
		log.info("helloBasic");
		return "ok";
	}

	/**
	 * PathVariable 사용
	 * 변수명이 같으면 생략 가능
	 * @PathVariable("userId") String userId -> @PathVariable userId
	 */
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String data){
		log.info("mappingPath userId={}", data);
		return "ok";
	}

	/**
	 * PathVariable 다중 사용
	 *
	 * @param userId
	 * @param orederId
	 * @return
	 */
	@GetMapping("/mapping/{userId}/orders/{orderId}")
	public String mappingPath(@PathVariable String userId, @PathVariable Long orederId){
		log.info("mappingPath userId={}, orderId={}", userId, orederId);
		return "ok";
	}

	/**
	 * 파라미터로 추가 매핑
	 * params="mode"
	 * params="!mode"
	 * params="mode=debug"
	 * params="mode!=debug"
	 * params= {"mode=debug", "data=good"}
	 *
	 */
	@GetMapping(value = "/mapping-param", params = "mode=debug")
	public String mappingParam(){
		log.info("mappingParam");
		return "ok";
	}

	/**
	 * 특정 헤더로 추가 매핑
	 * headers="mode"
	 * headers="!mode"
	 * headers="mode=debug"
	 * headers="mode!=debug"
	 */
	@GetMapping(value = "/mapping-header", headers = "mode=debug")
	public String mappingHeader(){
		log.info("mappingHeader");
		return "ok";
	}

	/**
	 * Content-Type 헤더 기반 추가 매핑 Media type
	 * consumes="application/json"
	 * consumes="!application/json"
	 * consumes="application/*"
	 * consumes="*\/*"
	 * MediaType.APPLICATION_JSON_VALUE
	 */
	@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String mappingConsumes(){
		log.info("mappingConsumes");
		return "ok";
	}
}
