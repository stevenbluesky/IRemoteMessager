package cn.com.isurpass.iremotemessager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.MultipartConfigElement;

@EnableJpaRepositories(basePackages = "cn.com.isurpass.iremotemessager.dao")
@EntityScan(basePackages = "cn.com.isurpass.iremotemessager.domain")
@SpringBootApplication
public class IremotemessagerApplication
{
	@SuppressWarnings("unused")
	@Autowired
	private SpringUtil springutil;  //ensure that SpringUtil will be initialize first ;

	public static void main(String[] args)
	{
		SpringApplication.run(IremotemessagerApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大20M
		factory.setMaxFileSize("20480KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("20480KB");
		return factory.createMultipartConfig();
	}
}
