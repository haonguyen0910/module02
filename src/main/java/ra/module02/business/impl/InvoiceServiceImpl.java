package ra.module02.business.impl;

import ra.module02.business.IInvoiceService;
import ra.module02.dao.IInvoiceDAO;
import ra.module02.dao.impl.InvoiceDAOImpl;
import ra.module02.model.entity.Invoice;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceServiceImpl implements IInvoiceService {
    private IInvoiceDAO invoiceDAO;

    public InvoiceServiceImpl() {
        this.invoiceDAO = new InvoiceDAOImpl();
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        if (invoice.getCustomer_id() == null) {
            System.out.println("Tên hóa đơn không được để trống!");
            return false;
        }

        if (invoice.getTotal_amount() == null || invoice.getTotal_amount().compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Tổng tiền phải lớn hơn 0!");
            return false;
        }
        return invoiceDAO.addInvoice(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }
}
