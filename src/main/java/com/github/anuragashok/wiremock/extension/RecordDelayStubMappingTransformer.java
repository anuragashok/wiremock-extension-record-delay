package com.github.anuragashok.wiremock.extension;


import static com.github.anuragashok.wiremock.extension.RecordDelayResponseTransformer.HEADER_KEY_RES_TIME;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.StubMappingTransformer;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

/**
 * Implementation of <code>StubMappingTransformer</code> extension to add the response time as delay
 * in the wiremock stub mapping
 */
public class RecordDelayStubMappingTransformer extends StubMappingTransformer {

  @Override
  public StubMapping transform(StubMapping stubMapping, FileSource fileSource,
      Parameters parameters) {

    if (null != stubMapping.getResponse().getHeaders().getHeader(HEADER_KEY_RES_TIME)) {
      int delay = Integer.parseInt(
          stubMapping.getResponse().getHeaders().getHeader(HEADER_KEY_RES_TIME).firstValue());
      final ResponseDefinition newResponseDefinition = ResponseDefinitionBuilder
          .like(stubMapping.getResponse()).but().withFixedDelay(delay).build();
      stubMapping.setResponse(newResponseDefinition);
    }

    return stubMapping;
  }

  @Override
  public boolean applyGlobally() {
    return false;
  }

  @Override
  public String getName() {
    return "RecordDelayStubMappingTransformer";
  }

}
