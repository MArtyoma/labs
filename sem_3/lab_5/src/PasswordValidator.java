public class PasswordValidator {
  public static boolean isValid(String password) {
    String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
    return password != null && password.matches(regex);
  }

  public static void main(String[] args) {
    String pwd = "MyPass123";
    System.out.println("Пароль корректен: " + isValid(pwd));
  }
}
