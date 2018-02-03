package Aleksey.Plekhanov.servlet;

import Aleksey.Plekhanov.cache.CacheInfo;
import Aleksey.Plekhanov.dbService.DBServiceImpl;
import Aleksey.Plekhanov.dbService.DbDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        CacheInfo cacheInfo = DbDispatcher.getCacheService();
        pageVariables.put("missCount", cacheInfo.getMiss());
        pageVariables.put("hitCount", cacheInfo.getHit());
        pageVariables.put("size", cacheInfo.getSize());
        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        if (!pageVariables.get("login").equals("admin")) {
            response.sendRedirect("login");
        }
        response.getWriter().println(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
