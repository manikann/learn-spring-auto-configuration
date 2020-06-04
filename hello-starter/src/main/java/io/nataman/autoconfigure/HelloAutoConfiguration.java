package io.nataman.autoconfigure;

import io.nataman.demo.CommandLineHelloService;
import io.nataman.demo.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnProperty(name="hello.enabled", havingValue = "true", matchIfMissing = true)
public class HelloAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public HelloService autoConfiguredHelloService(HelloProperties helloProperties) {
    return new CommandLineHelloService(helloProperties.getPrefix(), helloProperties.getSuffix());
  }
}
