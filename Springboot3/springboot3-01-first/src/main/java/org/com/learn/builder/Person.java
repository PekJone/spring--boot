package org.com.learn.builder;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  17:16
 */

/**
 * 建造者模式
 */
public class Person {
    /**
     * 一般建造者模式的bean的属性用final修饰
     */
    private final String name;

    private final int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //提供一个私有的全残构造函数
    private Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }


    public static PersonBuilder builder(){
        return new PersonBuilder();
    }
    //静态内部类
    public static class PersonBuilder{
        private String name;
        private int age;
        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age){
            this.age= age;
            return this;
        }

        public Person build(){
            return new Person(name,age);
        }

    }

}
