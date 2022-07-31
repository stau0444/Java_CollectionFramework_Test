### Decorator pattern

- 자바의 입출력 스트림은 decorator pattern 으로 구현되어 있다.
- 기반클래스(component)가 여러 decorator들을 활용하여 다양한 기능을 제공받음
- 상속 보다 유연한 구현방식
- 데코레이터는 다른 데코레이터나 또는 컴포넌트를 포함해야한다.
- 지속적인 기능의 추가와 제거가 용이하다.
- decorator와 component는 동일한 것이 아니다 (기반 스트림 클래스가 직접 읽고 쓸수 없다 , 보조클래스는 추가적인 제공만 제공)

<img width="637" alt="스크린샷 2022-07-31 오후 4 38 32" src="https://user-images.githubusercontent.com/51349774/182015416-b10e0016-821b-41a3-94af-d4c1442a1cd4.png">


