package com.epam.shop.filter;

import com.epam.shop.domain.User;
import com.epam.shop.security.SecurityManager;
import com.epam.shop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 29-Dec-14.
 */
@WebFilter(filterName = "security", urlPatterns = "/*",
        initParams =
        @WebInitParam(
                name = "securityFile",
                value = "D:\\git-repos\\cloud-trainings\\Mongo-shop\\Spring-shop\\src\\main\\resources\\security.xml"
        )
)
public class SecurityAccess implements Filter, Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAccess.class);
    private static final String SECURITY_PROPERTIES = "securityFile";
    private SecurityManager securityManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SecurityManager.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            securityManager = (SecurityManager) jaxbUnmarshaller.unmarshal(new File(filterConfig.getInitParameter(SECURITY_PROPERTIES)));
        } catch (JAXBException e) {
            LOGGER.error("Unhandled JAXBException. Your security file is incorrect.", e);
            throw new RuntimeException();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (securityManager.isPageConstraint(req.getRequestURI())) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (securityManager.hasAccess(req.getRequestURI(), user.getUserDetails().getRole())) {
                    chain.doFilter(request, response);
                } else {
                    resp.sendError(403);
                }
            } else {
                request.setAttribute("referrer", req.getRequestURI());
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
