/* COMP 1406 Summer 2016
 * Flag Getter For Project
 * Zachary Stroud-Taylor
 * 100955368
 * August 9, 2016
 *
 * Player heads towards flag, gets 
 * the flag, then returns to its base.
 * 
 */

// Player extends Entity 
public class FlagGetter extends Player{
 // private flagGetterCounter = 0
  private String situation = "to y";
  
  // Play overriden from Entity extended to Player extened to this
  @Override
  public void play(Field field){
    
    // Call function to make sure player is not out of bounds
    // Function similar to Stopping player (implemented at bottom)
    this.stopping(field);
    
    if (!this.holdingFlag()){
      if (this.getID() != 0 && this.getID()%2 == 0){indirectFlagGetterFunc(field);}
      // indirectFlagGetterFunc(field);
      directFlagGetterFunc(field);
    }
    
    // Check if player has arrived at flag if so, then stop
    if (this.pickUpFlag(field)){
      this.speedX = 0; 
      this.speedY = 0;  
      this.isHoldingFlag = true;
    }
    
    if (isHoldingFlag){ 
      this.hasPickedUpFlag(this.getID(), field); 
      Player firstTeam = (Player) field.getTeam1().get(0);
      Player secondTeam = (Player) field.getTeam2().get(0);
      Entity base = null;
      
      if (this.getTeam().equals(firstTeam.getTeam())){
        base = field.base1;
      }
      else if (this.getTeam().equals(secondTeam.getTeam())){
        base = field.base2;
      }
      if (this.closeEnough(field, this, base)){this.speedX = this.speedY = 0 ; this.winGame(field);}
    }   
  }
  
  
  // Update overriden from Entity extended to Player extened to this
  @Override
  public void update(Field field){}
  
  
  // FlagGetter constructor everything but speedX and speedY passed to super classes Player and Entity
  /* @param f is the field the player will be playing on
   * @param side is the side of the field the player will play on
   * @param name is this player's name 
   * @param number is this player's number
   * @param team is this player's team's name
   * @param symbol is a character representation of this player
   * @param x is the initial x position of this player
   * @param y is the initial y position of this player
   */
  public FlagGetter(Field field, int side, String name, int number, String team, char symbol, double x, double y){
    super(field, side, name, number, team, symbol, x, y);
    this.speedX = (Math.random()*3)+1;
    this.speedY = (Math.random()*3)+1;
  }
  
  
  // Checks if player is going out of bounds and stops them if they are
  private void stopping(Field field){
    if (this.x < field.minX+1){this.x = field.minX+1; this.speedX = 0; this.speedY = 0;}
    else if (this.x > field.maxX-16){this.x = field.maxX-16; this.speedX = 0; this.speedY = 0;}
    else if (this.y < field.minY+1){this.y = field.minY+1; this.speedX = 0; this.speedY = 0;}
    else if (this.y > field.maxY-16){this.y = field.maxY-16; this.speedX = 0; this.speedY = 0;}
  }
  
  private void directFlagGetterFunc(Field field){
    // Players to determine which team
    Player firstTeam = (Player) field.getTeam1().get(0);
    Player secondTeam = (Player) field.getTeam2().get(0);
    
    // Allocate space for flagX and flagY
    double flagX = 0;
    double flagY = 0;
    
    // Set flagX and flagY to appropriate
    // coordinates depedent on team 
    
    if (this.getTeam().equals(firstTeam.getTeam())){
      flagX = field.getFlag2Position()[0];
      flagY = field.getFlag2Position()[1];
    }
    else if (this.getTeam().equals(secondTeam.getTeam())){
      flagX = field.getFlag1Position()[0];
      flagY = field.getFlag1Position()[1];
    }
    
    // Get slope of player relative the flag (X2 - X1 / Y2 - Y1)
    double slopeRatio = (flagX - this.x)/(flagY - this.y);
    
    // Apply ratio to speed
    this.speedX = this.speedY * slopeRatio;
    
    // Check if x or y coordinate needs to be 
    // inverted but only if not already inverted
    if (this.x > flagX && this.speedX > 0){
      this.speedX = this.speedX * -1.0;
    }
    if (this.y > flagY && this.speedY > 0){
      this.speedY = this.speedY * -1.0;
    }
  }  
  
  
  private void indirectFlagGetterFunc(Field field){
    
    if (situation.equals("to y")){
      if (this.y > (field.maxY-30) || this.y < (field.minY+15)){
        situation = "to x";
        System.out.println(this.getTeam() + " changed situation to \"to x\"");
      }
      this.speedX = 0;
      if (this.getID() % 2 == 1 && this.speedY > 0){
        this.speedY *= -1.0;
      }
    }
    
    else if (situation.equals("to x")){
      if (this.x > (field.maxX-30) || this.x < (field.minX+15)){
        situation = "to flag";
        System.out.println(this.getTeam() + " changed situation to \"to flag\"");
      }
      this.speedX = (Math.random()*3)+1;
      if (this.getID() % 2 == 1 && this.speedX > 0) { this.speedX *= -1.0; }
      this.speedY = 0;
    }
    
    else if (situation.equals("to flag")){
      
      this.speedX = this.speedY = ( ( Math.random() * 3) + 1); 
      
      // Players to determine which team
      Player firstTeam = (Player) field.getTeam1().get(0);
      Player secondTeam = (Player) field.getTeam2().get(0);
      
      // Allocate space for flagX and flagY
      double flagX = 0;
      double flagY = 0;
      
      // Set flagX and flagY to appropriate
      // coordinates depedent on team 
      
      if (this.getTeam().equals(firstTeam.getTeam())){
        flagX = field.getFlag2Position()[0];
        flagY = field.getFlag2Position()[1];
      }
      else if (this.getTeam().equals(secondTeam.getTeam())){
        flagX = field.getFlag1Position()[0];
        flagY = field.getFlag1Position()[1];
      }
      
      // Get slope of player relative the flag (X2 - X1 / Y2 - Y1)
      double slopeRatio = (flagX - this.x)/(flagY - this.y);
      
      // Apply ratio to speed
      this.speedX = this.speedY * slopeRatio;
      
      // Check if x or y coordinate needs to be 
      // inverted but only if not already inverted
      if (this.x > flagX && this.speedX > 0){
        this.speedX = this.speedX * -1.0;
      }
      if (this.y > flagY && this.speedY > 0){
        this.speedY = this.speedY * -1.0;
      }
    }
    
    }
}

