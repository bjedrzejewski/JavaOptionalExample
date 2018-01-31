import javax.swing.text.html.Option;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Optional!");
        System.out.println("Divide three times 27 by 3");
        System.out.println(divideThreeTimes(27, 3));
        System.out.println("Divide three times 27 by 0");
        System.out.println(divideThreeTimes(27, 0));
    }


    static Optional<Double> divideThreeTimes(double a, double b){
        return safeDivision(a, b)
                .flatMap( x -> safeDivision(x, b))
                .flatMap( x -> safeDivision(x, b));
    }

    static Optional<Double> divideThreeTimesVeryPoor(double a, double b){
        Optional<Double> result = safeDivision(a, b);
        if(result.isPresent()){
            a = result.get();
        } else {
            return Optional.empty();
        }
        result = safeDivision(a, b);
        if(result.isPresent()){
            a = result.get();
        } else {
            return Optional.empty();
        }
        return safeDivision(a, b);
    }

    static Optional<Double> divideThreeTimesPoor(double a, double b){
        Optional<Double> resultOne = safeDivision(a, b);
        if(resultOne.isPresent()){
            Optional<Double> resultTwo = safeDivision(resultOne.get(), b);
            if(resultTwo.isPresent()){
                return safeDivision(resultTwo.get(), b);
            }
        }
        return Optional.empty();
    }

    static Optional<Double> safeDivision(double a, double b){
        if(b == 0){
            return Optional.empty();
        } else {
            return Optional.of(a/b);
        }
    }
}
