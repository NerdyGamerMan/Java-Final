// Imports
import java.util.Scanner;
import java.util.InputMismatchException;

public class Chopsticks_copy {
    public static void main(String[] args) {
        // Setting scanner
        Scanner keyboard = new Scanner(System.in);
        // Creating Players
        Player p1 = new Player();
        Player p2 = new Player();
        // Initilizing turn counter
        int turn = 1;
        // Checking end
        boolean end = false;
        // Starting Gameplay
        do {
            //
            int[] hands = { p1.getHand1(), p1.getHand2(), p2.getHand1(), p2.getHand2() };
            for (int i = 0; i < hands.length; i++) {
                if (p1.getHand1() == 0 && p1.getHand2() == 0 || p2.getHand1() == 0 && p2.getHand2() == 0) {
                    end = true;
                    break;
                }
            }
            if (end) {
                break;
            }
            printBoard(p1, p2, turn);
            System.out.println("                      What do you want to do?");
            String action = keyboard.nextLine();
            if (action.equalsIgnoreCase("attack")) {
                attack(p1, p2, turn);
            } else if (action.equalsIgnoreCase("split")) {
                if (playerNum(turn).equals("PLAYER 1")) {
                    split(p1);
                }
                if (playerNum(turn).equals("PLAYER 2")) {
                    split(p2);
                }
            }
            turn++;
        } while (!end);
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + p1.getHand1() + ", " + p1.getHand2()
                + ")                                      PLAYER 2: (" + p2.getHand1() + ", " + p2.getHand2()
                + ")%n");
        System.out.printf("\n\n                        %s is the Winner!!!\n\n\n", (turn % 2 == 0 ? "Player 1" : "Player 2"));

        keyboard.close();
    }

    public static String playerNum(int turn) {
        if (turn % 2 == 0) {
            return "PLAYER 2";
        } else {
            return "PLAYER 1";
        }
    }

    public static void printBoard(Player one, Player two, int turn) {
        System.out.print("\f");
        String playerTurn = playerNum(turn);
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + one.getHand1() + ", " + one.getHand2()
                + ")                                      PLAYER 2: (" + two.getHand1() + ", " + two.getHand2()
                + ")%n");
        System.out.printf("%n                              " + playerTurn + "%n"
                + "                           =============%n                           ATTACK||SPLIT%n");

    }

    // Attack the opponent
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
                    System.out.println("Thats not a Valid Input! (Try typing \"1\" or \"2\". \nYou can also use \"L\" and \"R\"!)");
                    attackReader.next();
                    exceptionFlag = false;
                }
                if (exceptionFlag == false) {
                    exceptionFlag = true;
                } else {
                    exceptionFlag = false;
                }
            }
            exceptionFlag = true;
            do {
                System.out.println("               Which hand do you wish attack with?");
                while (exceptionFlag) {
                    try {
                        attack = attackReader.nextInt(); 
                    } 
                    catch (InputMismatchException e) {
                        System.out.println("Thats not a Valid Input! (Try typing \"1\" or \"2\". \nYou can also use \"L\" and \"R\"!)");
                        attackReader.next();
                        exceptionFlag = false;
                    }
                    if (exceptionFlag == false) {
                        exceptionFlag = true;
                    } else {
                        exceptionFlag = false;
                    }
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


    // Checking the end of the game
    public static void endGame(Player one, Player two) {
        if (one.getHand1() == 0 && one.getHand2() == 0) {
            System.out.println("Player 2 has won");
        } else if (two.getHand1() == 0 && two.getHand2() == 0) {
            System.out.println("Player 1 has won");
        }
    }

    // Splitting functionality
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
        if (handNum == 1 && splitAmount <= player.getHand1()) {
            player.setHand2(player.getHand2() + splitAmount);
            player.setHand1(player.getHand1() - splitAmount);
        } else if (handNum == 2 && splitAmount <= player.getHand2()) {
            player.setHand2(player.getHand2() + splitAmount);
            player.setHand1(player.getHand1() - splitAmount);
        }
        splitRead.close();
    }
}