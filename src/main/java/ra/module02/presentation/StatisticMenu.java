package ra.module02.presentation;

import ra.module02.utils.Input;


public class StatisticMenu {

    public void showMenu() {
        while (true) {
            System.out.println("======== THỐNG KÊ DOANH THU ========");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.println("====================================");

            int choice = Input.getAnInteger("Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
                    Input.pressEnterToContinue();
            }
        }
    }


}
