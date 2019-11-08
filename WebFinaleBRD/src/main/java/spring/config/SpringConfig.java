package spring.config;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("spring")
@EnableWebMvc
public class SpringConfig {

	@Bean
	public InternalResourceViewResolver getResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public LocalSessionFactoryBean getBean(){
		LocalSessionFactoryBean lb = new LocalSessionFactoryBean();
		lb.setDataSource(getDataSource());
		Resource res = new ClassPathResource("hibernate.cfg.xml");
		lb.setConfigLocation(res);
		return lb;
	}
	
	@Bean
	 public ReloadableResourceBundleMessageSource messageSource(){
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("/WEB-INF/error/errorfile");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@10.1.50.198:1521:orcl");
		ds.setUsername("sh");
		ds.setPassword("sh");
		return ds;
	}
	
}
