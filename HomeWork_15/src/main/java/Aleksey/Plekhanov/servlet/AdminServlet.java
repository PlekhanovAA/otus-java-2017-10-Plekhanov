package Aleksey.Plekhanov.servlet;

import Aleksey.Plekhanov.app.FrontendService;
import Aleksey.Plekhanov.app.MessageSystemContext;
import Aleksey.Plekhanov.cache.CacheEngineImpl;
import Aleksey.Plekhanov.dbService.DbDispatcher;
import Aleksey.Plekhanov.dbService.MsgSysCacheSizeRequest;
import Aleksey.Plekhanov.messageSystem.Address;
import Aleksey.Plekhanov.messageSystem.Message;
import Aleksey.Plekhanov.messageSystem.MessageSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet implements FrontendService {

    private static final Logger log = LogManager.getLogger();
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private Address address;
    private int hitCount;
    private int missCount;
    private int size;


    private MessageSystemContext messageSystemContext;

    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        messageSystemContext = (MessageSystemContext) context.getBean("messageSystemContext");
        address = messageSystemContext.getFrontAddress();
        messageSystemContext.getMessageSystem().addAddressee(this);

        messageSystemContext.getMessageSystem().start();
        CacheEngineImpl cacheEngine = (CacheEngineImpl) context.getBean("cacheEngine");
        DbDispatcher dbDispatcher = (DbDispatcher) context.getBean("dbDispatcher");
        dbDispatcher.startDb(messageSystemContext, address, cacheEngine);

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("missCount", missCount);
        pageVariables.put("hitCount", hitCount);
        pageVariables.put("size", size);
        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        if (login == null || !login.equals("admin")) {
            response.sendRedirect("login");
        }
        getCacheStats();

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        response.getWriter().println(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void getCacheStats() {
        Message message = new MsgSysCacheSizeRequest(getAddress(), messageSystemContext.getDbAddress());
        messageSystemContext.getMessageSystem().sendMessage(message);
    }

    @Override
    public void setCacheStats(int hitCount, int missCount, int size){
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.size = size;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return messageSystemContext.getMessageSystem();
    }
}
