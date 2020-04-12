package com.xu.miaosha.validator;

import com.xu.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: miaosha_idea
 * @description: 验证手机号实现类
 * @author: Xu Changqing
 * @create: 2020-04-12 12:31
 **/
public class isMobileValidator implements ConstraintValidator<isMoblie, String> {

    private boolean required = false;

    /**
     * 初始化拿到注解
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(isMoblie constraintAnnotation) {

        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
