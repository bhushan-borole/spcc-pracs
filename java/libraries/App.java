import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        // Find areas of geometric figures
        System.out.println("---------- AREA ----------");
        Area area = new Area();
        System.out.printf("Area of a triangle with base %f and height %f: %f\n",
                5.0, 8.0, area.ofTriangle(5, 8));
        System.out.printf("Area of a parallelogram with base %f and height %f: %f\n",
                5.0, 8.0, area.ofParallelogram(5, 8));
        System.out.printf("Area of a square with side %f: %f\n",
                5.0, area.ofSquare(5));
        System.out.printf("Area of a circle with radius %f: %f\n",
                5.0, area.ofCircle(5));

        // Conversions
        System.out.println("---------- Conversions ----------");
        Conversions conversions = new Conversions();
        System.out.printf("\n%f Metre = %f Feet\n", 5.0, conversions.toFeet(5));
        System.out.printf("%f Feet = %f Metre\n", 5.0, conversions.toMetre(5));
        System.out.printf("%f Litre = %f Cubic Feet\n", 5.0, conversions.toCubicFeet(5));
        System.out.printf("%f Cubic Feet = %f Litre\n", 5.0, conversions.toLitre(5));
        System.out.printf("%f Celcius = %f Farhenheit\n", 5.0, conversions.toFahrenheit(5));
        System.out.printf("%f Farhenheit = %f Celcius\n", 5.0, conversions.toCelcius(5));

        // Properties
        System.out.println("---------- Properties ---------\n");
        Properties properties = new Properties();
        int n = 10;
        System.out.printf("Factorial of %d = %d\n", n,
                properties.factorial(n));
        System.out.printf("Sum of %d natural numbers = %d\n", n,
                properties.sumNaturalNumbers(n));
        System.out.printf("Factors of %d: %s\n", n,
                Arrays.toString(properties.factors(n).toArray()));

    }

}
