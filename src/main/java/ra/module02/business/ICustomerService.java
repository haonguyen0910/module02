package ra.module02.business;

import ra.module02.model.entity.Customer;

import java.util.List;

public interface ICustomerService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(long id);
    Customer getCustomerById(long id);
    List<Customer> getAllCustomers();
}
