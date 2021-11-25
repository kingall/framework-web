package com.lighting.framework.security;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class TokenAdapterSession extends DefaultWebSessionManager {
	private static final String TOKEN = "token";

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		String token = WebUtils.toHttp(request).getHeader(TOKEN);
		// 如果请求头中有 token 则其值为sessionId
		if (!StringUtils.isEmpty(token)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return token;
		} else {
			// 否则按默认规则从cookie取sessionId
			return super.getSessionId(request, response);
		}
	}
}
