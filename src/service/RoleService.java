package service;

import dao.RoleDao;
import domain.Role;

import java.sql.SQLException;
import java.util.Collection;

public class RoleService {
    private static RoleDao roleDao = RoleDao.getInstance();
    private static RoleService roleService = new RoleService();
    private RoleService(){

    }
    public static RoleService getInstance(){
        return roleService;
    }

    public Role find(int id) throws SQLException {
        return roleDao.find(id);
    }

    public Collection<Role> findAll() {
        return roleDao.findAll();
    }
}
