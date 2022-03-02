package uz.pdp.traindemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/images/**",
                        "/assets/css/**",
                        "/assets/fonts/**",
                        "/assets/js/**",
                        "/assets/pages/**",
                        "/assets/partials/**",
                        "/assets/scss/**",
                        "/assets/vendors/**",
                        "/assets/libs/**")
                .addResourceLocations(
                        "classpath:/templates/assets/img/",
                        "classpath:/templates/assets/fonts/",
                        "classpath:/templates/assets/js/",
                        "classpath:/templates/pages/",
                        "classpath:/templates/docs/",
                        "classpath:/templates/assets/scss/",
                        "classpath:/templates/assets/css/");
    }

}
