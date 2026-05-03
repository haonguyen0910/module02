package ra.module02.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Invoice {
    private Long id;
    private Long customer_id;
    private LocalDate created_at;
    private BigDecimal total_amount;

    public Invoice() {
    }

    public Invoice(Long id, Long customer_id, LocalDate created_at, BigDecimal total_amount) {
        this.id = id;
        this.customer_id = customer_id;
        this.created_at = created_at;
        this.total_amount = total_amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public void showInvoice() {
        System.out.printf("|%-4d|%-40s|%-30s|%15s|\n",
                id, customer_id, created_at, total_amount);
    }
}
