package com.demo.security.infrastructure.validate;

import com.demo.security.infrastructure.base.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author qp
 */
@Aspect
@Component
public class ValidAspect {
    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = VALIDATOR_FACTORY.getValidator().forExecutables();
    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }

    /**
     * 定义切面
     * */
    @Pointcut(value="execution(* com.demo.security.interfaces.facade.*.*(..))")
    public void valid() {
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     * @param pjp 固定参数
     * @return Object
     * @throws Throwable 异常抛出
     */
    @Around("valid()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //方法环绕start
        //取参数，如果没参数，那肯定不校验了
        Object[] objects = pjp.getArgs();
        if (objects.length == 0) {
            return pjp.proceed();
        }
        for (Object object : objects) {
            if (object instanceof BeanPropertyBindingResult) {
                //有校验
                BeanPropertyBindingResult result = (BeanPropertyBindingResult) object;
                if (result.hasErrors()) {
                    List<ObjectError> list = result.getAllErrors();
                    ObjectError error = list.get(0);
                    return JsonResult.error(error.getDefaultMessage());
                }
            }
        }
        //  获得切入目标对象
        Object target = pjp.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            for(ConstraintViolation<Object> constraintViolation : validResult) {
                // 获得校验的参数路径信息
                if(null != constraintViolation){
                    return JsonResult.error(constraintViolation.getMessage());
                }
            }
        }
        return pjp.proceed();
    }

}
