package io.github.netgeek.asciiwarehouse.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * A Converter.Factory in which uses GSON for JSON
 */
public final class NDJsonConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static NDJsonConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static NDJsonConverterFactory create(Gson gson) {
        return new NDJsonConverterFactory(gson);
    }

    private final Gson gson;

    private NDJsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    /**
     * Create an NDJsonResponseBodyConverter for converting an HTTP response body to {@code type}
     */
    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new NDJsonResponseBodyConverter<>(gson, type);
    }

    /**
     * Create an NDJsonRequestBodyConverter for converting {@code type} to an HTTP response body
     */
    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new NDJsonRequestBodyConverter<>(gson, type);
    }
}
