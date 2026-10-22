import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormChecker {
    static List<String> check(Object obj) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object v = f.get(obj);
            if (f.isAnnotationPresent(NotBlank.class) && (v == null || v.toString().isBlank()))
                errors.add(f.getName() + " must not be blank");
            if (f.isAnnotationPresent(MaxLength.class) && v != null) {
                int max = f.getAnnotation(MaxLength.class).value();
                if (v.toString().length() > max)
                    errors.add(f.getName() + " must be at most " + max + " characters");
            }
        }
        return errors;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Good form : " + check(new SignupForm("riya", "riya@x.com", "1234")));
        System.out.println("Bad form  : " + check(new SignupForm("", "a@b.com", "123456")));
        System.out.println("Worse form: " + check(new SignupForm("averyveryverylongname", " ", "9")));
    }
}
