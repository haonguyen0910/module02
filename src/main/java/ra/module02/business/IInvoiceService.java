package ra.module02.business;

import ra.module02.model.entity.Invoice;

import java.util.List;

public interface IInvoiceService {
    boolean addInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
}
