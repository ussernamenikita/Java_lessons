# Java_lessons
## Project for "Java programming tools" course.
##### Выполнение заданий по предмету инструментальные средства программирования Java.
На данный момент выполнены задания:
* Подготовить модель классов для социальной сети.
 * Реализовать паттерн Command для выполнения команд в социальной сети
    
    Реализованы команды : Создать пользователя,логин,отправить сообщение всем друзьям, отправить сообщение конкретному пользователю,
  посмотреть сообщения от всех друзей, посмотреть сообщения от конкретного друга,просмотреть всех друзей.
 * JUnit - создать сервис для расчёта рейтинга какого-либо сообщения для пользователя. В связи с большим обьемом работы добавлен только один тест.
 * Maven - в проект добавлена поддержка maven, добавил FindBugs, CheckStyle.
 * JDBC - сделаны DAO для пользователей, сообщений и постов. База - Postgresql.
 * Mockito - создан класс [DataBaseService](https://github.com/ussernamenikita/Java_lessons/blob/master/src/main/java/com/leti/social_net/services/DatabaseService.java) и 4 теста для него [MockTestClass](https://github.com/ussernamenikita/Java_lessons/blob/master/src/test/java/com/leti/social_net/dao/impl/MockTestClass.java)
 * Spring Framework - добавлены бины и класс конфигруации [Configuration](https://github.com/ussernamenikita/Java_lessons/blob/master/src/main/java/com/leti/social_net/config/Configuration.java)
 * Добавлено логирование с помощью аспекта [LoggingAspect](https://github.com/ussernamenikita/Java_lessons/blob/master/src/main/java/com/leti/social_net/config/LoggingAspect.java)
 
 
  
