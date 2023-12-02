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

    public void add(int red, int green, int blue) {
        if (colours.size() >= size) {
            throw new IllegalStateException("ColourTable is full");
        }
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Invalid RGB value");
        }
        Integer rgb = (red << 16) | (green << 8) | blue;
        if (colours.contains(rgb)) {
            throw new IllegalArgumentException("Colour already exists in the ColourTable");
        }
        colours.add(rgb);
    }

    public List<Integer> getColours() {
        return colours;
    }

    public List<int[]> getRGB() {
        List<int[]> rgbValues = new ArrayList<>();
        for (int colour : colours) {
            int r = (colour >> 16) & 0xFF;
            int g = (colour >> 8) & 0xFF;
            int b = colour & 0xFF;
            rgbValues.add(new int[]{r, g, b});
        }
        return rgbValues;
    }

    public List<String> getHex() {
        List<String> hexValues = new ArrayList<>();
        for (int colour : colours) {
            int r = (colour >> 16) & 0xFF;
            int g = (colour >> 8) & 0xFF;
            int b = colour & 0xFF;
            String hex = String.format("#%02x%02x%02x", r, g, b);
            hexValues.add(hex);
        }
        return hexValues;
    }

}