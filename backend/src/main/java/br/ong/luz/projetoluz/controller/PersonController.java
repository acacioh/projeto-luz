package br.ong.luz.projetoluz.controller;

import br.ong.luz.projetoluz.model.Person;
import br.ong.luz.projetoluz.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de pessoas paginada", authorizations = {@Authorization(value = "jwtToken")})
    public Page<Person> findAll(@ApiParam("Número da página iniciado em 0") @RequestParam(defaultValue = "0") @Min(0) Integer page,
                               @ApiParam("Tamanho da página") @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return personService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra uma nova pessoa", authorizations = {@Authorization(value = "jwtToken")})
    public Person create(@ApiParam("Nome da pessoa") @RequestBody @NotBlank String name) {
        return personService.create(name);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna informações de uma pessoa específico", authorizations = {@Authorization(value = "jwtToken")})
    public Person findById(@ApiParam("Identificador da pessoa") @PathVariable Integer id) {
        return personService.findById(id);
    }

    @PutMapping
    @ApiOperation(value = "Atualiza as informações de uma pessoa", authorizations = {@Authorization(value = "jwtToken")})
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove uma pessoa da lista", authorizations = {@Authorization(value = "jwtToken")})
    public void delete(@ApiParam("Identificador da pessoa") @PathVariable Integer id) {
        personService.delete(id);
    }
}
