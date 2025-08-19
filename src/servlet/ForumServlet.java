package servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.*;
import dao.*;

import org.apache.commons.lang3.StringUtils;
import util.PageInfo;
import util.PathUtils;

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        System.out.println("method="+method);
        if("getForum".equals(method)) {
            this.getForum(request,response);
        } else if("leaveMessage".equals(method)) {
            this.leaveMessage(request,response);
        } else if("discuss".equals(method)) {
            this.discuss(request,response);
        } else if("reply".equals(method)) {
            this.reply(request,response);
        }
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        Integer HT = getIntParameter(request, "MID");

        Message message = new Message();
        message.setContent(content);
        message.setHT(HT);

        HttpSession session = request.getSession();
        String type= (String) session.getAttribute("type");
        if("0".equals(type)) {
            Owner_users owner_users = (Owner_users) session.getAttribute("user");
            Integer OID = owner_users.getOID();
            message.setOID(OID);
            try {
                DaoFactory.getInstance().getMessageDao().Owner_usersAdd(message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if("1".equals(type)) {
            Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");
            Integer TID = tenant_users.getTID();
            message.setTID(TID);
            try {
                DaoFactory.getInstance().getMessageDao().Tenant_usersAdd(message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            request.setAttribute("MID",HT);
            request.getRequestDispatcher("forum?method=discuss").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }//response.sendRedirect(PathUtils.getBasePath(request)+"forum?method=discuss");
    }

    private void discuss(HttpServletRequest request, HttpServletResponse response) {
        Integer MID = getIntParameter(request, "MID");
        System.out.println("MID="+MID);
        Integer pageNo = getIntParameter(request,"pageNo");
        PageInfo<Message> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getMessageDao().listDiscuss(MID, pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("page/Discussion.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leaveMessage(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        Message message = new Message();
        message.setContent(content);

        HttpSession session = request.getSession();
        String type= (String) session.getAttribute("type");
        if("0".equals(type)) {
            Owner_users owner_users = (Owner_users) session.getAttribute("user");
            Integer OID = owner_users.getOID();
            message.setOID(OID);
            try {
                DaoFactory.getInstance().getMessageDao().Owner_usersAdd(message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if("1".equals(type)) {
            Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");
            Integer TID = tenant_users.getTID();
            message.setTID(TID);
            try {
                DaoFactory.getInstance().getMessageDao().Tenant_usersAdd(message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendRedirect(PathUtils.getBasePath(request)+"forum?method=getForum");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getForum(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = getIntParameter(request,"pageNo");
        PageInfo<Message> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getMessageDao().listMessage(pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("page/Forum.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer getIntParameter(HttpServletRequest request,String name) {
        if(StringUtils.isNoneBlank(request.getParameter(name))) {
            return Integer.parseInt(request.getParameter(name));
        }else {
            return null;
        }
    }
}
