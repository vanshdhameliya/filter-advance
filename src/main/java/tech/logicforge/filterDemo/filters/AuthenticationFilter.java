package tech.logicforge.filterDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

// ContentCacheResponseWrapper

@Component
@Order(1)
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        if(token == null || !token.equals("12345")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");
            response.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Authentication is required\"\n" +
                            "}"
            );
            return;
        }
        filterChain.doFilter(request, response);
    }
}
