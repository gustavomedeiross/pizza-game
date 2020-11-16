package com.unisul.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTest {
    private Pizza sut;

    @BeforeEach
    protected void setUp() {
        sut = new Pizza(Pizza.Flavor.CALABRESA);
    }

    @Test
    public void testSimpleNeedsIngredient() {
        assertTrue(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
        assertTrue(sut.needsIngredient(Pizza.Ingredient.QUEIJO));
        assertFalse(sut.needsIngredient(Pizza.Ingredient.OVOS));
    }

    @Test
    public void testNeedsIngredientThatIsInAnotherPizza() {
        sut.addIngredient(Pizza.Ingredient.QUEIJO);
        Pizza pizza = new Pizza(Pizza.Flavor.MUCARELA);
        assertTrue(pizza.needsIngredient(sut.ingredientsFound().get(0)));
    }

    @Test
    public void testAddSpecificIngredient() {
        assertTrue(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertFalse(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
        assertTrue(sut.foundAnyIngredient());
    }

    @Test
    public void testShouldntAddIngredientNotRequired() {
        assertEquals(0, sut.ingredientsFound().size());
        assertFalse(sut.addIngredient(Pizza.Ingredient.OVOS));
        assertEquals(0, sut.ingredientsFound().size());
        assertFalse(sut.foundAnyIngredient());
    }

    @Test
    public void testShouldntAddSameIngredientTwoTimes() {
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertFalse(sut.addIngredient(Pizza.Ingredient.CALABRESA));
    }

    @Test
    public void testAddAnyIngredient() {
        assertEquals(0, sut.ingredientsFound().size());
        assertTrue(sut.addIngredient());
        assertEquals(1, sut.ingredientsFound().size());
    }

    @Test
    public void testFoundIngredients() {
        sut.addIngredient(Pizza.Ingredient.CALABRESA);
        sut.addIngredient(Pizza.Ingredient.QUEIJO);
        assertEquals(2, sut.ingredientsFound().size());
    }

    @Test
    public void testRemoveIngredient() {
        assertTrue(sut.addIngredient());
        assertEquals(1, sut.ingredientsFound().size());
        assertTrue(sut.removeIngredient());
        assertEquals(0, sut.ingredientsFound().size());
    }

    @Test
    public void testShouldntRemoveIngredientWhenItDoesntHaveAny() {
        assertEquals(0, sut.ingredientsFound().size());
        assertFalse(sut.removeIngredient());
        assertEquals(0, sut.ingredientsFound().size());
    }


    @Test
    public void testRemoveSpecificIngredient() {
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertEquals(1, sut.ingredientsFound().size());
        assertTrue(sut.removeIngredient(Pizza.Ingredient.CALABRESA));
        assertEquals(0, sut.ingredientsFound().size());
    }

    @Test
    public void testShouldntRemoveIngredientThatDoesntHave() {
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertEquals(1, sut.ingredientsFound().size());
        assertFalse(sut.removeIngredient(Pizza.Ingredient.QUEIJO));
        assertEquals(1, sut.ingredientsFound().size());
    }

    @Test
    public void testShouldntRemoveIngredientThatIsNotRequired() {
        assertFalse(sut.removeIngredient(Pizza.Ingredient.OVOS));
        assertEquals(0, sut.ingredientsFound().size());
    }

    @Test
    public void testShouldRemoveAllIngredients() {
        sut.addIngredient();
        sut.addIngredient();
        assertEquals(2, sut.ingredientsFound().size());
        sut.removeAllIngredients();
        assertEquals(0, sut.ingredientsFound().size());
    }
}