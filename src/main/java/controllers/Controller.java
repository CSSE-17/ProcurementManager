package controllers;

public interface Controller {
    void create();
    <T> T getFormData();
    void loadData();
    void delete();
    <T> T getSelectedInstance();
    void clearForm();
}
