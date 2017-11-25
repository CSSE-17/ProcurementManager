package dao;

import models.SupplierEntity;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupplierDAOTest {
    @Test
    public void read() throws Exception {

        SupplierDAO supDAO = new SupplierDAO();
        supDAO.setup();
        SupplierEntity supplier = new SupplierEntity();
        supDAO.exit();

        Assert.assertEquals("0768675643", supplier.getPhone());
    }

}