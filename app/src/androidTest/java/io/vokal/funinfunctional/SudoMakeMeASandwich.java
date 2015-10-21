package io.vokal.funinfunctional;

import io.vokal.funinfunctional.functional.FunctionalArray;
import io.vokal.funinfunctional.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class SudoMakeMeASandwich {

    /*******************
     * PRIVATE HELPERS *
     *******************/

    /**
     * @return An arrayList containing the sliceable foods which should be used in a sandwich.
     */
    private ArrayList<SliceableFood> foods() {
        return new ArrayList<>(
                Arrays.asList(new Bread(),
                        new Lettuce(),
                        new Tomato(),
                        new Turkey()));
    }

    /**
     * @return An array of ArrayLists of slices of foods.
     */
    private ArrayList<ArrayList<Slice>> sliceTheFoods(ArrayList<SliceableFood> foods) {
        //Convert the ArrayList to a functional array with the desired return type
        FunctionalArray<SliceableFood, ArrayList<Slice>> functionalFoods = new FunctionalArray<>(foods);

        //Apply the map function - it takes an array of the first type and returns an array of the second.
        return functionalFoods.map(new FunctionalArray.MapFunction<SliceableFood, ArrayList<Slice>>() {
            @Override
            public ArrayList<Slice> applyFunctionToItem(SliceableFood item) {
                //"Slice whatever this is into 10 pieces, and give all of them back to me."
                return item.slice(10);
            }
        });
    }

    /***
     * @param slicedFoods A 2-dimensional array of slices - the outer array holds arrays of Slices of
     *                    food for each food object.
     * @return An array of slices with one type for each object.
     */
    private ArrayList<Slice> grabASlice(ArrayList<ArrayList<Slice>> slicedFoods) {
        //Convert the 2D ArrayList into a functional array with the desired return type
        FunctionalArray<ArrayList<Slice>, Slice> functionalSliced = new FunctionalArray<>(slicedFoods);

        //Apply the map function - it takes an array of the first type and returns an array of the second.
        return functionalSliced.map(new FunctionalArray.MapFunction<ArrayList<Slice>, Slice>() {
            @Override
            public Slice applyFunctionToItem(ArrayList<Slice> item) {
                //"Grab me the first slice off the pile and hand it to me"
                return item.get(0);
            }
        });
    }

    /***
     * @param slicesOfFood An array of slices with each representing a different type of food.
     * @return Slices which are not tomatoes.
     */
    private ArrayList<Slice> holdTheTomatoes(ArrayList<Slice> slicesOfFood) {
        FunctionalArray<Slice, Slice> slicesToFilter = new FunctionalArray<>(slicesOfFood);
        return slicesToFilter.filter(new FunctionalArray.FilterFunction<Slice>() {
            @Override
            public boolean resultShouldIncludeItem(Slice item) {
                //"If this is not a tomato, give it to me"
                return !item.isOfType(Tomato.class);
            }
        });
    }

    /*********
     * TESTS *
     *********/

    @Test
    public void slicing() {
        ArrayList<ArrayList<Slice>> slicedPile = sliceTheFoods(foods());
        ArrayList<Slice> slices = grabASlice(slicedPile);
        ArrayList<Slice> expectedFoods = new ArrayList<>(Arrays.asList(new Slice("A slice of bread", Bread.class),
                new Slice("A slice of lettuce", Lettuce.class),
                new Slice("A slice of tomato", Tomato.class),
                new Slice("A slice of turkey", Turkey.class)));

        assertEquals(slices, expectedFoods);
    }

    @Test
    public void holdingTheTomatoes() {
        ArrayList<ArrayList<Slice>> slicedPile = sliceTheFoods(foods());
        ArrayList<Slice> slices = grabASlice(slicedPile);
        ArrayList<Slice> holdTheTomatoes = holdTheTomatoes(slices);

        ArrayList<Slice> expectedFoods = new ArrayList<>(Arrays.asList(new Slice("A slice of bread", Bread.class),
                new Slice("A slice of lettuce", Lettuce.class),
                new Slice("A slice of turkey", Turkey.class)));

        assertEquals(holdTheTomatoes, expectedFoods);
    }

    @Test
    public void makingASandwich() {
        ArrayList<ArrayList<Slice>> slicedPile = sliceTheFoods(foods());
        ArrayList<Slice> slices = grabASlice(slicedPile);
        ArrayList<Slice> holdTheTomatoes = holdTheTomatoes(slices);
        FunctionalArray<Slice, String> ingredients = new FunctionalArray<>(holdTheTomatoes);
        final String starter = "Sandwich Contains: ";
        String sammich = ingredients.reduce(starter, new FunctionalArray.ReduceFunction<Slice, String>() {

            @Override
            public String addToInitialValue(String initialValue, Slice itemToAdd) {
                if (!initialValue.equals(starter)) {
                    //If this isn't the starting value, add a comma and a space then the value
                    // to what was passed in.
                    return initialValue + ", " + itemToAdd.getDescription();
                } else {
                    //If this *is* the starting value, just add the value to what was passed in.
                    return initialValue + itemToAdd.getDescription();
                }
            }
        });

        String expectedSammich = "Sandwich Contains: A slice of bread, A slice of lettuce, A slice of turkey";
        assertEquals(sammich, expectedSammich);
    }
}
