package org.mybatis.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-11  15:17
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CodeEnum {
    OK(200,"成功"),
    FAIL(400,"失败"),
    BAD_REQUEST(400,"请求错误"),
    NOT_FOUND(404,"未找到资源"),
    INTERNAL_ERROR(500,"内部服务错误"),
    MODIFICATION_FAILED(400,"修改失败"),
    DELETION_FAILED(400,"删除失败");
    ;




    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String messgage;
}
