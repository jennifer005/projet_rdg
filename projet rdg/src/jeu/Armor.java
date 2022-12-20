package jeu;

public class Armor extends Item
{
    protected int resistance;

    public Armor(String nomObjet, int resistance)
    {
        super(nomObjet);
        this.resistance = resistance;
    }

}
