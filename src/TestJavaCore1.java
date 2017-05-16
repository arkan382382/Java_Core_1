/**
 * Created by Sebastian on 16.05.2017.
 */
public class TestJavaCore1 {
    public static void main(String[] argc){
        /* Metody nie modyfikują parametrów liczbowych (both void & float) */
        float tmp1 = 25;
        System.out.println("Value*3 przed metodą void: " + tmp1);
        tripleValue(tmp1);
        System.out.println("Value po metodzie: " + tmp1);

        System.out.println("\nValue*3 przed metodą float: " + tmp1);
        System.out.println("Value w metodzie float" + tripleValueFloat(tmp1));
        System.out.println("Value po metodzie float: " + tmp1);

        /* Metody zmieniają stan parametrów będących obiektami */
        Point tmp2 = new Point(30);
        System.out.println("\nValue przed metodą (obiekt): " + tmp2.getValueInClassPoint());
        tripleValueFromObiect(tmp2);
        System.out.println("Value po metodzie (obiekt): " + tmp2.getValueInClassPoint());

        /* Dwie klasy, jedna z domyślnym konstruktorem druga z zaprojektowanym */
        KonstruktorCheck1 tmp3 = new KonstruktorCheck1(); // konstruktor domyślny dostępny
        System.out.println("\n First \n int: " + tmp3.getVal_int_in_first());
        System.out.println("string: " + tmp3.getVal_string_in_first());
        System.out.println("bool: " + tmp3.getVal_bool_in_first());
        System.out.println("objekt: " + tmp3.getVal_Point_in_first());

        KonstruktorCheck2 tmp4 = new KonstruktorCheck2(1, "test", true, tmp2); // Konstruktor domyślny niedostępny
        System.out.println("\n Second \n int: " + tmp4.getVal_int_in_second());
        System.out.println("string: " + tmp4.getVal_string_in_second());
        System.out.println("bool: " + tmp4.getVal_bool_in_second());
        System.out.println("objekt: " + tmp4.getVal_Point_in_second());


    }
    public static void tripleValue(float x){
        x = 3 * x;
        System.out.println("Wewnątrz metody: " + x);
    }
    public static float tripleValueFloat(float x){
        x = 3 * x;
        return x;
    }
    /* Metoda zmienia stan obiektu poprzez kopię referencji do niego */
    public static void tripleValueFromObiect(Point x){
        x.raiseValue();
        System.out.println("Koniec metody (obiekt): " + x.getValueInClassPoint());
    }
}

class Point{
    private float valueInClassPoint;
    public Point(float valueInClassPoint){
        this.setValueInClassPoint(valueInClassPoint);
    }
    public void raiseValue(){
        this.valueInClassPoint = this.valueInClassPoint*3;
    }
    public void setValueInClassPoint(float valueInClassPoint){
        this.valueInClassPoint = valueInClassPoint;
    }
    public float getValueInClassPoint(){
        return this.valueInClassPoint;
    }
}

/* Klasa bez konstruktora */
class KonstruktorCheck1{
    private int val_int_in_first;
    private String val_string_in_first;
    private boolean val_bool_in_first;
    private Point val_Point_in_first;

    public int getVal_int_in_first(){               // ustaione na 0
        return this.val_int_in_first;
    }
    public String getVal_string_in_first(){         // ustawione na null
        return val_string_in_first;
    }
    public boolean getVal_bool_in_first(){          // ustawione na false
        return val_bool_in_first;
    }
    public Point getVal_Point_in_first(){           // ustawione na null
        return this.val_Point_in_first;
    }
}

/* Klasa z konstruktorem */
class KonstruktorCheck2{
    private int val_int_in_second;
    private String val_string_in_second;
    private boolean val_bool_in_second;
    private Point val_Point_in_second;

    public KonstruktorCheck2(int val_int_in_second, String val_string_in_second, boolean val_bool_in_second, Point val_Point_in_second){
        this.val_int_in_second = val_int_in_second;
        this.val_string_in_second = val_string_in_second;
        this.val_bool_in_second = val_bool_in_second;
        this.val_Point_in_second = val_Point_in_second;
    }

    public int getVal_int_in_second(){               // ustaione w main
        return this.val_int_in_second;
    }
    public String getVal_string_in_second(){         // ustawione w main
        return val_string_in_second;
    }
    public boolean getVal_bool_in_second(){          // ustawione w main
        return val_bool_in_second;
    }
    public Point getVal_Point_in_second(){           // ustawione w main
        return this.val_Point_in_second;
    }
}