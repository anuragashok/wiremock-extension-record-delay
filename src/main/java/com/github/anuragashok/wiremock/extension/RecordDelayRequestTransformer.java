package com.github.anuragashok.wiremock.extension;

import com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction;
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestWrapper;
import com.github.tomakehurst.wiremock.extension.requestfilter.StubRequestFilter;
import com.github.tomakehurst.wiremock.http.Request;


/**
 * Implementation of <code>StubRequestFilter</code> extension to register start time as a header
 */
public class RecordDelayRequestTransformer extends StubRequestFilter {

  public static final String HEADER_KEY_START_TIME = "X-Start-Time";

  @Override
  public RequestFilterAction filter(Request request) {

    Request wrappedRequest = RequestWrapper.create()
        .addHeader(HEADER_KEY_START_TIME, String.valueOf(System.currentTimeMillis()))
        .wrap(request);

    return RequestFilterAction.continueWith(wrappedRequest);
  }

  @Override
  public String getName() {
    return "RecordDelayRequestTransformer";
  }
}
