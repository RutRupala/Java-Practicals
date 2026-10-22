public class Driver {
    public static void main(String[] args) {
        Card[] incoming = { new Card("Ace", "Spades"), new Card("Queen", "Hearts"),
                            new Card("Ten", "Clubs"), new Card("Ace", "Spades") };
        Card[] deck = new Card[incoming.length];
        int n = 0;
        for (Card c : incoming) {
            boolean dup = false;
            for (int i = 0; i < n; i++) if (deck[i].equals(c)) { dup = true; break; }
            if (dup) { System.out.println("Duplicate found: " + c); break; }
            deck[n++] = c;
            System.out.println("Added: " + c);
        }
    }
}
