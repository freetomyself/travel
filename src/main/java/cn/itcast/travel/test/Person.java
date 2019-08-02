package cn.itcast.travel.test;

/**
 * @program: travel--cn.itcast.travel.test
 * @author: WaHotDog 2019-07-18 15:28
 **/


public class Person {
    public void eat(){
        System.out.println("eat...");
    }
    public Person(){

    }
    public Person( String name,int id) {
        this.id = id;
        this.name = name;
    }

    private int id ;
    private String name;

    public String a;
    public String b;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
