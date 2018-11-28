package com.javacourse.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Filter for setting UTF-8 as a default encoding
 */

@WebFilter(filterName = "EncodingFilter",
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name="encoding", value = "UTF-8", description = "Page encoding")})
public class EncodingFilter implements Filter {
    private String code;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String codeRequest = req.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            req.setCharacterEncoding(code);
            resp.setCharacterEncoding(code);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter("encoding");
    }

}
