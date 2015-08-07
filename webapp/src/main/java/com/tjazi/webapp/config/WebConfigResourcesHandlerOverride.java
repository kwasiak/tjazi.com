package com.tjazi.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * Created by kwasiak on 12/07/15.
 */
@Configuration
public class WebConfigResourcesHandlerOverride extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebConfigResourcesHandlerOverride.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info("Adding web paths (templates, templatessecure, etc.)...");

        // static content handling
        registry.addResourceHandler("/templates/**").addResourceLocations("/WEB-INF/templates/");
        registry.addResourceHandler("/templatessecure/**").addResourceLocations("/WEB-INF/templatessecure/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/fonts/");

        // configure version-related naming convention for JS and CSS files
        registry.addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("/WEB-INF/css/", "/WEB-INF/js/")

                /* Update for production - set cache and resourceChain to 'true' */
                .setCachePeriod(0)
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }
}
