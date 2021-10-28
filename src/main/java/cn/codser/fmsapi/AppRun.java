package cn.codser.fmsapi;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;

@Api(hidden = true)
@EnableAsync
@EnableTransactionManagement
@MapperScan(basePackages = {"cn.codser.fmsapi.mapper"})
@SpringBootApplication
@RestController
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


	@GetMapping("/")
	public String index() {
		return "[fms-api]服务启动成功!";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppRun.class, args);
	}

}
