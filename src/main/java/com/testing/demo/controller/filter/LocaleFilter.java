package com.testing.demo.controller.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@WebFilter
public class LocaleFilter implements Filter{
    private Map<String, Locale> languages = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        languages.put("ua",new Locale("ua"));
        languages.put("en",new Locale("en"));
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Do locale filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String path = request.getRequestURI();
        String page = request.getHeader("referer");
        if(path.contains("/language/")){
            String language = path.replaceAll(".*/language/", "");
            session.setAttribute("language", languages.get(language));
            response.sendRedirect(request.getContextPath() + page);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
