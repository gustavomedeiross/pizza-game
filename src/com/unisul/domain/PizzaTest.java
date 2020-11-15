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
    public void testFlavorSelection() {
        assertTrue(sut.ingredients.size() >= 0);
    }

    @Test
    public void testSimpleNeedsIngredient() {
        assertTrue(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
        assertTrue(sut.needsIngredient(Pizza.Ingredient.QUEIJO));
        assertFalse(sut.needsIngredient(Pizza.Ingredient.OVOS));
    }

    @Test
    public void testAddSpecificIngredient() {
        assertTrue(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertFalse(sut.needsIngredient(Pizza.Ingredient.CALABRESA));
    }

    @Test
    public void testShouldntAddIngredientNotRequired() {
        assertEquals(3, sut.ingredients.size());
        assertFalse(sut.addIngredient(Pizza.Ingredient.OVOS));
        assertEquals(3, sut.ingredients.size());
    }

    @Test
    public void testShouldntAddSameIngredientTwoTimes() {
        assertTrue(sut.addIngredient(Pizza.Ingredient.CALABRESA));
        assertFalse(sut.addIngredient(Pizza.Ingredient.CALABRESA));
    }

    @Test
    public void testAddAnyIngredient() {
        assertEquals(3, sut.ingredients.size());
        assertTrue(sut.addIngredient());
        assertEquals(2, sut.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        assertTrue(sut.addIngredient());
        assertEquals(2, sut.ingredients.size());
        assertTrue(sut.removeIngredient());
        assertEquals(3, sut.ingredients.size());
    }

    @Test
    public void testShouldntRemoveIngredient() {
        assertEquals(3, sut.ingredients.size());
        assertFalse(sut.removeIngredient());
        assertEquals(3, sut.ingredients.size());
    }
}