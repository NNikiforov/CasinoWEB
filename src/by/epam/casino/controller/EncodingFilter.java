package by.epam.casino.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * This filter is used to set the encoding.
 *
 */
public class EncodingFilter implements Filter {
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(EncodingFilter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOGGER.debug(((HttpServletRequest)request).getRequestURL()
				+ ": The request is in the encoding filter.");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
}
