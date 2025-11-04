
package irrgarten;

import java.util.ArrayList;

public class Irrgarten {
    
    
    public static void main(String[] args) {
       
       /* 
       Player player= new Player('0',1.2f,2.1f);
       Weapon weapon = new Weapon(3f,5);
       */
       //player.takeWeapon(weapon);
       
       //Labyrinth lab = new Labyrinth(4,5,2,3);
       ArrayList <Player> players = new ArrayList<Player>();
       
       Player fernando = new Player('1',1f,2f);
       fernando.setPos(1,1);
       
       players.add(fernando);
       
       
       Labyrinth lab = new Labyrinth(4,3,2,2);
       int a = 1;
    }
    
}
