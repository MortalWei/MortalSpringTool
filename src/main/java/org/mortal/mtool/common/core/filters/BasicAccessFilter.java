package org.mortal.mtool.common.core.filters;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mortal.mtool.common.advanced.executer.AsyncAgent;
import org.mortal.mtool.common.advanced.generators.IdGenerator;
import org.mortal.mtool.common.core.Constants;
import org.mortal.mtool.common.entity.AccessLog;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 10:24
 * @description API访问过滤器，主要用于日志响应日志记录。注意需要在启动项目上添加@ServletComponentScan注解
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
@Order(1)
public class BasicAccessFilter extends OncePerRequestFilter {

    private static final List<String> DEFAULT_DOWNLOAD_CONTENT_TYPE = Lists.newArrayList(
            "application/vnd.ms-excel",//.xls
            "application/msexcel",//.xls
            "application/cvs",//.cvs
            MediaType.APPLICATION_OCTET_STREAM_VALUE,//.*（ 二进制流，不知道下载文件类型）
            "application/x-xls",//.xls
            "application/msword",//.doc
            MediaType.TEXT_PLAIN_VALUE,//.txt
            "application/x-gzip"//.gz
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        log.info("BasicAccessFilter doFilterInternal url->{}", request.getRequestURL());
        MDC.put(Constants.MDC_REQUEST_ID, IdGenerator.nextId().toString());

        final String requestMethod = request.getMethod();
        final boolean shouldWrapMethod = StringUtils.equalsIgnoreCase(requestMethod, HttpMethod.PUT.name())
                || StringUtils.equalsIgnoreCase(requestMethod, HttpMethod.POST.name());

        final boolean isFirstRequest = !isAsyncDispatch(request);

        final boolean shouldWrapRequest = isFirstRequest && !(request instanceof ContentCachingRequestWrapper) && shouldWrapMethod;
        final HttpServletRequest requestToUse = shouldWrapRequest ? new ContentCachingRequestWrapper(request) : request;

        final boolean shouldWrapResponse = !(response instanceof ContentCachingResponseWrapper) && shouldWrapMethod;
        final HttpServletResponse responseToUse = shouldWrapResponse ? new ContentCachingResponseWrapper(response) : response;

        final long startTime = System.currentTimeMillis();
        Throwable t = null;
        try {
            filterChain.doFilter(requestToUse, responseToUse);
        } catch (Exception e) {
            log.error("BasicAccessFilter catch->{}", e.getMessage(), e);
            throw e;
        } finally {
            if (!requestToUse.getServletPath().equals("/actuator/health")) {
                final long endTime = System.currentTimeMillis();
                log.info("BasicAccessFilter finally");
                doSaveAccessLog(requestToUse, responseToUse, startTime, endTime, t);
            }
        }
    }

    private void doSaveAccessLog(HttpServletRequest request, HttpServletResponse response, long startTime, long endTime, Throwable t) {
        if (isAsyncStarted(request)) {
            copyResponse(response);
            return;
        }

        try {
            final String requestString = isUpload(request) ? StringUtils.EMPTY : getRequestString(request);
            final String responseString = isDownload(response) ? StringUtils.EMPTY : getResponseString(response);
            log.info("doSaveAccessLog requestString->{}", requestString);
            log.info("doSaveAccessLog responseString->{}", responseString);

            AccessLog accessLog = AccessLog.ok(requestString, responseString, startTime, endTime);
            accessLog.setHttpStatus(response.getStatus());
            accessLog.setPath(request.getServletPath());
            accessLog.setMethodType(request.getMethod());

            AsyncAgent.post(accessLog);
        } catch (Exception e) {

        } finally {
            copyResponse(response);
        }
    }

    private void copyResponse(final HttpServletResponse response) {
        final ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            try {
                wrapper.copyBodyToResponse();
            } catch (IOException ignored) {
            }
        }
    }

    private boolean isUpload(final HttpServletRequest request) {
        final String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        return StringUtils.containsIgnoreCase(contentType, MediaType.MULTIPART_FORM_DATA_VALUE);
    }

    private boolean isDownload(final HttpServletResponse response) {
        final String contentType = response.getContentType();
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        return DEFAULT_DOWNLOAD_CONTENT_TYPE.stream().anyMatch(it -> StringUtils.equalsIgnoreCase(it, contentType));
    }

    private String getRequestString(final HttpServletRequest request) {
        final ContentCachingRequestWrapper contentCachingRequestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (contentCachingRequestWrapper != null) {
            try {
                final byte[] buffer = contentCachingRequestWrapper.getContentAsByteArray();
                return new String(buffer, StandardCharsets.UTF_8.name()).replaceAll("[\n\r]", "");
            } catch (UnsupportedEncodingException e) {
                return "[UNKNOWN]";
            }
        }
        return StringUtils.EMPTY;
    }

    private String getResponseString(final HttpServletResponse response) {
        final ContentCachingResponseWrapper contentCachingResponseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (contentCachingResponseWrapper != null) {
            try {
                final byte[] buffer = contentCachingResponseWrapper.getContentAsByteArray();
                return new String(buffer, StandardCharsets.UTF_8.name()).replaceAll("[\n\r]", "");
            } catch (UnsupportedEncodingException e) {
                return "[UNKNOWN]";
            }
        }
        return StringUtils.EMPTY;
    }
}
