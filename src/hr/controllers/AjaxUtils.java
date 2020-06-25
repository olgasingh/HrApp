package hr.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.WebRequest;

public class AjaxUtils {
	public static boolean isAjaxRequest(WebRequest webRequest) {

		String requestedWith = webRequest.getHeader("X-Requested-With");
		// String requestedWith = webRequest.getHeader("Faces-Request");
		if ("partial/ajax".equals(requestedWith)) {
			return true;
		}

		requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith)
				: false;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request
				.getHeader("X-Requested-With"));
	}

}
