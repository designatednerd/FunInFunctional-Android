package io.vokal.funinfunctional.model;

import java.util.ArrayList;

public interface Sliceable {
    String description();
    ArrayList<String> slices();
    void slice(int aSlices);
}
