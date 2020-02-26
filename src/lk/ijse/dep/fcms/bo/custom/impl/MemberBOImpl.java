package lk.ijse.dep.fcms.bo.custom.impl;

import lk.ijse.dep.fcms.bo.custom.MemberBO;
import lk.ijse.dep.fcms.dao.DAOFactory;
import lk.ijse.dep.fcms.dao.DAOTypes;
import lk.ijse.dep.fcms.dao.custom.MembershipDAO;
import lk.ijse.dep.fcms.dao.custom.MembershipDetailDAO;
import lk.ijse.dep.fcms.db.DBConnection;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.entity.Membership;
import lk.ijse.dep.fcms.entity.MembershipDetail;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    private MembershipDAO membershipDAO = DAOFactory.getInstance().getDAO(DAOTypes.MEMBERSHIP);
    private MembershipDetailDAO membershipDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.MEMBERSHIPDETAIL);

    @Override
    public boolean saveMember(MembershipDTO member) throws Exception {
        LocalDate dateOfIssue = LocalDate.now();
        LocalDate dateOfExpire = dateOfIssue.plusYears(1);

        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);

            String mId = member.getMemberID();
            boolean result = membershipDAO.save(new Membership(mId, member.getFirstName(), member.getLastName(), member.getAddress(), member.getContactNo(), member.getDateOfBirth(), member.getNic(), member.getGender(), member.getWeight(), member.getHeight()));


            if (!result) {
                connection.rollback();
                throw new RuntimeException("Error, Member didn't save!");
            }

            result = membershipDetailDAO.save(new MembershipDetail(mId, Date.valueOf(dateOfIssue),
                        Date.valueOf(dateOfExpire)));

                if (!result) {
                    connection.rollback();
                    throw new RuntimeException("Error, Member details didn't save!");
                }


            connection.commit();
            return true;

        } catch (Throwable e) {

            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateMember(MembershipDTO member) throws Exception {
        return membershipDAO.update(new Membership(member.getMemberID(), member.getFirstName(), member.getLastName(), member.getAddress(), member.getContactNo(), member.getDateOfBirth(), member.getNic(), member.getGender(), member.getWeight(), member.getHeight()));
    }

    @Override
    public boolean deleteMember(String memberID) throws Exception {
        return false;
    }

    @Override
    public boolean renewMember(MembershipDetailDTO member) throws Exception {
        LocalDate dateOfIssue = LocalDate.now();
        LocalDate dateOfExpire = dateOfIssue.plusYears(1);
        return membershipDetailDAO.save(new MembershipDetail(member.getMemberID(), Date.valueOf(dateOfIssue),
                Date.valueOf(dateOfExpire)));
    }

    @Override
    public List<MembershipDTO> findAllMembers() throws Exception {
        List<Membership> alMembers = membershipDAO.findAll();
        List<MembershipDTO> dtos = new ArrayList<>();
        for (Membership member : alMembers) {
            dtos.add(new MembershipDTO(member.getMemberID(), member.getFirstName(), member.getLastName(), member.getAddress(), member.getContactNo()));
        }
        return dtos;
    }

    @Override
    public String getLastMemberId() throws Exception {
        return membershipDAO.getLastMemberId();
    }

    @Override
    public MembershipDTO findMember(String memberID) throws Exception {
        Membership member = membershipDAO.find(memberID);
        return new MembershipDTO(member.getMemberID(), member.getFirstName(), member.getLastName(), member.getAddress(), member.getContactNo(), member.getDateOfBirth(), member.getNic(), member.getGender(), member.getWeight(), member.getHeight());
    }

    @Override
    public List<String> getAllMemberIDs() throws Exception {
        List<Membership> members = membershipDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Membership member : members) {
            ids.add(member.getMemberID());
        }
        return ids;
    }

    @Override
    public MembershipDetailDTO getLastMembershipDetail(String memberID) throws Exception {
        MembershipDetail member = membershipDetailDAO.find(memberID);
        return new MembershipDetailDTO(member.getMemberID(), member.getDateOfIssue(), member.getDateOfExpire());
    }

}
