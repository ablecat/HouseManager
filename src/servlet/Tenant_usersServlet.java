package servlet;

import dao.DaoFactory;
import entity.*;
import entity.Record;
import org.apache.commons.lang3.StringUtils;
import util.MD5;
import util.PageInfo;
import util.PathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static util.MD5.encrpyByMd5;

@WebServlet("/tenant_users")
public class Tenant_usersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");//获取表单提交的方法
        System.out.println("接受到请求为"+method);
        if("getVacantHouse".equals(method)) {
            this.getVacantHouse(request,response);
        } else if("seeHouse".equals(method)) {
            this.seeHouse(request,response);
        } else if("getSeenHouse".equals(method)) {
            this.getSeenHouse(request,response);
        } else if("rentHouse".equals(method)) {
            try {
                this.rentHouse(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if("register".equals(method)) {
            this.register(request,response);
        } else if("getRentedHouse".equals(method)) {
            this.getRentedHouse(request,response);
        } else if("checkItOut".equals(method)) {
            this.checkItOut(request,response);
        }
    }

    private void checkItOut(HttpServletRequest request, HttpServletResponse response) {
        String CID = request.getParameter("CID");
        try {
            DaoFactory.getInstance().getChargeDao().delete(Integer.parseInt(CID));
            response.sendRedirect(PathUtils.getBasePath(request)+"tenant_users?method=getVacantHouse");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void seeHouse(HttpServletRequest request, HttpServletResponse response) {
        //获取当前会话的用户
        HttpSession session = request.getSession();
        Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");

        Integer TID = tenant_users.getTID();
        Integer HID = getIntParameter(request,"HID");

        Record record = new Record();
        record.setHID(HID);
        record.setTID(TID);
        try {
            DaoFactory.getInstance().getRecordDao().add(record);
            //转到展示已看过的房子页面
            response.sendRedirect("http://localhost:8080/untitled1_war_exploded/tenant_users?method=getSeenHouse");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //得到当前会话的租赁者所看房屋
    private void getSeenHouse(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = getIntParameter(request,"pageNo");
        //构造一个pageInfo对象
        PageInfo<SeenHouse> pageInfo = new PageInfo<>(pageNo);
        HttpSession session = request.getSession();
        Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");
        System.out.println("租赁者的id为"+tenant_users.getTID());
        try {
            pageInfo = DaoFactory.getInstance().getSeenHouseDao().getSeenHouse(tenant_users,pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //将结果发送到展示已看过房屋界面
            request.getRequestDispatcher("page/Tenant_users/seenHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //得到当前会话的租赁者所租用房屋
    private void getRentedHouse(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = getIntParameter(request,"pageNo");
        //构造一个pageInfo对象
        PageInfo<RentedHouse> pageInfo = new PageInfo<>(pageNo);
        HttpSession session = request.getSession();
        Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");
        try {
            pageInfo = DaoFactory.getInstance().getRentedHouseDao().getRentedHouse(tenant_users,pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //转到已租用房屋界面
            request.getRequestDispatcher("page/Tenant_users/rentedHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //注册
    private void register(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = encrpyByMd5(request.getParameter("password"));
        String TName = request.getParameter("OName");
        String TAddress = request.getParameter("OAddress");
        String TTelephone = request.getParameter("OTelephone");
        String TSex = request.getParameter("TSex");

        Tenant_users tenant_users = new Tenant_users();
        tenant_users.setUserName(userName);
        tenant_users.setPassword(password);
        tenant_users.setTName(TName);;
        tenant_users.setTAddress(TAddress);
        tenant_users.setTTelephone(TTelephone);
        tenant_users.setTSex(TSex);
        try {
            DaoFactory.getInstance().getTenant_usersDao().add(tenant_users);
            response.sendRedirect("http://localhost:8080/untitled1_war_exploded/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Tenant_usersServlet处理租房请求,产生看房记录
    private void rentHouse(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");

        Integer TID = tenant_users.getTID();
        Integer HID = getIntParameter(request, "HID");
        Integer RID = DaoFactory.getInstance().getRecordDao().getRID(TID,HID);
        Integer charge = getIntParameter(request, "charge");
        Double amount = 0.0;
        //超过10000的部分收10%，超过1000的部分收5%，超过100的部分收1%
        if(charge > 10000) {
            amount += (charge-10000)*0.1;
            charge -= 10000;
        }
        if(charge > 1000) {
            amount += (charge-1000)*0.05;
            charge -= 1000;
        }
        if(charge > 100) {
            amount += (charge-100)*0.01;
            charge -= 100;
        }
        Charge entity = new Charge();
        entity.setRID(RID);
        entity.setAmount(amount);
        try {
            DaoFactory.getInstance().getChargeDao().add(entity);
            //租房成功后跳转到已租用房屋界面
            response.sendRedirect("http://localhost:8080/untitled1_war_exploded/tenant_users?method=getRentedHouse");
            //request.getRequestDispatcher("page/Tenant_users/rentedHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Tenant_usersServlet处理查询房屋请求(展示所有空闲房屋）
    private void getVacantHouse(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = getIntParameter(request,"pageNo");
        //构造一个pageInfo对象
        PageInfo<VacantHouse> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getVacantHouseDao().seeVacantHouse(pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //将结果发送到租赁者看房页面
            request.getRequestDispatcher("page/Tenant_users/seeHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //转Integer类型
    public Integer getIntParameter(HttpServletRequest request,String name) {
        if(StringUtils.isNoneBlank(request.getParameter(name))) {
            return Integer.parseInt(request.getParameter(name));
        }else {
            return null;
        }
    }
}
