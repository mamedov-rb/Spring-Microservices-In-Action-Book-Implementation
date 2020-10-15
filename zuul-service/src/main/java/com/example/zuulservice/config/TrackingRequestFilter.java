package com.example.zuulservice.config;

import com.example.zuulservice.util.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackingRequestFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;

    private final FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        String correlationId;
        if (filterUtils.getCorrelationId() == null) {
            filterUtils.setCorrelationId(UUID.randomUUID().toString());
            correlationId = filterUtils.getCorrelationId();
            log.info("Generated x-correlation-id: {} for request.", filterUtils.getCorrelationId());
        } else {
            correlationId = filterUtils.getCorrelationId();
            log.info("Request contains x-correlation-id: {}.", correlationId);
        }
        MDC.put(FilterUtils.CORRELATION_ID, correlationId);
        return null;
    }
}
