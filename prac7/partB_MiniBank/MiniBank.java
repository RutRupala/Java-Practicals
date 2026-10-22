import model.Account;
import model.SavingsAccount;
import util.AnnotationValidator;

import java.util.Arrays;

public class MiniBank {
    public static void main(String[] args) {
        Account bad = new SavingsAccount("Riya", -100, 0);
        Account good = new SavingsAccount("Arjun", 5000, 1000);
        Account longName = new SavingsAccount("Bartholomew Longname", 500, 0);

        System.out.println("Negative balance : " + Arrays.toString(AnnotationValidator.validate(bad)));
        System.out.println("Valid account    : " + Arrays.toString(AnnotationValidator.validate(good)));
        System.out.println("With field names : " + Arrays.toString(AnnotationValidator.validateWithFieldNames(longName)));
    }
}
