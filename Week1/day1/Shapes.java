interface ShapeArea{
   
    public double area();
    // double perimeter();
}
interface ShapePerimeter{
    double perimeter();
}
class Circle implements ShapeArea, ShapePerimeter{
    private double x;
    public Circle(double x){
        this.x = x;
    }
    @Override
    public double area(){
        return Math.PI*x*x;
        // return Math.PI*a*x;
    }
    @Override
    public double perimeter(){
        return 2*Math.PI*x;
    }
}
class Rectangle implements ShapeArea, ShapePerimeter {
    private double x, y;

    public Rectangle(double x, double y) {
        this.x=x; this.y=y;
    }
    @Override
    public double area(){
        return x*y;
    }
    @Override
    public double perimeter(){
        return 2*(x+y);
    }
}

class Triangle implements ShapeArea, ShapePerimeter{
    private double x,y,z;

    public Triangle(double x,double y,double z) {
        this.x=x; this.y=y;this.z=z;
    }
    @Override
    public double area(){
        return (x*y)/2;
    }
    @Override
    public double perimeter(){
        return x+y+z;
    }
}

public class Shapes {
    public static void main(String[] args) {
        ShapeArea ob = new Circle(3);
        ShapePerimeter ob1 = new Circle(3);

        System.out.println(ob.area());
        System.out.println(ob1.perimeter());
        
        ob = new Rectangle(2, 3);
        ob1 = new Rectangle(2, 3);
        
        System.out.println(ob.area());
        System.out.println(ob1.perimeter());
        
        ob = new Triangle(2, 3, 4);
        ob1 = new Triangle(2, 3, 4);
        
        System.out.println(ob.area());
        System.out.println(ob1.perimeter());

        System.out.println("Thank You");
    }
}