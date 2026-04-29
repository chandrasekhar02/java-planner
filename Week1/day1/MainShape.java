public class MainShape {
    public static void main (String []args){
        MultipleShapes Shape= new MultipleShapes();
        MultipleShapes circle = new Cirlce();
        MultipleShapes sqaure= new Sqaure();
        Triangl triangle = new Triangl();
        

        Shape.area();
        sqaure.area();
        circle.area();
        triangle.area();

    }
}
