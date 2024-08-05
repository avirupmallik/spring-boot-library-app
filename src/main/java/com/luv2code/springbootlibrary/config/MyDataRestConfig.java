package com.luv2code.springbootlibrary.config;

import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST,HttpMethod.PATCH,HttpMethod.DELETE,HttpMethod.PUT};
        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
        disabledHttpMethods(Book.class,config,theUnsupportedActions);
        disabledHttpMethods(Review.class,config,theUnsupportedActions);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }

    private void disabledHttpMethods(Class clazz, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {

    config.getExposureConfiguration().forDomainType(clazz).withItemExposure((metdata,httpmethod) ->
        httpmethod.disable(theUnsupportedActions)).withCollectionExposure((metdata,httpmethod) ->
            httpmethod.disable(theUnsupportedActions));

    }
}
