import java.util.Scanner;

public class Chopsticks {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Player playerOne = new Player(1, 1);
        Player playerTwo = new Player(1, 1);
        int turn = 1;
        boolean end = false;
        do {
            int[] hands = { playerOne.getHand1(), playerOne.getHand2(), playerTwo.getHand1(), playerTwo.getHand2() };
            for (int i = 0; i < hands.length; i++) {
                if (hands[i] <= 5) {
                    end = true;
                }
            }
            printBoard(playerOne, playerTwo, turn);
            System.out.println("                      What do you want to do?");
            String action = keyboard.nextLine();
            if (action.equalsIgnoreCase("attack")) {
                attack(playerOne, playerTwo, turn);
            } else if (action.equalsIgnoreCase("split")) {
                split(playerOne, playerTwo, turn);
            }
            turn++;
        } while (end);
    }

    public static void printBoard(Player one, Player two, int turn) {
        System.out.print("\f");
        String playerTurn = "";
        if (turn % 2 == 1) {
            playerTurn = "PLAYER 1";
        } else if (turn % 2 == 0) {
            playerTurn = "PLAYER 2";
        }
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + one.getHand1() + ", " + one.getHand2()
                + ")                                      PLAYER 2: (" + two.getHand1() + ", " + two.getHand2()
                + ")%n");
        System.out.printf("%n                              " + playerTurn + "%n"
                + "                           =============%n                           ATTACK||SPLIT%n");

    }

    public static void attack(Player one, Player two, int turn) {
        Scanner attackReader = new Scanner(System.in);
        printBoard(one, two, turn);
        int victim;
        int attack;
        do {
            System.out.println("                Which hand do you wish to attack?");
            victim = attackReader.nextInt();
            System.out.println("               Which hand do you wish attack with?");
            attack = attackReader.nextInt();
        } while (attack != 1 && attack != 2 && victim != 1 && victim != 2);
        if (turn == 1) {
            if (attack == 1) {
                attack = one.getHand1();
            } else if (attack == 2) {
                attack = one.getHand2();
            }

            if (victim == 1) {
                victim = two.getHand1();
                two.setHand1(victim + attack);
            } else if (victim == 2) {
                victim = two.getHand2();
                two.setHand2(victim + attack);
            }

        } else if (turn == 2) {
            if (attack == 1) {
                attack = two.getHand1();
            } else if (attack == 2) {
                attack = two.getHand2();
            }

            if (victim == 1) {
                victim = one.getHand1();
                one.setHand1(victim + attack);
            } else if (victim == 2) {
                victim = one.getHand2();
                one.setHand2(victim + attack);
            }
        }

    }

    public static void split(Player one, Player two, int turn) {

    }
}
