package com.oliver.vmovier.core.guice;

public interface NewInstanceFactory {

    <T> T create(Class<T> targetClass);

}
