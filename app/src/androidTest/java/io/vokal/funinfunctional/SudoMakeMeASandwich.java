package io.vokal.funinfunctional;

import io.vokal.funinfunctional.functional.FunctionalArray;
import io.vokal.funinfunctional.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class SudoMakeMeASandwich {

    private Bread mBread;
    private Lettuce mLettuce;
    private Tomato mTomato;
    private Turkey mTurkey;

    /******************
     * TEST LIFECYCLE *
     ******************/

    @Before
    public void beforeTest() {
        mBread = new Bread();
        mLettuce = new Lettuce();
        mTomato = new Tomato();
        mTurkey = new Turkey();
    }

    /*******************
     * PRIVATE HELPERS *
     *******************/

    private FunctionalArray<SliceableFood, String> foods() {
        return new FunctionalArray<>(Arrays.asList(mBread, mLettuce, mTomato, mTurkey));
    }

    private ArrayList<String> sliceTheFoods() {
        return foods().map(new FunctionalArray.MapFunction<SliceableFood, String>() {
            @Override
            public String applyFunctionToItem(SliceableFood item) {
                item.slice(10);
                return item.getSlice();
            }
        });
    }

    private ArrayList<String> holdTheTomatoes(ArrayList<String> slicedFoods) {
        FunctionalArray<String, String> slicesToFilter = new FunctionalArray<>(slicedFoods);
        return slicesToFilter.filter(new FunctionalArray.FilterFunction<String>() {
            @Override
            public boolean resultShouldIncludeItem(String item) {
                return !item.contains("tomato");
            }
        });
    }

    /*********
     * TESTS *
     *********/

    @Test
    public void slicing() {
        ArrayList<String> slices = sliceTheFoods();
        ArrayList<String> expectedFoods = new ArrayList<>(Arrays.asList("A slice of bread",
                "A slice of lettuce",
                "A slice of tomato",
                "A slice of turkey"));

        assertEquals(slices, expectedFoods);

        assertEquals(mBread.slices().size(), 9);
        assertEquals(mLettuce.slices().size(), 9);
        assertEquals(mTomato.slices().size(), 9);
        assertEquals(mTurkey.slices().size(), 9);
    }

    @Test
    public void filteringOutTerribleFood() {
        ArrayList<String> slices = sliceTheFoods();
        ArrayList<String> holdTheTomatoes = holdTheTomatoes(slices);

        ArrayList<String> expectedFoods = new ArrayList<>(Arrays.asList("A slice of bread",
                "A slice of lettuce",
                "A slice of turkey"));

        assertEquals(holdTheTomatoes, expectedFoods);
    }

    @Test
    public void makingASandwich() {
        ArrayList<String> slices = sliceTheFoods();
        ArrayList<String> holdTheTomatoes = holdTheTomatoes(slices);
        FunctionalArray<String, String> ingredients = new FunctionalArray<>(holdTheTomatoes);
        final String starter = "Sandwich Contains: ";
        String sammich = ingredients.reduce(starter, new FunctionalArray.ReduceFunction<String, String>() {

            @Override
            public String addToInitialValue(String initialValue, String itemToAdd) {
                if (!initialValue.equals(starter)) {
                    return initialValue + ", " + itemToAdd;
                } else {
                    return initialValue + itemToAdd;
                }
            }
        });

        String expectedSammich = "Sandwich Contains: A slice of bread, A slice of lettuce, A slice of turkey";
        assertEquals(sammich, expectedSammich);
    }
}
