
package irrgarten;

import java.util.ArrayList;
import irrgarten.Controller;
import irrgarten.TextUI;


public class Irrgarten {
    
    
    public static void main(String[] args) {
    
        
    TextUI textui = new TextUI();
    Game game = new Game(1);
    Controller controller = new Controller (game, textui);
    controller.play();
    
    
    
    
    
    
   
       
      
    }
    
}
