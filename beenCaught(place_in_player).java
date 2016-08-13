 public void beenCaught(Player opponent, int id, Field field){
    int[] j = {0,0};
    /* check if the caller knows this entity's id */
    if( this.id != id ){
      throw new SecurityException("Unauthorized attempt to call beenCaught ");
    }
    
// check if the opponent sucsessfully caught the player
    if (field.catchOpponent(opponent, this) == true){
      
// Print out the line.
      System.out.println("the player " + this.name + "was caught by " + opponent.getName());
      
// check if the player is on team 1
      if (field.getTeam.get(this).equals(new Integer(1))){
      j = field.getJail2Position();
      }
      else {
      j = field.getJail1Position();
      }
      // For the horizontal movement
//if the current position (X) is less than the coordinate move towards it
      if (this.x < j[0]){
      this.x +=this.speedX;
      }
      
//if the current position (X) is greater than the coordinate move back towards it 
      if (this.x > j[0]){
      this.x +=this.speedX*-1;
      }
//if the current position (X) is within acceptable range (+-10) stop moving 
      
      if (this.x <= j[0]+5 && this.x >= j[0]-5){
      this.speedX =0;

      }

// For the vertical movement
//if the current position (X) is less than the coordinate move towards it
      if (this.y < j[1]){
      this.y +=this.speedY;
      }
      
//if the current position (X) is greater than the coordinate move back towards it 
      if (this.y > j[1]){
      this.y +=this.speedY*-1;
      }
//if the current position (X) is within acceptable range (+-10) stop moving 
      
      if (this.y <= j[1]+5 && this.x >= j[1]-5){
      this.speedY =0;

      }
    }
  }
