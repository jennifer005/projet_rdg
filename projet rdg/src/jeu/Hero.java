package jeu;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends Combattant
{
    protected List<Item> objects;
    protected boolean isDefense = false;
    public Hero(String name, int hp, int degats)
    {
        super(name,hp, degats);
        objects = new ArrayList<>();
    }

    public Weapon getArm()
    {
        for(int i = 0; i < objects.size(); i++)
        {
            Item item = objects.get(i);

            if(item instanceof Weapon)
            {
                return (Weapon)item;
            }
        }
        return null;
    }

    public Armor getArmor()
    {
        for(int i = 0; i < objects.size(); i++)
        {
            Item item = objects.get(i);

            if(item instanceof Armor)
            {
                return (Armor)item;
            }
        }
        return null;
    }

    public void addObjects(Item item)
    {
        objects.add(item);
    }

    //Calcule des dégats commit par le héro et sa potentielle arme
    @Override
    public int calculeDegats() 
    {
        Weapon arm = getArm();
        int degatArm = 0;

        if(arm != null)
        {
            degatArm = arm.degatWeapon;
        }
        return degats + degatArm;
    }

    @Override
    public void attaquer(Combattant combattant)
    {
        super.attaquer(combattant);
        isDefense = false;
    }

    public void afficherItem(boolean consumable)
    {
        if (!consumable) {
            for (int i = 0; i < objects.size(); i++) {
                System.out.println(i + "-" + objects.get(i).nomObjet);
            }
        }
        else {
            for (int i = 0; i < objects.size(); i++) {
                if ((objects.get(i) instanceof Food)| (objects.get(i) instanceof Potion)){
                    System.out.println(i + "-" + objects.get(i).nomObjet);
                }
            }
        }

    }

    public boolean utiliserConsommable(int choixConsommable)
    {
        Item objetCourant = objects.get(choixConsommable);

        if(objetCourant instanceof Food)
        {
            hp = hp + ((Food) objetCourant).hp;
            if (hp > 100) {
                hp = 100;
            }

            System.out.println("Voici à présent votre niveau d'hp : " + hp);

            objects.remove(objetCourant);

            return true;
        }

        return false;
    }

}

