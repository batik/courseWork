package com.epam.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 31-Oct-14.
 */
@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class FilterEncoding implements Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding(DEFAULT_ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
