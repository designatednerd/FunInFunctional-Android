package io.vokal.funinfunctional.model;

import java.util.ArrayList;

public abstract class SliceableFood {

    protected String mDescription;

    protected abstract String singularDescriptor();

    public ArrayList<Slice> slice(int aSlices) {
        ArrayList<Slice> slices = new ArrayList<>();

        for (int i = 0; i < aSlices; i++) {
            Slice slice = new Slice("A slice of " + singularDescriptor(), this.getClass());
            slices.add(slice);
        }

        return slices;
    }

    public String description() {
        return mDescription;
    }
}
