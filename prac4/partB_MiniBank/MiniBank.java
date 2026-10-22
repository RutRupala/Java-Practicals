public class MiniBank {
    public static void main(String[] args) {
        System.out.println("isValidMobile(9876543210): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile(12345)     : " + Validator.isValidMobile("12345"));
        System.out.println("isValidEmail(abc@xyz.com): " + Validator.isValidEmail("abc@xyz.com"));
        System.out.println("isValidEmail(abc@xyz)    : " + Validator.isValidEmail("abc@xyz"));
        System.out.println("isValidPan(ABCDE1234F)   : " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("isValidPan(abcde1234f)   : " + Validator.isValidPan("abcde1234f"));
        System.out.println("isValidIfsc(SBIN0001234) : " + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("isValidIfsc(SBIN1001234) : " + Validator.isValidIfsc("SBIN1001234"));

        Command c = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("type=" + c.type() + ", accountNumber=" + c.accountNumber() + ", amount=" + c.amount());
        try {
            CommandParser.parse("DEPOSIT AC0001");
        } catch (IllegalArgumentException e) {
            System.out.println("Parse error: " + e.getMessage());
        }
        Account a = new Account("Riya", 4000);
        System.out.println(StatementFormatter.buildStatement(a));
    }
}
