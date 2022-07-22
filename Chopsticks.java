import java.util.Scanner;

//BUGS:
//PLAYER STILL SWITHES AFTER TYPO IN FIRST INPUT (ACTION)
public class Chopsticks {
    public static void main(String[] args) {
        // Setup Variables
        Scanner keyboard = new Scanner(System.in); // Scanner
        Player p1 = new Player();// Player 1
        Player p2 = new Player();// Player 2
        int turn = 1;// Turn Counter
        boolean end = false;// Game end
        String action = "";// Action input variable

        // Starting GamePlay
        do {

            // Checking if game is over
            int[] hands = { p1.getHand1(), p1.getHand2(), p2.getHand1(), p2.getHand2() };// Hand Values
            for (int i = 0; i < hands.length; i++) {
                if (p1.getHand1() == 0 && p1.getHand2() == 0 || p2.getHand1() == 0 && p2.getHand2() == 0) {// checking
                                                                                                           // if either
                                                                                                           // player is
                                                                                                           // DEAD
                    end = true;// Setting end to true
                    break;
                }
            }

            // ending game
            if (end) {
                break;
            }

            // If game hasn't ended, gameplay starts
            printBoard(p1, p2, turn);// printing board

            // repeat action input until valid
            do {
                System.out.println("                      What do you want to do?");
                action = keyboard.nextLine();

                // checking for action
                if (action.equalsIgnoreCase("attack")) {
                    attack(p1, p2, turn);// attack sequence
                } else if (action.equalsIgnoreCase("split")) {
                    if (turn % 2 != 0) {// chekcing who's turn it is
                        split(p1);// split for player 1
                    } else {
                        split(p2);// split for player 2
                    }
                } else {// Logic Exception Handling
                    System.out.println("                That's not an action you can take");
                }
            } while (!action.equalsIgnoreCase("attack") && !action.equalsIgnoreCase("split"));
            turn++;
        } while (!end);

        // End game sequence
        endOfGame(p1, p2, turn);

    }

    // endOfGame Method
    public static void endOfGame(Player p1, Player p2, int turn) {
        // Printing header
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + p1.getHand1() + ", " + p1.getHand2()
                + ")                                      PLAYER 2: (" + p2.getHand1() + ", " + p2.getHand2()
                + ")%n");

        // Printing winner
        System.out.printf("\n\n                        %s is the Winner!!!\n\n\n",
                (turn % 2 == 0 ? "Player 1" : "Player 2"));
    }

    // Print Board method
    public static void printBoard(Player one, Player two, int turn) {
        System.out.print("\f");// Clearing terminal

        // setting player
        String playerTurn = "";
        if (turn % 2 == 0) {
            playerTurn = "PLAYER 2";
        } else {
            playerTurn = "PLAYER 1";
        }

        // Printing header
        System.out.println("======================================================================");
        System.out.println("==                   Vrishabh & Ansh's Chopsticks                   ==");
        System.out.println("======================================================================");
        System.out.printf("PLAYER 1: (" + one.getHand1() + ", " + one.getHand2()
                + ")                                      PLAYER 2: (" + two.getHand1() + ", " + two.getHand2()
                + ")%n");
        System.out.printf("%n                              " + playerTurn + "%n"// Printing options
                + "                           =============%n                           ATTACK||SPLIT%n");// Printing
                                                                                                          // Options

    }

    // attack method
    public static void attack(Player one, Player two, int turn) {
        // Setting method variables
        Scanner attackReader = new Scanner(System.in);
        Player attacker = new Player();
        Player attacked = new Player();
        int victim;
        int attack;

        // setting attacker and attacked (attacking player and defending player)
        if (turn % 2 == 0) {
            attacker = two;
            attacked = one;
        } else if (turn % 2 != 0) {
            attacker = one;
            attacked = two;
        }

        // Running Attack Sequence
        do {
            System.out.println("                Which hand do you wish to attack?");
            victim = attackReader.nextInt();

            // if attacking hand 1...
            if (victim == 1 && attacked.getHand1() == 0) {// checking if attacking dead hand
                System.out.println("                      That hand is DEAD");

                // repeating input until valid
                do {
                    System.out.println("                Which hand do you wish to attack?");
                    victim = attackReader.nextInt();
                    if (victim == 1 && attacked.getHand1() == 0) {
                        System.out.println("                      That hand is DEAD");
                    }
                } while (victim != 2);

                // if attacking hand 2...
            } else if (victim == 2 && attacked.getHand2() == 0) {
                System.out.println("                      That hand is DEAD");
                do {
                    System.out.println("                Which hand do you wish to attack?");
                    victim = attackReader.nextInt();
                    if (victim == 2 && attacked.getHand2() == 0) {
                        System.out.println("                      That hand is DEAD");
                    }
                } while (victim != 1);
            }
            System.out.println("               Which hand do you wish attack with?");
            attack = attackReader.nextInt();
            if (attack == 1 && attacker.getHand1() == 0) {
                System.out.println("                      That hand is DEAD");
                do {
                    System.out.println("               Which hand do you wish attack with?");
                    attack = attackReader.nextInt();
                    if (attack == 1 && attacker.getHand1() == 0) {
                        System.out.println("                      That hand is DEAD");
                    }
                } while (attack != 2);
            } else if (attack == 2 && attacker.getHand2() == 0) {
                System.out.println("                      That hand is DEAD");
                do {
                    System.out.println("               Which hand do you wish attack with?");
                    attack = attackReader.nextInt();
                    if (attack == 2 && attacker.getHand2() == 0) {
                        System.out.println("                      That hand is DEAD");
                    }
                } while (attack != 1);
            }
        } while (attack != 1 && attack != 2 && victim != 1 && victim != 2
        /* || attack != 'L' && attack != 'R' && victim != 'L' && victim != 'R' */);
        if ((turn - 1) % 2 == 0) {
            if (attack == 1) {
                attack = one.getHand1();
            } else if (attack == 2) {
                attack = one.getHand2();
            }

            if (victim == 1) {
                victim = two.getHand1();
                if (victim + attack > 5) {
                    two.setHand1((victim + attack) - 5);
                } else if (victim + attack == 5) {
                    two.setHand1(0);
                } else {
                    two.setHand1(victim + attack);
                }
            } else if (victim == 2) {
                victim = two.getHand2();
                if (victim + attack > 5) {
                    two.setHand2((victim + attack) - 5);
                } else if (victim + attack == 5) {
                    two.setHand2(0);
                } else {
                    two.setHand2(victim + attack);
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
                } else if (victim + attack == 5) {
                    one.setHand1(0);
                } else {
                    one.setHand1(victim + attack);
                }
            } else if (victim == 2) {
                victim = one.getHand2();
                if (victim + attack > 5) {
                    one.setHand2((victim + attack) - 5);
                } else if (victim + attack == 5) {
                    one.setHand2(0);
                } else {
                    one.setHand2(victim + attack);
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