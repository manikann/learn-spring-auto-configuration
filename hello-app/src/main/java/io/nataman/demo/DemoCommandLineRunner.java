package io.nataman.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DemoCommandLineRunner implements CommandLineRunner {

  private final HelloService helloService;

  @Override
  public void run(String... args) throws Exception {
    helloService.sayHello("spring");
  }
}
