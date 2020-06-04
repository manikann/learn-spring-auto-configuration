package io.nataman.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import io.nataman.demo.CommandLineHelloService;
import io.nataman.demo.HelloService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@ExtendWith(OutputCaptureExtension.class)
class HelloAutoConfigurationTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));

  @Test
  void defaultServiceIsAutoConfigured(CapturedOutput output) {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(HelloService.class);
          context.getBean(HelloService.class).sayHello("spring-autoconfigure");
          assertThat(output).contains("Hello spring-autoconfigure!");
        });
  }

  @Test
  void defaultServiceBacksOff(CapturedOutput output) {
    contextRunner
        .withUserConfiguration(UserConfiguration.class)
        .withAllowBeanDefinitionOverriding(true)
        .run(
            context -> {
              assertThat(context).hasSingleBean(HelloService.class);
              context.getBean(HelloService.class).sayHello("spring-autoconfigure");
              assertThat(output).contains("Mine spring-autoconfigure**");
            });
  }

  @Test
  void defaulServiceWithPropertyOverride(CapturedOutput output) {
    contextRunner
        .withPropertyValues("hello.prefix=Howdy")
        .run(
            context -> {
              assertThat(context).hasSingleBean(HelloService.class);
              context.getBean(HelloService.class).sayHello("spring-autoconfigure");
              assertThat(output).contains("Howdy spring-autoconfigure!");
            });
  }

  @Configuration
  public static class UserConfiguration {
    @Bean
    public HelloService userConfiguredHelloService() {
      return new CommandLineHelloService("Mine", "**");
    }
  }
}
