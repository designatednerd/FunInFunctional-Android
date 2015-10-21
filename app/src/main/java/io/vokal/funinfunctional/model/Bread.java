package io.vokal.funinfunctional.model;

import java.util.ArrayList;

public class Bread extends SliceableFood {

    public Bread() {
        mDescription = "A loaf of bread";
    }

    @Override
    protected String singularDescriptor() {
        return "bread";
    }
}
