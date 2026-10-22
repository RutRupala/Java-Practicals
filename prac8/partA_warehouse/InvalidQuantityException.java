public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(int qty) { super("Invalid quantity: " + qty); }
}
