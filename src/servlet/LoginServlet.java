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
import util.MD5;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = MD5.encrpyByMd5(req.getParameter("password"));
		String type = req.getParameter("type");
		//判断非空
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(type)) {
			req.setAttribute("error", "录入信息不能为空!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		//新建会话
		HttpSession session = req.getSession();
		if(session==null){
			System.out.println("wrong");
		}
		session.setAttribute("type",type);
		if(StringUtils.isNotBlank(type)) {
			try {
				if("0".equals(type)) {
					//房主登录验证
					Owner_users owner_users = DaoFactory.getInstance().getOwner_usersDao().login(userName, password);
					if(owner_users != null) {
						session.setAttribute("user", owner_users);
						session.setAttribute("type", type);
						resp.sendRedirect("index.jsp");
					}else {
						req.setAttribute("error", "用户名或密码错误!");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}else if("1".equals(type)) {
					//租赁者登录验证
					Tenant_users tenant_users = DaoFactory.getInstance().getTenant_usersDao().login(userName, password);
					if(tenant_users != null) {
						session.setAttribute("user", tenant_users);
						session.setAttribute("type", type);
						resp.sendRedirect("index.jsp");
					}else {
						System.out.println(userName+password);
						req.setAttribute("error", "用户名或密码错误!");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
