//package net.lesno.stock.configuration;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
//@Configuration
//@EnableAsync
//public class AsyncConfig {
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(AsyncConfig.class, args).close();
//	}
//
//	@Bean
//	public Executor asyncExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(5);
//		executor.setMaxPoolSize(5);
//		executor.setQueueCapacity(500);
//		executor.setThreadNamePrefix("AsyncJob-");
//		executor.initialize();
//		return executor;
//	}
//
//}
