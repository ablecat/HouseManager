package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Charge;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.House;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import util.PageInfo;
import util.PropertiesUtils;

import javax.servlet.http.HttpSession;

public class ChargeDao {

    public void add(Charge charge) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO charge(amount, RID) VALUES (?,?)";
        Object[] params = {charge.getAmount(),charge.getRID()};
        queryRunner.update(sql, params);
        System.out.println("租用房屋成功");
    }

    public void delete(int CID) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        List<Object> _list = new ArrayList<Object>();
        _list.add(CID);
        Object[] arr = new Object[_list.size()];
        for (int i = 0;i< _list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "DELETE FROM charge WHERE RID = ?";
        queryRunner.update(sql, arr);
        System.out.println("退房成功");
    }
}
