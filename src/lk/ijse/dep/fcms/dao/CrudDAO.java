package lk.ijse.dep.fcms.dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lk.ijse.dep.fcms.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO {

    public abstract List<T> findAll() throws Exception;

    public abstract T find(ID id)throws Exception;

    public abstract boolean save(T entity)throws Exception;

    public abstract boolean update(T entity)throws Exception;

    public abstract boolean delete(ID id)throws Exception;
}
