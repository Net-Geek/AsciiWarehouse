package io.github.netgeek.discountasciiwarehouse.util;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Utility class to convert an NDJson ResponseBody to a Json array ResponseBody
 */
public class ResponseBodyUtil {

    /**
     * Converts an NDJson ResponseBody into a Json array ResponseBody
     *
     * @param NDJsonResponseBody the NDJson ResponseBody to be converted
     * @return the Json array formatted response body
     * @throws IOException
     */
    public static ResponseBody convertToJsonArray(ResponseBody NDJsonResponseBody) throws IOException {
        // replace new line delimiters with commas and remove last unneeded comma
        String jsonResponseBodyString = NDJsonResponseBody.string().replaceAll("\n", ",");
        jsonResponseBodyString = jsonResponseBodyString.substring(0, jsonResponseBodyString.length() - 1);

        // add json array syntax
        jsonResponseBodyString = "[" + jsonResponseBodyString + "]";

        return ResponseBody.create(NDJsonResponseBody.contentType(), jsonResponseBodyString);
    }
}
