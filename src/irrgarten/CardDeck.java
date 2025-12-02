
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T> {
    protected final static int TAM_CARDECK = 5;
    
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        this.cardDeck = new ArrayList<>();
    }
    
    //protected abstract void addCards(); 
    
    protected void addCards(ArrayList<T> cardArray){
        for(int i = 0 ; i<cardArray.size() ; i++){
            this.addCard(cardArray.get(i));
        }
    }
    
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
   public T nextCard(){
        
        if(this.cardDeck.size() == 0){
            ArrayList<T> cards = new ArrayList<>();
            this.addCards(cards); //va a llamar al addcards del tipo dinamico
            Collections.shuffle(this.cardDeck);
        }
        return this.cardDeck.remove(0); //remove lo elimina y lo devuelve
   }
   

}
