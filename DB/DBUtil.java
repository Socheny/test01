package DB;

import model.Picture;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class DBUtil {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;


    /**
     * Insert方法封装
     *
     * @param
     */
    public static void Insert(List<File> files) {
        conn = DBconn.conn();
        /**调用 DBconnection 类的 conn() 方法连接数据库*/
        String InsertMainT = "INSERT INTO CarPictureFile  ([CarPictureFileOID], [CPictureName], [CPictureType], [CpictureAddress]) VALUES(NEWID(),?,?,?)";
        /**插入sql语句*/
        try {
            ps = conn.prepareStatement(InsertMainT);

            for (int i = 0; i < files.size(); i++) {

                File file = files.get(i);
                String path1 = file.getPath();
                //String path = "WebSiteResource/ImageCarFile/" + path1;
                //System.out.printf(path1);
               // String oid = rs.getString("CarEssentialInformationOID");
                ps.setString(1, file.getName());
                ps.setString(2, file.getName().substring(file.getName().lastIndexOf(".") + 1));
                ps.setString(3, path1);
               // ps.setString(4, oid);
                ps.addBatch();

            }
            ps.executeBatch();
            /**执行sql语句*/
            ps.close();
            DBconn.close();

             System.out.println("批量插入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconn.close();
        }
    }


    public static void InsertError(File temp) {
        /**调用 DBconnection 类的 conn() 方法连接数据库*/
        conn = DBconn.conn();
        /**插入sql语句*/
        String InsertMainF = "INSERT INTO [CarPictureFile_error] ([CarPictureFileOID],[CPictureName],[CPictureType],[CPictureDescript])VALUES(NEWID(),?,?,?)";

        try {
            ps = conn.prepareStatement(InsertMainF);
            //ps.setString(1, );
            String error = "找不到车辆信息数据";
            ps.setString(1, temp.getName());
            ps.setString(2, temp.getName().substring(temp.getName().indexOf("."), temp.getName().length()));
            ps.setString(3, error);
            /**执行sql语句*/
            ps.executeUpdate();
            // System.out.println("插入到ERROR表成功(*￣︶￣)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconn.close();
        }
    }

    public static ResultSet Query(String Carnumber) {
        conn = DBconn.conn();

        String QuerySql = "SELECT * FROM CarEssentialInformation  WHERE  CarNumber = '" + Carnumber + "'";
        try {
            ps = conn.prepareStatement(QuerySql);
            /**执行sql语句*/
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(Carnumber + ",获取到车辆信息");
            } else {
                System.out.println(Carnumber + ",不存在该车辆信息");
            }
            DBconn.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet Query() {
        conn = DBconn.conn();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String QuerySql = "SELECT * FROM CarEssentialInformation ";
        try {
            ps = conn.prepareStatement(QuerySql);
            /**执行sql语句*/
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }

            DBconn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
