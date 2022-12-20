package jeu;

public abstract class SpellCaster extends Hero
{

    protected int mana;
    protected int manaParSort;
    public SpellCaster(String name, int hp, int mana, int manaParSort)
    {
        super(name, hp, 45);
        this.mana = mana;
        this.manaParSort = manaParSort;
    }

    @Override
    public boolean utiliserConsommable(int choixConsommable)
    {
        boolean utiliserConsommableClassique = super.utiliserConsommable(choixConsommable);
        Item objetCourant = objects.get(choixConsommable);

        if(objetCourant instanceof Potion)
        {
            mana = mana + ((Potion) objetCourant).mana;
            System.out.println("Voici à présent votre niveau de mana : " + mana);

            objects.remove(objetCourant);

            return true;
        }
        return utiliserConsommableClassique;
    }

}
