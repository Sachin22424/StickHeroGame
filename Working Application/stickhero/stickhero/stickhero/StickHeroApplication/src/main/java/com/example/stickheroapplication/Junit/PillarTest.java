package com.example.stickheroapplication.Junit;

import com.example.stickheroapplication.Pillars;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PillarTest {

    @Test
    public void testInitRect() {
        Pillars pillars = new Pillars();
        Rectangle platform = pillars.initRect();

        assertNotNull(platform);
//        assertEquals(Pillars.PLATFORM_HEIGHT, platform.getHeight());
        assertEquals(Color.BLACK, platform.getFill());
    }

    @Test
    public void testGeneratePlatform() {
        Pillars pillars = new Pillars();
        Rectangle platform = pillars.generatePlatform();

        assertNotNull(platform);
//        assertEquals(Pillars.PLATFORM_HEIGHT, platform.getHeight());
        assertEquals(Color.BLACK, platform.getFill());
    }

    @Test
    public void testGeneratenewPillar() {
        Pillars pillars = new Pillars();
        Rectangle pillar = pillars.generatenewPillar();

        assertNotNull(pillar);
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testUpdatePillar() {
        Pillars pillars = new Pillars();
        Rectangle pillar = new Rectangle();
        pillars.updatePillar(pillar);

        // Add assertions based on your specific implementation
    }

    @Test
    public void testRemovePillar() {
        Pillars pillars = new Pillars();
        Rectangle pillar = new Rectangle();
        pillars.removePillar(pillar);

        // Add assertions based on your specific implementation
    }
}
