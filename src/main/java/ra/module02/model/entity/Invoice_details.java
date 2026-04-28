package ra.module02.model.entity;

import java.math.BigDecimal;

public class Invoice_details {
    private Long id;
    private Long invoice_id;
    private Long product_id;
    private Integer quantity;
    private BigDecimal unit_price;

    public Invoice_details() {
    }

    public Invoice_details(Long id, Long invoice_id, Long product_id, Integer quantity, BigDecimal unit_price) {
        this.id = id;
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
