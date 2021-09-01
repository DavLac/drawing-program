package io.davlac.drawingapp.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

public class Constants {
    public static final String QUIT_ACTION = String.valueOf(ActionCommand.QUIT.getCommand());
    public static final String CREATE_ACTION = String.valueOf(ActionCommand.CREATE_CANVAS.getCommand());
    public static final String DRAW_LINE_ACTION = String.valueOf(ActionCommand.DRAW_LINE.getCommand());
    public static final String DRAW_RECTANGLE_ACTION = String.valueOf(ActionCommand.DRAW_RECTANGLE.getCommand());
    public static final String FILL_ACTION = String.valueOf(ActionCommand.DRAW_BUCKET_FILL.getCommand());
}
