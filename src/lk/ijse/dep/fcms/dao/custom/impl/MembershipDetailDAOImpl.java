package lk.ijse.dep.fcms.dao.custom.impl;

import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.MembershipDetailDAO;
import lk.ijse.dep.fcms.entity.MembershipDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MembershipDetailDAOImpl implements MembershipDetailDAO {

    @Override
    public List<MembershipDetail> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM MembershipDetail");
        List<MembershipDetail> membershipDetails = new ArrayList<>();
        while (rst.next()) {
            membershipDetails.add(new MembershipDetail(rst.getString(1),
                    rst.getDate(2),
                    rst.getDate(3)));
        }
        return membershipDetails;
    }

    @Override
    public MembershipDetail find(String memberID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM MembershipDetail WHERE memberID=? ORDER BY dateOfExpire DESC LIMIT 1", memberID);
        if (rst.next()) {
            return new MembershipDetail(rst.getString(1),
                    rst.getDate(2),
                    rst.getDate(3));
        }
        return null;
    }

    @Override
    public boolean save(MembershipDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO MembershipDetail VALUES (?,?,?)", entity.getMemberID(), entity.getDateOfIssue(), entity.getDateOfExpire());
    }

    @Override
    public boolean update(MembershipDetail entity) throws Exception {
        return CrudUtil.execute("UPDATE MembershipDetail SET dateOfIssue=?, dateOfExpire=? WHERE memberID=?", entity.getDateOfIssue(), entity.getDateOfExpire(), entity.getMemberID());
    }

    @Override
    public boolean delete(String memberID) throws Exception {
        return CrudUtil.execute("DELETE FROM MembershipDetail WHERE memberID=?", memberID);
    }
}
