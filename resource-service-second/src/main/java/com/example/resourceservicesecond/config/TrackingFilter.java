package com.example.resourceservicesecond.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TrackingFilter extends OncePerRequestFilter {

    public static final String CORRELATION_ID = "x-correlation-id";

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final String correlationId = request.getHeader(CORRELATION_ID);
        Assert.notNull(correlationId, String.format("Request must contain header: %s", CORRELATION_ID));
        Assert.isTrue(correlationId.length() > 0, String.format("Request must contain header value: %s", CORRELATION_ID));
        MDC.put(CORRELATION_ID, correlationId);
        filterChain.doFilter(request, response);
    }
}
