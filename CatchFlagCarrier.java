public class CatchFlagCarrier extends Player{
  
  
  
  
// constructor (it's the same as the deafult dummy player
    public CatchFlagCarrier(Field f, int side, String name, int number, String team,char symbol, double x, double y){
    super(f, side, name, number, team, symbol, x, y);
    this.speedX = 0;
    this.speedY = 0;
    }
  


// Play Method 
    public void play(Field field){
      // some variables that need to be initialized 
      
      Player a = this;
      int[] coordinates = {0, 0};
      Player b = null;
      
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
            coordinates = field.getFlag2Position();

          }
        }
      }
      

      
      
//Player movement behavior
// move towards the coordinates
//the distance between the x coordinates of the player and the flag 
  
      double mX = coordinates[0] - this.x;

//the distance between the y coordinates of the player and the flag 
      double mY = coordinates[1] - this.y;
      
      if(mX > 0){
        mX = 2;
      }
      if(mX < 0){
      mX = -2;
      }
      if(mY > 0){
        mY = 2;
      }
      if(mY < 0){
      mY = -2;
      }
    
      this.speedX = mX;
    
      this.speedY = mY;
    
      
      
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
      update(field);
      }

   
    
// Update method
    public void update(Field field){
    
    
    }
  


}
