public class Main {

    public static void main(String[] args) {

        Ressource ressourceJoueur = new Ressource(0, 0, 50, 100, 1);

        boolean condition = false;
        String victoryOrDeafeat = "";

        while (condition) {
            condition = victoryOrDefeatCondition(victoryOrDeafeat);
        }
    }

    public static boolean victoryOrDefeatCondition(String victoryOrDeafeat){

        if (victoryOrDeafeat.equals("deafeat")){
            System.out.println("Loooooooooosssssserrrrrr !!!!");
        }
        else if (victoryOrDeafeat.equals("victory")){
            System.out.println("Bravo vous avez créé votre chateau");
        }
        return true;
    }


}
