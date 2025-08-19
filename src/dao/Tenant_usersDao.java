package dao;

import entity.House;
import entity.Owner_users;
import entity.Tenant_users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PageInfo;
import util.PropertiesUtils;

import java.sql.SQLException;
import java.util.List;

public class Tenant_usersDao {
    public void add(Tenant_users tenant_users) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO tenant_users(userName,password,TName,TAddress,TTelephone,TSex) VALUES (?,?,?,?,?,?)";
        Object[] params = {tenant_users.getUserName(), tenant_users.getPassword(), tenant_users.getTName(),tenant_users.getTAddress(),tenant_users.getTTelephone(),tenant_users.getTSex()};
        queryRunner.update(sql, params);
    }

    public void delete(String ID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "DELETE FROM tenant_users WHERE TID = ?";
        Object[] params = {ID};
        queryRunner.update(sql, params);
    }

    public List<Tenant_users> list(Tenant_users tenant_users) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM tenant_users";
        List<Tenant_users> list = queryRunner.query(sql, new BeanListHandler<>(Tenant_users.class));
        return list;
    }


    public Tenant_users login(String userName,String password) throws  SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM tenant_users WHERE userName = ? AND password = ?";
        Object[] params = {userName,password};
        Tenant_users entity = queryRunner.query(sql, new BeanHandler<>(Tenant_users.class),params);
        return entity;
    }
}
