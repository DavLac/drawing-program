package io.davlac.drawingapp.context.output.model;

import java.util.Arrays;

public class RawOutput {
    private final String[] content;

    public RawOutput(String[] content) {
        this.content = content;
    }

    public String[] getContent() {
        return content;
    }

    public void print() {
        if(this.content == null) {
            return;
        }

        Arrays.stream(this.content).forEach(System.out::println);
    }
}
