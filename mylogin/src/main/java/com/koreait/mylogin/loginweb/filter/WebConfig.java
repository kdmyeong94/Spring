package com.koreait.mylogin.loginweb.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koreait.mylogin.loginweb.interceptor.LogInterceptor;
import com.koreait.mylogin.loginweb.interceptor.LoginCheckInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer{

	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor( new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/error");
			
			registry.addInterceptor( new LoginCheckInterceptor())
			.order(2)
			.addPathPatterns("/**") // 모든 경로 전체
			.excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**");
		}
	
//	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		
		filterRegistrationBean.setFilter(new LogFilter()); // LogFilter 등록
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");		// 모든 url 다 적용
		return filterRegistrationBean;
		
	}
	
//	@Bean
	public FilterRegistrationBean loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		
		filterRegistrationBean.setFilter(new LoginCheckFilter()); // LoginCheckFilter 등록
		filterRegistrationBean.setOrder(2);   				// 필터 순서
		filterRegistrationBean.addUrlPatterns("/*");		// 모든 url 다 적용
		return filterRegistrationBean;
		
	}
}
