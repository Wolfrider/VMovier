package com.oliver.vmovier.core.guice;

public class NotFoundTargetClassException extends RuntimeException {

    private Class<?> mTargetClass;

    public NotFoundTargetClassException(Class<?> target) {
        super("create instance failed, cannot find the target class " + target);
        mTargetClass = target;
    }

    public Class<?> getTargetClass() {
        return mTargetClass;
    }
}
