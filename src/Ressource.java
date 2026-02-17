public class Ressource {

    int bois;
    int pierre;
    int or;
    int nourriture;
    int habitant;
    boolean mine;

    public Ressource(int bois, int pierre, int or, int nourriture, int habitant, boolean mine) {
        this.mine = mine;
        this.bois = bois;
        this.pierre = pierre;
        this.or = or;
        this.nourriture = nourriture;
        this.habitant = habitant;
    }

    public int getBois() {
        return bois;
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

    public int getOr() {
        return or;
    }

    public int getNourriture() {
        return nourriture;
    }

    public int getHabitant() {
        return habitant;
    }

    public void feedPeople() {
        if ( this.nourriture <= this.habitant){
            int minusHab = this.habitant-this.nourriture;
            if(minusHab > this.habitant){
                this.habitant -= minusHab;
                this.nourriture = 0;
            }else {
                this.habitant=0;
            }
        }
        this.nourriture -= this.habitant;
    }

    public void addBois(int bois){
        this.bois += bois;
    }

    public void addNourriture(int nourriture){
        this.nourriture += nourriture;
    }

}
