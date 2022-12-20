package jeu;

public class Mage extends SpellCaster
{
    public Mage(String name, int hp)
    {
        super(name, hp, 80, 20);
        addObjects(new Armor("Bouclier", 25));
    }

    @Override
    public void attaquer(Combattant combattant)
    {
        if(mana >= manaParSort)
        {
            super.attaquer(combattant);
        }
        else
        {
            System.out.println("Il n'y a pas assez de mana pour pouvoir attaquer ");
        }
    }
}
