public class SignupForm {
    @NotBlank @MaxLength(10)
    String username;

    @NotBlank
    String email;

    @MaxLength(5)
    String pin;

    SignupForm(String username, String email, String pin) {
        this.username = username; this.email = email; this.pin = pin;
    }
}
