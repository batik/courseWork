package com.epam.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 04-Dec-14.
 */
@WebFilter(filterName = "no-cache", urlPatterns = "/*")
public class FilterNoCaching implements Filter {

    private String CACHE_CONTROL = "Cache-Control";
    private String PRAGMA = "Pragma";
    private String EXPIRES = "Expires";

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(CACHE_CONTROL, "no-store, no-cache, max-age=0");
        httpResponse.setHeader(PRAGMA, "no-store, no-cache");
        httpResponse.setDateHeader(EXPIRES, -1);
        chain.doFilter(request, response);
    }
}
