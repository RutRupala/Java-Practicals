import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    static boolean beats(Move a, Move b) {
        return switch (a) {
            case ROCK -> b == Move.SCISSORS || b == Move.LIZARD;
            case PAPER -> b == Move.ROCK || b == Move.SPOCK;
            case SCISSORS -> b == Move.PAPER || b == Move.LIZARD;
            case LIZARD -> b == Move.SPOCK || b == Move.PAPER;
            case SPOCK -> b == Move.SCISSORS || b == Move.ROCK;
        };
    }

    /** 1 if a beats b, -1 if b beats a, 0 for a tie. */
    static int winner(Move a, Move b) {
        if (a == b) return 0;
        return beats(a, b) ? 1 : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        Move[] all = Move.values();
        int you = 0, comp = 0;
        for (int round = 1; round <= 5; round++) {
            Move c = all[rnd.nextInt(all.length)];
            Move p;
            while (true) {
                System.out.print("Round " + round + " - your move (ROCK/PAPER/SCISSORS/LIZARD/SPOCK): ");
                try { p = Move.valueOf(sc.next().toUpperCase()); break; }
                catch (IllegalArgumentException e) { System.out.println("Invalid move."); }
            }
            int r = winner(p, c);
            String res = r == 0 ? "Tie" : (r > 0 ? "You win the round" : "Computer wins the round");
            if (r > 0) you++; else if (r < 0) comp++;
            System.out.println("You: " + p + "  Computer: " + c + "  -> " + res);
        }
        if (you > comp) System.out.println("You win " + you + "-" + comp);
        else if (comp > you) System.out.println("Computer wins " + comp + "-" + you);
        else System.out.println("Overall tie " + you + "-" + comp);
    }
}
