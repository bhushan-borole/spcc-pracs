public class Conversions {
    public double toFeet(float metre){
        return metre * 3.2808;
    }

    public double toMetre(float feet){
        return feet / 3.2808;
    }

    public double toLitre(float cf){
        return 0.0353 * cf;
    }

    public double toCubicFeet(float litre){
        return litre / 0.0353;
    }

    public double toFahrenheit(float c){
        return (c * 9/5) + 32;
    }

    public double toCelcius(float f){
        return (f - 32) * 5/9;
    }
}
