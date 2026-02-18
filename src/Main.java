import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(200, 0, 50, 100, 1, false, 0, false);
        Scanner scanner = new Scanner(System.in);
        String victoryOrDeafeat = "";
        int tourJoueur = 0;

        System.out.println("\nBienvenue sur le jeu JEMPIRE !\n");
        while (victoryOrDefeatCondition(victoryOrDeafeat)) {
            System.out.println("Tour : " + tourJoueur + "\n");
            System.out.println("Vos ressources : \n" +
                    "Bois : " + ressourceJoueur.getBois() + " | Pierre : " + ressourceJoueur.getPierre() + " | Or : "
                    + ressourceJoueur.getOr() + " | Nourriture : " + ressourceJoueur.getNourriture()
                    + " | Habitant(s) : " + ressourceJoueur.getHabitant());
            menu(ressourceJoueur, scanner);
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

    public static boolean victoryOrDefeatCondition(String victoryOrDeafeat) {

        if (victoryOrDeafeat.equals("deafeat")) {
            System.out.println("Loooooooooosssssserrrrrr !!!!");
            return false;
        } else if (victoryOrDeafeat.equals("victory")) {
            System.out.println("Bravo vous avez créé votre chateau");
            return false;
        }
        return true;
    }

    public static void ExploreForest(Ressource ressourceJoueur) {
        ressourceJoueur.addBois(2 * ressourceJoueur.getHabitant());
        ressourceJoueur.addNourriture(3 * ressourceJoueur.getHabitant());
        int aleatoire1 = (int) (Math.random() * 10);
        int aleatoire2 = (int) (Math.random() * 30);
        if (aleatoire1 == 5) {
            System.out.println("Vous avez trouvé un coin à champignon !");
            ressourceJoueur.addNourriture(10);
        } else if (aleatoire2 == 5) {
            System.out.println("Vou avez ramassé des champignons toxiques. Vous perdez 10 nourritures");
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
        System.out.println("Menu de commerce :\n1 - commercer du bois pour de l'or\n2 - commercer de la pierre pour de l'or\n");
        int choiceCommerce = scanner.nextInt();
        switch (choiceCommerce) {
            case 1:
                if (ressourceJoueur.getBois() >= 10) {
                    ressourceJoueur.deleteWood(10);
                    ressourceJoueur.addGold(5);
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
            int aleatoireGobelins = (int) (Math.random() * 15);
            if (aleatoireGobelins == 10) {
                System.out.println("Vous croisez une bande de gobelin, les mineurs ce défende mais certains meures");
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
                System.out.println("Vous avez creuser trop profond et vous avez trouver un balrog");
                ressourceJoueur.deletePeople(ressourceJoueur.getHabitant() - 1);
            }
        }
        int aleatoireVeineRiche = (int) (Math.random() * 20);
        if (aleatoireVeineRiche == 10) {
            System.out.println("Vous avez trouvé une veine riche !");
            ressourceJoueur.addGold(20);
        }
    }

    public static void menu(Ressource ressourceJoueur, Scanner scanner) {
        String createOrUpgradeMine = "";
        boolean isValid = false;
        do {
            if (ressourceJoueur.getMineLevel() == 0) {
                createOrUpgradeMine = "2- Créer une mine: -10 bois";
            } else {
                createOrUpgradeMine = "2- Améliorer la mine: -50 bois";
            }
            System.out.println(
                    "\nQue souhaitez vous faire : \n\t1- Explorer la forêt: +2 bois x nombre d'habitant | +3 nourritures x nombre d'habitant \n\t" + createOrUpgradeMine + "\n\t3- Travailler à la mine: -5 Nourriture | +5 Pierre | +2 Or\n\t4- Recruter un habitant: -30 Or\n\t5- Commercer: -5 Pierre | +10 Or\n\t6- Construire le Château: -100 Bois | -100 Pierre | -200 Or | -40 Habitants");
            int choice = scanner.nextInt();
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
                    } else if (ressourceJoueur.getMineLevel() >= 1 && ressourceJoueur.getMineLevel() < 3 && ressourceJoueur.getBois() >= 50) {
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
