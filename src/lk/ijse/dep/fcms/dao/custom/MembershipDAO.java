package lk.ijse.dep.fcms.dao.custom;

import lk.ijse.dep.fcms.dao.CrudDAO;
import lk.ijse.dep.fcms.entity.Membership;

public interface MembershipDAO extends CrudDAO<Membership, String> {

    String getLastMemberId() throws Exception;
}
