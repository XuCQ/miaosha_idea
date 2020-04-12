package com.xu.miaosha.exception;

import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: miaosha_idea
 * @description: 全局异常信息处理
 * @author: Xu Changqing
 * @create: 2020-04-12 12:43
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        logger.info(e);
        if (e instanceof GlobalExecption) {
            GlobalExecption ex = (GlobalExecption) e;
            return Result.error(ex.getCm());
        }
        //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
        else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
