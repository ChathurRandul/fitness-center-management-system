package lk.ijse.dep.fcms.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.LoginDAO;
import lk.ijse.dep.fcms.entity.Login;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public List<Login> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Login");
        List<Login> logins = new ArrayList<>();
        while (rst.next()) {
            logins.add(new Login(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)));
        }
        return logins;
    }

    @Override
    public Login find(String userID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Login WHERE userID=?", userID);
        if (rst.next()) {
            return new Login(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
        }
        return null;
    }

    @Override
    public boolean save(Login entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Login VALUES (?,?,?)", entity.getUserID(), entity.getPassword(), entity.getUserType());
    }

    @Override
    public boolean update(Login entity) throws Exception {
        return CrudUtil.execute("UPDATE Login SET password=?, userType=? WHERE userID=?", entity.getPassword(), entity.getUserType(), entity.getUserID());
    }

    @Override
    public boolean delete(String userID) throws Exception {
        return CrudUtil.execute("DELETE FROM Login WHERE userID=?", userID);
    }
}
