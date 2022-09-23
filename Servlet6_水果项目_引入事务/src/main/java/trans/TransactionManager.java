package trans;


import Util.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    // 开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        // 获取连接对象
        Connection conn = ConnUtil.getConn();
        // 提交事务
        conn.commit();
        ConnUtil.closeConn();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
