package lk.ijse.dep.fcms.bo.custom;

import lk.ijse.dep.fcms.bo.SuperBO;
import lk.ijse.dep.fcms.dto.LoginDTO;

import java.util.List;

public interface LoginBO extends SuperBO {

    boolean saveLogin(LoginDTO login) throws Exception;

    boolean updateLogin(LoginDTO login) throws Exception;

    boolean deleteLogin(String userID) throws Exception;

    List<LoginDTO> findAllLogins() throws Exception;

    LoginDTO findLogin(String userID) throws Exception;

}
