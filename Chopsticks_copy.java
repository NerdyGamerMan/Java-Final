
//imports
import java.util.*;

public class Chopsticks_copy {
    public static void main(String[] args) {
        // Players, Scanner, Turn Counter, and Game Ender
        Scanner keyboard = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        int turn = 1;
        boolean end = false;
        do { // Gameplay Begins
            int[] hands = { p1.getHand1(), p1.getHand2(), p2.getHand1(), p2.getHand2() };
            // Array of hand values: [Used for checking if either player has lost yet]
            for (int i = 0; i < hands.length; i++) { // Checking if either player has lost yet
                if (p1.getHand1() == 0 && p1.getHand2() == 0 || p2.getHand1() == 0 && p2.getHand2() == 0) {
                    end = true; // Setting endGame var to true
                    break;
                }
            }
            if (end) {// Checking if game should be ended
                break;// Ending game
            }
            printBoard(p1, p2, turn); // Printing the board

            // Asking for action input
            System.out.println("                      What do you want to do?");
            String action = keyboard.nextLine();

            // Checking and executing action
            if (action.equalsIgnoreCase("attack")) {
                attack(p1, p2, turn);
            } else if (action.equalsIgnoreCase("split")) {
                if (turn % 2 != 0) {
                    split(p1);
                } else {
                    split(p2);
                }
            }
        } while (!end);

        // Ending Game message
        endOfGame(p1, p2, turn);

    }

    // EndOfGame Board Method
    public static void endOfGame(Player p1, Player p2, int turn) {
        // Title Card
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + p1.getHand1() + ", " + p1.getHand2()
                + ")                                      PLAYER 2: (" + p2.getHand1() + ", " + p2.getHand2()
                + ")%n");

        // Winner
        System.out.printf("\n\n                        %s is the Winner!!!\n\n\n",
                (turn % 2 == 0 ? "Player 1" : "Player 2"));
    }

    // PringBoard method
    public static void printBoard(Player one, Player two, int turn) {
        // Checking which player's turn it is
        System.out.print("\f");
        String playerTurn = "";
        if (turn % 2 == 0) {
            playerTurn = "PLAYER 2";// Setting player turn
        } else {
            playerTurn = "PLAYER 1";// Setting player turn
        }

        // Title card
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + one.getHand1() + ", " + one.getHand2()
                + ")                                      PLAYER 2: (" + two.getHand1() + ", " + two.getHand2()
                + ")%n");// Player scores
        System.out.printf("%n                              " + playerTurn + "%n"
                + "                           =============%n                           ATTACK||SPLIT%n");// Options

    }

    // Attack method
    public static void attack(Player one, Player two, int turn) {
        // Creating local method variables
        Scanner attackReader = new Scanner(System.in);
        int victim = 0;
        int attack = 0;
        boolean exceptionFlag = true;

        // Starting 'check' loop
        do {
            System.out.println("                Which hand do you wish to attack?");
            while (exceptionFlag) {
                try {
                    victim = attackReader.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Thats not a Valid Input! (Try typing \"1\" or \"2\"");
                    attackReader.next();
                    exceptionFlag = false;
                }
                if (exceptionFlag == false) {
                    exceptionFlag = true;
                } else {
                    exceptionFlag = false;
                }
            }

            do {
                System.out.println("               Which hand do you wish attack with?");
                try {
                    attack = attackReader.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Thats not a Valid Input! (Try typing \"1\" or \"2\"");
                    attackReader.next();
                    exceptionFlag = false;
                }
            } while (victim != 1 && victim != 2);

        } while (attack != 1 && attack != 2
        /* || attack != 'L' && attack != 'R' && victim != 'L' && victim != 'R' */);
        if ((turn - 1) % 2 == 0) {
            if (attack == 1 && one.getHand1() > 0) {
                attack = one.getHand1();
            } else if (attack == 2 && one.getHand2() > 0) {
                attack = one.getHand2();
            } else {

            }

            if (victim == 1 && two.getHand1() > 0) {
                victim = two.getHand1();
                if (victim + attack > 5) {
                    two.setHand1((victim + attack) - 5);
                    turn++;
                } else if (victim + attack == 5) {
                    two.setHand1(0);
                    turn++;
                } else {
                    two.setHand1(victim + attack);
                    turn++;
                }
            } else if (victim == 2 && two.getHand2() > 0) {
                victim = two.getHand2();
                if (victim + attack > 5) {
                    two.setHand2((victim + attack) - 5);
                    turn++;
                } else if (victim + attack == 5) {
                    two.setHand2(0);
                    turn++;
                } else {
                    two.setHand2(victim + attack);
                    turn++;
                }

            }

        } else if (turn % 2 == 0) {
            if (attack == 1) {
                attack = two.getHand1();
            } else if (attack == 2) {
                attack = two.getHand2();
            }

            if (victim == 1) {
                victim = one.getHand1();
                if (victim + attack > 5) {
                    one.setHand1((victim + attack) - 5);
                    turn++;
                } else if (victim + attack == 5) {
                    one.setHand1(0);
                    turn++;
                } else {
                    one.setHand1(victim + attack);
                    turn++;
                }
            } else if (victim == 2) {
                victim = one.getHand2();
                if (victim + attack > 5) {
                    one.setHand2((victim + attack) - 5);
                    turn++;
                } else if (victim + attack == 5) {
                    one.setHand2(0);
                    turn++;
                } else {
                    one.setHand2(victim + attack);
                    turn++;
                }
            }
        }
    }

    public static void split(Player player) {
        Scanner splitRead = new Scanner(System.in);
        int splitAmount = 0;
        int handNum = 0;
        do {
            System.out.println("                   How much do you want to split: ");
            splitAmount = splitRead.nextInt();
            System.out.println("                Which hand do you want to split off: ");
            handNum = splitRead.nextInt();
        } while (handNum != 1 && handNum != 2);
        if (handNum == 1 && splitAmount <= player.getHand1() && player.getHand1() > 0) {
            player.setHand2(player.getHand2() + splitAmount);
            player.setHand1(player.getHand1() - splitAmount);
        } else if (handNum == 2 && splitAmount <= player.getHand2() && player.getHand2() > 0) {
            player.setHand1(player.getHand1() + splitAmount);
            player.setHand2(player.getHand2() - splitAmount);
        } else {
            System.out.println("                            Invalid Input");
        }
    }
}
