package io.github.netgeek.discountasciiwarehouse.util;

import android.util.Log;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Utility class for converting between an NDJson ResponseBody and a Json array ResponseBody
 */
public class NDJsonBodyUtil {

    /**
     * Converts an NDJson ResponseBody into a Json array ResponseBody
     *
     * @param ndjsonResponseBody the NDJson ResponseBody to be converted
     * @return the Json array formatted ResponseBody
     * @throws IOException
     */
    public static ResponseBody convertToJsonArray(ResponseBody ndjsonResponseBody) throws IOException {
        // replace new line delimiters with comma delimiters and remove last unneeded comma
        String jsonResponseBodyString = ndjsonResponseBody.string().replaceAll("\n", ",");
        jsonResponseBodyString = jsonResponseBodyString.substring(0, jsonResponseBodyString.length() - 1);

        // add json array syntax
        jsonResponseBodyString = "[" + jsonResponseBodyString + "]";

        return ResponseBody.create(ndjsonResponseBody.contentType(), jsonResponseBodyString);
    }

    /**
     * Converts a Json array RequestBody into an NDJson RequestBody
     * @param jsonRequestBody the Json array RequestBody to be converted
     * @return the NDJson formatted RequestBody
     * @throws IOException
     */
    public static RequestBody convertToNDJson(RequestBody jsonRequestBody) throws IOException {
        // remove json array syntax
        String ndjsonRequestBodyString = jsonRequestBody.toString().substring(1, jsonRequestBody.toString().length() - 1);

        // replace comma delimiters with new line delimiters
        ndjsonRequestBodyString = ndjsonRequestBodyString.replaceAll("},", "}\n");
        ndjsonRequestBodyString = ndjsonRequestBodyString.concat("\n");

        return RequestBody.create(jsonRequestBody.contentType(), ndjsonRequestBodyString);
    }
 }
