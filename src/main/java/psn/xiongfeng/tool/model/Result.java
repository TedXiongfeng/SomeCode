package psn.xiongfeng.tool.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 *
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    //声明接口来控制返回对象中的成员属性，接口之间可以互相继承
    //Controller使用该注解则使用相应的成员变量
    @JsonView
    private interface WithoutData {
    }

    //于成员属性的get方法使用注解并注明配置的接口名称
    @JsonView(WithoutData.class)
    public Integer getCode() {
        return code;
    }

    @JsonView(WithoutData.class)
    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    private Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
