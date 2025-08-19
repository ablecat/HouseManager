package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import util.PageInfo;
import util.PropertiesUtils;

import javax.servlet.http.HttpSession;

//HID,HAddress,layout,capacity,rent,con,OID
public class HouseDao {
    //添加房屋
    public void add(House house) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO house(HAddress,layout,capacity,rent,con,OID) VALUES (?,?,?,?,?,?)";
        Object[] params = {house.getHAddress(), house.getLayout(),house.getCapacity(),house.getRent(),house.getCon(),house.getOID()};
        queryRunner.update(sql, params);
        //System.out.println("插入新房屋成功");
    }

    //用房屋编号删除房屋
    public void delete(Integer HID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "DELETE FROM House WHERE HID = ?";
        Object[] params = {HID};
        queryRunner.update(sql, params);
        System.out.println("删除房屋成功");
    }

    //更新房屋
    public void update(House house) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "UPDATE house SET HAddress = ?,layout = ?,capacity = ?,rent = ?,con = ? WHERE HID = ?";
        Object[] params = {house.getHAddress(), house.getLayout(),house.getCapacity(),house.getRent(),house.getCon(),house.getHID()};
        queryRunner.update(sql, params);
        System.out.println("修改房屋成功");
    }

    //分页改造
    public PageInfo<House> listHouse(Owner_users owner_users, PageInfo<House> pageInfo) throws SQLException {
        //连接数据库
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String _sql = "";
        List<Object> _list = new ArrayList<Object>();
        //通过外键约束查询
        _sql += " and OID = ?";
        _list.add(owner_users.getOID());
        //_list转数组
        Object[] arr = new Object[_list.size()];
        for (int i = 0;i< _list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "SELECT * FROM house WHERE 1=1 "+_sql+" LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        System.out.println(sql);
        System.out.println(Arrays.toString(arr));
        List<House> list = queryRunner.query(sql,new BeanListHandler<>(House.class),arr);

        pageInfo.setList(list);
        pageInfo.setTotalCount((long)list.size());
        return pageInfo;
    }
    //返回利用外键查询的所有房子数
    public Long count(House house) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String _sql = "";
        List<Object> _list = new ArrayList<Object>();
        //通过外键约束查询
        if (house.getOID() != null) {
            _sql += " and OID = ?";
            _list.add(house.getOID());
        }
        //_list转数组
        Object[] arr = new Object[_list.size()];
        for (int i = 0;i< _list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "SELECT COUNT(*) FROM house WHERE 1=1 "+_sql;
        Long count = (Long)queryRunner.query(sql,new ScalarHandler(),arr);
        System.out.println("count="+count);
        return count;
    }
    public House findByHID(Integer HID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM House WHERE HID = ?";
        House house = queryRunner.query(sql,new BeanHandler<>(House.class),HID);
        return house;
    }

}
