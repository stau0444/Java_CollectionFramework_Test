package IOStream.Serialization;

import java.io.*;

class Person implements Serializable{
    String name;
    String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public String toString() {
        return name + ", "+job;
    }
}


public class SerializationTest {
    public static void main(String[] args) {
        Person ugo = new Person("ugo", "cook");
        Person gogo = new Person("gogo", "programmer");


        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serial.txt"));){
            oos.writeObject(ugo);
            oos.writeObject(gogo);

        }catch (IOException e){
            e.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serial.txt"));){
            Person o = (Person)ois.readObject();
            Person g = (Person)ois.readObject();
            System.out.println("o.toString() = " + o.toString());
            System.out.println("g.toString() = " + g.toString());
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
