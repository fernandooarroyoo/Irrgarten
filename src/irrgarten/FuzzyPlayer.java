
package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player {
    public FuzzyPlayer(Player other){
        super(other); //Llama al constructor de Player o al de LabyrinthCharacter? -> Entiendo que si el padre es Player, llama a Player
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions output;
        boolean contained = validMoves.contains(direction);
        if(contained && validMoves.size() > 0){
            output = Dice.nextStep(direction,validMoves,getIntelligence());
            
        }else{
            validMoves.remove(direction);
            output = Dice.nextStep(validMoves.get(0), validMoves, getIntelligence());
        }
        return output;
    }
    
    public float attack(){
        return super.attack();
    }
    
    protected float defensiveEnergy(){
        return super.defensiveEnergy();
    }
}
