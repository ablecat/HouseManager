package dao;

import entity.Owner_users;
import entity.Record;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PropertiesUtils;

import java.sql.SQLException;
import java.util.List;

//TID,HID,charge
public class RecordDao {
    //添加Record
    public void add(Record record) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "INSERT INTO record(TID,HID) VALUES (?,?)";
        Object[] params = {record.getTID(), record.getHID()};
        queryRunner.update(sql, params);
    }

    public Integer getRID(Integer TID,Integer HID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT RID FROM record WHERE TID = ? AND HID = ?";
        Object[] params = {TID, HID};
        Record record = queryRunner.query(sql, new BeanHandler<>(Record.class), params);
        return record.getRID();
    }
}
