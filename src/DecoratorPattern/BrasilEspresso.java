package DecoratorPattern;

public class BrasilEspresso extends Coffee{
    @Override
    public void brewing() {
        System.out.println("brasil espresso");
    }
}
