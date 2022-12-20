package jeu;

public class Warrior extends Hero
{
    public Warrior(String name, int hp)
    {
        super(name, hp, 20);
        addObjects(new Armor("Bouclier", 35));
    }
}
