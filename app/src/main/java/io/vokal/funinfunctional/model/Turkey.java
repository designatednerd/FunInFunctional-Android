package io.vokal.funinfunctional.model;

import java.util.ArrayList;

public class Turkey extends SliceableFood {


    public Turkey() {
        mDescription = "A turkey breast";
    }

    @Override
    protected String singularDescriptor() {
        return "turkey";
    }
}
