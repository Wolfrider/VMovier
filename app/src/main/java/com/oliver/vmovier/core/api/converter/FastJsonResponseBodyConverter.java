package com.oliver.vmovier.core.api.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;

import android.support.annotation.Nullable;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type mType;

    FastJsonResponseBodyConverter(Type type) {
        mType = type;
    }

    @Nullable
    @Override
    public T convert(ResponseBody value) throws IOException {
        try (BufferedSource bufferedSource = Okio.buffer(value.source())) {
            return JSON.parseObject(bufferedSource.readUtf8(), mType);
        }
    }
}
