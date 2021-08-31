package io.davlac.drawingapp.context.canvascontent.utils;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

public class FloodFillUtils {

    private FloodFillUtils() {
    }

    /**
     * It mainly finds the previous color on (y, x) and
     * calls floodFillRecurs()
     */
    public static void floodFill(char[][] canvasContent, Coordinates c, char newColor) {
        int prevColor = canvasContent[c.getY() - 1][c.getX() - 1];
        if (prevColor == newColor) {
            return;
        }
        floodFillRecurs(canvasContent, c.getY() - 1, c.getX() - 1, prevColor, newColor);
    }

    /**
     * A recursive function to replace previous color 'prevColor' at '(y ,x)'
     * and all surrounding pixels of (y, x) with new color 'newColor'
     */
    private static void floodFillRecurs(char[][] canvasContent, int y, int x,
                                        int prevColor, char newColor) {
        // Base cases
        if (x < 0 || x >= canvasContent[0].length ||
                y < 0 || y >= canvasContent.length) {
            return;
        }

        if (canvasContent[y][x] != prevColor) {
            return;
        }

        // Replace the color at (x, y)
        canvasContent[y][x] = newColor;

        // Recur for north, east, south and west
        floodFillRecurs(canvasContent, y, x + 1, prevColor, newColor);
        floodFillRecurs(canvasContent, y, x - 1, prevColor, newColor);
        floodFillRecurs(canvasContent, y + 1, x, prevColor, newColor);
        floodFillRecurs(canvasContent, y - 1, x, prevColor, newColor);
    }
}
