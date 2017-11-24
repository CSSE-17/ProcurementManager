package dao;

import java.util.List;

public interface GenericDAO <T>{

    /** Persist the newInstance object into database */
    void create(T newInstance);

    /**
     * Read all instances from table
     */
    List read(Class c);
}
