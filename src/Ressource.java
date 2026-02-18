public class Ressource {

    int bois;
    int pierre;
    int or;
    int nourriture;
    int habitant;
    boolean mine;
    boolean castle;
    int farmer;

    public Ressource(int bois, int pierre, int or, int nourriture, int habitant, boolean mine, boolean castle, int farmer) {
        this.mine = mine;
        this.bois = bois;
        this.pierre = pierre;
        this.or = or;
        this.nourriture = nourriture;
        this.habitant = habitant;
        this.castle = castle;
        this.farmer = farmer;
    }

    public int getBois() {
        return bois;
    }

    public boolean getCastle(){
        return castle;
    }

    public void setCastle(boolean castle){
        this.castle = castle;
    }

    public boolean getMine(){
        return mine;
    }

    public void setMine(boolean mine){
        this.mine = mine;
    }

    public void deleteWood(int bois){
        this.bois -= bois;
    }

    public int getPierre() {
        return pierre;
    }

    public void deleteStone(int stone){
        this.pierre -= stone;
    }

    public void addStone(int stone){
        this.pierre += stone;
    }

    public int getOr() {
        return or;
    }
    public void addGold(int gold){
        this.or += gold;
    }

    public void deleteGold(int gold){
        this.or -= gold;
    }

    public int getNourriture() {
        return nourriture;
    }

    public int getHabitant() {
        return habitant;
    }

    public void deletePeople(int people){
        this.habitant -=people;
    }

    public void addPeople(int peopleToAdd){
        this.habitant += peopleToAdd;
    }

    public void feedPeople() {
        if (this.habitant < this.nourriture ){
            this.nourriture -= this.habitant;
        } else {
            int minusHab = this.habitant-this.nourriture;
            this.habitant -= minusHab;
            this.nourriture = 0;
        }
    }

    public void addBois(int bois){
        this.bois += bois;
    }

    public void addNourriture(int nourriture){
        this.nourriture += nourriture;
    }

    public void deleteFood(int food){
        this.nourriture -= food;
    }

    public int getFarmer() {
        return farmer;
    }

    public void addFarmer(int farmer) {
        this.farmer += farmer;
    }
   

}
