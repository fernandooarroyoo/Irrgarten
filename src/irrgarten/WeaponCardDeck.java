
package irrgarten;

import java.util.ArrayList;

public class WeaponCardDeck extends CardDeck<Weapon>{
    
    
    protected void addCards(){
        for(int i=0; i < WeaponCardDeck.TAM_CARDECK ; i++){
            this.addCard(new Weapon(Dice.weaponPower(),Dice.usesLeft()));
        }

        /*for(int i=0 ; i< WeaponCardDeck.TAM_CARDECK; i++){
            arr.add(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
        super.addCards(arr);
        */
    }

  

    
}
