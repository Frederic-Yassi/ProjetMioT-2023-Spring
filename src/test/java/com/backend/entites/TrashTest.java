package com.backend.entites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrashTest {
    @Test
    void testConst(){
        Trash t = new Trash("2 rue de Lavoisier",90,"work");
        assertEquals("2 rue de Lavoisier",t.getAddress());
        assertEquals(90,t.getLevel());
        assertEquals("work",t.getState());
    }
    @Test
    void testConstVide(){
        Trash t = new Trash();
        assertNull(t.getAddress());
        assertNull(t.getState());
        assertEquals(0,t.getLevel());
    }
}