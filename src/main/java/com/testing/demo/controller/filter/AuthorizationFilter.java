package com.testing.demo.controller.filter;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.controller.util.PageResourceManager;
import com.testing.demo.model.entity.User;
import com.testing.demo.model.entity.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Do filter");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        String path = req.getRequestURI();
        if (path.contains("admin")) {
            if (user.getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourceManager.getProperty("page.not.permissions"));
            }
        } else if (path.contains("student")) {
            if (user.getEmail()!=null && user.getRole().equals(Role.STUDENT)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourceManager.getProperty("page.not.permissions"));
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
