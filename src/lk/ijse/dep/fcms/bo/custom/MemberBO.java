package lk.ijse.dep.fcms.bo.custom;

import lk.ijse.dep.fcms.bo.SuperBO;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;

import java.util.List;

public interface MemberBO extends SuperBO {

    boolean saveMember(MembershipDTO member)throws Exception;

    boolean updateMember(MembershipDTO member)throws Exception;

    boolean deleteMember(String memberID) throws Exception;

    boolean renewMember(MembershipDetailDTO member)throws Exception;

    List<MembershipDTO>findAllMembers() throws Exception;

    String getLastMemberId()throws Exception;

    MembershipDTO findMember(String memberID) throws Exception;

    List<String> getAllMemberIDs() throws Exception;

    MembershipDetailDTO getLastMembershipDetail(String memberID) throws Exception;
}
