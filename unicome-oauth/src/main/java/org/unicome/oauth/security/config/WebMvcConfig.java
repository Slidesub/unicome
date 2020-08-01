package org.unicome.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.unicome.oauth.security.constant.SecurityConstants;

/**
 * WebMvcConfigurerAdapter已失效，推荐使用WebMvcConfigurer
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    SecurityConstants securityConstants;
    /**
     * 视图跳转控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(securityConstants.getDefaultLoginPageUrl()).setViewName("login");
        registry.addViewController(securityConstants.getEmailLoginPageUrl()).setViewName("login/email");
        registry.addViewController(securityConstants.getMobileLoginPageUrl()).setViewName("login/mobile");
        registry.addViewController(securityConstants.getSmsLoginPageUrl()).setViewName("login/sms");
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 允许跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
