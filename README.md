# Layer-Architecture Problems

- 이 글은 가장 흔히 사용되고 있는 레이어드 아키텍처에 관한 글이며, 어떻게 레이어드 아키텍처를 효율적으로 사용할 수 있을까에 대한 휴리스틱한 내용입니다.

<br>

## 레이어드 아키텍처란 무엇인가?

- 레이어드 아키텍처란 아래 그림에서 볼 수 있듯이 모든 요청은 위에서 아래로 흐르는 단방향 아키텍처입니다. 여기서 중요한 점은 '단방향' 입니다. 의존성이 역전되면 양방향이 되니 주의가 필요합니다.

![스크린샷 2024-08-07 오후 10 16 05](https://github.com/user-attachments/assets/5a1753b3-8223-4c87-b5a5-5e6aa0af405d)

<br><br>

## 계층형 Vs 도메인형

#### 계층형 레이어드 아키텍처

- 계층형은 레이어드 아키텍처를 폴더처럼 만들어서 사용하는 경우입니다. 이는 아키텍처가 아닙니다. 단순 각 컴포넌트들을 분류에 맞게 배치한 것일뿐입니다.
- 물론, 프로젝트의 규모가 작다면 해당 방법을 사용해도 되긴하지만 프로젝트는 언제 어느순간에 커질지모르기 때문에 최소한의 대비정도는 해야한다고 생각합니다.

```text
├── controller
│   ├── Membercontroller
│   └── Postcontroller
├── service
│   ├── MemberService
│   └── PostService
└── infrastructure
    └── Aws
```

#### 도메인형 레이어드 아키텍처

- 도메인형은 도메인을 기준으로 나눈것입니다.

```text
├── member
│   ├── controller
│   ├── domain
│   ├── dto
│   ├── repository
│   └── service
├── post
│   ├── controller
│   ├── domain
│   ├── dto
│   ├── repository
│   └── service
├── global
│   ├── exception
│   ├── utils
│   └── config
└── infrastructure
    └── Aws
```

#### 💡 장점

- 도메인별로 구분이 되어 응집도를 높일 수 있습니다.
- 생명주기가 같은 객체들을 묶을 수 있습니다.

#### 🤔 단점

- 모호한 도메인들이 생길 수 있습니다. 모호한 도메인을 어디에 위치시켜놔야 하는지 고민점이 생기게됩니다.

<br>

## 그래서 어떤 아키텍처가 좋을까?

- 도메인형 아키텍처가 좋다고 느낄 수 있지만 실은 둘 다 아닙니다. 도메인형 아키텍처와 헥사고날 아키텍처의 장점을 조합하여 문제를 해결하면 지속 가능한 소프트웨어를 만들 수 있습니다.
- DIP 원칙을 적용한다면 더 좋은 아키텍처를 가질 수 있습니다.

#### DIP 원칙을 적용시켜보자

- application layer에 위치하고 있는 Service 클래스들은 JPARepository나 Infrastructure에 위치하고 있는 컴포넌트들에 직접적으로 접근하지 않습니다. 중간에 인터페이스를 통해 접근을 하게됩니다.
- 우리가 해결하야하는 본질적인 문제는 비지니스를 어떻게 풀어낼까? 입니다. DIP를 적용함으로써 우리는 저수준 레벨을 조금이지만 덜 신경쓰고, 본질적인 문제에 집중할 수 있습니다. 또한 Service 클래스를 테스트하기 위해서 충분히 Mocking을 할 수 있습니다. 그것이 Stub이든 Fake든 말이죠.

![스크린샷 2024-08-07 오후 10 35 41](https://github.com/user-attachments/assets/62b7edb7-de6a-46cb-a258-3b70923be672)

<br>

#### 🤔 왜 DIP 원칙을 적용하는걸까?

- SOLID 원칙 중에서 왜 DIP 원칙을 적용하는걸까요?
- DIP 원칙을 적용함으로써 서비스에 대한 테스트 코드를 조금 더 유연하게 구성할 수 있을 뿐만 아니라, 인프라스트럭처에 강하게 의존하여 서비스가 침범되는 상황을 방지할 수도 있습니다.

<br>

#### 🧐 왜 Controller - Service 사이에는 DIP를 적용하지 않는거야?

- 위 사진에서 controller와 service 사이에는 DIP를 적용하지 않았습니다. 왜 일까요? 물론 아래 그림처럼 적용할수는 있지만 그닥 장점이 없다고 생각합니다.
- 이유
    1. Application 로직이 변경된다면 그것은 새로운 요구사항입니다.
    2. DIP를 적용함으로써 복잡성만 증가될 우려가 있습니다.

<img width="1032" alt="스크린샷 2024-08-07 오후 10 41 45" src="https://github.com/user-attachments/assets/5f124012-e98b-4db7-86ef-fc59fd478802">

<br>
<br>

## 정리

- 위 글의 내용과 예제 코드를 정리해봅시다.

```text
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── layer_architecture_problems
    │   │               ├── Application.java
    │   │               ├── domain
    │   │               │   ├── member
    │   │               │   │    ├── controller
    │   │               │   │    ├── dao
    │   │               │   │    ├── domain
    │   │               │   │    ├── dto
    │   │               │   │    ├── infrastructure
    │   │               │   │    ├── util
    │   │               │   │    └── service
    │   │               │   └── post
    │   │               │   │    ├── controller
    │   │               │   │    ├── dao
    │   │               │   │    ├── domain
    │   │               │   │    ├── dto
    │   │               │   │    ├── infrastructure
    │   │               │   │    ├── util
    │   │               │   │    └── service
    │   │               ├── global
    │   │               │   ├── config ----------- 설정 파일
    │   │               │   ├── exception -------- 예외 처리
    │   │               │   ├── properties ------- yml 데이터 의존성 주입
    │   │               │   └── utils ------------ 공통 쿨래스
    │   │               └── infrastructure
    │   │                   ├── alimtalk -- 공통으로 사용되는 알림톡
    │   │                   └── mailer ---- 공통으로 사용되는 메일
    │   └── resources
    │       ├── application-*.yml
    │       └── message.properties
```

--- 

## 레이어별 특징

#### ~ Controller

1. 컨트롤러는 비지니스 로직을 수행할 서비스에게 위임합니다.

#### ~ Service

1. 도메인에게 비지니스 로직을 위임하며, 인터페이스를 통해 외부 API와 협력한다.
2. 외부 인터페이스와 통신할 때 interface를 통해서만 의사소통한다.
    - Dao 등과 같은 인터페이스 사용 

#### ~ Dao

1. 데이터베이스에서 도메인을 조회할 수 있도록 도와주는 인터페이스입니다.

#### ~ Domain

1. 비지니스 로직을 수행하는 도메인 객체를 정의한다.

#### ~ Dto

1. In/Out 스펙을 정의하는 클래스다.

#### ~ Infrastructure

1. 각 도메인별로 데이터 베이스를 조회하는 클래스들이 모여있는 곳입니다.
2. 해당 디렉토리에 있는 클래스 및 인터페이스의 접근 제한자는 default로 설정로 설정하며 DAO를 통해서만 외부로 접근할 수 있습니다.

#### ~ Util

1. 해당 도메인에서 공통적으로 사용하는 클래스를 정의한다.

--- 

### Global

#### ~ Config

1. 설정 파일을 정의한다.

#### ~ Exception

1. 예외 처리에 관련된 클래스를 정의한다.

#### ~ Properties

1. @Value 어노테이션 대신 @ConfigurationProperties 어노테이션을 사용하여 yml에 정의되어 있는 값을 읽어 얼 수 있도록 한다.

#### ~ Utils

1. 프로젝트에서 공통적으로 사용하는 클래스를 정의한다.

---

### Infrastructure

1. 공통적으로 사용되는 외부 API와 연동하는 모듈을 정의합니다.
2. 서비스 레이어에서는 인터페이스를 통해 인프라 코드에 접근합니다.
3. 인프라 레이어의 서비스 클래스 접근 제한자는 public이 아닌 default로 정의합니다.

<br>

## 리뷰 후 의견들

### 1. 🧐 DTO는 어디서 변환해야하는가?

- 즉 DTO와 Entity는 어느 계층에서 변환되어야 하는가이다

#### 1. 서비스 레이어에서 변환해야하는 이유 (개인적으로 선호)

- 서비스 레이어에서 변환하는 방법, 컨트롤러에서 변환하는 방법, Mapper를 사용하는 방법이 있는데 나는 서비스 레이어에서 변환하는 방법을 선호하고 그 이유는 아래와 같다.
- 이유
  1. 클라이언트에게 반환되는 DTO에 있는 정보들은 하나의 Entity의 정보만으로는 부족합니다. 예를들어 회원 정보를 반환하는 DTO에 Member Entity가 주로 사용되지만 DTO안에 권한이라던가, 회원이 설정한 알림 여부 설정 정보들을 반환해야할 때 이것은 어느 계층에서 조합해야 하는가?
  나는 서비스 레이어에서 권한 엔티티, 회원별 알림 설정 정보 엔티티를 조회하고 서비스 레이어에서 조합해야 한다고 생각한다. 즉, 
  하나의 DTO에 포함될 정보가 하나의 도메인으로 해결되지 않는 경우 다른 서비스나 도메인을 사용하게 되면 서비스 로직이 컨트롤러에 포함될 여지가 있지 않을까?
  2. 마틴 파울러도 서비스 계층에서 DTO로 변환하는 것을 제시한다. 간단하게 말하면 비지니스 로직을 캡슐화하고, 트랜잭션을 제어하고, 작업 구현 시 응답을 조정한다고 한다.
     https://martinfowler.com/eaaCatalog/serviceLayer.html
  3. 컨트롤러와 서비스에 강결합이 발생한다고 생각할 수도 있다. 하지만 나의 생각은 컨트로러와 서비스는 하나의 역할과 책임만 가지고 있어야 한다고 생각한다. 하나의 Http request는 하나의 요구사항을 만족해야하며 그럼 느슨한 결합을 추구할 필요가 있는가? 나는 없다고 본다.

#### 2. Mapper를 사용하는 방법

- 우아한 기술블로그(https://techblog.woowahan.com/2711/)를 보면 mapper를 사용하는 방식이 나온다. 
- 이 방법은 클라이언트가 요청받은 DTO와 서비스가 필요로하는 DTO를 나눈 방식이다.
예를들어 회원가입을 한다면 클라이언트는 MemberCreateRequest 클래스를 활용하여 데이터를 입력하고, 컨트롤러에서는 서비스가 필요로하는 데이터로 변환해서 넘겨야 한다.
  https://sedangdang.tistory.com/296 이런식이다.
- 이 방법은 어떻게 보면 타당하고 괜찮은 방법인거 같다. 하지만 컨트롤러에서 사용하는 DTO와 서비스에서 사용하는 DTO의 속성이 모두 같다면 중복된 클래스가 2개 만들어지는건데 의미가 있을까 싶다. 물론 대규모 아키텍처라면 의미가 있을지도?

#### 3. 컨트롤러에서 DTO를 Entity로 변환하고, Service에는 엔티티를 넘기는 방법

- 우선 장점으로는 서비스는 특정 DTO에 얽매이지 않으므로 재사용성이 높아진다고 하는데, 서비스를 무슨 목적을 가지고 재사용하는 건가? 개인적으로 와닿지 않는다.
- 단점으로는 특정 DTO만으로 어떻게 온전한 엔티티를 만들것인가? 그리고 DTO의 여러 속성으로 여러 엔티티로 만들거나 엔티티 관계를 정립해야하는데 어떻게 할 것인가?  
- 개인적인 생각으로 DTO의 여러 속성으로 복잡한 엔티티를 만드는 것 자체가 비지니스 로직인데 이 비지니스 로직을 컨트롤러에 위치시키는 것인가?

<br>

### 2. 🧐 DAO를 통해서 XXXRepository를 사용하거나 QueryDSL을 사용하는게 맞을까?

- 우선 예제 코드를 보면 Service에서는 xxxDao를 호출함으로써 특정 엔티티 레포지토리나 QueryDSL로 작성한 메서드에 접근할 수 있습니다.
- Service에서 xxxRepository를 선언하여 간단한 메서드를 호출할 수도 있고, xxxRepository 인터페이스에 CustomXXX를 extends하여 CustomXXXImpl 클래스에서 CustomXXX를 구현하여 사용할 수도 있습니다.

#### 위 질문사항에 대한 제 생각들은 이렇습니다.

1. 서비스 레이어에서 특정 xxxRepository를 선언하여 메서드를 호출할 수 있지만 간단한 findById가 아니라 findByIdAndStatusFalse 이러한 메서드를 호출할 수도 있습니다. 이 메서드는 어떤 목적을 가지고, 왜 호출하는 것일까요? 저는 메서드명에는 목적이 내포되어 있어야하며, 메서드명으로 이러한 이유가 있구나라는 것을 도출할 수 있어야한다고 생각하는데 예시로 들었던 findByIdAndStatusFalse는 이러한 맥락을 파악하기에는 힘들다고 생각합니다.
2. 그리고 xxxRepository 인터페이스에 CustomXXX를 extends하여 CustomXXXImpl 클래스에서 CustomXXX를 구현하여 사용하는 것은 오히려 더 복잡성만 증가시키는 것이라고 생각합니다. 
3. 그럼 xxxDao를 사용하면 어떻게 될까? 진입점은 하나가 되고, xxxDao에 메서드 시그니처를 작성하고 해당 xxxDao를 구현한 xxxDaoImpl은 모든 메서드 시그니처를 구현해줘야 합니다. 우리는 repository에 있는 findById, save 메서드를 더 간편하게 사용할 수 있는데 이 두 메서드 시그니처를 구현해줘야하는게 같은걸 반복하는 작업 아니야? 라고 생각할 수 있습니다. 하지만 이렇게 사용함으로써 메서드 시그니처명에 조금 더 생각하게되고 의미없는 findByIdAndStatusFalse와 같은 메서드명을 안 만들게 되지 않을까요? 그리고 어디서는 repository를 의존하고 있고, 또 어디서는 repository와 customRepository를 함께 의존하고 있고, 또 어디서는 customRepository만을 의존하고 있고 이러한 상황은 저는 규칙성이 없다고 생각합니다. 팀 내부 조직은 이러한 규칙을 명학하게 세우고 헷갈리지 않게 해야한다고 생각합니다.  
또한 데이터 베이스에 관련된 모든 쿼리는 하나의 클래스 파일에 있으므로 응집도가 높아진다고 생각합니다.

<br><br>

#### 같이 읽어보면 좋은 글

- https://github.com/kdg0209/realizers/blob/main/%EC%9E%90%EB%B0%94%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20%EC%8B%A4%EC%9A%A9%EC%A3%BC%EC%9D%98%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/10%EC%9E%A5%20%EB%8F%84%EB%A9%94%EC%9D%B8.md
- https://github.com/kdg0209/realizers/blob/main/%EC%9E%90%EB%B0%94%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20%EC%8B%A4%EC%9A%A9%EC%A3%BC%EC%9D%98%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/14%EC%9E%A5%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EB%8C%80%EC%97%AD.md
- https://kdg-is.tistory.com/entry/Layered-Architecture


