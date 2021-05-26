package com.testing.demo.controller.filter;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.controller.util.PageResourceManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();
        String loginURI = request.getContextPath() + PageResourceManager.getProperty("key.login");
        String registrationURI = request.getContextPath() + PageResourceManager.getProperty("key.registration");

        boolean loggedIn = session != null && session.getAttribute(AttributesResourceManager.getProperty("parameter.user")) != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean signUpRequest = request.getRequestURI().contains(registrationURI);
        if (loggedIn && (loginRequest || signUpRequest)) {
            request.getRequestDispatcher("/testing/main").forward(request, response);
        } else if (loggedIn || loginRequest || signUpRequest) {
            filterChain.doFilter(request, response);
        } else if (request.getRequestURI().equals(PageResourceManager.getProperty("key.registration"))) {
            log.info("Registration Forward");
            request.getRequestDispatcher(PageResourceManager.getProperty("page.registration")).forward(request, response);
        } else if (request.getRequestURI().equals(PageResourceManager.getProperty("key.login"))) {
            log.info("Login Forward");
            request.getRequestDispatcher(PageResourceManager.getProperty("page.login")).forward(request, response);
        } else {
            response.sendRedirect(PageResourceManager.getProperty("key.login"));
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
