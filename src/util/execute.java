package util;

import java.sql.*;

//封装对数据库的操作
public class execute {
    protected Connection connection = null;     //Connection接口
    protected Statement statement = null;       //Statement接口
    protected ResultSet resultSet = null;       //记录结果集
    protected PreparedStatement preparedStatement = null;   //PreparedStatement接口
    protected String sql;                       //sql String
    protected boolean isConnect = true;         //与数据库连接标识
    public execute() {
        try {
            sql = "";
            connection = DBConnectionManager.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            isConnect = false;
        }
    }

    public Statement getStatement() {
        return statement;
    }
    public Connection getConnection() {
        return connection;
    }
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
    public ResultSet getResultSet() {
        return resultSet;
    }
    public String getSql() {
        return sql;
    }

    public int executeUpdate(String s) {
        int result = 0;
        System.out.println("--更新语句:"+s+"\n");
        try {
            result = statement.executeUpdate(s);
        } catch (Exception ex) {
            System.out.println("执行更新错误！");
        }
        return result;    //返回跟新语句影响的数量
    }

    public ResultSet executeQuery(String s) {
        ResultSet resultset = null;
        System.out.print("--查询语句:"+s+"\n");
        try {
            resultset = statement.executeQuery(s);   //执行查询并将返回的结果集赋予resultset
            System.out.println(s);
        } catch (Exception ex) {
            System.out.println("ִ执行查询错误！");
        }
        return resultset;
    }
    public void executeInsert(String s){
        try {
            statement.executeUpdate(s);  //执行插入
            System.out.println(s);
        } catch (SQLException e) {
            System.out.println("执行插入错误！");
        }
    }
    public void preExecuteInsert(String s) {
        try {
            preparedStatement = connection.prepareStatement(s); //用preparedstatement对象执行插入
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {   //关闭与数据库的连接
        try {
            statement.close();
            connection.close();
            resultSet.close();
        } catch (Exception e) {
        }
    }

}
