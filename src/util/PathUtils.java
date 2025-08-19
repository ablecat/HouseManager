package util;

import javax.servlet.http.HttpServletRequest;

public class PathUtils {

	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() +"://" + request.getServerName() +":"+request.getServerPort()+path+"/";
		return basePath;
	}

	public static String getServletPath() {
		String servletPath = "http://localhost:8080/untitled1_war_exploded/";
		return servletPath;
	}
}
