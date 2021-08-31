package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

public interface FloodFillService {
    char[][] floodFill(char[][] grid, Coordinates startingCoordinates, char newColor);
}
