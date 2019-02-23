### 사용기술
- java 11
- spring boot 2
- mybatis
- h2db

### Getting Started
```
./gradlew clean bootJar
java -jar -Dserver.port=8020 build/libs/meeting-room-*.jar
```

### Document
http://localhost:8080/docs/index.html

### 스펙 구현
- 명시적을 나와있는 스펙구현은 아래 테스트로 확인할 수 있습니다.

|스펙|테스트|
|---|---|
|30분 단위 정시예약|ReservationControllerIntegTest#예약시간이_30분_단위가아니면_예약불가|
|반복예약|ReservationControllerIntegTest#정상_반복_예약|
|시간이겹치면 예약불가|ReservationControllerIntegTest#이미_예약된_회의실은_예약불가|
|이전회의실 종료시간과 다음회의실 시작시간이 같으면 예약가능|ReservationControllerIntegTest#이전예약_종료시간과_다음예약_시간시간이같으면_예약가능|