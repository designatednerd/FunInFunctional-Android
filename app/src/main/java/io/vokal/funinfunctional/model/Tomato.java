package io.vokal.funinfunctional.model;

public class Tomato extends SliceableFood {

    public Tomato() {
        mDescription = "A whole tomato";
    }

    @Override
    protected String singularDescriptor() {
        return "tomato";
    }
}
