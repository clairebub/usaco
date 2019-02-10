public class EulerMethod {
  public static void main(String[] args) {
    double y = 3;
    double h = 0.001;
    long steps = Math.round(1/h);

    for (int i = 1; i <= steps; i++) {
      double x = h*i;
      y = y + h * (x*x*6 - 3*x*x*y);
      System.out.println("i=" + i + ", y=" + y);
    }
    System.out.println(y);
  }
}
