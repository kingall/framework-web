package com.lighting.framework.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VuePermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = getSubject(request, response);
		String unauthorizedUrl = getUnauthorizedUrl();

		if (StringUtils.hasText(unauthorizedUrl)) {
			WebUtils.issueRedirect(request, response, unauthorizedUrl);
		} else {
			WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return super.onAccessDenied(request, response);
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		return super.isAccessAllowed(request, response, mappedValue);
	}
}
