package ra.module02.dao.impl;

import ra.module02.dao.IProductDAO;
import ra.module02.model.entity.Product;
import ra.module02.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    @Override
    public boolean addProduct(Product product) {
        boolean result = false;
        String sql = "insert into product(name, brand, price, stock) values (?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

            int i = pstmt.executeUpdate();
            if(i>0){
                result = true;
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            DBUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean result = false;
        String sql = "update product set name = ?, brand = ?, price = ?, stock = ? where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setLong(5, product.getId());

            int i = pstmt.executeUpdate();
            if(i>0){
                result = true;
            }
        } catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }   finally {
            DBUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    @Override
    public boolean deleteProduct(long id) {
        boolean result = false;
        String sql = "delete from product where id = ?";
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
        } catch (SQLException e) {
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
    public Product getProductById(long id) {
        Product product = null;

        String sql = "select * from product where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Product prod = new Product();
                prod.setId(rs.getLong("id"));
                prod.setName(rs.getString("name"));
                prod.setBrand(rs.getString("brand"));
                prod.setPrice(rs.getBigDecimal("price"));
                prod.setStock(rs.getInt("stock"));

                products.add(prod);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return products;
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where brand ilike ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + brand + "%");
            rs = pstmt.executeQuery();

            while (rs.next()){
                Product prod = new Product();
                prod.setId(rs.getLong("id"));
                prod.setName(rs.getString("name"));
                prod.setBrand(rs.getString("brand"));
                prod.setPrice(rs.getBigDecimal("price"));
                prod.setStock(rs.getInt("stock"));

                products.add(prod);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }
        return products;
    }

    @Override
    public List<Product> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where price between ? and ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setBigDecimal(1, minPrice);
            pstmt.setBigDecimal(2, maxPrice);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Product prod = new Product();
                prod.setId(rs.getLong("id"));
                prod.setName(rs.getString("name"));
                prod.setBrand(rs.getString("brand"));
                prod.setPrice(rs.getBigDecimal("price"));
                prod.setStock(rs.getInt("stock"));

                products.add(prod);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }
        return products;
    }

    @Override
    public List<Product> searchByNameAndStock(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where name ilike ? and stock > 0";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            while (rs.next()){
                Product prod = new Product();
                prod.setId(rs.getLong("id"));
                prod.setName(rs.getString("name"));
                prod.setBrand(rs.getString("brand"));
                prod.setPrice(rs.getBigDecimal("price"));
                prod.setStock(rs.getInt("stock"));

                products.add(prod);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeAll(conn, pstmt, rs);
        }
        return products;
    }
}
