package controllers;

public interface Controller {
    void create();
    <T> T getFormData();
    void loadData();
}
