package br.ong.luz.projetoluz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person {
    @Id
    private Integer id;
    private String name;
    private LocalDate birth;
}
