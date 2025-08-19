package dao;

import entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PageInfo;
import util.PropertiesUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageDao {
    public PageInfo<Message> listMessage(PageInfo<Message> pageInfo) throws SQLException {
        //连接数据库
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM message WHERE 1=1 AND HT IS NULL LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<Message> list = queryRunner.query(sql,new BeanListHandler<>(Message.class));

        pageInfo.setList(list);
        pageInfo.setTotalCount((long)list.size());
        return pageInfo;
    }
    public void Owner_usersAdd(Message message) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO message(content, OID, HT) VALUES (?,?,?)";
        Object[] params = {message.getContent(), message.getOID(), message.getHT()};
        queryRunner.update(sql, params);
        System.out.println("房主用户留言成功");
    }

    public void Tenant_usersAdd(Message message) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO message(content, TID, HT) VALUES (?,?,?)";
        Object[] params = {message.getContent(), message.getTID(), message.getHT()};
        queryRunner.update(sql, params);
        System.out.println("租赁者用户留言成功");
    }

    public PageInfo<Message> listDiscuss(Integer MID, PageInfo<Message> pageInfo) throws SQLException {
        //连接数据库
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());

        String _sql1 = "";
        List<Object> _list1 = new ArrayList<Object>();
        //通过外键约束查询
        _sql1 += " and MID = ?";
        _list1.add(MID);
        //_list1转数组
        Object[] arr1 = new Object[_list1.size()];
        for (int i = 0;i< _list1.size();i++) {
            arr1[i] = _list1.get(i);
        }
        String sql1 = "SELECT * FROM message WHERE 1=1 "+_sql1+" LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<Message> list1 = queryRunner.query(sql1,new BeanListHandler<>(Message.class),arr1);

        String _sql2 = "";
        List<Object> _list2 = new ArrayList<Object>();
        //通过外键约束查询
        _sql2 += " and HT = ?";
        _list2.add(MID);
        //_list2转数组
        Object[] arr2 = new Object[_list2.size()];
        for (int i = 0;i< _list2.size();i++) {
            arr2[i] = _list2.get(i);
        }
        String sql = "SELECT * FROM message WHERE 1=1 "+_sql2+" LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<Message> list2 = queryRunner.query(sql,new BeanListHandler<>(Message.class),arr2);

        List<Message> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        System.out.println(list1.size());
        System.out.println(list1.size());
        System.out.println(list.size());
        pageInfo.setList(list);
        pageInfo.setTotalCount((long)list.size());
        return pageInfo;
    }
}
