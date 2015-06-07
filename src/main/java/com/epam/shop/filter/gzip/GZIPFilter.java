package com.epam.shop.filter.gzip;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 06-Dec-14.
 */
@WebFilter(filterName = "gzip", urlPatterns = "/*")
public class GZIPFilter implements Filter {
    private String ACCEPT_ENCODING = "Accept-Encoding";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptsGZipEncoding(httpRequest)) {
            GZIPResponseWrapper gzipResponse =
                    new GZIPResponseWrapper(httpResponse, "text");
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader(ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }

    @Override
    public void destroy() {

    }
}
