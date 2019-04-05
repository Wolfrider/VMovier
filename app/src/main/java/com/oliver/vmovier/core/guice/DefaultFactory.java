package com.oliver.vmovier.core.guice;

public class DefaultFactory implements NewInstanceFactory {

    @Override
    public <T> T create(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException("create instance failed, " + targetClass, ex);
        }
    }
}
