package ra.module02.presentation;

import ra.module02.business.ICustomerService;
import ra.module02.business.impl.CustomerServiceImpl;
import ra.module02.model.entity.Customer;
import ra.module02.utils.Input;

import java.util.List;

public class CustomerMenu {
    private ICustomerService customerService;

    public CustomerMenu() {
        this.customerService = new CustomerServiceImpl();
    }

    public void showMenu() {
        while (true) {
            System.out.println("======== QUẢN LÝ KHÁCH HÀNG ========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("====================================");

            int choice = Input.getAnInteger("Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    displayAllCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
                    Input.pressEnterToContinue();
            }
        }
    }

    private void displayAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("\nChưa có khách hàng nào trong danh sách!");
        } else {
            System.out.println("\nDanh sách khách hàng");
            for (Customer c : customers) {
                c.showCustomer();
            }
        }
        Input.pressEnterToContinue();
    }

    private void addCustomer() {

        System.out.println("\nTHÊM KHÁCH HÀNG MỚI");
        String name = Input.getString("Họ tên khách hàng: ");
        String phone = Input.getPhone("Số điện thoại: ");
        String email = Input.getEmail("Email: ");
        String address = Input.getString("Địa chỉ: ");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);

        if (customerService.addCustomer(customer)) {
            System.out.println("Thêm khách hàng thành công!");
        } else {
            System.out.println("Thêm khách hàng thất bại!");
        }
        Input.pressEnterToContinue();
    }

    private void updateCustomer() {

        System.out.println("\nCẬP NHẬT KHÁCH HÀNG");
        int id = Input.getAnInteger("Nhập ID khách hàng cần cập nhật: ");
        Customer existing = customerService.getCustomerById(id);

        if (existing == null) {
            System.out.println("ID khách hàng không hợp lệ!");
            Input.pressEnterToContinue();
            return;
        }

        System.out.println("\nThông tin khách hàng:");
        System.out.println("Tên: " + existing.getName());
        System.out.println("SĐT: " + existing.getPhone());
        System.out.println("Email: " + existing.getEmail());
        System.out.println("Địa chỉ: " + existing.getAddress());

        boolean isChanged = false;

        System.out.println("\nNhập thông tin mới (để trống nếu không muốn thay đổi):");

        String name = Input.getStringOptional("Tên mới: ", existing.getName());
        if (!name.equals(existing.getName())) {
            existing.setName(name);
            isChanged = true;
        }

        String phone = Input.getPhoneOptional("SĐT mới: ", existing.getPhone());
        if (!phone.equals(existing.getPhone())) {
            existing.setPhone(phone);
            isChanged = true;
        }

        String email = Input.getEmailOptional("Email mới: ", existing.getEmail());
        if (!email.equals(existing.getEmail())) {
            existing.setEmail(email);
            isChanged = true;
        }

        String address = Input.getStringOptional("Địa chỉ mới: ", existing.getAddress());
        if (!address.equals(existing.getAddress())) {
            existing.setAddress(address);
            isChanged = true;
        }

        if (!isChanged) {
            System.out.println("Không có dữ liệu nào thay đổi!");
            Input.pressEnterToContinue();
            return;
        }

        if (customerService.updateCustomer(existing)) {
            System.out.println("Cập nhật khách hàng thành công!");
        } else {
            System.out.println("Cập nhật khách hàng thất bại!");
        }
        Input.pressEnterToContinue();
    }

    private void deleteCustomer() {

        System.out.println("\nXÓA KHÁCH HÀNG");

        int id = Input.getAnInteger("Nhập ID khách hàng cần xóa: ");
        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            System.out.println("ID khách hàng không hợp lệ!");
        } else {
            System.out.println("\nThông tin khách hàng sẽ xóa:");
            customer.showCustomer();

            if (Input.getConfirmation("\nBạn có chắc chắn muốn xóa khách hàng này?")) {
                if (customerService.deleteCustomer(id)) {
                    System.out.println("Xóa khách hàng thành công!");
                } else {
                    System.out.println("Xóa khách hàng thất bại!");
                }
            } else {
                System.out.println("Đã hủy xóa khách hàng này.");
            }
        }
        Input.pressEnterToContinue();
    }
}
