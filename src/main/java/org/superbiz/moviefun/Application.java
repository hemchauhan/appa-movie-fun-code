package org.superbiz.moviefun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by pivotal on 5/1/17.
 */
@SpringBootApplication
public class Application {

    private  ServletRegistrationBean servletRegistrationBean;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean getServletBean(ActionServlet actionServlet)
    {
    return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }
}
