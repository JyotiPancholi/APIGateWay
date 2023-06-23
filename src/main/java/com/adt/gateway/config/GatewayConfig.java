package com.adt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adt.gateway.routers.AuthenticationFilter;

@Configuration
public class GatewayConfig {
	
	@Value("${hrms.service.url}")
	private String hrmsUrl;
	
	@Value("${auth.service.url}")
	private String authUrl;
	
	@Value("${payroll.service.url}")
	private String payrollUrl;
	
	@Value("${expense.service.url}")
	private String expenseUrl;
	
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()	
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri(authUrl)) 
                
                .route("auth-service", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri(authUrl))
                
                .route("position_Module", r -> r.path("/hrms/**")
                        .filters(f -> f.filter(filter))
                        .uri(hrmsUrl))
                
                .route("hrms-payroll", r -> r.path("/payroll/**")
                        .filters(f -> f.filter(filter))
                        .uri(payrollUrl))
                
                .route("expense-service", r -> r.path("/expensemanagement/**")
                		.filters(f -> f.filter(filter))
                        .uri(expenseUrl))
                .build();
    }

}
