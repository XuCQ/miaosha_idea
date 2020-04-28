package com.xu.miaosha.result;

/**
 * @program: miaosha_idea
 * @description: 结果类
 * @author: Xu Changqing
 * @create: 2020-04-08 20:58
 **/
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    //成功时调用
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    //失败时调用  cm中无data，不存在类型转换的问题
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;

    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();


    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
