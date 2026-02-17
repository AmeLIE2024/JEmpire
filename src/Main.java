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
            optionMenu(scanner);
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

     public static void optionMenu(Scanner scanner){
        System.out.println("\nQue souhaitez vous faire : \n\t1- Explorer la forêt: +5 bois | +3 nourritures \n\t2- Créer une mine: -10 bois \n\t3- Travailler à la mine: -5 Nourriture | +5 Pierre | +2 Or\n\t4- Recruter un habitant: -30 Or\n\t5- Commercer: -5 Pierre | +10 Or\n\t6- Construire le Château: -100 Bois | -100 Pierre | -200 Or | -40 Habitants");
        int choiceOption = scanner.nextInt();

    }

    

}
