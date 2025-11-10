public class IPValidator {
  public static boolean isValidIP(String ip) {
    if (ip == null)
      return false;
    String regex =
        "^((25[0-5]|2[0-4][0-9]|(0?[1-9][0-9]?|1?[0-9][0-9]?))\\.){3}(25[0-5]|2[0-4][0-9]|(0?[1-9][0-9]?|1?[0-9][0-9]?))$";
    return ip.matches(regex);
  }

  public static void main(String[] args) {
    String ip = "192.168.1.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));

    ip = "2.168.1.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));

    ip = "02.168.1.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));

    ip = "002.168.1.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));

    ip = "192.168.01.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));

    ip = "192.168.001.1";
    System.out.println("IP " + ip + " корректен: " + isValidIP(ip));
  }
}
