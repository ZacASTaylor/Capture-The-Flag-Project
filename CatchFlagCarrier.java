public class CatchFlagCarrier extends Player{
  
  
  
  
// constructor (it's the same as the deafult dummy player
    public CatchFlagCarrier(Field f, int side, String name, int number, String team,char symbol, double x, double y){
    super(f, side, name, number, team, symbol, x, y);
    this.speedX = Math.random()*4-2;
    this.speedY = Math.random()*4-2;
    }
  


// Play Method 
    public void play(Field field){
      // some variables that need to be initialized 
      
      Player a = this;
      int[] coordinates = {0, 0};
      Player b;
      
      // the purpose of the player is to go after the player if(f) they have the flag.
      // check what team the player is on 
      // check if their flag has been stolen 
      // go to the co-ordinates of the flag

// if they are on team 1
      if (field.getTeam.get(a).equals(new Integer(1))){
        
        // check if anyone on the opposing team has picked up the flag
        // checks the array list that getTeam2() returns at the specific index i
        for (int i =0; i< field.getTeam2().size(); i++){
          
          // makes the entity retuned by the method a temporary variable and casts it as a player (cuz its always a player)
          b = (Player) field.getTeam2().get(i);
          
          //check if the player at index i has picked up the flag 
          if (field.pickUpFlag(b) == true){
         
           
          // saves the coordinates of the flag into a variable
            coordinates = field.getFlag1Position();
          } 
        }
      }
      
//if they are on team 2
      else {    

        for (int i =0; i< field.getTeam1().size(); i++){
       
          // makes the entity retuned by the method a temporary variable and casts it as a player (cuz its always a player)
          b = (Player) field.getTeam1().get(i);
          
          //check if the player at index i has picked up the flag 
          if (field.pickUpFlag(b) == true){
            
          // saves the coordinates of the flag into a variable
            field.getFlag2Position();

          }
        }
      }
      
      
      
//Player movement behavior
// move towards the coordinates
// this assumes that the player is moving in the right direction to begin with.

// For the horizontal movement
//if the current position (X) is less than the coordinate move towards it
      if (this.x < coordinates[0]){
      this.x +=this.speedX;
      }
      
//if the current position (X) is greater than the coordinate move back towards it 
      if (this.x > coordinates[0]){
      this.x +=this.speedX*-1;
      }
//if the current position (X) is within acceptable range (+-10) stop moving 
      
      if (this.x == coordinates[0] || this.x <= coordinates[0]+10 && this.x >= coordinates[0]-10){
      this.speedX =0;
      }

// For the vertical movement
//if the current position (X) is less than the coordinate move towards it      
      if (this.y < coordinates[1]){
      this.y +=this.speedX;
      }
      
//if the current position (X) is greater than the coordinate move back towards it       
      if (this.y > coordinates[1]){
      this.y +=this.speedY*-1;
      }
//if the current position (X) is within acceptable range (+-10) stop moving 
      if (this.y == coordinates[0] || this.y <= coordinates[0]+10 && this.y >= coordinates[0]-10){
      this.speedY =0;
      }
      
// behavior so that the player will not go out of bounds (right now it rests their position and gives them a new random speed.    
      if (this.x < field.minX+2 || this.x > field.maxX - 25|| this.y < field.minY+ 2 || this.y > field.maxY - 25){

// Resets position if the player hits any of the four edges
      if(this.x > field.maxX - 25){
      this.x = field.maxX-25;
      }
      if(this.x < field.minX+2){
        this.x = field.minX+2;
      }
      if(this.y < field.minY+ 2){
        this.y = field.minY+ 2;
      }
      if(this.y > field.maxY - 25){
      this.y = field.maxY - 25;
      }
    
// resets the speed to a new random value
      this.speedX = 0;
      this.speedY = 0;
      this.speedX = Math.random()*4-2;
      this.speedY = Math.random()*4-2;
      }
      }

   
    
// Update method
    public void update(Field field){
    
    
    }
  


}
