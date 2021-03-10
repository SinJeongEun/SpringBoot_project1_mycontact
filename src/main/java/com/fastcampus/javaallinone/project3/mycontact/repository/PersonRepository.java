package com.fastcampus.javaallinone.project3.mycontact.repository;


import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    List<Person> findByBlockIsNull(); //차단되지 않는 사람 where조건 쿼리문으로 가져오도록 하기 위하여 하는 작업

    List<Person> findByBloodType(String bloodtype);


    @Query(value = "select  person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person>findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select  * from Person person where person.deleted=true",nativeQuery = true)
    List<Person>findPeopleDeleted();
}
