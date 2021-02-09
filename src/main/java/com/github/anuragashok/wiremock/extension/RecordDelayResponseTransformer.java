package com.github.anuragashok.wiremock.extension;



import static com.github.anuragashok.wiremock.extension.RecordDelayRequestTransformer.HEADER_KEY_START_TIME;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

/**
 * Implementation of <code>ResponseTransformer</code> extension to register end time,
 * calculate response time and add it to the response header
 */
public class RecordDelayResponseTransformer extends ResponseTransformer {

  public static final String HEADER_KEY_RES_TIME = "X-Response-Time";

  @Override
  public Response transform(Request request, Response response, FileSource files,
      Parameters parameters) {

    if (null != request.getHeaders().getHeader(HEADER_KEY_START_TIME)) {
      long startTime = Long.parseLong(request.getHeaders().getHeader(HEADER_KEY_START_TIME).firstValue());
      long endTime = System.currentTimeMillis();
      int delay = Math.toIntExact(endTime - startTime);
      return Response.Builder.like(response).but().headers(
          response.getHeaders().plus(new HttpHeader(HEADER_KEY_RES_TIME, String.valueOf(delay))))
          .build();
    }

    return response;
  }

  @Override
  public String getName() {
    return "RecordDelayResponseTransformer";
  }

  @Override
  public boolean applyGlobally() {
    return false;
  }
}