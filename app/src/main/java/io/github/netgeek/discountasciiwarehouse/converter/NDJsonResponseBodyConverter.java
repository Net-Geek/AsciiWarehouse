package io.github.netgeek.discountasciiwarehouse.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import io.github.netgeek.discountasciiwarehouse.util.NDJsonBodyUtil;
import retrofit.Converter;

final class NDJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    NDJsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        value = NDJsonBodyUtil.convertToJsonArray(value);
        Reader reader = value.charStream();
        try {
            return gson.fromJson(reader, type);
        } finally {
           closeReader(reader);
        }
    }

    static void closeReader(Closeable reader){
        if (reader == null) return;
        try {
            reader.close();
        } catch (IOException ignored) {
        }
    }
}
