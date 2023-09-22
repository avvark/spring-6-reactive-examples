package guru.springframework.spring6reactiveexamples.repositories;

import guru.springframework.spring6reactiveexamples.domein.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class PersonRepositoryImplTest {

  PersonRepository personRepository = new PersonRepositoryImpl();

  @Test
  void testMonoByIdBlock() {
    // Do not do this
    Mono<Person> personMono = personRepository.getById(1);
    Person person = personMono.block();
    System.out.println(person);
  }

  @Test
  void testMonoByIdSubscriber() {
    Mono<Person> personMono = personRepository.getById(1);
    personMono.subscribe(System.out::println);
  }

  @Test
  void testMapOperation() {
    Mono<Person> personMono = personRepository.getById(1);
    personMono.map(Person::getFirstName)
        .subscribe(System.out::println);
  }

  @Test
  void testFluxBlockFirst() {
    // Do not do this
    Flux<Person> personFlux = personRepository.findAll();
    Person person = personFlux.blockFirst();
    System.out.println(person);
  }

  @Test
  void testFluxSubscriber() {
    Flux<Person> personFlux = personRepository.findAll();
    personFlux.subscribe(System.out::println);
  }

  @Test
  void testFluxMapOperation() {
    Flux<Person> personFlux = personRepository.findAll();
    personFlux.map(Person::getFirstName)
        .subscribe(System.out::println);
  }

  @Test
  void testFluxToList() {
    Flux<Person> personFlux = personRepository.findAll();
    Mono<List<Person>> listMonoPerson = personFlux.collectList();
    listMonoPerson.subscribe(list -> list.forEach(System.out::println));
  }

}