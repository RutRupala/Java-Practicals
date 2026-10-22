public class Person {
    @Column("name") String name;
    @Column("age") int age;
    @Column("city") String city;
    @Override public String toString() { return "Person[name=" + name + ", age=" + age + ", city=" + city + "]"; }
}
