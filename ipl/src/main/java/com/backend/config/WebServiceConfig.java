/*
package com.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//@ComponentScan({"com.backend.web.controller"})
public class WebServiceConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

*/
/*InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/webapp/views/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);*//*


		registry.jsp("/META-INF/resources/views/pages/", ".jsp");
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		//registry.addResourceHandler("/webapp/**").addResourceLocations("/webapp/");
	}




}


*/
