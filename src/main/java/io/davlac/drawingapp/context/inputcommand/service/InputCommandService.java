package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

import java.util.Scanner;

public interface InputCommandService {

    String[] toRawInputCommand(Scanner userInput);

    InputCommand toInputCommand(String[] rawInputCommand);

}
