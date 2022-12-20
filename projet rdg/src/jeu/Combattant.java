package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Combattant
{
    protected String name;
    protected int hp;
    protected int degats;
    protected int resistance;
    protected int effPotion;
    protected int effNourriture;

    public Combattant(String name, int hp, int degats)
    {
        this.name=name;
        this.hp=hp;
        this.degats = degats;
    }

    // Initialiser la valeur Hp
    public void removeHp(int hp)
    {

        this.hp = this.hp - hp;
    }

    //Retourner la valeur Hp
    public int getHp()
    {
        return this.hp;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {

        return this.name;
    }

    public boolean isDead()
    {
        if(hp <= 0)
        {
            System.out.println("Le combattant " + name + " est mort");

            return true;
        }

        return false;
    }

    // permet de retirer l'hp de la personne qu'on attaque si c'est un héro ou un ennemie
    public void attaquer(Combattant combattant)
    {
        combattant.removeHp(calculeDegats());
    }

    public abstract int calculeDegats();

}
