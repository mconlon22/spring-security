package com.bezkoder.springjwt.security.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,

			AuthenticationException authException) throws IOException, ServletException {
				String ipAddress = request.getHeader("X-FORWARDED-FOR");  
if (ipAddress == null) {  
    ipAddress = request.getRemoteAddr();  
}
System.out.println(ipAddress);
				
		logger.error("Unauthorized error: {}", authException.getMessage());
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error Unauthorized");
	}

}
