package com.koreait.core2.controller.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestParamController {

	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
	
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		response.getWriter().write("username : " + username);
		response.getWriter().write("age : " + age);
//		response.getWriter().write("ok");
	}
	
	/*
	 * @ResponseBody
	 *  	- view 조회 ( viewResolver )를 무시하고, HTTP message body에 직접 해당 내용 입력
	 *  ----------------------------------------------------------
	 *  @RequestParam
	 *  	- 파라미터 이름으로 바인딩
	 *  	- form 태그 안의 name 속성이 파라미터 이름으로 사용
	 *  ----------------------------------------------------------
	 *  http://localhost:9090/request-param-v2?username=test&age=20
	 */
		
	@ResponseBody
	@RequestMapping("/request-param-v2")	
	public String requestParamV2(@RequestParam("username") String username , @RequestParam("age") int age)
			throws IOException {
	
//		System.out.println("username : " + username);
//		System.out.println("age : " + age);
		return "username : " + username + "<br>" + "age : " + age;
//		return "ok";
	}
	
	/*
	 *  @RequestParam 사용
	 *  	- HTTP 파라미터 이름이 변수 이름과 같으면
	 *  	@RequestParam("변수이름")생략 가능
	 *  -----------------------------------------------------------
	 *   http://localhost:9090/request-param-v3?username=test&age=20
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-v3")	
	public String requestParamV3(@RequestParam String username , @RequestParam int age) throws IOException {
	
//		System.out.println("username : " + username);
//		System.out.println("age : " + age);
		return "username : " + username + "<br>" + "age : " + age;
//		return "ok";
	}
	
	
	/*
	 * @RequestParam
	 * 	- String, int 등 단순 타입이면 @RequestParam 생략 가능 
	 *  - MVC내부에서 required=false 를 적용한다.
	*/
	@ResponseBody
	@RequestMapping("/request-param-v4")	
	public String requestParamV4(String username , int age) throws IOException {	
//		System.out.println("username : " + username);
//		System.out.println("age : " + age);
		return "username : " + username + "<br>" + "age : " + age;
//		return "ok";
	}
	
	/*
	 *  required = true : 반드시 파라미터 값이 들어와야 한다.
	 *  ----------------------------------------------------
	 *  @RequestParam -> required
	 *  /request-param-required -> username이 없을므로 에러
	 *  /request-param-required?username= -> 빈 문자로 통과
	 *  /request-param-required?username=test -> null을 int에 입력하는 것이 불가능, 따라서 Integer로변경해야함
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-required")	
	public String requestParamRequired(@RequestParam(required=true) String username , @RequestParam(required=false) Integer age) 
			throws IOException {
//		System.out.println("username : " + username);
//		System.out.println("age : " + age);
		return "username : " + username + "<br>" + "age : " + age;
//		return "ok";
	}
	
	/*
	 * defaultBalue
	 * 		- 파라미터 값이 없는 경우 defaultValue를 사용하면 기본 값을 적용 할 수 있다.
	 * 		- 기본값이 있기 때문에 required는 의미가 없다.
	 * 		- 빈 문자열에도 적용 
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")	
	public String requestParamDefault(@RequestParam(required=true , defaultValue = "guest") String username,
			@RequestParam(required=false, defaultValue = "-1") Integer age) 
			throws IOException {
//		System.out.println("username : " + username);
//		System.out.println("age : " + age);
		return "username : " + username + "<br>" + "age : " + age;
//		return "ok";
	}
	
	/*
	 * @RequestParam
	 * 	- Map으로 조회하기
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")	
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) 
			throws IOException {
//		System.out.println("username : " + paramMap.get("username"));
//		System.out.println("age : " + paramMap.get("age"));
		return "username : " + paramMap.get("username") + "<br>" + "age : " + paramMap.get("age");
//		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")	
	public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
		
		HelloData hello = new HelloData();
		hello.setUsername(username);
		hello.setAge(age);
		
//		System.out.println("username : " + hello.getUsername());
//		System.out.println("age : " + hello.getAge());
		return hello.toString();
//		return "ok";
	}
	
	
	/*
	 *  @ModelAtrribute
	 *  	- 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주는 과정을 자동화
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")	
	public String modelAttributeV2(@ModelAttribute HelloData hellodata){
		
//		System.out.println("username : " + hellodata.getUsername());
//		System.out.println("age : " + hellodata.getAge());
//		System.out.println("hello : " + hellodata.toString());
		return hellodata.toString();
//		return "ok";
	}
	
	/*
	 * @ModelAttribute 생략가능
	 * 	- String, int 와 같은 단순 타입 -> @RequestParam
	 * 	- 객체						-> @ModelAttribute
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v3")	
	public String modelAttributeV3( HelloData hellodata){
		
//		System.out.println("username : " + hellodata.getUsername());
//		System.out.println("age : " + hellodata.getAge());
//		System.out.println("hello : " + hellodata.toString());
		return hellodata.toString();
//		return "ok";
	}
}
