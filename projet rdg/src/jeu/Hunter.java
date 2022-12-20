package jeu;

public class Hunter extends Hero
{

    public Hunter (String name, int hp)
    {
        super(name,hp, 30);
        addObjects(new Bow("arc", 15, 35));
        addObjects(new Armor("capuchon", 25));
    }

    public void attaquer(Hero hero) {
        for (int j = 0; j < objects.size(); j++) {
            if(objects.get(j) instanceof Bow) {
                ((Bow) objects.get(j)).removeFleches();
            }
        }

        super.attaquer(hero);
//                hero.removeHp(calculeDegats());
    }


}
