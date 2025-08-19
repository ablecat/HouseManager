package dao;

import entity.Owner_users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PropertiesUtils;
import util.MD5;

import java.sql.SQLException;
import java.util.List;

import static util.MD5.encrpyByMd5;

public class Owner_usersDao {
    public void add(Owner_users owner_users) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO owner_users(userName,password,OName,OAddress,OTelephone) VALUES (?,?,?,?,?)";
        Object[] params = {owner_users.getUserName(), owner_users.getPassword(), owner_users.getOName(), owner_users.getOAddress(), owner_users.getOTelephone()};
        queryRunner.update(sql, params);
        System.out.println("注册成功！");
    }

    public void delete(String ID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "DELETE FROM owner_users WHERE OID = ?";
        Object[] params = {ID};
        queryRunner.update(sql, params);
    }

    public Owner_users login(String userName,String password) throws  SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM owner_users WHERE userName = ? AND password = ?";
        Object[] params = {userName, password};
        Owner_users entity = queryRunner.query(sql, new BeanHandler<>(Owner_users.class),params);
        return entity;
    }

}
