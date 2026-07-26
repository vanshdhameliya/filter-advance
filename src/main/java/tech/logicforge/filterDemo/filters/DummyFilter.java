package tech.logicforge.filterDemo.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(3)
public class DummyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;

        String uri = httpServletRequest.getRequestURI();

        if(!uri.startsWith("/api/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        System.out.println("Dummy filter called");

        filterChain.doFilter(servletRequest, servletResponse);

    }
}