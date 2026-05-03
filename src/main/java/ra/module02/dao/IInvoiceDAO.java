package ra.module02.dao;

import ra.module02.model.entity.Invoice;

import java.util.List;

public interface IInvoiceDAO {
    boolean addInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
}
