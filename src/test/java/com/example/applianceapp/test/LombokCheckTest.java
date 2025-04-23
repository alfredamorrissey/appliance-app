package com.example.applianceapp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LombokCheckTest {

    @Test
    void testLombokWorks() {
        LombokCheck check = new LombokCheck();
        check.setName("test");
        assertEquals("test", check.getName());
    }
}
