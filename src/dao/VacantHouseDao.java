package dao;

import entity.VacantHouse;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PageInfo;
import util.PropertiesUtils;

import java.lang.invoke.VarHandle;
import java.sql.SQLException;
import java.util.List;

public class VacantHouseDao {

    public PageInfo<VacantHouse> seeVacantHouse(PageInfo<VacantHouse> pageInfo) throws SQLException {
        //连接数据库
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "SELECT * FROM vacant_house WHERE 1=1 LIMIT "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<VacantHouse> list = queryRunner.query(sql,new BeanListHandler<>(VacantHouse.class));
        for(int i=0;i<list.size();++i){
            VacantHouse vacantHouse = list.get(i);
        }
        pageInfo.setList(list);
        pageInfo.setTotalCount((long)list.size());
        return pageInfo;
    }
}
