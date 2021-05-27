package com.myproject.redisserver;

import java.sql.*;

/**
 * @program redis-server
 * @description:
 * @author: chenyang
 * @create: 2021/05/26 22:31
 */
public class JdbcTest {

    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "localhost:3306/dev?useUnicode=true&characterEncoding=utf8&useCursorFetch=true";

    private static final String user = "root";

    private static final String password = "password123";

    public static void main(String[] args){
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取一个数据库链接
            conn = DriverManager.getConnection(url,user,password);
            //创建sql语句
            String sql = "select id from a where 1=1";
            //sql预编译
            ps = conn.prepareStatement(sql);
            //执行操作
            rs = ps.executeQuery();
            //执行修改操作
            // ps.executeUpdate();
            //处理结果
            while(rs.next()){
                rs.getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
