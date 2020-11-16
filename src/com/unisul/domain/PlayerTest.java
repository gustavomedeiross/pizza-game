package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player sut;
    private Player anotherPlayer;
    private Player anotherPlayer2;
    private CircularLinkedList<Player> players;

    @BeforeEach
    protected void setUp() {
        players = new CircularLinkedList<Player>();
        sut = new Player(new Pizza(Pizza.Flavor.CALABRESA));
        anotherPlayer = new Player(new Pizza(Pizza.Flavor.MUCARELA));
        anotherPlayer2 = new Player(new Pizza(Pizza.Flavor.PORTUGUESA));
        players.add(sut);
        players.add(anotherPlayer);
        players.add(anotherPlayer2);
    }

    // TODO rename helper functions
    private void addNeededIngredientOnAnotherPizza() {
        assertTrue(anotherPlayer.pizza.addIngredient(Pizza.Ingredient.QUEIJO));
    }

    private void addAnotherNeededIngredientOnAnotherPizza() {
        assertTrue(anotherPlayer.pizza.addIngredient(Pizza.Ingredient.AZEITONA));
    }

    private void addIngredientNotNeededOnAnotherPizza() {
        assertTrue(anotherPlayer.pizza.addIngredient(Pizza.Ingredient.TOMATE));
    }

    private void addNeededIngredientOnAnotherPlayer2Pizza() {
        assertTrue(anotherPlayer2.pizza.addIngredient(Pizza.Ingredient.CALABRESA));
    }

    @Test
    public void testShouldStealIngredientFromAnotherPlayer() {
        addNeededIngredientOnAnotherPizza();
        assertEquals(1, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
        sut.stealIngredientFromAnotherPlayer(players);
        assertEquals(0, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(1, sut.pizza.ingredientsFound().size());
    }

    @Test
    public void testShouldOnlyStealOneIngredientFromAnotherPlayer() {
        addNeededIngredientOnAnotherPizza();
        addAnotherNeededIngredientOnAnotherPizza();
        assertEquals(2, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
        sut.stealIngredientFromAnotherPlayer(players);
        assertEquals(1, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(1, sut.pizza.ingredientsFound().size());
    }

    @Test
    public void testShouldOnlyRemoveWhenAnotherPlayerDoesntHaveAnyNeededIngredient() {
        addIngredientNotNeededOnAnotherPizza();
        assertEquals(1, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
        sut.stealIngredientFromAnotherPlayer(players);
        assertEquals(0, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
    }

    @Test
    public void testShouldntChangeAnythingIfTheOtherPlayersDontHaveAnyIngredientAtAll() {
        assertEquals(0, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
        sut.stealIngredientFromAnotherPlayer(players);
        assertEquals(0, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
    }

    @Test
    public void testStealIngredientFromAnotherPlayer2() {
        addIngredientNotNeededOnAnotherPizza();
        addNeededIngredientOnAnotherPlayer2Pizza();
        assertEquals(1, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(1, anotherPlayer2.pizza.ingredientsFound().size());
        assertEquals(0, sut.pizza.ingredientsFound().size());
        sut.stealIngredientFromAnotherPlayer(players);
        assertEquals(1, anotherPlayer.pizza.ingredientsFound().size());
        assertEquals(0, anotherPlayer2.pizza.ingredientsFound().size());
        assertEquals(1, sut.pizza.ingredientsFound().size());
    }
}