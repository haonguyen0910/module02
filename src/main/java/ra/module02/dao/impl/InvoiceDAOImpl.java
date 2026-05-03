package ra.module02.dao.impl;

import ra.module02.dao.IInvoiceDAO;
import ra.module02.model.entity.Invoice;
import ra.module02.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements IInvoiceDAO {
    @Override
    public boolean addInvoice(Invoice invoice) {
        boolean result = false;
        String sql = "insert into invoice (customer_id, total_amount) values (?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, invoice.getCustomer_id());
            pstmt.setBigDecimal(2, invoice.getTotal_amount());

            int i = pstmt.executeUpdate();
            if(i > 0) {
                result = true;
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            DBUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "select * from invoice";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getLong("id"));
                invoice.setCustomer_id(rs.getLong("customer_id"));
                invoice.setCreated_at(rs.getObject("created_at", LocalDate.class));
                invoice.setTotal_amount(rs.getBigDecimal("total_amount"));

                invoices.add(invoice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }
        return invoices;
    }
}
