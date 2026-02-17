import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(0, 0, 50, 100, 1);
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        String victoryOrDeafeat = "";
        int tourJoueur = 0;

        System.out.println("\nBienvenue sur le jeu JEMPIRE !\n");
        while (condition) {
            System.out.println("Tour : " + tourJoueur +"\n");
            System.out.println("Vos ressources : \n" +
                            "Bois : " + ressourceJoueur.getBois() + " | Pierre : " + ressourceJoueur.getPierre() + " | Or : " + ressourceJoueur.getOr() + " | Nourriture : " + ressourceJoueur.getNourriture() + " | Habitant(s) : " + ressourceJoueur.getHabitant() );
            scanner.nextLine();
            condition = victoryOrDefeatCondition(victoryOrDeafeat);
            tourJoueur++;
        }
    }

    public static boolean victoryOrDefeatCondition(String victoryOrDeafeat){

        if (victoryOrDeafeat.equals("deafeat")){
            System.out.println("Loooooooooosssssserrrrrr !!!!");
        }
        else if (victoryOrDeafeat.equals("victory")){
            System.out.println("Bravo vous avez créé votre chateau");
        }
        return false;
    }

}
