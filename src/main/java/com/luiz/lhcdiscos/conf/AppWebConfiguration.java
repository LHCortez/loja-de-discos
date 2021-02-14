package com.luiz.lhcdiscos.conf;

import com.luiz.lhcdiscos.controllers.HomeController;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.CarrinhoCompras;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.services.BandaService;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.annotation.WebServlet;

@Configuration
public class AppWebConfiguration implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // define o local:
        resolver.setPrefix("/WEB-INF/view/");

        // define a extensão das views:
        resolver.setSuffix(".jsp");

        resolver.setExposedContextBeanNames("carrinhoCompras");

        return resolver;
    }

}
