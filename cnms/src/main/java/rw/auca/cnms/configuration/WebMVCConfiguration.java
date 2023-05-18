package rw.auca.cnms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry)  {

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/access-denied").setViewName("access-denied");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/serving").setViewName("serving");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/about").setViewName("about");

    }
}
