package ra.module02.business.impl;

import ra.module02.business.ICustomerService;
import ra.module02.dao.ICustomerDAO;
import ra.module02.dao.impl.CustomerDAOImpl;
import ra.module02.model.entity.Customer;
import ra.module02.model.entity.Product;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerServiceImpl implements ICustomerService {
    private ICustomerDAO customerDAO;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            System.out.println("Tên khách hàng không được để trống!");
            return false;
        }

        if (customer.getPhone() != null && !customer.getPhone().trim().isEmpty()) {
            if (!Pattern.matches("^[0-9]{10}$", customer.getPhone())) {
                System.out.println("Số điện thoại không hợp lệ (phải 10 số)!");
                return false;
            }
        }

        if (customer.getEmail() != null && !customer.getEmail().trim().isEmpty()) {
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", customer.getEmail())) {
                System.out.println("Không hợp lệ!");
                return false;
            }
        }
        return customerDAO.addCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        Customer id = customerDAO.getCustomerById(customer.getId());
        if (id == null) {
            System.out.println("ID khách hàng không hợp lệ!");
            return false;
        }

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            System.out.println("Tên khách hàng không được để trống!");
            return false;
        }

        if (customer.getPhone() != null && !customer.getPhone().trim().isEmpty()) {
            if (!Pattern.matches("^[0-9]{10}$", customer.getPhone())) {
                System.out.println("Số điện thoại không hợp lệ (phải 10 số)!");
                return false;
            }
        }

        if (customer.getEmail() != null && !customer.getEmail().trim().isEmpty()) {
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", customer.getEmail())) {
                System.out.println("Email không hợp lệ!");
                return false;
            }
        }
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(long id) {
        Customer customer = customerDAO.getCustomerById(id);
        if (customer == null) {
            System.out.println("ID khách hàng không hợp lệ!");
            return false;
        }
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer customer = customerDAO.getCustomerById(id);
        if (customer == null) {
            System.out.println("ID khách hàng không hợp lệ!");
        }
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
