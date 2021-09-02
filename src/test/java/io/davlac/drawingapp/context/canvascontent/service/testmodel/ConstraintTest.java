package io.davlac.drawingapp.context.canvascontent.service.testmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ConstraintTest {
    @NotEmpty
    private String field1;

    @Size(min = 2, max = 4)
    private String field2;

    public ConstraintTest(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
