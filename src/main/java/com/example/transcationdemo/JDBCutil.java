package com.example.transcationdemo;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @copyright:
 * @description:
 * @author: Guang.Chen
 * @since: 2021/10/7 17:32
 * @history: 1.2021/10/7 created by Guang.Chen
 */
public class JDBCutil {


    static String name=null;
    static String password=null;
    static String url=null;
    static String driverClass=null;
    static {
        try {
            //创建一个属性配置对象
            Properties properties=new Properties();
            //InputStream is=new FileInputStream("jdbc.properties");

            InputStream is=JDBCutil.class.getClassLoader().getResourceAsStream("jdbc.properties");

            //导入输入流
            properties.load(is);
            //读取属性
            driverClass=properties.getProperty("driverClass");
            name=properties.getProperty("name");
            url=properties.getProperty("url");
            password=properties.getProperty("password");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConn() {
        Connection conn=null;
        try {
            //注册驱动
            Class.forName(driverClass);
            //建立连接
            conn= DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }


    /*释放资源*/
    public static void release(ResultSet rs, Statement st, Connection conn) {
        CloseRs(rs);
        CloseRs(st);
        CloseConn(conn);

    }
    //接受重载
    public static void release(Statement st,Connection conn) {

        CloseRs(st);
        CloseConn(conn);

    }

    private static void CloseConn(Connection conn) {
        // TODO Auto-generated method stub
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                conn=null;
            }

        }
    }

    private static void CloseRs(Statement st) {
        // TODO Auto-generated method stub
        if (st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                st=null;
            }
        }
    }

    private static void CloseRs(ResultSet rs) {
        // TODO Auto-generated method stub
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }

    }

}
