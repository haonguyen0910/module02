package ra.module02.presentation;

import ra.module02.business.IInvoiceService;
import ra.module02.business.impl.InvoiceServiceImpl;
import ra.module02.model.entity.Customer;
import ra.module02.model.entity.Invoice;
import ra.module02.utils.Input;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceMenu {
    private IInvoiceService invoiceService;

    public InvoiceMenu() {
        this.invoiceService = new InvoiceServiceImpl();
    }

    public void showMenu() {
        while (true) {
            System.out.println("======== QUẢN LÝ HÓA ĐƠN ========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("====================================");

            int choice = Input.getAnInteger("Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    displayAllInvoices();
                    break;
                case 2:
                    addInvoice();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
                    Input.pressEnterToContinue();
            }
        }
    }

    private void displayAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        if (invoices.isEmpty()) {
            System.out.println("\nChưa có hóa đơn nào trong danh sách!");
        } else {
            System.out.println("\nDanh sách hóa đơn");
            for (Invoice i : invoices) {
                i.showInvoice();
            }
        }
        Input.pressEnterToContinue();
    }

    private void addInvoice() {

        System.out.println("\nTHÊM HÓA ĐƠN MỚI");
        Long name = Input.getALong("Customer_id: ");
        BigDecimal total_amount = Input.getBigDecimal("Tổng tiền (VNĐ): ");

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(name);
        invoice.setTotal_amount(total_amount);

        if (invoiceService.addInvoice(invoice)) {
            System.out.println("Thêm hóa đơn thành công!");
        } else {
            System.out.println("Thêm hóa đơn thất bại!");
        }
        Input.pressEnterToContinue();
    }


}
