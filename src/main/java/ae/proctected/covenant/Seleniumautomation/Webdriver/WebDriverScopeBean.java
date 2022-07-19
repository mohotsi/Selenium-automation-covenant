package ae.proctected.covenant.Seleniumautomation.Webdriver;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebDriverScopeBean {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new WebDriverBeanFactoryPostProcessor();
    }
}

