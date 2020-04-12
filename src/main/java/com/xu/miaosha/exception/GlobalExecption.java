package com.xu.miaosha.exception;

import com.xu.miaosha.result.CodeMsg;

/**
 * @program: miaosha_idea
 * @description: 定义了一个全局业务异常
 * @author: Xu Changqing
 * @create: 2020-04-12 17:21
 **/
public class GlobalExecption extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;

    public GlobalExecption(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public void setCm(CodeMsg cm) {
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
