
package irrgarten;

public class TestP1 {
    public static void mainn(String[] args){
        //Probamos las clases
        //gameState, shield, weapon
        GameState gamestate = new GameState("Laberinto","José","Zombi",2,true,"log_test");
        Shield escudo = new Shield(10,1);
        Weapon arma = new Weapon(1,10);
        
        //Probamos los enum
        //Directions,GameCharacter,Orientation
        Directions direccion = Directions.UP;
        GameCharacter tipoPersonaje = GameCharacter.PLAYER;
        Orientation orientacion = Orientation.HORIZONTAL;
        
        //Probamos la clase Dice
        for(int i = 0; i<100 ; ++i){
            Dice.discardElement(i);
            Dice.healthReward();
            Dice.intensity(i);
            Dice.randomIntelligence();
            Dice.randomPos(i);
            Dice.randomStrength();
            Dice.resurrectPlayer();
            Dice.whoStarts(i);
            Dice.weaponsReward();
            Dice.shieldPower();
            Dice.usesLeft();
            Dice.shieldsReward();
            Dice.weaponPower();
        }
        
        
    }
}
