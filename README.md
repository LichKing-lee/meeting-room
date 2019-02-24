### 사용기술
- java 11
- spring boot 2
- mybatis
- h2db
- spring restdocs
- jquery
- jquery full calendar

### Getting Started
- jdk 11 필수

```
./gradlew clean bootJar
java -jar -Dserver.port=8020 build/libs/meeting-room-*.jar
```

### API Document
http://localhost:8080/docs/index.html

### 스펙 구현
- 명시적을 나와있는 스펙구현은 아래 테스트로 확인할 수 있습니다.

|스펙|테스트|
|---|---|
|30분 단위 정시예약|ReservationControllerIntegTest#예약시간이_30분_단위가아니면_예약불가|
|반복예약|ReservationControllerIntegTest#정상_반복_예약|
|시간이겹치면 예약불가|ReservationControllerIntegTest#이미_예약된_회의실은_예약불가|
|이전회의실 종료시간과 다음회의실 시작시간이 같으면 예약가능|ReservationControllerIntegTest#이전예약_종료시간과_다음예약_시간시간이같으면_예약가능|

### 구현중 고민거리들 (편하게 나열)
- Mybatis 를 써놓고 구현은 JPA 형태로 하는바람에 그 사이에서 고통스러움
  - JPA로 바꿀까 했지만 이런저런 현실세계의 이슈로 그냥 진행
- 회의실 예약을 meetingRoom 도메인에서 처리하는게 나을지 reservation 도메인에서 처리할지 고민
  - 구현은 reservation에서 하긴했는데 이걸 쓰면서 생각해보니 meetingRoom이 나은것 같기도하고..
- 프론트 페이지 구현
  - API개발만 맡다가 너무 오랜만에 프론트 작업이라 어떻게할지 굉장히 고민(코딩테스트 그냥 포기할까까지)
  - 유행하는 프레임워크들을 쓸까 하다가 일만 키우는것 같다는 생각에 우리의 친구 jquery 사용
- API Document까지 제공하면 좀 더 잘봐주지않을까 하는 맘에 restdocs 적용
- 평소에 객체지향에 대한 관심이 많고, 최대한 객체지향적으로 코드를 작성하려고 노력하는 편인데 얼마 되지않는 코드에 어설프게 이것저것해서 오히려 읽기어려워진게 아닌가 하는 걱정이있음
- java 11로 짜놓고 11에서 지원하는걸 아무것도 쓰지않은 죄책감(var 라도 한번 쓸걸)