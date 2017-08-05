package com.hust.weixin.exception;

/**
 * Created by Administration on 2016/5/14.
 */
public class SysException extends RuntimeException {
    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }
}
