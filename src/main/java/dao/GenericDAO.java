package dao;

public interface GenericDAO <T>{

    /** Persist the newInstance object into database */
    void create(T newInstance);
}
