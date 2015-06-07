package com.epam.shop.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 29-Dec-14.
 */
@XmlRootElement(name = "security")
public class SecurityManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityManager.class);

    private List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        return constraints;
    }

    @XmlElement(name = "constraint")
    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    public boolean isPageConstraint(String url) {
        return foundByPage(url) != null;
    }

    public boolean hasAccess(String url, String role) {
        Constraint constraint = foundByPage(url);
        return constraint != null && constraint.getRole().contains(role);
    }

    private Constraint foundByPage(String url) {
        for (Constraint current : constraints) {
            if (current.getUrl().startsWith(url)) {
                return current;
            }
        }
        return null;
    }

    private static class Constraint {
        private String url;
        private List<String> role;

        public String getUrl() {
            return url;
        }

        @XmlElement(name = "url-pattern")
        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getRole() {
            return role;
        }

        @XmlElement(name = "role")
        public void setRole(List<String> role) {
            this.role = role;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Constraint{");
            sb.append("url='").append(url).append('\'');
            sb.append(", role=").append(role);
            sb.append('}');
            return sb.toString();
        }
    }
}
