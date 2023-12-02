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

}
