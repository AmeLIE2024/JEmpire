import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(50, 0, 50, 100, 21, false);
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        String victoryOrDeafeat = "";
        int tourJoueur = 0;

        System.out.println("\nBienvenue sur le jeu JEMPIRE !\n");
        while (condition) {
            System.out.println("Tour : " + tourJoueur + "\n");
            System.out.println("Vos ressources : \n" +
                    "Bois : " + ressourceJoueur.getBois() + " | Pierre : " + ressourceJoueur.getPierre() + " | Or : "
                    + ressourceJoueur.getOr() + " | Nourriture : " + ressourceJoueur.getNourriture()
                    + " | Habitant(s) : " + ressourceJoueur.getHabitant());
            menu(ressourceJoueur, scanner);
            ressourceJoueur.feedPeople();
            if (ressourceJoueur.habitant == 0) {
                victoryOrDeafeat = "deafeat";
            }
            condition = victoryOrDefeatCondition(victoryOrDeafeat);
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

    public static void createMine(Ressource ressourceJoueur){
        ressourceJoueur.deleteWood(10);
    }

    public static void menu(Ressource ressourceJoueur, Scanner scanner) {

        boolean isValid = false;
        do{
            System.out.println(
                    "\nQue souhaitez vous faire : \n\t1- Explorer la forêt: +5 bois | +3 nourritures \n\t2- Créer une mine: -10 bois \n\t3- Travailler à la mine: -5 Nourriture | +5 Pierre | +2 Or\n\t4- Recruter un habitant: -30 Or\n\t5- Commercer: -5 Pierre | +10 Or\n\t6- Construire le Château: -100 Bois | -100 Pierre | -200 Or | -40 Habitants");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: //Todo explorer la forêt

                    break;
                case 2:
                    if (ressourceJoueur.getMine()) {
                        System.out.println("La mine est déjà crée");
                    }
                    else {
                        if(ressourceJoueur.getBois() > 10){
                            createMine(ressourceJoueur);
                            ressourceJoueur.setMine(true);
                            isValid = true;
                        }
                        else {
                            System.out.println("Pas assez de bois");
                        }
                    }
                    break;
                case 3: // Todo Travailler à la mine

                    break;
                case 4: //Todo Recruter un habitant

                    break;
                case 5: //Todo Commercer

                    break;
                case 6: // Todo Construire le château

                    break;

                default:
                    break;
            }
        }while (!isValid);
    }

}
