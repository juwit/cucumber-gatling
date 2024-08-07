package io.codeka.glue;

import java.util.Map;

public record HTTPRequestParameters(Map<String, String> headers, Map<String, Object> queryParams) {

}
