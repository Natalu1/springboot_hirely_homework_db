## Do zrobienia przed podejściem do zadania
   * Stworzyć w Postgres bazę danych o nazwie springboot_hirely_homework_db,
   * W `main/resources/application.properties  ` podaj hasło do niej.
## Zadanie domowe
### Opis
   * Mamy rozpoczęty i skonfigurowany projekt z mavenem zawierający zależności do spock, hibernate, spring,
   * Jest dodany skrypt flyway generujący przykładowe dane,
   * Istnieje encja Employee zawierająca: `id`, `fullName`, `mail`, `baseSalary`, `position` (enum: [DIRECTOR, CLEANER, DEVELOPER]), `employmentDate`.
### Zadania do wykonania
1. Napisać encję Departament zawierającą: `name`.
2. Dodać relację pomiędzy Departament a Employee - relacja jeden do wiele. Należy do istniejącej tabeli employee dodać nową kolumnę z kluczem obcym do departamentu. Wykorzystaj poniższy skrypt DDL (umieść go w pliku SQL o kolejnej wersji). Raz uruchomiony plik SQL przez Flyway nie może być już zmieniony.
```sql
ALTER TABLE employee
ADD department_id BIGINT;

UPDATE employee SET department_id = 1; --pamiętaj, że department o id=1 musi istnieć
ALTER TABLE employee
ALTER department_id SET NOT NULL;

ALTER TABLE employee
ADD FOREIGN KEY (department_id) REFERENCES department(id);
```
3. Napisać serwis wyliczający pensję pracownika:
   * DIRECTOR - baseSalary + 10% * liczba przepracowanych lat,
   * CLEANER - baseSalary,
   * DEVELOPER - baseSalary + 20% * [dodatek 20%, jeżeli przepracował minimum rok].
4. Napisać endpoint wyświetlający sumę pensji w każdym departamencie. Dto powinno zawierać nazwę departamentu wraz z sumą pensji wszystkich jego pracowników (w serwisie wyliczającym pensję pracownika należy użyć polimorfizmu).
5. Napisz po jednym teście dla każdej warstwy aplikacji.
### Zadania do wykonania DLA CHĘTNYCH
1. Zaimplementuj  endpointy:
   * Dodawanie nowego departamentu 
   * Dodawanie nowego pracownika (wraz  z przypisaniem do departamentu)
## Podpowiedzi i uwagi:
1. Zarówno baza danych produkcyjna (dla działającej aplikacji) Postgres oraz baza testowa H2 są zarządzane przez Flyway. Dane w nich mogą się różnić i posiadają własne pliki SQL w katalogach main/resources i test/resources.
2. Jeśli masz problem z bazą danych lub Flyway i aplikacja się nie uruchamia, możesz ją skasować i stworzyć od nowa.
3. Projekt ma już skonfigurowany Maven, Spring Boot, Flyway i Spock wraz z Groovy. Pamiętaj o kliknięciu w dwie kręcące się strzałki, aby zaciągnąć zależności Mavena.
4. Projekt posiada już dwa testy. Upewniaj się regularnie, że nie przestały one działać.
