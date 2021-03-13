# JPA data.sql 사용하기

- data.sql spring context를 실행시킬 때, data.sql에 적혀있는 쿼리를 실행시킴 
- main/resources에 +선언하게 되면, 서버를 시작할 때 쿼리가 실행됨 
- test/resources에 선언하게 되면, 테스트를 실행할 +때 쿼리가 실행됨



# JPA Relation



- #### cascade

- +cascade 관련된 entity의 영속성을 함께 관리할 수 있도록 해줌
- +CascadeType.PERSIST 

​	-insert할 경우 관련 entity도 함께 insert함

+CascadeType.MERGE 

​	-update할 경우 관련 entity도 함께 update함

+CascadeType.REMOVE 

​	-delete할 경우 관련 entity도 함께 delete함

+CascadeType.ALL 

​	-모든 케이스에 대해 영속성을 함께 관리함

- #### orphanRemoval 

+관련 entity의 relation이 사라질 때, entity를 함께 삭제해줌

- #### fetch 

+FetchType.EAGER 항상 relation이 있는 entity를 join하여 값을 함께 가져옴

+FetchType.LAZY 해당 객체가 필요한 시점에 id를 통해 새로 select해서 값을 가져옴

# JPA @ Query

@Embedded 다른 객체를 Entity의 속성으로 가져옴

@Embeddable 자기 객체를 다른 Entity의 속성으로 사용할 수 있음

@Query Naming 컨벤션에 따라 생성되는 쿼리가 아니라, 커스터마이징된 쿼리를 직접 지정하여 생성함 ?1 (숫자)를 통해서 parameter를 순서대로 주입하여 사용할 수 있음

@Param("A")으로 parameter를 받게 되면, :A로 파라미터를 주입하여 사용할 수 있음 nativeQuery=true를 사용하게 되면, JPQL이 아니라 써준 그대로 네이티브 쿼리를 생성해줌

@Valid 해당 객체의 유효성을 검토하겠다는 것을 의미함



# controller 사용

## @GetMapping 

- @GetMapping
  - Get 메소드의 Http 요청을 받을 수 있는 메소드임을 명시하는 어노테이션
- @PathVariable
  - Rest의 Url의 값을 읽어서 메소드의 파라미터로 매핑시킬 수 있도록 도와줌
  - {id}로 표기하면, 해당 위치에 들어오는 문자열을 id 파라미터에 할당해줌

## @PostMapping 

- @PostMapping
  - Post 메소드의 Http 요청을 받을 수 있는 메소드임을 명시하는 어노테이션
- @RequestBody
  - Request Body에 있는 데이터를 읽어서 파라미터로 매핑할 수 있도록 도와줌
- @ResponseStatus
  - http 응답에 대한 코드값을 지정한 값으로 변경할 수 있음

## @PutMapping 

- @PutMapping

  - Put 메소드의 Http 요청을 받을 수 있는 메소드임을 명시하는 어노테이션   //전체수정

  

## @PatchMapping 

- @PatchMapping
  - Patch 메소드의 Http 요청을 받을 수 있는 메소드임을 명시하는 어노테이션  //부분수정

## @DeleteMapping 

- @DeleteMapping
  - Delete 메소드의 Http 요청을 받을 수 있는 메소드임을 명시하는 어노테이션

# Mock-Test

- Mock 테스트의 장점
  - 테스트를 더 빠르게 실행할 수 있음
  - 테스트를 더 구체적이고 세밀하게 할 수 있음
- @ExtendWith
  - 테스트를 진행할 컨테이너를 별도로 지정해줌
  - Junit4에서 @RunWith를 대체하는 어노테이션
- MockitoExtension
  - Mockito를 사용할 수 있도록 처리해줌
- @InjectMocks
  - @Mock으로 지정된 객체들을 생성해서, 테스트의 주체가 되는 클래스에 주입(Autowired)까지 해줌
- @Mock
  - 실제 Bean이 아니라 가짜 객체(Mock)를 만들어서 실제 Bean을 대체함
- when...thenReturn
  - Mock의 어떤 메소드와 파라미터가 매핑되는 경우, 결과값에 대해서 지정해줄 수 있음

- verify

  - Mock의 액션에 대해서 별도로 검증할 수 있음

  - 주로 return값이 없는 void 타입의 메소드나, 반복문을 테스트해야 하는 경우에 사용함

- argThat
  - ArgumentMatcher class를 이용해서 결과를 검증하겠다는 의미
- ArgumentMatcher
  - 테스트 결과값을 검증하는 matcher를 custom하게 개발할 수 있도록 제공하는 인터페이스
  - matches()
    - 해당 method를 override하여 값을 검증할 수 있음
    - 검증 후 결과를 boolean으로 리턴하여 테스트를 검증할 수 있도록 함





person 삭제 방식은 db삭제보단 업데이트 방식사용-> private boolean deleted 삭제된거면 true로 설정

assertThat은 자바 객체 검증 -> assertThat(result.getname()).isEqualsTo("martin")

JSon타입 검증 -> jsonPath($.name).value("martin") //위와 동일한 기능이지만 $는 객체별을 의미

