package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class DBconn {

    private static final String url = "jdbc:sqlserver://;database=";
    /**数据库地址
     *
     */
    private static final String username = "xxx";
    /**数据库地址
     *
     */
    private static final String password = "xxx";
    /**
    *数据库密码
    */
    private static final Connection conn = null;
    /**
    * */
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
    **/
    /**
     * 连接数据库
     * @return
     */
    public static Connection conn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            /**加载数据库驱动*/
            try {
                conn = DriverManager.getConnection(url, username, password);
                /**连接数据库*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     * @return
     */
    public static void close() {
        if(conn != null) {
            try {
                conn.close();  //关闭数据库链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
