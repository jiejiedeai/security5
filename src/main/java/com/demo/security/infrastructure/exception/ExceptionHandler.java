package com.demo.security.infrastructure.exception;

import com.demo.security.infrastructure.base.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@Order(1)
@RestControllerAdvice(basePackages = "com.demo.security.interfaces.facade")
@Slf4j
public class ExceptionHandler<T> implements ResponseBodyAdvice<T> {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public JsonResult<T> baseException(Exception exception) {
		log.error("",exception);
		if (!StringUtils.hasLength(exception.getMessage())){
			return JsonResult.error(exception.getMessage());
		}
		return JsonResult.error("系统繁忙，请稍后再试");
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		return false;
	}

	@Override
	public T beforeBodyWrite(T body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		return null;
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = CustomerException.class)
	public JsonResult<T> handleCustomerException(CustomerException exception) {
		log.error("",exception);
		return JsonResult.error(exception.getMessage());
	}
}
