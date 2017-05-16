/**
 * Created by Sebastian on 16.05.2017.
 * this:
 *      referencja do parametru niejawnego
 *      wywołanie innego konstruktora tej samej klasy
 * super:
 *      wywołanie metody nadklasy
 *      wywołanie konstruktora nadklasy
 */
public class TestJavaCore1 {
    public static void main(String[] argc) throws InterruptedException {
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

        KonstruktorCheck2 tmp4 = new KonstruktorCheck2(1, "test", true, tmp2); // Konstruktor domyślny niedostępny bo zdefiniowano inny
        System.out.println("\n Second \n int: " + tmp4.getVal_int_in_second());
        System.out.println("string: " + tmp4.getVal_string_in_second());
        System.out.println("bool: " + tmp4.getVal_bool_in_second());
        System.out.println("objekt: " + tmp4.getVal_Point_in_second());

        Upper tmp5 = new Upper("Adam", 25, 2);
        Downer tmp6 = new Downer("ala", 22, 5, 31, 99);

        Upper tmp7 = new Downer("alan", 22, 2, 25, 99);       // ok - tam gdzie obiekt nadklasy, tam też i podklasy (ale nie odwrotnie)
        //Downer tmp8 = new Upper("akka", 20, 8);                               // nie można

        /* Jak działa polimorfizm */
        Downer down = new Downer("down", 12, 12, 12, 21);
        Upper[] many = new Upper[2];
        many[0] = down; // down
        many[1] = tmp5; // up

        for(int i = 0; i<many.length; i++){
           // System.out.println("many[" + i + "]: " + many[i].getClass());
            System.out.printf("\nmany[%d]: %s", i, many[i].getClass());
        }
        /* year should be 12 */
        System.out.println("\n"+many[0].getYear());     // zwiększone o 100
        System.out.println(many[1].getYear());         // jak podane            patrz na funkcję w klasie up i przesłoniętą w klasie down

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

class Upper{
    private String name;
    private int year;
    private int month;
    public Upper(){                                  // konstruktor domyślny
        this.name = "";
        this.year = 0;
        this.month = 0;
    }
    public Upper(String name, int year, int month){
        this.setName(name);                         // ustawianie przez settera
        this.year = year;                           // lub ustawianie na sztywno
        this.month = month;
    }
    public Upper(int Compare){
        this("a", 2, 4);        // wywołanie innego konstruktora tej samej klasy
        this.year = Compare;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public String getName(){
        return this.name;
    }
    public int getYear(){
        return this.year;
    }
    public int getMonth(){
        return this.month;
    }
}

class Downer extends Upper{
    private int day;
    private int specyficData;
    public Downer(String name, int year, int month, int day, int specyficData){
        super(name, year, month);                               // jeśli konstruktor podklasy nie wywołuje jawnie konstruktora nadklasy, wywołyuwany jest domyślny konstruktor nadklasy
        this.day = day;
        this.specyficData = specyficData;
    }
    public int getYear(){
        int tempYear = super.getYear();                         // drugie zastosownie 'super' metoda z nadklasy
        return tempYear + 100;
    }
    public int getSpecyficData(){
        return this.specyficData;
    }
}

/**
 *
 Value*3 przed metodą void: 25.0
 Wewnątrz metody: 75.0
 Value po metodzie: 25.0

 Value*3 przed metodą float: 25.0
 Value w metodzie float75.0
 Value po metodzie float: 25.0

 Value przed metodą (obiekt): 30.0
 Koniec metody (obiekt): 90.0
 Value po metodzie (obiekt): 90.0

 First
 int: 0
 string: null
 bool: false
 objekt: null

 Second
 int: 1
 string: test
 bool: true
 objekt: Point@1540e19d
 */