package jeu;

public class Healer extends SpellCaster
{

    public Healer(String name, int hp)
    {
        super(name, hp, 100, 15);
        addObjects(new Armor("Veste",15 ));
    }

    public boolean soignerHero(Hero heroCourant)
    {
        if(mana >= manaParSort )
        {
            if(heroCourant.isDead())
            {
                System.out.println("Votre Héro est mort, il ne peut pas être soigné");

                return false;
            }
            mana -= manaParSort;
            heroCourant.hp += degats;
            if (heroCourant.hp > 100) {
                heroCourant.hp = 100;
            }

            System.out.println("Voici le niveau d'hp de votre héro une fois soigné : " + heroCourant.hp);

            return true;
        }
        else
        {
            System.out.println("Il n'y a pas assez de mana pour pouvoir soigner le Héro");

            return false;
        }
    }
}

