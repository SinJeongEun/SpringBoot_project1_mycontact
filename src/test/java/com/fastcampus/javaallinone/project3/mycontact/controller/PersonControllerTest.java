package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Slf4j
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

    }

    @Test
    void getParam() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void postPerson()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "    \"name\" : \"martin2\",\n" +
                                "    \"age\":20,\n" +
                                "    \"bloodType\" :\"A\"\n" +
                                "}"))
                        .andDo(print())
                        .andExpect(status().isCreated());


    }

    @Test
    void modifyPerson() throws Exception{

        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();

        assertAll(
                ()-> assertThat(result.getName()).isEqualTo("martin"),
                ()-> assertThat(result.getHobby()).isEqualTo("programming"),
                ()->  assertThat(result.getAddress()).isEqualTo("판교"),
                ()-> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                ()->  assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );



    }

   @Test
    void modifyPersonIfNameIsDifferent() throws Exception{

        PersonDto dto = PersonDto.of("james","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        assertThrows(NestedServletException.class, () ->
                mockMvc.perform(
                    MockMvcRequestBuilders.put("/api/person/1")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(toJsonString(dto)))
                    .andDo(print())
                    .andExpect(status().isOk()));
    }

    @Test
    void modifyName() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name","martinModified"))
                .andDo(print())

                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");

    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
               // .andExpect(content().string("true"));
        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }

}