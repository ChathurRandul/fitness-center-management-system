package lk.ijse.dep.fcms.dao.custom.impl;

import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.MembershipDAO;
import lk.ijse.dep.fcms.entity.Membership;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public List findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Membership");
        List<Membership> members = new ArrayList<>();
        while (rst.next()) {
            members.add(new Membership(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)));
        }
        return members;
    }

    @Override
    public Membership find(String memberID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Membership WHERE memberID=?", memberID);
        if (rst.next()) {
            return new Membership(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getInt(10));
        }
        return null;
    }

    @Override
    public boolean save(Membership entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Membership VALUES (?,?,?,?,?,?,?,?,?,?)", entity.getMemberID(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getContactNo(), entity.getDateOfBirth(), entity.getNic(), entity.getGender(), entity.getWeight(), entity.getHeight());
    }

    @Override
    public boolean update(Membership entity) throws Exception {
        return CrudUtil.execute("UPDATE Membership SET firstName=?, lastName=?, address=?, contactNo=?, dateOfBirth=?, nic=?, gender=?, weight=?, height=? WHERE memberID=?", entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getContactNo(), entity.getDateOfBirth(), entity.getNic(), entity.getGender(), entity.getWeight(), entity.getHeight(), entity.getMemberID());
    }

    @Override
    public boolean delete(String memberID) throws Exception {
        return CrudUtil.execute("DELETE FROM Membership WHERE memberID=?", memberID);
    }

    @Override
    public String getLastMemberId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT memberID FROM Membership ORDER BY memberID DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
