package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1(){
		ModelAndView mav = new ModelAndView("response/hello")
				.addObject("data", "hello!");

		return mav;
	}

	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model){
		model.addAttribute("data", "hello!");
		return "response/hello";
	}

	//void를 반환하는 경우 경로의 이름과 view 위치의 이름이 같으면 그대로 실행시켜준다.
	@RequestMapping("/response/hello")
	public void responseViewV3(Model model){
		model.addAttribute("data", "hello!");

	}
}
