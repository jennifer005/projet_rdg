package jeu;

import java.util.ArrayList;

import java.util.Scanner;

public class Food extends Consumable
{
    public enum FoodType
    {

        APPLE, PIZZA;

        public static FoodType valueOfOrNull(String value)
        {
            try
            {
                return FoodType.valueOf(value);
            }
            catch (IllegalArgumentException ex)
            {
                return null;
            }
        }
    }
    protected int hp;
    public Food(int hp, String name) {
        super(name);
        this.hp = hp;
    }

    //Génère de la nourriture pour augmenter le hp d'un héro
    public static Food generateFood(FoodType foodType) {
        switch (foodType) {
            case APPLE:
                return new Food(100, "Apple");
            case PIZZA:
                return new Food(50, "Pizza");
            default:
                return null;
        }
    }
}


