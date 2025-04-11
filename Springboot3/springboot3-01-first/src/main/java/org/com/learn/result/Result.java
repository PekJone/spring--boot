package org.com.learn.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-11  14:42
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
   private int code;
   private String msg ;

   private T data;

   //为了方便返回数据 这里的方法一直是静态方法
    // 方法一般包括两大类  ，一半是成功 ，一半是失败

    public static <T> Result<T> OK(){
        return Result.<T>builder().code(CodeEnum.OK.getCode()).msg(CodeEnum.OK.getMessgage()).build();
    };


    public static <T> Result<T> OK(T data){
        return Result.<T>builder().code(CodeEnum.OK.getCode()).msg(CodeEnum.OK.getMessgage()).data(data).build();
    };


    public static <T> Result<T> FAIL(){
        return Result.<T>builder().code(CodeEnum.FAIL.getCode()).msg(CodeEnum.FAIL.getMessgage()).build();
    };


    public static <T> Result<T> FAIL(CodeEnum codeEnum){
        return Result.<T>builder().code(codeEnum.getCode()).msg(codeEnum.getMessgage()).build();
    };

}
