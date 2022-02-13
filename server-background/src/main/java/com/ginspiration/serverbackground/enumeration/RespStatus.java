package com.ginspiration.serverbackground.enumeration;

public enum RespStatus {
    SUCCESS(200,"成功"),
    ERROR400(400,"失败"),
    ERROR404(404,"页面未找到"),
    ;
    private final int status;
    private final String message;

    RespStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
