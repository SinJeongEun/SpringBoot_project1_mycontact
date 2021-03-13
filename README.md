# SpringBoot_project1_mycontact

data.sql
spring context를 실행시킬 때, data.sql에 적혀있는 쿼리를 실행시킴
main/resources에 선언하게 되면, 서버를 시작할 때 쿼리가 실행됨
test/resources에 선언하게 되면, 테스트를 실행할 때 쿼리가 실행됨


cascade
관련된 entity의 영속성을 함께 관리할 수 있도록 해줌

CascadeType.PERSIST
insert할 경우 관련 entity도 함께 insert함

CascadeType.MERGE
update할 경우 관련 entity도 함께 update함

CascadeType.REMOVE
delete할 경우 관련 entity도 함께 delete함

CascadeType.ALL
모든 케이스에 대해 영속성을 함께 관리함

orphanRemoval
관련 entity의 relation이 사라질 때, entity를 함께 삭제해줌


fetch
FetchType.EAGER
항상 relation이 있는 entity를 join하여 값을 함께 가져옴

FetchType.LAZY
해당 객체가 필요한 시점에 id를 통해 새로 select해서 값을 가져옴


JPA @ Query

@Embedded
다른 객체를 Entity의 속성으로 가져옴

@Embeddable
자기 객체를 다른 Entity의 속성으로 사용할 수 있음

@Query
Naming 컨벤션에 따라 생성되는 쿼리가 아니라, 커스터마이징된 쿼리를 직접 지정하여 생성함
?1 (숫자)를 통해서 parameter를 순서대로 주입하여 사용할 수 있음

@Param("A")으로 parameter를 받게 되면, :A로 파라미터를 주입하여 사용할 수 있음
nativeQuery=true를 사용하게 되면, JPQL이 아니라 써준 그대로 네이티브 쿼리를 생성해줌

@Valid
해당 객체의 유효성을 검토하겠다는 것을 의미함


putmapping 전체 수정

patchmapping 부분수정



person 삭제 방식은 db삭제보단 업데이트 방식사용-> private boolean deleted 삭제된거면 true로 설정

assertThat은 자바 객체 검증 ->  assertThat(result.getname()).isEqualsTo("martin")

JSon타입 검증 -> jsonPath($.name).value("martin")    //위와 동일한 기능이지만 $는 객체별을 의미

mock테스트
springboottest보다 속도 빠름, 별다른 디비 삽입하지 않아도 자세한 테스트 검증 가능
mocktest 검증은 verify로 수행 -> ArgumentMatcher 구현한 함수 사용하여 테스트 수행
