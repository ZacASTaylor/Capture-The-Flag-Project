/* COMP 1406 Summer 2016
 * Assignment 5
 * Player
 * Zachary Stroud-Taylor
 * 100955368
 * August 9, 2016
 */ 

public abstract class Player extends Entity{
 
  protected boolean isHoldingFlag = false; 
  
  public boolean holdingFlag(){ return isHoldingFlag; }
  /** this player's team name */
  protected String team;
  
  /** this player's name */
  protected String name;
  
  /** this player's number */
  protected int number;
  
   
  /** gets this player's team name
    * 
    * @return the team name that this player is on
    */
  public final String getTeam(){ return this.team; }
  
  
  /** gets this player's name
    * 
    * @return the name of this player
    */
  public final String getName(){ return this.name; }

  /** gets this player's number
    * 
    * @return the number of this player
    */
  public final int getNumber(){ return this.number; }

  
  /** creates a player with specified symbol at specified position 
    * 
    * @param f is the field the player will be playing on
    * @param side is the side of the field the player will play on
    * @param name is this name of the player
    * @param number is this player's number 
    * @param team is this player's team name
    * @param symbol is a character (char) representation of this player
    * @param x is the x-coordinate of this player
    * @param y is the y-coordinate of this player
    */
  public Player(Field f, int side, String name, int number, String team, char symbol, double x, double y){
    super(symbol, x, y);
    this.name = name;
    this.number = number;
    this.team = team;
    f.registerPlayer(this, this.id, side);  // register the player on the field
  }
  
  /** attempt to catch an opponent player
    * 
    * @param opponent a player on the opponent's team that you are trying to catch
    * @param field is the field the game is being played on
    * @return true if this player successfully catches the opponent player, false otherwise
    */
  public final boolean catchOpponent(Player opponent, Field field){
    return field.catchOpponent(this, opponent);
  }
  


  /** Informs this player that they have been caught by another player. 
    * <p>
    * This method should only be called from within the Field class.  
    * 
    * @param opponent is the player that caught this player  
    * @param id should be the id of the this player
    */
  public void beenCaught(Player opponent, int id){
    /* check if the caller knows this entity's id */
    if( this.id != id ){
      throw new SecurityException("Unauthorized attempt to call beenCaught ");
    }
    
  }
    
  /** attempt to free a teammate from jail
    * 
    * @param teammate is another player on this player's team
    * @param field is the field the game is being played on
    * @return true if the <code>teammate</code> is successfully freed from jail, false otherwise 
    */
  public final boolean freeTeammate(Player teammate, Field field){
    return field.freeTeammate(this, teammate);
  }
    
  /** Informs this player that they have been freed by a teammate 
    * <p>
    * This method should only be called from within the Field class.  
    * 
    * @param teammate is the player that caught this player  
    * @param id should be the id of the this player
    */
  public void hasBeenFreed(Player teammate, int id){
    /* check if the caller knows this entity's id */
    if( this.id != id){
      throw new SecurityException("Unauthorized attempt to call hasBeenFreed ");
    }
    
  }
  
  
  
  /** attempt to pick up the opponent's flag
    * 
    * @param field is the field the game is being played on
    * @return true if this player successfully picked up the opponent's flag, false otherwise 
    */
  public final boolean pickUpFlag(Field field){
    return field.pickUpFlag(this);
  }
  
  
  /** Informs this player that they have picked up the flag
    * <p>
    * This method should only be called from with the Field class.  
    * 
    * @param id should be the id of the this player
    */
  ///////////////////////////////////////////////////////////////////////////ADDED FIELD ATTRIBUTE
  public void hasPickedUpFlag(int id, Field field){
    /* check if the caller knows this entity's id */
    if( this.id != id ){
      throw new SecurityException("Unauthorized attempt to call hasPickedUpFlag ");
    }
    Flag flag = null;
    double homeBaseX = 0; 
    double homeBaseY = 0;
    Player firstTeam = (Player) field.getTeam1().get(0);
    Player secondTeam = (Player) field.getTeam2().get(0);
    if (this.getTeam().equals(firstTeam.getTeam())){
      flag = (Flag) field.flag2;
      homeBaseX = field.getBase1Position()[0];
      homeBaseY = field.getBase1Position()[1];
    }
    else if (this.getTeam().equals(secondTeam.getTeam())){
      flag = (Flag) field.flag1;
      homeBaseX = field.getBase2Position()[0];
      homeBaseY = field.getBase2Position()[1];
      }
      
      // Get slope of player relative the flag (X2 - X1 / Y2 - Y1)
      double slopeRatioHome = (homeBaseX - this.x)/(homeBaseY - this.y);
      
      // Apply ratio to speed
      this.speedY = Math.random()*3+1;
      this.speedX = this.speedY * slopeRatioHome;
      
      // Check if x or y coordinate needs to be 
      // inverted but only if not already inverted
      if (this.x > homeBaseX && this.speedX > 0){
        this.speedX = this.speedX * -1.0;
      }
      if (this.y > homeBaseY && this.speedY > 0){
        this.speedY = this.speedY * -1.0;
      }
      flag.setSpeedX(this.speedX, flag.getID());
      flag.setSpeedY(this.speedY, flag.getID());
    
      //if (this.x
    //}
  }
  
  /** Informs this player that they have dropped the flag
    * <p>
    * This method should only be called from within the Field class.  
    * 
    * @param id should be the id of the this player
    */
  public void hasDroppedFlag(int id){
    /* check if the caller knows this entity's id */
    if( this.id != id ){
      throw new SecurityException("Unauthorized attempt to call hasDroppedFlag ");
    }
    
  }
  
  
  /** attempt to win the game
    * 
    * @param field is the field the game is being played on
    * @return true if this player successfully brings the opponent's flag back to this player's base, false otherwise 
    */
  public final void winGame(Field field){
    field.winGame(this);
  }
  
   
  
  
}