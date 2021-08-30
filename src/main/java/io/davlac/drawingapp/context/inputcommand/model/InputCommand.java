package io.davlac.drawingapp.context.inputcommand.model;

import java.util.List;
import java.util.stream.Collectors;

public final class InputCommand {

    private final ActionCommand actionCommand;

    // List instead of an Array to make children's field immutable
    private final List<String> arguments;

    public InputCommand(InputCommand.Builder builder) {
        this.actionCommand = builder.actionCommand;
        this.arguments = builder.arguments;
    }

    public static InputCommand.Builder builder() {
        return new InputCommand.Builder();
    }

    public static class Builder {

        private ActionCommand actionCommand;
        private List<String> arguments;

        public Builder actionCommand(ActionCommand actionCommand) {
            this.actionCommand = actionCommand;
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

    public ActionCommand getAction() {
        return actionCommand;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
