package io.nataman.demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandLineHelloService implements HelloService {

  private final String prefix;
  private final String suffix;

  @Override
  public void sayHello(String name) {
    System.out.println(String.format("%s %s%s", prefix, name, suffix));
  }
}
