# Hogwarts Houses in Java - sprint 3

## Story

What if I said, that magic is real? What if I said, that wizards, witches, and all the Harry Potter world is real, and they need you now?

In this rushing, modern world, they can't just solve everything with spells and potions! They need someone who can sort everything out, while they are
 dealing with "You-Know-Who".

You will need to help with the student's accommodations and create a house-managing app for Hogwarts to make their lives easier. Are you ready?

## What are you going to learn?

You will learn and practice how to do the following things in Java Spring:

- Spring Data
- Spring Data Queries
- Spring Database Connection
- Spring JPA Repositories
- Spring Testing

## Tasks

1. As this is the 3rd sprint of the same project, solutions can vary a lot. If you feel like your previous sprint is not a suitable base for another iteration, ask your mentor for a starter code, which you can build on. Otherwise, feel free to copy your existing codebase. Save this first step in a commit.
    - The student or team committed and pushed the initial codebase.

2. The last battle is coming. Harry will need all the teachers to protect Hogwarts. Help Harry to find all the teachers!
    - Create a new entity `Teacher`. `Teacher` class has the field: `id`, which is generated. `Teacher` class has the fields: `name` and `subject`, which are Strings. `Teacher` class has the field: `isWitch`, which is a boolean. `Teacher` class has the field: `age`, which is an int. `Teacher` class has the field: `wand`, which will be an instance of a new class, `Wand`.
 Each teacher has one, and only one wand.
    - Create a new repository `TeacherRepository` which is JPA repository.
    - Create a new service `TeacherService` which will use the methods of `TeacherRepository`.
    - Create a new restcontroller `TeacherController` which will use the methods of `TeacherService`. Create a new endpoint `/teachers` which will retrieve all the teachers from the database. Create a new endpoint `/teachers/{id}` which will find teachers by their ids from the database.

3. How could we win the battle without our precious wands? Quickly, everyone, wands up, and wait till the sign!
    - Create a new entity `Wand`. `Wand` class has the field: `id`, which is generated. `Wand` class has the fields: `woodType` and `color` which are Strings. `Wand` class has the field: `teacher`, which refers to its owner (who is a Teacher). `Wand` class has the field: `spells`, which is a Set of the casted spells (`Spell` is a new class.) Each wand has one, and only one owner, who is a teacher. All the wands can cast many spells, and all the spells can be casted by any wands.
    - Create a new repository `WandRepository` which is JPA repository.
    - Create a new service `WandService` which will use the methods of `WandRepository`.
    - Create a new restcontroller `WandController` which will use the methods of `WandService`. Create a new endpoint `/wands` which will retrieve all the wands from the database. Create a new endpoint `/wands/{id}` which will find wands by their ids from the database.

4. Did you know? Spells never get lost! After a witch or wizard uses their wand, the wand will remember all the spells. How cool is that!
    - Create a new entity `Spell`. `Spell` class has the field: `id`, which is generated. `Spell` class has the fields: `name` and usage, which are Strings. `Spell` class has the field: `power`, which is an Integer. `Spell` class has the field: `isBanned`, which is a boolean.
    - Create a new repository `SpellRepository` which is JPA repository.
    - Create a new service `SpellService` which will use the methods of `SpellRepository`.
    - Create a new restcontroller `SpellController` which will use the methods of `SpellService`. Create a new endpoint `/spells` which will retrieve all the spells from the database. Create a new endpoint `/spells/{id}` which will find spells by their ids from the database.

5. So many things have changed lately! Who can follow all these actions? Better write them down before you forget them.
    - Update the `application.properties` so you can use postgresql as datasource.
    - Create a new table for `wand` with a field referring to its owner `teacher`.
    - Create a new table for `teacher` with a field referring to `wand`.
    - Create a new table for `spell`.

6. Wow, all the paperwork is done. Such a huge amount of scrolls! Someone should read them, as a quick check.
    - Expand your `data.sql` with some sample data for the newly created tables.

7. Look, a wand! Who can be its owner? Anyone lost a Hawthorn wand?
    - Create a new query in `TeacherRepository` which will retrieve a list of the teachers name if the `woodType` of their wands matches the given one.
    - Create a new endpoint `teachers/wand` in `TeacherController` which will wait for one parameter `wand` and returns names of the matching teachers.

8. Make sure everyone's ready and everything works well!
    - Create a new test class `SpellUnitTest` in `test/java/com/codecool/hogwartshouses` package. In `SpellUnitTest` class, test all the endpoints from `SpellController`.
    - Create a new test class `WandUnitTest` in `test/java/com/codecool/hogwartshouses` package. In `WandUnitTest` class, test all the endpoints from `WandController`.
    - Create a new test class `TeacherUnitTest` in `test/java/com/codecool/hogwartshouses` package. In `TeacherUnitTest` class, test the `/teachers` and `/teachers/{id}` endpoints from `TeacherController`.

9. ***Note: this is an optional task.*** After the big battle, nothing much left of Hogwarts. Help the students to rebuild the whole building!
    - Remove the whole `DAO` package, which contained all the connections to JDBC.
    - Create a new class `RoomRepository`, which is a JPA repository. Create 2 queries in `RoomRepository` for `findAvailableRooms()` and `findRoomWithNoCatOrOwl()`. Update `RoomService` to use the methods of `RoomRepository`. Annotate the class `Room` as an entity class. Note: All the rooms has more residents, but one student has only one room.
    - Annotate the class `Building` as an entity class. Note: all the building has many rooms, but all the rooms are part of only one building.
    - Annotate the class `Student` as an entity class.
    - Create a new class `RecipeRepository`, which is a JPA repository. Create 3 queries in `RecipeRepository` to implement the methods which `PotionService` class needs. Annotate the class `Recipe` as an entity class.

## General requirements

None

## Hints

Read more about Spring [here](project/curriculum/materials/competencies/java-spring-basics/about-spring.md.html).

Read more about Spring Data [here](project/curriculum/materials/competencies/java-spring-data/spring-data.md.html).

## Background materials

[Spring Database Connection](project/curriculum/materials/competencies/java-spring-data/sping-database-connection.md.html)

[Spring Data](project/curriculum/materials/competencies/java-spring-data/spring-data.md.html)

[Spring Data Queries](project/curriculum/materials/competencies/java-spring-data/spring-data-queries.md.html)

[Spring JPA Repositories](project/curriculum/materials/competencies/java-spring-data/spring-jpa-repositories.md.html)

[Spring testing](project/curriculum/materials/competencies/java-spring-basics/spring-testing.md.html)
