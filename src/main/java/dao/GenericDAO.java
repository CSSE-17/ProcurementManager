package dao;

import java.util.List;

public interface GenericDAO <T>{

    /** Persist the newInstance object into database */
    void create(T newInstance);

    /**
     * Read all instances from table
     */
    List read(Class c);

    /**
     * Delete instance.
     */
    void delete(T instance);

//    void update(T instance);
}
