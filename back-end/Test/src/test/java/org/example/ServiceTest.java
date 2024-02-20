package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void  getNameNegativeNumberTest(){
        Service service = new Service();
        String result = service.getName(-1);

        Assertions.assertEquals("a",result);
    }
    @Test
    void  getNamePossitiveNumberTest(){
        Service service = new Service();
        String result = service.getName(1);

        Assertions.assertEquals("b",result);
    }
    @Test
    void  getNameZeroNumberTest(){
        Service service = new Service();
        String result = service.getName(0);
    }
}