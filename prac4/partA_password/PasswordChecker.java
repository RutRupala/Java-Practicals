public class PasswordChecker {
    static boolean longEnough(String pw) { return pw.length() >= 8; }
    static boolean hasUpper(String pw) { return pw.matches(".*[A-Z].*"); }
    static boolean hasDigit(String pw) { return pw.matches(".*[0-9].*"); }
    static boolean hasSpecial(String pw) { return pw.matches(".*[^A-Za-z0-9].*"); }

    static String strength(String pw) {
        int score = 0;
        if (longEnough(pw)) score++;
        if (hasUpper(pw)) score++;
        if (hasDigit(pw)) score++;
        if (hasSpecial(pw)) score++;
        if (score <= 1) return "Weak";
        if (score <= 3) return "Medium";
        return "Strong";
    }
}
