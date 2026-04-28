package ra.module02.dao;

import ra.module02.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDAO {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(long id);
    Product getProductById(long id);
    List<Product> getAllProducts();
    List<Product> searchByBrand(String brand);
    List<Product> searchByPriceRange(BigDecimal minPrice, BigDecimal  maxPrice);
    List<Product> searchByNameAndStock(String name);
}
