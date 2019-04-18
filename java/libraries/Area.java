public class Area {
    public double ofTriangle(float base, float height){
        return 0.5 * base * height;
    }

    public double ofSquare(float side){
        return side * side;
    }

    public double ofCircle(float radius){
        return 3.14 * radius * radius;
    }

    public double ofParallelogram(float base, float height){
        return base * height;
    }
}
