package DecoratorPattern;

public class CaffeLatte extends Decorator{
    public CaffeLatte(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void brewing() {
        super.brewing();
        System.out.println("Add Milk");
    }
}
