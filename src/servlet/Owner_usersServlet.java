package servlet;

import dao.DaoFactory;
import entity.House;
import entity.Owner_users;
import org.apache.commons.lang3.StringUtils;
import util.MD5;
import util.PageInfo;
import util.PathUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.DatagramPacket;
import java.sql.SQLException;

import static util.MD5.encrpyByMd5;

@WebServlet("/owner_users")
public class Owner_usersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");//获取表单提交的方法
        System.out.println("接受到请求为"+method);
        if("listHouse".equals(method)) {
            this.listHouse(request,response);
        } else if("addHouse".equals(method)) {
            this.addHouse(request,response);
        } else if("deleteHouse".equals(method)) {
            this.deleteHouse(request,response);
        } else if("editHouse".equals(method)) {
            this.findByHID(request,response);
        } else if("editSubmit".equals(method)) {
            this.editSubmit(request,response);
        } else if("register".equals(method)) {
            this.register(request,response);
        }

    }
    //Owner_usersServlet处理注册房主用户请求
    private void register(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = encrpyByMd5(request.getParameter("password"));
        String OName = request.getParameter("OName");
        String OAddress = request.getParameter("OAddress");
        String OTelephone = request.getParameter("OTelephone");

        Owner_users owner_users = new Owner_users();
        owner_users.setUserName(userName);
        owner_users.setPassword(password);
        owner_users.setOName(OName);;
        owner_users.setOAddress(OAddress);
        owner_users.setOTelephone(OTelephone);
        try {
            DaoFactory.getInstance().getOwner_usersDao().add(owner_users);
            response.sendRedirect("http://localhost:8080/untitled1_war_exploded/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Owner_usersServlet处理查询房屋请求
    private void listHouse(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = getIntParameter(request,"pageNo");
        //新建一个house对象
        House house = new House();
        //获得当前会话的用户
        HttpSession session = request.getSession();
        Owner_users owner_users = (Owner_users) session.getAttribute("user");
        //构造一个pageInfo对象
        PageInfo<House> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getHouseDao().listHouse(owner_users,pageInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //回写到页面
            request.setAttribute("house",house);
            //将结果发送到房主查询房屋界面
            request.getRequestDispatcher("page/Owner_users/listHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Owner_usersServlet处理添加房屋请求
    public void addHouse(HttpServletRequest request, HttpServletResponse response) {
        String HAddress = request.getParameter("HAddress");
        String layout = request.getParameter("layout");
        Integer capacity = getIntParameter(request,"capacity");
        Integer rent = getIntParameter(request,"rent");
        Boolean con = getBoolParameter(request,"con");
        //获得当前会话的用户，提取OID
        HttpSession session = request.getSession();
        Owner_users owner_users = (Owner_users) session.getAttribute("user");
        Integer OID = owner_users.getOID();

        House house = new House();
        house.setHAddress(HAddress);
        house.setCapacity(capacity);
        house.setLayout(layout);
        house.setRent(rent);
        house.setCon(con);
        house.setOID(OID);

        try {
            DaoFactory.getInstance().getHouseDao().add(house);
            //修改成功后跳转到查询房屋界面
            response.sendRedirect(PathUtils.getBasePath(request)+"owner_users?method=listHouse");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Owner_usersServlet处理删除房屋请求
    public void deleteHouse(HttpServletRequest request, HttpServletResponse response) {
        String HID = request.getParameter("HID");
        try {
            DaoFactory.getInstance().getHouseDao().delete(Integer.parseInt(HID));
            response.sendRedirect(PathUtils.getBasePath(request)+"owner_users?method=listHouse");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Owner_usersServlet处理编辑房屋请求
    public void editSubmit(HttpServletRequest request, HttpServletResponse response) {
        Integer HID = getIntParameter(request,"HID");
        String HAddress = request.getParameter("HAddress");
        String layout = request.getParameter("layout");
        Integer capacity = getIntParameter(request,"capacity");
        Integer rent = getIntParameter(request,"rent");
        Boolean con = getBoolParameter(request,"con");
        //获得当前会话的用户，提取OID
        HttpSession session = request.getSession();
        Owner_users owner_users = (Owner_users) session.getAttribute("user");
        Integer OID = owner_users.getOID();

        House house = new House();
        house.setHID(HID);
        house.setHAddress(HAddress);
        house.setCapacity(capacity);
        house.setLayout(layout);
        house.setRent(rent);
        house.setCon(con);
        house.setOID(OID);
        try {
            DaoFactory.getInstance().getHouseDao().update(house);
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"owner_users?method=listHouse");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    //转Boolean类型
    public Boolean getBoolParameter(HttpServletRequest request,String name) {
        if(StringUtils.isNoneBlank(request.getParameter(name))) {
            return Boolean.parseBoolean(request.getParameter(name));
        }
        else {
            return null;
        }
    }
    //用HID查找房屋对象
    private void findByHID(HttpServletRequest request, HttpServletResponse response) {
        String HID = request.getParameter("HID");
        try {
            House house = DaoFactory.getInstance().getHouseDao().findByHID(Integer.parseInt(HID));
            request.setAttribute("house",house);
            request.getRequestDispatcher("page/Owner_users/editHouse.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
