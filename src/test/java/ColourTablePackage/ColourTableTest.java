package ColourTablePackage;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ColourTableTest {

    @Test
    public void testValidTableSizes() {
        new ColourTable(2);
        new ColourTable(4);
        new ColourTable(8);
        new ColourTable(1024);
    }

    @Test
    public void testInvalidTableSizes() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1));
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1025));
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(-3));
    }

    @Test
    public void testSizeValuesPowerOfTwo() {
        new ColourTable(2);
        new ColourTable(4);
        new ColourTable(8);
        new ColourTable(1024);

        assertThrows(IllegalArgumentException.class, () -> new ColourTable(3));
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(6));
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1025));
    }

    @Test
    public void testAddValidColours() {
        ColourTable ct = new ColourTable(2);
        ct.add(0, 0, 0);
        ct.add(205, 255, 215);
    }

    @Test
    public void testAddInvalidColours() {
        ColourTable ct = new ColourTable(2);
        assertThrows(IllegalArgumentException.class, () -> ct.add(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> ct.add(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> ct.add(0, 0, 256));
    }

    @Test
    public void testTableCapacity() {
        ColourTable ct = new ColourTable(2);
        ct.add(0, 0, 0);
        ct.add(255, 255, 255);
        assertThrows(IllegalStateException.class, () -> ct.add(128, 128, 128));
    }

    @Test
    public void testDuplicateColourAddition() {
        ColourTable ct = new ColourTable(2);
        ct.add(0, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> ct.add(0, 0, 0));
    }

    @Test
    public void testGetColours() {
        ColourTable ct = new ColourTable(2);
        ct.add(255, 0, 0);
        ct.add(0, 255, 0);

        List<Integer> colours = ct.getColours();
        assertEquals(2, colours.size());
        assertEquals(16711680, (int) colours.get(0));
        assertEquals(65280, (int) colours.get(1));
    }

    @Test
    public void testColoursStoredCorrectly() {
        ColourTable ct = new ColourTable(2);
        ct.add(255, 0, 0);
        ct.add(0, 255, 0);

        List<Integer> colours = ct.getColours();
        for (int rgb : colours) {
            assertTrue(rgb >= 0x000000 && rgb <= 0xFFFFFF);
        }
    }

    @Test
    public void testGetRGB() {
        ColourTable ct = new ColourTable(2);
        ct.add(255, 0, 0);
        ct.add(0, 255, 0);

        List<int[]> rgbValues = ct.getRGB();
        assertEquals(2, rgbValues.size());
        assertArrayEquals(new int[]{255, 0, 0}, rgbValues.get(0));
        assertArrayEquals(new int[]{0, 255, 0}, rgbValues.get(1));
    }

    @Test
    public void testGetHex() {
        ColourTable ct = new ColourTable(2);
        ct.add(255, 0, 0);
        ct.add(0, 255, 0);

        List<String> hexValues = ct.getHex();
        assertEquals(2, hexValues.size());
        assertEquals("#ff0000", hexValues.get(0));
        assertEquals("#00ff00", hexValues.get(1));
    }

    @Test
    public void testDisplay() {
        ColourTable ct = new ColourTable(4);
        ct.add(255, 0, 0);
        ct.add(0, 255, 0);
        ct.add(0, 0, 255);
        ct.add(255, 255, 255);

        String output = ct.display();
        String expectedOutput = "\033[48;2;255;0;0m \033[0m\033[48;2;0;255;0m \033[0m\033[48;2;0;0;255m \033[0m\033[48;2;255;255;255m \033[0m\n";
        assertEquals(expectedOutput, output);
    }

}
