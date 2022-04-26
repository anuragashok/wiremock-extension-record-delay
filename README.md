# wiremock-extension-record-delay
Set of wiremock extensions to record response time as delay during recording / snapshotting.

For the background behind the library and how it works , refer to this blog post https://theoverengineered.blog/posts/capture-response-time-in-wiremock-recordings


### How to use

#### Import the library 

```xml
<dependency>
    <groupId>com.github.anuragashok</groupId>
    <artifactId>wiremock-extension-record-delay</artifactId>
    <version>1.0.8</version>
</dependency>
```

#### Register extensions with wiremock
Use any of the following
```java
options().extensions(RecordDelay.getExtensionClassNames());
options().extensions(RecordDelay.getExtensionClasses());
options().extensions(RecordDelay.getExtensions());
```

### Enable extensions for specific stubs.
All the extensions are non-global and should be enabled only for the recording/snapshotting stubs that forward the request to a real backend.
Example:
```java
wm.stubFor(post(urlMatching("/abc")).atPriority(1)
        .willReturn(aResponse().withTransformers(RecordDelay.getExtensionClassNames()).proxiedFrom("https://www.xyz.com"))))
```

Updated