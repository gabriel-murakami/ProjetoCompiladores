import java.util.Scanner;
public class MainClass {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a;
    String teste;
    int b;
    double numero;
    int x;
    int y;
    int z;
    a = scanner.nextInt();
    b = scanner.nextInt();
    teste = "AWESOME STRING";
    b = 90;
    System.out.println(b);
    System.out.println(teste);
    if (b==90) {
	    System.out.println("b é noventa");
    } else {
	    System.out.println("b não é noventa");
    }
    a = 120;
    while (b<0) {
      System.out.println(b);
      b = b-1;
    }
    numero = (a+b)/2.0;
    switch (teste) {
      case "AWESOME STRING":
        System.out.println("AWESOME");
        break;
      case "NOT AWESOME STRING":
        System.out.println("BAD");
        break;
      default:
        System.out.println("NDA");
        break;
    }
    scanner.close();
  }
}
