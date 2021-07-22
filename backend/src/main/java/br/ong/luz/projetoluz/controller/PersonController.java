package br.ong.luz.projetoluz.controller;

import br.ong.luz.projetoluz.model.Person;
import br.ong.luz.projetoluz.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de pessoas paginada")
    public Page<Person> findAll(@ApiParam("Número da página iniciado em 0") @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                @ApiParam("Tamanho da página") @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return personService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra uma nova pessoa")
    public Person create(@RequestParam @NotBlank String name, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birth) {
        return personService.create(name, birth);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna informações de uma pessoa específico")
    public Person findById(@ApiParam("Identificador da pessoa") @PathVariable Integer id) {
        return personService.findById(id);
    }

    @PutMapping
    @ApiOperation(value = "Atualiza as informações de uma pessoa")
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove uma pessoa da lista")
    public void delete(@ApiParam("Identificador da pessoa") @PathVariable Integer id) {
        personService.delete(id);
    }
}
