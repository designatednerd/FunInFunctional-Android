package io.vokal.funinfunctional.model;

import java.util.ArrayList;

public abstract class SliceableFood implements Sliceable {

    protected String mDescription;
    protected ArrayList<String> mSlices;

    protected abstract String singularDescriptor();

    @Override
    public void slice(int aSlices) {
        mSlices = new ArrayList<>();

        for (int i = 0; i < aSlices; i++) {
            mSlices.add("A slice of " + singularDescriptor());
        }
    }

    @Override
    public String description() {
        return mDescription;
    }

    @Override
    public ArrayList<String> slices() {
        return mSlices;
    }

    public String getSlice() {
        if (mSlices == null || mSlices.size() == 0) {
            return null;
        } else {
            String slice = mSlices.remove(0);
            return slice;
        }
    }
}
