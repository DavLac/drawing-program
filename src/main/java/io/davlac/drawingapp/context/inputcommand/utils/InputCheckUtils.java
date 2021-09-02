package io.davlac.drawingapp.context.inputcommand.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

import java.util.List;

public interface InputCheckUtils {
    static boolean checkArgumentLength(List<String> arguments, ActionCommand actionCommand) {
        if (arguments == null || arguments.size() < actionCommand.getMinArgumentSize()) {
            throw new IllegalArgumentException(String.format("ERROR: Action '%s' - not enough arguments (%d)",
                    actionCommand, actionCommand.getMinArgumentSize()));
        }
        return true;
    }
}
