package jeu;

public class Bow extends Weapon
{
    public int nbFleches;
    public Bow(String name, int nbFleches, int degatWeapon)
    {
        super(name, degatWeapon);
        this.nbFleches = nbFleches;
    }

    public void removeFleches()
    {

            this.nbFleches = -1;

    }

    public void augmenterFleches()
    {
        this.nbFleches += 2;
    }

}
