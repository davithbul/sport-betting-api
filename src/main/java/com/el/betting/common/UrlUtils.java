package com.el.betting.common;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class UrlUtils {

    public static String UTF_8 = "UTF-8";

    public static String encodeURL(String url) {
        String[] parts = StringUtils.split(url, "=");
        StringBuilder encodedURL = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            try {
                String encodePart = URLEncoder.encode(parts[i], UTF_8);
                encodedURL.append(encodePart);
                if (i != parts.length - 1) {
                    encodedURL.append("=");
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return encodedURL.toString();
    }

    public static String decodeURL(String url) {
        try {
            return URLDecoder.decode(url, UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String addParameters(String url, Map<String, Object> parameters) {
        if(parameters.isEmpty()) {
            return url;
        }

        if(!url.contains("?")) {
            url += "?";
        } else {
            url += "&";
        }

        Iterator<Map.Entry<String, Object>> iterator = parameters.entrySet().iterator();
        Map.Entry<String, Object> parameterEntry = iterator.next();
        url += parameterEntry.getKey() + "=" + parameterEntry.getValue();

        while (iterator.hasNext()) {
            parameterEntry = iterator.next();
            url += "&" + parameterEntry.getKey() + "=" + parameterEntry.getValue();
        }

        return  url;
    }

    public static Map<String, String> getUrlParams(String url) {
        if(url.contains("?")) {
            url = url.substring(url.indexOf("?") + 1).trim();
        }

        return Splitter.on("&")
                .omitEmptyStrings()
                .trimResults()
                .withKeyValueSeparator(Splitter.on('=').limit(2))
                .split(url);

    }
}
