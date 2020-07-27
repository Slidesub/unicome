package org.unicome.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.unicome.oauth.security.constant.SecurityConsts;

/**
 * WebMvcConfigurerAdapter已失效，推荐使用WebMvcConfigurer
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    SecurityConsts securityConsts;
    /**
     * 视图跳转控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(securityConsts.getDefaultLoginPageUrl()).setViewName("login");
        registry.addViewController(securityConsts.getEmailLoginPageUrl()).setViewName("login/email");
        registry.addViewController(securityConsts.getMobileLoginPageUrl()).setViewName("login/mobile");
        registry.addViewController(securityConsts.getSmsLoginPageUrl()).setViewName("login/sms");
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
