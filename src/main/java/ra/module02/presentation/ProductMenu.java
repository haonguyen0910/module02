package ra.module02.presentation;

import ra.module02.business.IProductService;
import ra.module02.business.impl.ProductServiceImpl;
import ra.module02.model.entity.Product;
import ra.module02.utils.Input;

import java.math.BigDecimal;
import java.util.List;

public class ProductMenu {
    private IProductService productService;

    public ProductMenu() {
        this.productService = new ProductServiceImpl();
    }

    public void showMenu() {
        while (true) {
            System.out.println("======== QUẢN LÝ SẢN PHẨM ========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tên + tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("==================================");

            int choice = Input.getAnInteger("Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchByBrand();
                    break;
                case 6:
                    searchByPriceRange();
                    break;
                case 7:
                    searchByNameAndStock();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-8.");
                    Input.pressEnterToContinue();
            }
        }
    }

    private void displayAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("\nChưa có sản phẩm nào trong danh sách!");
        } else {
            System.out.println("\nDanh sách sản phẩm");
            for (Product p : products) {
                p.showProduct();
            }
        }
        Input.pressEnterToContinue();
    }

    private void addProduct() {
        System.out.println("\nTHÊM SẢN PHẨM MỚI");

        String name = Input.getString("Tên sản phẩm: ");
        String brand = Input.getString("Nhãn hàng: ");
        BigDecimal price = Input.getBigDecimal("Giá bán (VNĐ): ");
        int stock = Input.getAnInteger("Số lượng tồn kho: ");

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setStock(stock);

        if (productService.addProduct(product)) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.out.println("Thêm sản phẩm thất bại!");
        }
        Input.pressEnterToContinue();
    }

    private void updateProduct() {
        System.out.println("\nCẬP NHẬT SẢN PHẨM");

        int id = Input.getAnInteger("Nhập ID sản phẩm cần cập nhật: ");
        Product existing = productService.getProductById(id);

        if (existing == null) {
            System.out.println("ID sản phẩm không hợp lệ!");
            Input.pressEnterToContinue();
            return;
        }

        System.out.println("\nThông tin sản phẩm:");
        System.out.println("Tên: " + existing.getName());
        System.out.println("Nhãn hàng: " + existing.getBrand());
        System.out.println("Giá: " + existing.getPrice() + " VNĐ");
        System.out.println("Tồn kho: " + existing.getStock());

        boolean isChanged = false;

        System.out.println("\nNhập thông tin mới (để trống nếu không muốn thay đổi):");

        String name = Input.getStringOptional("Tên sản phẩm (mới): ", existing.getName());
        if (!name.equals(existing.getName())) {
            existing.setName(name);
            isChanged = true;
        }

        String brand = Input.getStringOptional("Nhãn hàng (mới): ", existing.getBrand());
        if (!brand.equals(existing.getBrand())) {
            existing.setBrand(brand);
            isChanged = true;
        }

        BigDecimal newPrice = Input.getBigDecimalOptional("Giá sản phẩm (mới): ", existing.getPrice());
        if (newPrice.compareTo(existing.getPrice()) != 0) {
            existing.setPrice(newPrice);
            isChanged = true;
        }

        int newStock = Input.getAnIntegerOptional("Tồn kho (mới): ", existing.getStock());
        if (newStock != existing.getStock()) {
            existing.setStock(newStock);
            isChanged = true;
        }

        if (!isChanged) {
            System.out.println("Không có dữ liệu nào thay đổi!");
            Input.pressEnterToContinue();
            return;
        }

        if (productService.updateProduct(existing)) {
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Cập nhật sản phẩm thất bại!");
        }
        Input.pressEnterToContinue();
    }

    private void deleteProduct() {
        System.out.println("\nXÓA SẢN PHẨM");

        int id = Input.getAnInteger("Nhập ID sản phẩm cần xóa: ");
        Product product = productService.getProductById(id);

        if (product == null) {
            System.out.println("ID sản phẩm không hợp lệ!");
            Input.pressEnterToContinue();
            return;
        }

        System.out.println("\nThông tin sản phẩm sẽ xóa:");
        product.showProduct();

        if (Input.getConfirmation("\nBạn có chắc chắn muốn xóa sản phẩm này?")) {
            if (productService.deleteProduct(id)) {
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Xóa sản phẩm thất bại!");
            }
        } else {
            System.out.println("Đã hủy xóa sản phẩm.");
        }
        Input.pressEnterToContinue();
    }

    private void searchByBrand() {
        String brand = Input.getString("Nhập tên nhãn hàng cần tìm: ");
        List<Product> products = productService.searchByBrand(brand);

        if (products == null || products.isEmpty()) {
            System.out.println("\nKhông tìm thấy sản phẩm nào của nhãn hàng: " + brand);
        } else {
            System.out.println("\nDanh sách sản phẩm");
            for (Product p : products) {
               p.showProduct();
            }
        }
        Input.pressEnterToContinue();
    }

    private void searchByPriceRange() {
        BigDecimal minPrice = Input.getBigDecimal("Giá tối thiểu (VNĐ): ");
        BigDecimal maxPrice = Input.getBigDecimal("Giá tối đa (VNĐ): ");
        List<Product> products = productService.searchByPriceRange(minPrice, maxPrice);

        if (products.isEmpty()) {
            System.out.println("\nKhông tìm thấy sản phẩm nào trong khoảng giá " + minPrice + " - " + maxPrice + " VNĐ");
        } else {
            System.out.println("\nDanh sách sản phẩm");
            for (Product p : products) {
               p.showProduct();
            }
        }
        Input.pressEnterToContinue();
    }

    private void searchByNameAndStock() {
        String name = Input.getString("Nhập tên sản phẩm: ");
        List<Product> products = productService.searchByNameAndStock(name);

        if (products == null || products.isEmpty()) {
            System.out.println("\nKhông tìm thấy sản phẩm nào có tên chứa " + name + " và còn hàng!");
        } else {
            System.out.println("\nDanh sách sản phẩm");
            for (Product p : products) {
                System.out.println(p);
            }
        }
        Input.pressEnterToContinue();
    }
}
