package io.vokal.funinfunctional.model;

public class Lettuce extends SliceableFood {

    public Lettuce() {
        mDescription = "A head of lettuce";
    }

    @Override
    protected String singularDescriptor() {
        return "lettuce";
    }
}
