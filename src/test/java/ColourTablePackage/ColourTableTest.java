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

}
