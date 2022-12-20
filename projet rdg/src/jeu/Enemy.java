package jeu;

public abstract class Enemy extends Combattant
{

    Enemy(String name, int hp, int degats)
    {
        super(name,hp, degats);

    }

    @Override
    public int calculeDegats()
    {
        return degats;
    }

    public void removeEnemy()
    {
    }
}


