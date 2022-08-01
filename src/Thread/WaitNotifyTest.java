package Thread;

import java.util.ArrayList;
import java.util.List;

class Library{
    public List<String> books = new ArrayList<>();

    public Library() {
        books.add("book1");
        books.add("book2");
//        books.add("book3");

    }
    public synchronized String getBook() throws InterruptedException {
        while(books.size() == 0){
            System.out.println(Thread.currentThread().getName() + "기다리는 중");
            wait();
            System.out.println(Thread.currentThread().getName() + "기다리기 끝");
        }
        if(books.size() > 0){
            String book = books.remove(0);
            String name = Thread.currentThread().getName();
            System.out.println(name+" lend book = " + book);
            return book;
        }else {
            return  null;
        }
    }
    public synchronized void returnBook(String book){
        String name = Thread.currentThread().getName();
        books.add(book);
        notifyAll();
        System.out.println(name+" return book = " + book);
    }
}

class Student extends Thread{
    public Student(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            String book = WaitNotifyTest.library.getBook();
            if(book == null) {
                System.out.println(getName() + ", 책을 빌리지 못함 ");
                return;
            }
            sleep(5000);
            WaitNotifyTest.library.returnBook(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class WaitNotifyTest {
    public static Library library = new Library();

    public static void main(String[] args) {
        Student std1 = new Student("std1");
        Student std2 = new Student("std2");
        Student std3 = new Student("std3");
        Student std4 = new Student("std4");
        Student std5 = new Student("std5");
        Student std6 = new Student("std6");
        std1.start();
        std2.start();
        std3.start();
        std4.start();
        std5.start();
        std6.start();
    }
}
