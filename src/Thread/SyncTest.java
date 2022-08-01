package Thread;

class Bank{
    private int money = 10000;

    public synchronized void saveMoney(int plus){
        int m = getMoney();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setMoney( m + plus );
    }

    public void minusMoney(int minus){
        synchronized (this) {
            int m = getMoney();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setMoney(m - minus);
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
class Parent extends Thread{
    @Override
    public void run() {
        synchronized (SyncTest.bank){
            System.out.println("parents save money");
            SyncTest.bank.saveMoney(3000);
            System.out.println("saveMoney(3000):" + SyncTest.bank.getMoney());
        }
    }
}
class Child extends Thread{
    @Override
    public void run() {
        System.out.println("child minus money");
        SyncTest.bank.minusMoney(1000);
        System.out.println("minusMoney(1000):" + SyncTest.bank.getMoney());
    }
}

public class SyncTest {
    public static Bank bank = new Bank();

    public static void main(String[] args) {
        Parent p = new Parent();
        p.start();

        Child c = new Child();
        c.start();


    }
}
