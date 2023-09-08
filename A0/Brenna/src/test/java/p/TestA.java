package p;

import org.junit.*;
import static org.junit.Assert.*;

public class TestA {
    @Test
    public void testm() {
        assertEquals(5, A.m());
    }
}