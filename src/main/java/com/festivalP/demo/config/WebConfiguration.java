//package com.festivalP.demo.config;
//
//
//import com.festivalP.demo.filter.AdminFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//
//@Configuration
//public class WebConfiguration {
//
////    @Bean
////    public FilterRegistrationBean logFilter(){
////
////        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
////        filterFilterRegistrationBean.setFilter(new CustomFilter());
////        filterFilterRegistrationBean.setOrder(1);
////        filterFilterRegistrationBean.addUrlPatterns("/*");
////        return filterFilterRegistrationBean;
////    }
//
//    @Bean
//    public FilterRegistrationBean customFilter() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(new AdminFilter());
//        filterFilterRegistrationBean.setOrder(2);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }
//}
