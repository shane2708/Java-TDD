package ColourTablePackage;

import java.util.ArrayList;
import java.util.List;

public class ColourTable {
    private final List<Integer> colours;
    private final int size;

    public ColourTable(int size) {
        if (size < 2 || size > 1024 || (size & (size - 1)) != 0) {
            throw new IllegalArgumentException("Size must be a power of two and between 2 and 1024");
        }
        this.size = size;
        this.colours = new ArrayList<>(size);
    }

}