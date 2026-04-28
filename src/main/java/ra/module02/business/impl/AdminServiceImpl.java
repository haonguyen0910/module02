package ra.module02.business.impl;

import ra.module02.business.IAdminService;
import ra.module02.dao.IAdminDAO;
import ra.module02.dao.impl.AdminDAOImpl;

public class AdminServiceImpl implements IAdminService {
    private IAdminDAO adminDAO;

    public AdminServiceImpl() {
        this.adminDAO = new AdminDAOImpl();
    }

    @Override
    public boolean login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống!");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Mật khẩu không được để trống!");
            return false;
        }
        return adminDAO.checkLogin(username, password);
    }
}
