
package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    //variables de relacion
    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster>monsters;
    private Labyrinth labyrinth;
    
    public Game(int nPlayers){
        this.log = null;
        this.currentPlayerIndex = Dice.whoStarts(nPlayers);
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        
        for (int i = 0; i < nPlayers; i++) {
            Player jugador = new Player((char) (i + '0'), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(jugador); 
        }
        
        this.currentPlayer = players.get(currentPlayerIndex);
        
        this.labyrinth = null;
        this.configureLabyrinth();
        labyrinth.spreadPlayers(players);
    }
    
    public boolean finished(){
        return this.labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        this.log = "";
        boolean dead = this.currentPlayer.dead();
        
        if(!dead){
            Directions direction = this.actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                this.logPlayerNoOrders();
            }
            
            Monster monster = this.labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){
                this.logNoMonster();
            }else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        
        if(!endGame)
            this.nextPlayer(); 
        
        return endGame;
    }
    
    public GameState getGameState(){
       String g = "";
       return new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, labyrinth.haveAWinner(), log);
    }
    
    private void configureLabyrinth(){
        this.labyrinth = new Labyrinth(10,10,5,5);
    }
    
    private void nextPlayer(){
        if (this.currentPlayerIndex == this.players.size()-1){
            this.currentPlayerIndex = 0;
        }
        else{
            this.currentPlayerIndex += 1;
        }
        
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow();
        int currentCol = this.currentPlayer.getCol();
        ArrayList<Directions> validMoves = this.labyrinth.validMoves(currentRow, currentCol);
        Directions output = this.currentPlayer.move(preferredDirection, validMoves);
        
        return output;    
    }
    
    private GameCharacter combat(Monster monster){
        float playerAttack = this.currentPlayer.attack();
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        boolean lose = monster.defend(playerAttack);
        
        while((!lose) && (rounds < this.MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = this.currentPlayer.defend(monsterAttack);
            
            if((!lose)){
                playerAttack = this.currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(monsterAttack);
            }
        }
        this.logRounds(rounds, this.MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            this.currentPlayer.receiveReward();
            this.logPlayerWon();
        }else{
            this.logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            this.currentPlayer.resurrect();
            this.logResurrected();
        }else{
            this.logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        this.log +="El jugador ha ganado el combate\n";
    }
    
    private void logMonsterWon(){
        this.log +="El monstruo ha ganado el combate\n";
    }
    
    private void logResurrected(){
        this.log +="El jugador ha resucitado\n";
    }
    
    private void logPlayerSkipTurn(){
        this.log += "El jugador ha pasado su turno\n";
    }
    
    private void logPlayerNoOrders(){
        this.log += "El jugador no ha seguido las órdenes del humano\n";
    }
    
    private void logNoMonster(){
        this.log += "El jugador se ha movido a una celda vacía, o no ha podido moverse\n";
    }
    
    private void logRounds(int rounds, int max){
        this.log += "ronda #{rounds} de #{max}\n";
    }
    
   
}
