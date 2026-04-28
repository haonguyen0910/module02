package ra.module02.dao.impl;

import ra.module02.dao.IAdminDAO;
import ra.module02.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements IAdminDAO {
    @Override
    public boolean checkLogin(String username, String password) {
        boolean result = false;
        String sql = "select * from admin where username = ? and password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            if (rs.next()) { // có dữ liệu => login thành công
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}
