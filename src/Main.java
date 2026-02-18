import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(0, 0, 50, 100, 1, false, 0, false, 0);
        Scanner scanner = new Scanner(System.in);
        String victoryOrDeafeat = "";
        int tourJoueur = 0;
        System.out.println("\nBienvenue sur le jeu JEMPIRE ! \n");

        while (victoryOrDefeatCondition(victoryOrDeafeat)) {
            System.out.println("Tour : " + tourJoueur + "\n");
            System.out.println("Vos ressources : \n");
            System.out.println(
                    "+---------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Bois : " + ressourceJoueur.getBois() + " | Pierre : " + ressourceJoueur.getPierre() + " | Or : "
                            + ressourceJoueur.getOr() + " | Nourriture : " + ressourceJoueur.getNourriture()
                            + " | Habitant(s) : " + ressourceJoueur.getHabitant() + " | Farmer(s) : "
                            + ressourceJoueur.getFarmer() + " | Mine : "
                            + (ressourceJoueur.getMine() ? "lvl." + ressourceJoueur.getMineLevel() : "Non") + "  |");
            System.out.println(
                    "+----------------------------------------------------------------------------------------------------------+");
            menu(ressourceJoueur, scanner);
            if (ressourceJoueur.getFarmer() > 0) {
                ressourceJoueur.addNourriture(10 * ressourceJoueur.getFarmer());
            }
            ressourceJoueur.feedPeople();
            if (ressourceJoueur.getHabitant() <= 0) {
                victoryOrDeafeat = "deafeat";
            }
            if (ressourceJoueur.getCastle()) {
                victoryOrDeafeat = "victory";
            }
            tourJoueur++;

        }
    }

    public static int readInt(Scanner scanner) {
        System.out.print("Entrez un nombre: ");
        String input;
        boolean isValid = false;
        int result = 0;
        do {
            input = scanner.nextLine();
            try {
                result = Integer.parseInt(input);
                isValid = true;
            } catch (Exception e) {
                System.err.print("Entrez un nombre valide: ");
            }
        } while (!isValid);

        return result;
    }

    public static boolean victoryOrDefeatCondition(String victoryOrDeafeat) {

        if (victoryOrDeafeat.equals("deafeat")) {
            System.out.println("Loooooooooosssssserrrrrr !!!!");
            return false;
        } else if (victoryOrDeafeat.equals("victory")) {
            System.out.println("*********Bravo vous avez créé votre chateau*************");
            return false;
        }
        return true;
    }

    public static void ExploreForest(Ressource ressourceJoueur) {
        ressourceJoueur.addBois(5 * ressourceJoueur.getHabitant());
        ressourceJoueur.addNourriture(3 * ressourceJoueur.getHabitant());
        int aleatoire1 = (int) (Math.random() * 10);
        int aleatoire2 = (int) (Math.random() * 30);
        if (aleatoire1 == 5) {
            System.out.println("Vous avez trouvé un coin à champignon ! => +10 nourritures !");
            ressourceJoueur.addNourriture(10);
        } else if (aleatoire2 == 5) {
            System.out.println("Vou avez ramassé des champignons toxiques. - 10 nourritures !");
            ressourceJoueur.deleteFood(10);
        }
    }

    public static void createMine(Ressource ressourceJoueur) {
        ressourceJoueur.deleteWood(10);
    }

    public static void recrut(Ressource ressourceJoueur) {
        ressourceJoueur.deleteGold(30);
        ressourceJoueur.addPeople(1);
    }

    public static void constructCastle(Ressource ressourceJoueur) {
        ressourceJoueur.setCastle(true);
    }

    public static boolean menuCommerce(Scanner scanner, Ressource ressourceJoueur) {
        System.out.println(
                "Menu de commerce :\n1 - commercer du bois pour de l'or\n2 - commercer de la pierre pour de l'or\n");
        int choiceCommerce = readInt(scanner);
        switch (choiceCommerce) {
            case 1:
                if (ressourceJoueur.getBois() >= 30) {
                    ressourceJoueur.deleteWood(30);
                    ressourceJoueur.addGold(15);
                    return true;
                } else {
                    System.out.println("Vous n'avez pas assez de bois");
                    return false;
                }
            case 2:
                if (ressourceJoueur.getPierre() >= 10) {
                    ressourceJoueur.deleteStone(10);
                    ressourceJoueur.addGold(5);
                    return true;
                } else {
                    System.out.println("Vous n'avez pas assez de pierre");
                    return false;
                }
            default:
                return false;
        }
    }

    public static void mineWork(Ressource ressourceJoueur, int mineLevel) {
        if (mineLevel == 1) {
            int aleatoireEboullis = (int) (Math.random() * 15);
            if (aleatoireEboullis == 10) {
                System.out.println("Eboulement vous perdez 1 habitants !");
                ressourceJoueur.deletePeople(1);
            }
            ressourceJoueur.deleteFood(5);
            ressourceJoueur.addStone(5);
            ressourceJoueur.addGold(5);
        } else if (mineLevel == 2) {
            int aleatoireGobelins = (int) (Math.random() * 25);
            if (aleatoireGobelins == 10) {
                System.out.println(
                        "Vous croisez une bande de gobelin, les mineurs se défendent mais certains meurent / Vous perdez 3 habitants !");
                ressourceJoueur.deletePeople(3);
            }
            ressourceJoueur.deleteFood(3);
            ressourceJoueur.addStone(10);
            ressourceJoueur.addGold(10);
        } else if (mineLevel == 3) {
            ressourceJoueur.deleteFood(3);
            ressourceJoueur.addStone(15);
            ressourceJoueur.addGold(15);
            int aleatoireBalrog = (int) (Math.random() * 100);
            if (aleatoireBalrog == 1) {
                System.out.println(
                        "Vous avez creusé trop profond et vous avez trouvé un balrog. Il ne vous reste plus qu'un seul habitant !");
                ressourceJoueur.deletePeople(ressourceJoueur.getHabitant() - 1);
            }
        }
        int aleatoireVeineRiche = (int) (Math.random() * 20);
        if (aleatoireVeineRiche == 10) {
            System.out.println("Vous avez trouvé une veine riche !");
            ressourceJoueur.addGold(45);
        }
    }

    public static void menu(Ressource ressourceJoueur, Scanner scanner) {
        String createOrUpgradeMine = "";
        boolean isValid = false;
        do {
            if (ressourceJoueur.getMineLevel() == 0) {
                createOrUpgradeMine = "Créer une mine        | -10 bois                                | Débloque accès à la mine|";
            } else {
                createOrUpgradeMine = "Améliorer la mine     | -50 bois                                | Amélioration (max lvl.3)|";
            }
            System.out.println("\nQue souhaitez vous faire : \n");
            System.out.println(
                    "+-------+-----------------------+-----------------------------------------+-------------------------+");
            System.out.println(
                    "| Choix | Action                | Coût / Risque                           | Gain                    |");
            System.out.println(
                    "+-------+-----------------------+-----------------------------------------+-------------------------+");
            System.out.println(
                    "|   1   | Explorer la forêt     | Aucun                                   | +5 Bois, +3 Nourriture  |");
            System.out.println("|   2   | " + createOrUpgradeMine + "                                ");
            System.out.println(
                    "|   3   | Travailler à la mine  | -5 Nourriture                           | +5 Pierre, +2 Or        |");
            System.out.println(
                    "|   4   | Recruter un habitant  | -30 Or                                  | +1 habitant             |");
            System.out.println(
                    "|   5   | Commercer             | -5 Pierre +10 Or                        | -200 Or                 |");
            System.out.println(
                    "|   6   | Construire le Château | -100 Bois, -100 Pierre, | -40 Habitants |                         |");
            System.out.println(
                    "+-------+-----------------------+-----------------------------------------+-------------------------+");

            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    ExploreForest(ressourceJoueur);
                    isValid = true;
                    break;
                case 2:
                    if (ressourceJoueur.getBois() >= 10 && ressourceJoueur.getMineLevel() == 0) {
                        createMine(ressourceJoueur);
                        System.out.println("Vous avez construit une mine\n");
                        ressourceJoueur.setMine(true);
                        ressourceJoueur.upgradeMine();
                        isValid = true;
                    } else if (ressourceJoueur.getMineLevel() >= 1 && ressourceJoueur.getMineLevel() < 3
                            && ressourceJoueur.getBois() >= 50) {
                        ressourceJoueur.deleteWood(50);
                        ressourceJoueur.upgradeMine();
                        System.out.println("Mine améliorer au niveau : " + ressourceJoueur.getMineLevel());
                        isValid = true;
                    } else if (ressourceJoueur.getMineLevel() == 3) {
                        System.out.println("La mine est déjà augmenter au maximum");
                    } else {
                        System.out.println("Ressources insufisantes: bois");
                    }
                    break;
                case 3:
                    if (ressourceJoueur.getMine()) {
                        mineWork(ressourceJoueur, ressourceJoueur.getMineLevel());
                        isValid = true;
                    } else {
                        System.out.println("Vous n'avez pas contruit de mine");
                    }
                    break;
                case 4:
                    if (ressourceJoueur.getOr() >= 30) {
                        int aleatoire5 = (int) (Math.random() * 10);
                        if (aleatoire5 == 10) {
                            System.out.println("L'habitant recruté est un paysan ! + 10 nourritures par tour !");
                            ressourceJoueur.addFarmer(1);
                            ressourceJoueur.addNourriture(10);
                        }
                        recrut(ressourceJoueur);
                        isValid = true;
                    } else {
                        System.out.println("Pas assez d'or");
                    }

                    break;
                case 5:
                    isValid = menuCommerce(scanner, ressourceJoueur);
                    break;
                case 6:
                    if (ressourceJoueur.getHabitant() <= 40) {
                        System.out.println("Pas assez d'habitant");
                    } else if (ressourceJoueur.getBois() < 100) {
                        System.out.println("Pas assez de bois");
                    } else if (ressourceJoueur.getOr() < 200) {
                        System.out.println("Pas assez d'Or");
                    } else if (ressourceJoueur.getPierre() < 100) {
                        System.out.println("Pas assez de pierre");
                    } else {
                        constructCastle(ressourceJoueur);
                        isValid = true;
                    }
                    break;
                default:
                    break;
            }
        } while (!isValid);
    }
}
