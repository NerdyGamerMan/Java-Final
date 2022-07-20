import java.util.Scanner;

public class Chopsticks_copy{
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        int turn = 1;
        boolean end = false;
        do {
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
                split(p1, p2, turn);
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

    }

    public static void printBoard(Player one, Player two, int turn) {
        System.out.print("\f");
        String playerTurn = "";
        if (turn % 2 == 0) {
            playerTurn = "PLAYER 2";
        } else {
            playerTurn = "PLAYER 1";
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
        if ((turn-1)%2 == 0) {
            if (attack == 1) {
                attack = one.getHand1();
            } else if (attack == 2) {
                attack = one.getHand2();
            }

            if (victim == 1) {
                victim = two.getHand1();
                if (victim + attack > 5) {
                    two.setHand1((victim + attack) - 5);
                } 
                else if (victim + attack == 5) {
                    two.setHand1(0);
                }
                else {
                    two.setHand1(victim + attack);
                }
            } else if (victim == 2) {
                victim = two.getHand2();
                if (victim + attack > 5) {
                    two.setHand2((victim + attack) - 5);
                }
                else if (victim + attack == 5) {
                    two.setHand2(0);
                } 
                else {
                    two.setHand2(victim + attack);
                }
                    
            }

        } else if (turn%2 == 0) {
            if (attack == 1) {
                attack = two.getHand1();
            } else if (attack == 2) {
                attack = two.getHand2();
            }

            if (victim == 1) {
                victim = one.getHand1();
                if (victim + attack > 5) {
                    one.setHand1((victim + attack) - 5);
                }
                else if (victim + attack == 5) {
                    one.setHand1(0);
                }
                else {
                    one.setHand1(victim + attack);
                }
            } else if (victim == 2) {
                victim = one.getHand2();
                if (victim + attack > 5) {
                    one.setHand2((victim + attack) - 5);
                }
                else if (victim + attack == 5) {
                    one.setHand2(0);
                }
                else {
                    one.setHand2(victim + attack);
                }
            }
        }
    }

    public static void split(Player one, Player two, int turn) {
        return;
    }
}
