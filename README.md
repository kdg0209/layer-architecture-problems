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

<br><br>

#### 같이 읽어보면 좋은 글

- https://github.com/kdg0209/realizers/blob/main/%EC%9E%90%EB%B0%94%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20%EC%8B%A4%EC%9A%A9%EC%A3%BC%EC%9D%98%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/10%EC%9E%A5%20%EB%8F%84%EB%A9%94%EC%9D%B8.md
- https://github.com/kdg0209/realizers/blob/main/%EC%9E%90%EB%B0%94%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20%EC%8B%A4%EC%9A%A9%EC%A3%BC%EC%9D%98%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/14%EC%9E%A5%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EB%8C%80%EC%97%AD.md
- https://kdg-is.tistory.com/entry/Layered-Architecture


