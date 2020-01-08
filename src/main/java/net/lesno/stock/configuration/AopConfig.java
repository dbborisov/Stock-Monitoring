package net.lesno.stock.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("net.lesno.stock")
public class AopConfig {

}
//quiz.demo.service for comp scan