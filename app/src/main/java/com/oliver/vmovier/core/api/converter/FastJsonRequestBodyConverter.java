package com.oliver.vmovier.core.api.converter;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import android.support.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    FastJsonRequestBodyConverter() {

    }

    @Nullable
    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
