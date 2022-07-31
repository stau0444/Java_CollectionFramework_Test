package DecoratorPattern;

public class CaffeMocha extends Decorator{
    public CaffeMocha(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void brewing() {
        super.brewing();
        System.out.println("add mocha");
    }
}
