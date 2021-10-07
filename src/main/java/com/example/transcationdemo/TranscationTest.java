package com.example.transcationdemo;

import java.sql.*;

/**
 * @copyright:
 * @description:
 * @author: Guang.Chen
 * @since: 2021/10/7 17:37
 * @history: 1.2021/10/7 created by Guang.Chen
 */
public class TranscationTest {

    public void testQuery() {
        String sql="update account set money=money-? where id=?";
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;

        try {
            conn.setAutoCommit(false);
            conn=JDBCutil.getConn();
            PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);

            /*
             * 为第一个账户减掉100
             * */
            ps.setInt(1, 100);
            ps.setInt(2, 1);
            ps.executeUpdate();

            int a=10/0;
            /*
             * 为第二个用户加上100
             * */
            ps.setInt(1, -100);
            ps.setInt(2, 2);
            ps.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            try{
                conn.rollback();
            }catch (SQLException e1) {
                // TODO: handle exception
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCutil.release(rs, st, conn);
        }
    }
}
