
public class test {
    public static void main(String[] args) {
        Transportation feet = new Feet("feet", "flip flops");
        Person p1 = new Person("Johnny", feet);
        Location location = new Location("CoC", 57.23, 35.12);
        Location newLocation  = new Location("CULC", 30, 30.12);
        Transportation bike =  new Bicycle("bike", "blue");
        Person p2 = new Person("Robert", location);
        Person p3 = new Person("Bobby", newLocation, feet);
        Person p4 = new Person("Nick");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p2.moveTo(newLocation));
        System.out.println("feet make noise: " + feet.makeNoise());
        System.out.println("bike make noise: " + bike.makeNoise());
        p2.setTransportation(bike);
        System.out.println(p2.moveTo(newLocation));
        System.out.println(p2);
        p2.getTransportation().setName("foot-foot");
        System.out.println(p2.getTransportation().getName());
    }
}
