package DecoratorPattern;

// 데코레이터는 기반클래스 혹은 그 클래스를 상속받은 클래스를 가져야한다.
// Decorator 상속용 상위 클래스
public abstract class Decorator extends Coffee{
    Coffee coffee;

    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void brewing() {

    }
}
