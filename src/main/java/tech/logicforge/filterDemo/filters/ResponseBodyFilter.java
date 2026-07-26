package tech.logicforge.filterDemo.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(2)
public class ResponseBodyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(request, wrappedResponse);
            modifyResponseBody(wrappedResponse);

        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void modifyResponseBody(ContentCachingResponseWrapper response) throws IOException {

        String originalBody = getOriginalBody(response);
        String modifiedBody = createModifiedBody(originalBody);

        response.resetBuffer();
        response.getWriter().write(modifiedBody);
    }

    private String getOriginalBody(ContentCachingResponseWrapper response) {

        byte[] bodyBytes = response.getContentAsByteArray();
        return new String(bodyBytes, StandardCharsets.UTF_8);
    }

    private String createModifiedBody(String originalBody) {

        return """
                {
                    "originalResponse": %s,
                    "appName": "Student Management System"
                }
                """.formatted(originalBody);
    }
}