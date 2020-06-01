package test;

import org.example.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {
    Service service;
    @Before
    public void init(){
        service = new Service();
    }
    @Test
    public void testGetOruulagch(){
        String actual = service.getOruulagch("Bold_AH.xlsx");
        Assert.assertEquals(actual, "Bold");
    }
}
