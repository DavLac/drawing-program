package io.davlac.drawingapp.context.canvascontent.service.impl.floodfill;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

import static io.davlac.drawingapp.context.canvasbody.utils.ArrayUtils.coordinatesAreOutsideTheArray;
import static io.davlac.drawingapp.context.canvasbody.utils.ArrayUtils.deepCopyArray;

/**
 * This flood fill class follow the Breadth-First Search flood fill algorithm
 */
@Component
public class BreadthFirstSearchFloodFillService implements FloodFillService {

    @Override
    public char[][] floodFill(char[][] grid, Coordinates startingCoordinates, char newColor) {
        if (grid == null || grid.length == 0) {
            return grid;
        }

        checkCoordinates(startingCoordinates, grid);

        char prevColor = grid[startingCoordinates.getY() - 1][startingCoordinates.getX() - 1];

        if (prevColor == newColor) {
            return grid;
        }

        return floodFillBFS(grid, startingCoordinates.getY() - 1, startingCoordinates.getX() - 1, newColor, prevColor);
    }

    private static char[][] floodFillBFS(char[][] grid, int y, int x, char newColor, char prevColor) {
        char[][] gridModified = deepCopyArray(grid);
        int gridHeight = gridModified.length;
        int gridWidth = gridModified[0].length;
        int[] directions = new int[]{0, 1, 0, -1, 0};

        gridModified[y][x] = newColor;

        // init discovered matrix to all false
        boolean[][] discovered = new boolean[gridHeight][gridWidth];

        // create a queue for doing BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});

        // loop till queue is empty
        while (!queue.isEmpty()) {
            // dequeue front node
            int[] current = queue.poll();
            discovered[current[0]][current[1]] = true;

            // Perform BFS traversal from all undiscovered nodes to cover all unconnected components
            for (int i = 0; i < directions.length - 1; i++) {
                int nextRow = current[0] + directions[i];
                int nextColumn = current[1] + directions[i + 1];
                if (nextRowIsOutsideTheGrid(nextRow, gridHeight) ||
                        nextColumnIsOutsideTheGrid(nextColumn, gridWidth) ||
                        gridModified[nextRow][nextColumn] != prevColor ||
                        discovered[nextRow][nextColumn]) {
                    continue;
                }
                gridModified[nextRow][nextColumn] = newColor;
                queue.offer(new int[]{nextRow, nextColumn});
            }
        }
        return gridModified;
    }

    private static boolean nextRowIsOutsideTheGrid(int nextRow, int gridHeight) {
        return nextRow < 0 || nextRow >= gridHeight;
    }

    private static boolean nextColumnIsOutsideTheGrid(int nextColumn, int gridWidth) {
        return nextColumn < 0 || nextColumn >= gridWidth;
    }

    private static void checkCoordinates(Coordinates coord, char[][] grid) {
        if (coord == null) {
            throw new InternalError("ERROR : Coordinates is null");
        }

        if (coord.getX() == 0 || coord.getY() == 0) {
            throw new InternalError("ERROR : One of coordinates is 0");
        }

        if (coordinatesAreOutsideTheArray(grid, coord.getX(), coord.getY())) {
            throw new InternalError(String.format("ERROR: coordinates are outside the grid : '%s'.", coord));
        }
    }
}
