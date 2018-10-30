package cn.com.isurpass.iremotemessager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "cn.com.isurpass.iremotemessager.dao")
@EntityScan(basePackages = "cn.com.isurpass.iremotemessager.domain")
@SpringBootApplication
@ComponentScan
public class IremotemessagerApplication
{
	@SuppressWarnings("unused")
	@Autowired
	private SpringUtil springutil;  //ensure that SpringUtil will be initialize first ;

	public static void main(String[] args)
	{
		SpringApplication.run(IremotemessagerApplication.class, args);
	}
}
