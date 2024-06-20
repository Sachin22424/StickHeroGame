package com.example.stickheroapplication.Junit;

import com.example.stickheroapplication.Diamond;
import org.testng.annotations.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.*;

public class DiamondTest {

    @Test
    public void testCherryInitialization() {
        // When
        Diamond dmd = new Diamond();

        // Then
        assertNotNull(dmd);
        assertFalse(dmd.isHasBeenCollected());
        assertEquals(150, dmd.getHeight());
        assertEquals(150, dmd.getWidth());
    }
}
