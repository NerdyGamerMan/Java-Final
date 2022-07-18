
public class Player
{
    private int handOne;
    private int handTwo;
    
    //Constructors for both hands
    public Player(){
        this.handOne = 1;
        this.handTwo = 1;
    }
    
    public Player(int handOne, int handTwo){
        this.handOne = handOne;
        this.handTwo = handTwo;
    }
    
    //Getters and Setters for Hand 1
    public int getHand1(){
        return handOne;
    }
    
    public void setHand1(int hand){
        this.handOne = hand;
    }
    
    //Getters and Setters for Hand 2
    public int getHand2(){
        return handTwo;
    }
    
    public void setHand2(int hand){
        this.handTwo = hand;
    }
    
}
