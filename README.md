# SpringBoot_project1_mycontact

data.sql
spring context를 실행시킬 때, data.sql에 적혀있는 쿼리를 실행시킴
main/resources에 선언하게 되면, 서버를 시작할 때 쿼리가 실행됨
test/resources에 선언하게 되면, 테스트를 실행할 때 쿼리가 실행됨

putmapping 전체 수정
patchmapping 부분수정

person 삭제 방식은 db삭제보단 업데이트 방식사용-> private boolean deleted 삭제된거면 true로 설정

assertThat은 자바 객체 검증 ->  assertThat(result.getname()).isEqualsTo("martin")

JSon타입 검증 -> jsonPath($.name).value("martin")    //위와 동일한 기능이지만 $는 객체별을 의미

mock테스트
springboottest보다 속도 빠름, 별다른 디비 삽입하지 않아도 자세한 테스트 검증 가능
mocktest 검증은 verify로 수행 -> ArgumentMatcher 구현한 함수 사용하여 테스트 수행
