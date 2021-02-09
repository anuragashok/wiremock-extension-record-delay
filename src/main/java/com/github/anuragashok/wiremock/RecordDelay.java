package com.github.anuragashok.wiremock;

import com.github.anuragashok.wiremock.extension.RecordDelayRequestTransformer;
import com.github.anuragashok.wiremock.extension.RecordDelayResponseTransformer;
import com.github.anuragashok.wiremock.extension.RecordDelayStubMappingTransformer;
import com.github.tomakehurst.wiremock.extension.Extension;


/**
 * Helper Utility to return list of extensions to be registered with wiremock.
 * <p>
 * Example usage<br>
 * <code>options().extensions(RecordDelay.getExtensionClassNames());</code><br>
 * <code>options().extensions(RecordDelay.getExtensionClasses());</code><br>
 * <code>options().extensions(RecordDelay.getExtensions());</code><br>
 * </p>
 *
 * @see <a href="http://wiremock.org/docs/extending-wiremock">Extending Wiremock</a>
 */
public class RecordDelay {

  /**
   * @return list of extensions as string array of class names
   */
  public static String[] getExtensionClassNames() {
    return new String[]{"com.github.anuragashok.wiremock.extension.RecordDelayRequestTransformer",
        "com.github.anuragashok.wiremock.extension.RecordDelayResponseTransformer",
        "com.github.anuragashok.wiremock.extension.RecordDelayStubMappingTransformer"};
  }

  /**
   * @return list of extensions as array of classes
   */
  public static Class<?>[] getExtensionClasses() {
    return new Class[]{RecordDelayRequestTransformer.class, RecordDelayResponseTransformer.class,
        RecordDelayStubMappingTransformer.class};
  }

  /**
   * @return list of extensions as an array of instances
   */
  public static Extension[] getExtensions() {
    return new Extension[]{new RecordDelayRequestTransformer(),
        new RecordDelayResponseTransformer(), new RecordDelayStubMappingTransformer()};
  }

}
