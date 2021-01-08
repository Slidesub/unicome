package org.unicome.sample.design.chain.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "sampleFilter",urlPatterns = {"/sample"})
public class SampleFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(SampleFilter.class);
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("current filter", this.filterConfig.getFilterName());
        logger.info("next filter", chain.getClass().getName());
        // 调用下一个 filter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
