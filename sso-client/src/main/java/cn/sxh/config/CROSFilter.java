//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
///**
// * @Description:
// * @author: sxh
// * @Date: 2018/10/20
// */
//@Configuration
//public class CROSFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
////    @Override
////    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
//////        设置请求头为请求的域名：当前是 http://localhost:8080
////        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
////        System.out.println(request.getHeader("Origin"));
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        // 设置允许访问的请求头
////        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
//////      设置是否加入用户凭证，true/false
////        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
////        filterChain.doFilter(request, response);
////
////    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
