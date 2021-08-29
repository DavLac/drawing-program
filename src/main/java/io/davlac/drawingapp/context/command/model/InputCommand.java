package io.davlac.drawingapp.context.command.model;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.stream.Collectors;

import static io.davlac.drawingapp.context.utils.StringsUtils.splitWithoutEmptyElements;

public final class InputCommand {

    private static final String ARGUMENT_SEPARATOR = " ";

    private final Action action;
    private final List<String> arguments;

    public InputCommand(InputCommand.Builder builder) {
        this.action = builder.action;
        this.arguments = builder.arguments;
    }

    public static InputCommand.Builder builder() {
        return new InputCommand.Builder();
    }

    public InputCommand(String rawInputCommand) {
        String[] rawCommandSplitted = splitWithoutEmptyElements(rawInputCommand, ARGUMENT_SEPARATOR);
        String[] rawCommandArguments = ArrayUtils.remove(rawCommandSplitted, 0);
        this.action = Action.getActionByCommand(rawCommandSplitted[0]);
        this.arguments = List.of(rawCommandArguments);
    }

    public static class Builder {

        private Action action;
        private List<String> arguments;

        public Builder action(Action action) {
            this.action = action;
            return this;
        }

        public Builder arguments(List<String> arguments) {
            this.arguments = arguments.stream()
                    .collect(Collectors.toUnmodifiableList());
            return this;
        }

        public InputCommand create() {
            return new InputCommand(this);
        }
    }

    public Action getAction() {
        return action;
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "InputCommand{" +
                "action=" + action +
                ", arguments=" + arguments +
                '}';
    }
}
