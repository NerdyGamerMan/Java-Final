/*
 * This is a java class
 * just to experiment with
 * different features, potentially
 * being things that we may
 * consider implementing!!
 */

public class Experiment {
    String[] args = new String[] {"By", "Hi", "Cry"};
    public static void main(String[] args) {
        Player test1 = new Player();
        Player test2 = new Player(3, 4);
        int[] list1 = {test1.getHand1(), test1.getHand2(), test2.getHand1(), test2.getHand2()};
        for (int i = 0; i < list1.length; i++) {
            System.out.print(list1[i] + " ");
        }
        System.out.print("\n");
    }
    
}
