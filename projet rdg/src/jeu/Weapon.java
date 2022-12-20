package jeu;

import java.util.ArrayList;

public class Weapon extends Item
{
    protected int nombre;
    protected String name;
    protected int degatWeapon;

    public Weapon(String name, int degatWeapon)
    {
        super(name);
        this.degatWeapon = degatWeapon;
    }
}
