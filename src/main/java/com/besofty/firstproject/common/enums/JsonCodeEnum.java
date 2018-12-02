package com.besofty.firstproject.common.enums;

public enum JsonCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(401, "失败"),
    PARAMETER_INVALID(404, "参数不合法"),
    EXISTING(405, "已存在"),
    UNREGISTERED(406, "未注册"),
    TOKEN_NOT_FIND(407, "未找到"),
    OVERTIME(408, "已超时或过期"),
    EMPTY(409, "空数据"),
    PARAMETER_ERROR(410, "参数填写错误"),
    CONFLICT(411, "冲突"),
    BALANCE_NOT_ENOUGH(412, "余额不足"),
    NOT_AUTHORIZATION(413, "没有权限"),
    STATUS_DISABLED(414, "状态不可用"),
    STOP(415, "用户已被禁用"),
    REDIRECT(416, "请重定向返回地址");

    JsonCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * 判断是否是正确返回
     *
     * @param jsonCode 枚举
     * @return 是=true
     */
    public static boolean isSuccess(JsonCodeEnum jsonCode) {
        if (jsonCode != null) {
            if (jsonCode.equals(JsonCodeEnum.SUCCESS)) {
                return true;
            }
        }
        return false;
    }
}
