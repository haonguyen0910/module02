package ra.module02.dao.impl;

import ra.module02.dao.ICustomerDAO;
import ra.module02.model.entity.Customer;
import ra.module02.model.entity.Product;
import ra.module02.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
    @Override
    public boolean addCustomer(Customer customer) {
        boolean result = false;
        String sql = "insert into customer (name, phone, email, address) values (?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());

            int i = pstmt.executeUpdate();
            if(i>0){
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
    public boolean updateCustomer(Customer customer) {
        boolean result = false;
        String sql = "update customer set name = ?, phone = ?, email = ?, address = ? where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setLong(5, customer.getId());

            int i = pstmt.executeUpdate();
            if(i>0){
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
    public boolean deleteCustomer(long id) {
        boolean result = false;
        String sql = "delete from customer where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            int i = pstmt.executeUpdate();
            if(i>0){
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
    public Customer getCustomerById(long id) {
        Customer customer = null;

        String sql = "select * from customer where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Customer cust = new Customer();
                cust.setId(rs.getLong("id"));
                cust.setName(rs.getString("name"));
                cust.setPhone(rs.getString("phone"));
                cust.setEmail(rs.getString("email"));
                cust.setAddress(rs.getString("address"));

                customers.add(cust);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return customers;
    }
}
