package lk.ijse.dep.fcms.bo.custom.impl;

import lk.ijse.dep.fcms.bo.custom.LoginBO;
import lk.ijse.dep.fcms.dao.DAOFactory;
import lk.ijse.dep.fcms.dao.DAOTypes;
import lk.ijse.dep.fcms.dao.custom.LoginDAO;
import lk.ijse.dep.fcms.dao.custom.impl.LoginDAOImpl;
import lk.ijse.dep.fcms.dto.LoginDTO;
import lk.ijse.dep.fcms.entity.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginBOImpl implements LoginBO {

    private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);

    @Override
    public boolean saveLogin(LoginDTO login) throws Exception {
        return loginDAO.save(new Login(login.getUserID(), login.getPassword(), login.getUserType()));
    }

    @Override
    public boolean updateLogin(LoginDTO login) throws Exception {
        return loginDAO.update(new Login(login.getUserID(), login.getPassword(), login.getUserType()));
    }

    @Override
    public boolean deleteLogin(String userID) throws Exception {
        return false;
    }

    @Override
    public List<LoginDTO> findAllLogins() throws Exception {
        List<Login> alLogins = loginDAO.findAll();
        List<LoginDTO> dtos = new ArrayList<>();
        for (Login login : alLogins) {
            dtos.add(new LoginDTO(login.getUserID(), login.getPassword(), login.getUserType()));
        }
        return dtos;
    }

    @Override
    public LoginDTO findLogin(String userID) throws Exception {
        Login login = loginDAO.find(userID);
        return new LoginDTO(login.getUserID(), login.getPassword(), login.getUserType());
    }
}
