package jeu;

public class Potion extends Consumable
{
    protected int mana;
    public Potion(String nomObjet, int mana)
    {
        super(nomObjet);
        this.mana = mana;
    }
}
