import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(0, 0, 50, 100, 1, false, false);
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
            if (ressourceJoueur.habitant <= 0) {
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

    public static void ExploreForest(Ressource ressourceJoueur){
        ressourceJoueur.addBois(5 *ressourceJoueur.getHabitant());
        ressourceJoueur.addNourriture(3 * ressourceJoueur.getHabitant());
        int aleatoire1 = (int) (Math.random() * 10);
        int aleatoire2 = (int) (Math.random() * 30);
        if ( aleatoire1 == 5) {
            System.out.println("Vous avez trouvé un coin à champignon !");
            ressourceJoueur.addNourriture(10);
        } else if ( aleatoire2 == 5) {
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

    public static void mineWork(Ressource ressourceJoueur){
        ressourceJoueur.deleteFood(5);
        ressourceJoueur.addStone(5);
        ressourceJoueur.addGold(2);
        int aleatoire3 =(int)(Math.random() * 20);
        int aleatoire4 = (int) (Math.random() * 30);
        if ( aleatoire3 == 10) {
            System.out.println("Vous avez trouvé une veine riche !");
            ressourceJoueur.addGold(20);
        } else if (aleatoire4 == 10) {
            System.out.println("Eboulement vous perdez 1 habitants !");
            ressourceJoueur.deletePeople(1);
        }
    }

    public static void menu(Ressource ressourceJoueur, Scanner scanner) {

        boolean isValid = false;
        do {
            System.out.println(
                    "\nQue souhaitez vous faire : \n\t1- Explorer la forêt: +5 bois | +3 nourritures \n\t2- Créer une mine: -10 bois \n\t3- Travailler à la mine: -5 Nourriture | +5 Pierre | +2 Or\n\t4- Recruter un habitant: -30 Or\n\t5- Commercer: -5 Pierre | +10 Or\n\t6- Construire le Château: -100 Bois | -100 Pierre | -200 Or | -40 Habitants");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ExploreForest(ressourceJoueur);
                    isValid = true;
                    break;
                case 2:
                    if (ressourceJoueur.getMine()) {
                        System.out.println("La mine est déjà crée");
                    } else {
                        if (ressourceJoueur.getBois() >= 10) {
                            createMine(ressourceJoueur);
                            System.out.println("Vous avez construit une mine\n");
                            ressourceJoueur.setMine(true);
                            isValid = true;
                        } else {
                            System.out.println("Ressources insufisantes: bois");
                        }
                    }
                    break;
                case 3:
                    if (ressourceJoueur.getMine()) {
                        mineWork(ressourceJoueur);
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
                    if (ressourceJoueur.getPierre() >= 5) {
                        ressourceJoueur.deleteStone(5);
                        ressourceJoueur.addGold(10);
                        isValid = true;
                    } else {
                        System.out.println("Ressources insuffisantes: pierres");
                    }

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
