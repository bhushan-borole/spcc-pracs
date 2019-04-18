import java.util.ArrayList;
import java.util.List;

public class Properties {
    public long factorial(int n){
        long fact = 1;
        for(int i=1; i<=n; i++){
           fact *= i;
        }
        return fact;
    }

    public long sumNaturalNumbers(int n){
        long sum = 0;
        for(int i=1; i<=n; i++){
            sum += i;
        }
        return sum;
    }

    public List<Integer> factors(int n){
        List<Integer> factors = new ArrayList<>();
        for(int i=1; i<=Math.floorDiv(n, 2); i++){
            if(n%i == 0)
                factors.add(i);
        }
        factors.add(n);
        return factors;
    }
}
