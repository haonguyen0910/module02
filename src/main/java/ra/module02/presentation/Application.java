package ra.module02.presentation;

import ra.module02.business.IAdminService;
import ra.module02.business.impl.AdminServiceImpl;
import ra.module02.utils.Input;

public class Application {
    private IAdminService adminService;
    private ProductMenu productMenu;
    private CustomerMenu customerMenu;
    private InvoiceMenu invoiceMenu;
    private StatisticMenu statisticMenu;

    public Application() {
        this.adminService = new AdminServiceImpl();
        this.productMenu = new ProductMenu();
        this.customerMenu = new CustomerMenu();
        this.invoiceMenu = new InvoiceMenu();
        this.statisticMenu = new StatisticMenu();
    }

    public void start() {
        if (!showLogin()) {
            System.out.println("\nCảm ơn bạn đã sử dụng hệ thống!");
            return;
        }

        showMainMenu();
    }

    private boolean showLogin() {
        System.out.println("========= HỆ THỐNG QUẢN LÝ CỬA HÀNG ========");
        System.out.println("1. Đăng nhập Admin");
        System.out.println("2. Thoát");
        System.out.println("============================================");

        int choice = Input.getAnInteger("Nhập lựa chọn: ");

        if (choice == 2) return false;

        if (choice != 1) {
            System.out.println("Lựa chọn không hợp lệ!");
            Input.pressEnterToContinue();
            return showLogin();
        }

        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("\n======== ĐĂNG NHẬP QUẢN TRỊ ========");

            String username = Input.getString("Tài khoản: ");
            String password = Input.getString("Mật khẩu: ");

            if (adminService.login(username, password)) {
                System.out.println("\nĐăng nhập thành công!");
                System.out.println("Chào mừng " + username + " quay trở lại!");
                Input.pressEnterToContinue();
                return true;
            }

            attempts++;
            System.out.println("\nSai tài khoản hoặc mật khẩu!");

            if (attempts < MAX_ATTEMPTS) {
                System.out.println("Còn " + (MAX_ATTEMPTS - attempts) + " lần thử.");
            }
        }
        System.out.println("\nBạn đã nhập sai quá số lần cho phép!");
        Input.pressEnterToContinue();
        return false;
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("======== MENU CHÍNH ========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn (làm chưa hoàn thiện hết)");
            System.out.println("4. Thống kê doanh thu (chưa làm)");
            System.out.println("5. Đăng xuất");
            System.out.println("============================");

            int choice = Input.getAnInteger("Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    productMenu.showMenu();
                    break;
                case 2:
                    customerMenu.showMenu();
                    break;
                case 3:
                    invoiceMenu.showMenu();
                    break;
                case 4:
                    statisticMenu.showMenu();
                    break;
                case 5:
                    if (confirmLogout()) {
                        System.out.println("\nTạm biệt! Hẹn gặp lại!");
                        Input.pressEnterToContinue();
                        return;
                    }
                    break;
                default:
                    System.out.println("\nLựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
                    Input.pressEnterToContinue();
            }
        }
    }

    private boolean confirmLogout() {
        System.out.println("\nĐĂNG XUẤT");

        if (Input.getConfirmation("Bạn có chắc chắn muốn đăng xuất và thoát chương trình?")) {
            System.out.println("\nĐã đăng xuất thành công!");
            return true;
        } else {
            System.out.println("\nTiếp tục sử dụng chương trình.");
            Input.pressEnterToContinue();
            return false;
        }
    }
}
