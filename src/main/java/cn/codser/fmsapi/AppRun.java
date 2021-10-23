package cn.codser.fmsapi;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

@EnableAsync
@EnableTransactionManagement
@MapperScan(basePackages = {"cn.codser.fmsapi.mapper"})
@SpringBootApplication
public class AppRun {
	private static final Logger log = LoggerFactory.getLogger(AppRun.class);

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		String tmp = System.getProperty("java.io.tmpdir")+"fms";
		log.info("fms-api-tmp->["+tmp+"]");
		factory.setLocation(tmp);
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppRun.class, args);
	}

}
