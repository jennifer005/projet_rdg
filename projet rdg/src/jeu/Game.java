package jeu;

import java.util.*;
import java.util.stream.Collectors;

//import static jeu.Food.generateFood;

public class Game
{
    private static int nombre_h;
    private static int nombre_e;
    public static ArrayList<String>  liste;
    public static int niveau =0;
    public static int NIVEAU_MAX = 4;

    private static List<Hero> listeHeros= new ArrayList<>();
    private static List<Enemy> listeEnemies= new ArrayList<>();

    private Combattant combattant;

    public static void main(String[]args) {
        initGame();

        int compteurCombats = 0;
        boolean enemiesAllDead = false;
        boolean herosAllDead = false;
        Random rand = new Random();

        while (compteurCombats <= 4 && !herosAllDead) {
            initEnnemies(compteurCombats);
            enemiesAllDead = false;

            // Début de boucle pour un combat
            while (!enemiesAllDead && !herosAllDead) {
                Collections.shuffle(listeHeros);

                //Tous les Héros attaquent en même temps
                // Boucle pour l'attaque des Héros
                for (int i = 0; i < listeHeros.size(); i++) {

                    boolean actionDone = false;
                    while (!actionDone) {
                        Hero heroCourant = listeHeros.get(i);
                        System.out.println("Hero " + heroCourant.name + " " + i + " :");
                        System.out.println("Que voulez vous faire : 1. Attaquer ou Soigner ( pour le Healer), 2. Se défendre, 3. Utiliser un consommable : ");
                        Scanner scanner = new Scanner(System.in);
                        int choix = scanner.nextInt();

                        if (choix == 1) {
                            choisirEtAttaquerEnnemieOuSoigner(scanner, heroCourant);
                            actionDone = true;
                        } else if (choix == 2) {
                            heroCourant.isDefense = true;
                            actionDone = true;
                        } else if (choix ==3){
                            int k=0;
                            for (int j = 0; j < heroCourant.objects.size(); j++) {
                                if ((heroCourant.objects.get(i) instanceof Food)| (heroCourant.objects.get(i) instanceof Potion)){
                                    k++;
                                }
                            }
                            if (k>0) {

                                heroCourant.afficherItem(true);
                                int choixConsommable = scanner.nextInt();

                                actionDone = heroCourant.utiliserConsommable(choixConsommable);

                                if (!actionDone) {
                                    System.out.println("Vous n'avez rien consommé ");
                                }
                            } else {
                                System.out.println("Vous n'avez aucun consommable ");

                            }
                        }
                    }
                }

                // Boucle pour l'attaque des ennemies.
                for (int i = 0; i < listeEnemies.size(); i++) {
                    Enemy enemyCourant = listeEnemies.get(i);
                    if (!enemyCourant.isDead()) {
                        Hero heroAttack = listeHeros.get(rand.nextInt(listeHeros.size()));
                        enemyCourant.attaquer(heroAttack);
                        System.out.println("points de vie de " + heroAttack.name + " : " + heroAttack.hp);

                        if (heroAttack.isDead()) {
                            listeHeros.remove(heroAttack);
                        }

                    }
                }

                // On va vérifier que nos heros ne sont pas morts
                herosAllDead = listeHeros.size() == 0;
                if (!herosAllDead) {
                    System.out.println("Vos héros ne sont pas morts");
                }

                // Si on a plus d'ennemies dans la liste alors ils sont tous morts.
                enemiesAllDead = listeEnemies.size() == 0;
                if (enemiesAllDead) {
                    // vérification que les ennemies soient morts
                    System.out.println("Les ennemies sont morts");
                }
            } // Fin de boucle d'un combat


            if (enemiesAllDead) {
                // tous les héros ont gagné le combat
                // choisir ce que le héro veut augmenter avec scanner, parcourir la liste hero
                // et verifier pour chaque hero si ils sont vivants pour pouvoir augmenter pour chacun

                for (int i = 0; i < listeHeros.size(); i++) {
                    System.out.println("Vous avez gagné le combat, que voulez vous faire : 1. Augmenter les dégats que vous causez, 2. Augmenter votre résistance aux attaques " +
                            "3.Augmenter le nombre de potions ou de nourriture," +
                            " 4. Augmenter le nombre de flèches pour le Hunter, " + "5.augmenter la mana pour spell caster seulement" +
                            " : ");
                    Scanner scanner = new Scanner(System.in);
                    int choix = scanner.nextInt();

                    if (choix == 1) {
                        // augmenter les dégats du hero via hero.degats += une valeur

                        listeHeros.get(i).degats += 15;
                    } else if (choix == 2) {
                        // augmenter la defense du héro via hero.defense += une valeur

                        for (int k = 0; k < listeHeros.get(i).objects.size(); k++) {
                            if(listeHeros.get(i).objects.get(k) instanceof Armor) {
                                ((Armor) listeHeros.get(i).objects.get(k)).resistance += 5;
                            }
                        }
                    } else if (choix == 3) {
                        choixfood(i, scanner);
                    } else if (choix == 4) {
                        // augmenter le nombre de fleche seulement pour le hunter
                        if (listeHeros.get(i) instanceof Hunter ){
                            for (int j = 0; j < listeHeros.get(i).objects.size(); j++) {
                                if(listeHeros.get(i).objects.get(j) instanceof Bow) {
                                    ((Bow) listeHeros.get(i).objects.get(j)).nbFleches+=2;
                                }
                            }
                        }


                    } else if (choix == 5) {
                        // augmenter la mana pour spell caster seulement
                        if (listeHeros.get(i) instanceof SpellCaster ){
                            ((SpellCaster) listeHeros.get(i)).mana+= 5;
                        }
                    }
                }

            }

            if (herosAllDead) {
                // message tous les héros sont morts partie terminé , vérif que tous les héros sont morts
            }

            compteurCombats++;
        } // Fin de boucle de jeu
    }

    /**
     * Méthode pour choisir et attaquer un ennemie ou soigner un hero.
     *
     * @param scanner
     * @param heroCourant
     * @return
     */
    private static void choisirEtAttaquerEnnemieOuSoigner(Scanner scanner, Hero heroCourant) {
        // Cas du healer qui heal un hero
        if (heroCourant instanceof Healer)
        {
            System.out.println("Lequel des héros ci-dessous voulez-vous soigner : ");

            for (int j = 0; j <  + listeHeros.size() ; j++)
            {
                System.out.println(listeHeros.get(j).name + " (" + j + ")");
            }
            int heroChoisit = scanner.nextInt();
            ((Healer) heroCourant).soignerHero(listeHeros.get(heroChoisit));
        }
        else
        {
            // Ici on attaque un ennemie
            System.out.println("Quel monstre voulez vous attaquer ? ");

            for (int j = 0; j < listeEnemies.size(); j++)
            {
                // On vérifie que l'ennemie n'est pas mort
                if (!listeEnemies.get(j).isDead())
                {
                    System.out.println(j + " - " + listeEnemies.get(j).name + " - " + listeEnemies.get(j).hp);
                }
            }
            int enemyatt = scanner.nextInt();
            Enemy enemyToAttack = listeEnemies.get(enemyatt);
            heroCourant.attaquer(enemyToAttack);

            if (enemyToAttack.isDead())
            {
                // L'ennemie est mort on le retire de la liste
                 listeEnemies.remove(enemyToAttack);
            }
        }
    }

    private static void choixfood(int i, Scanner scanner)
    {
        System.out.println("Veuillez choisir la nourriture que votre héro va consommer : ");
        System.out.println(Arrays.stream(Food.FoodType.values()).map(Enum::name).collect(Collectors.joining(",")));
        String choixFood = scanner.next().toUpperCase();
        Food.FoodType foodType = Food.FoodType.valueOfOrNull(choixFood);

        while(foodType == null)
        {
            System.out.println("Veuillez choisir la nourriture que votre héro va consommer : ");
            System.out.println(Arrays.stream(Food.FoodType.values()).map(Enum::name).collect(Collectors.joining(",")));
            choixFood = scanner.next().toUpperCase();
            foodType = Food.FoodType.valueOfOrNull(choixFood);
        }
        //Création d'une nourriture
        Food food = Food.generateFood(foodType);

        // ajout de la nourriture créé au héro choisit
        listeHeros.get(i).addObjects(food);
    }

    private static void initGame()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir le nombre de héros ");
        nombre_e = getInputGame(scanner , "Veuillez choisir le nombre de héros ");

        System.out.println("Le nombre d'heros est : " + nombre_e);

        System.out.println("Le nombre d'ennemis est : " + nombre_e);

        System.out.println("\n");

        int i;
        int classe = 0;

        int compteurHunter = 0;
        int compteurWarrior = 0;
        int compteurMage = 0;
        int compteurHealer = 0;

        for (i = 0; i < nombre_e; i++)
        {

            boolean valeurOk = false;

            while (!valeurOk)
            {
                System.out.println("Veuillez choisir la classe de votre héro : \n" + "1. Hunter \n" + "2. Warrior \n" + "3. Mage \n" + "4. Healer \n");
                classe = getInputGame(scanner , "Veuillez choisir la classe de votre héro : \n" + "1. Hunter \n" + "2. Warrior \n" + "3. Mage \n" + "4. Healer \n");

                valeurOk = classe >= 1 && classe <= 5;

                if (valeurOk)
                {

                    Hero hero = null;

                    if (classe == 1) {
                        hero = new Hunter("Hunter" + compteurHunter, 100);
                        compteurHunter++;
                    } else if (classe == 2) {
                        hero = new Warrior("Warrior" + compteurWarrior, 100);
                        compteurWarrior++;
                    } else if (classe == 3) {
                        hero = new Mage("Mage" + compteurMage, 100);
                        compteurMage++;
                    } else if (classe == 4){
                        hero = new Healer("Healer" + compteurHealer, 100);
                        compteurHealer++;
                    } else {System.out.println("saisie incorrecte "); return;}

                    listeHeros.add(hero);
                }
            }
        }
    }

    private static void initEnnemies(int niveau)
    {
        listeEnemies.clear();

        if(niveau == NIVEAU_MAX)
        {
            listeEnemies.add(new Monster("Boss", 300, 50));
        }
        else {
            for (int i = 0; i < nombre_e; i++)
            {
                if(niveau == 0)
                {
                    listeEnemies.add(new Monster("Archere", 100, 5));
                }
                else if (niveau == 1)
                {
                    listeEnemies.add(new Monster("Dragon", 125, 20));
                }
                else if(niveau == 2)
                {
                    listeEnemies.add(new Monster("Geant", 200, 25));
                }
                else if(niveau == 3)
                {
                    listeEnemies.add(new Monster("Loki", 250, 30));
                }

            }
        }
    }
    public static int getInputGame (Scanner scanner, String texte) {

        int nombre = -99;
        while (nombre == -99) {
            try {
                nombre = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("erreur saisie");
                System.out.println(texte);
                String test = scanner.next();
            }
        }
        return nombre;
    }
}
