package org.wr.tianji.common.result;

import lombok.Data;

import java.io.Serializable;

import static org.wr.tianji.common.constant.CodeConstant.Fail;
import static org.wr.tianji.common.constant.CodeConstant.SUCCESS;

/**
 * 后端统一结果返回
 */
@Data
public class Result<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.success = true;
        return result;
    }
    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.data = object;
        result.success = true;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<T>();
        result.message = msg;
        result.success = false;
        return result;
    }

}
