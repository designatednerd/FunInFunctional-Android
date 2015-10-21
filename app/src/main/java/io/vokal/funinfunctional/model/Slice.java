package io.vokal.funinfunctional.model;

public class Slice {

    private String mDescription;
    private Class mItemClazz;

    public Slice(String aDescription, Class aItemClazz) {
        mDescription = aDescription;
        mItemClazz = aItemClazz;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isOfType(Class clazzType) {
        return mItemClazz == clazzType;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Slice) {
            Slice slice = (Slice)o;

            //If it's the same description and type, it's the same item.
            return (slice.mDescription.equals(this.mDescription)
                    && slice.isOfType(this.mItemClazz));
        }

        return false;
    }
}
