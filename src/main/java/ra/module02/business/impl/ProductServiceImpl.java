package ra.module02.business.impl;

import ra.module02.business.IProductService;
import ra.module02.dao.IProductDAO;
import ra.module02.dao.impl.ProductDAOImpl;
import ra.module02.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private IProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống!");
            return false;
        }
        if (product.getBrand() == null || product.getBrand().trim().isEmpty()) {
            System.out.println("Nhãn hàng không được để trống!");
            return false;
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0!");
            return false;
        }
        if (product.getStock() < 0) {
            System.out.println("Tồn kho phải lớn hơn hoặc bằng 0!");
            return false;
        }
        return productDAO.addProduct(product);

    }

    @Override
    public boolean updateProduct(Product product) {
        Product id = productDAO.getProductById(product.getId());
        if (id == null) {
            System.out.println("ID sản phẩm không hợp lệ!");
            return false;
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống!");
            return false;
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0!");
            return false;
        }
        if (product.getStock() < 0) {
            System.out.println("Tồn kho phải lớn hơn hoặc bằng 0!");
            return false;
        }
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(long id) {
        Product product = productDAO.getProductById(id);
        if (product == null) {
            System.out.println("ID sản phẩm không hợp lệ!");
            return false;
        }
        return productDAO.deleteProduct(id);
    }

    @Override
    public Product getProductById(long id) {
        Product product = productDAO.getProductById(id);
        if (product == null) {
            System.out.println("ID sản phẩm không hợp lệ!");
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            System.out.println("Vui lòng nhập từ khóa tìm kiếm!");
            return null;
        }
        return productDAO.searchByBrand(brand);
    }

    @Override
    public List<Product> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            System.out.println("Giá không được để trống!");
            return null;
        }

        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0!");
            return null;
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            System.out.println("Giá sản phẩm tối thiểu không được lớn hơn giá sản phẩm tối đa!");
            return null;
        }
        return productDAO.searchByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> searchByNameAndStock(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Vui lòng nhập từ khóa tìm kiếm!");
            return null;
        }
        return productDAO.searchByNameAndStock(name);
    }
}
