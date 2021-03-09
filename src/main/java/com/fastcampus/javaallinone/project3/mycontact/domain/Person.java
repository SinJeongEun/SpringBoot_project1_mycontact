package com.fastcampus.javaallinone.project3.mycontact.domain;

import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@Data   //@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode를 한꺼번에 선언해줌
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private  int age;

    private String hobby;

    @NonNull
    private String bloodType;


    private String address;

    @Embedded
    private Birthday birthday;

    private  String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne
    private Block block;

}
