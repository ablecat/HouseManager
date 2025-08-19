package dao;

import entity.SeenHouse;
import entity.Tenant_users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PageInfo;
import util.PropertiesUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeenHouseDao {
    public PageInfo<SeenHouse> getSeenHouse(Tenant_users tenant_users, PageInfo<SeenHouse> pageInfo) throws SQLException {
        //连接数据库
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String _sql = "";
        List<Object> _list = new ArrayList<Object>();
        _list.add(tenant_users.getTID());
        _sql += " and TID = ?";
        //_list转数组
        Object[] arr = new Object[_list.size()];
        for (int i = 0;i< _list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "SELECT * FROM seen_house WHERE 1=1 "+_sql+" LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<SeenHouse> list = queryRunner.query(sql,new BeanListHandler<>(SeenHouse.class),arr);

        pageInfo.setList(list);
        pageInfo.setTotalCount((long)list.size());
        return pageInfo;
    }
}
