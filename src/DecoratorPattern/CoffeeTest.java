package DecoratorPattern;

public class CoffeeTest {
    public static void main(String[] args) {
        Coffee brasil = new BrasilEspresso();
        brasil.brewing();
        Coffee brasilLatte = new CaffeLatte(brasil);
        brasilLatte.brewing();

        Coffee brasilMocha = new CaffeMocha(new CaffeLatte(brasil));
        brasilMocha.brewing();
    }
}
