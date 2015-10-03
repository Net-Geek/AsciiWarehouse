package io.github.netgeek.discountasciiwarehouse.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import io.github.netgeek.discountasciiwarehouse.util.NDJsonBodyUtil;
import okio.Buffer;
import retrofit.Converter;

final class NDJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType NDJSON_MEDIA_TYPE = MediaType.parse("application/ndjson; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final Type type;

    NDJsonRequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        try {
            gson.toJson(value, type, writer);
            writer.flush();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        return NDJsonBodyUtil.convertToNDJson(RequestBody.create(NDJSON_MEDIA_TYPE, buffer.readByteString()));
    }
}