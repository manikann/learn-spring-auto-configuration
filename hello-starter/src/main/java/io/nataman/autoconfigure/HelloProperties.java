package io.nataman.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("hello")
public class HelloProperties {
  /** Hello enable? */
  private boolean enabled = true;
  /** Hello prefix */
  private String prefix = "Hello";
  /** Hello suffix */
  private String suffix = "!";
}
