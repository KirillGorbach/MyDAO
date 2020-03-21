# MyDAO
Что нужно для работы:
1. Подключить JDBC (для SQLite можно скачать здесь: https://bitbucket.org/xerial/sqlite-jdbc/downloads, для maven ищем в репозитории MVN).
Подключить: "Project Structure" => "Libraries" => "+" и указат путь.
2. Скачать SQLite можно с официального сайта.
3. Создать файл базы данных. В консоли это "sqlite3 myDatabase.db".
4. Создать таблицу пользователей по шаблону:

CREATE TABLE usr2 (
user_id INT,
user_name VARCHAR(30),
user_password VARCHAR(30),
user_money INT,
user_level VARCHAR(30));

Имя таблицы может быть другое, названия колонок тоже. Главное- расставить типы и прописать нзвания в классе UserDAO
(там написано, куда).

3. Указать в классе UserDAO строку DB_addr
в формате "jdbc:sqlite:[путь к файлу базы данных]" 
Если не получится, можно попробовать указать название диска так: "jdbc:sqlite:C:[filepath]".

Рабочие методы - addUser (сама создаёт юзера, даёт ему номер и возвращает), inspectPassword (проверка), getUser, setUserMoney и setUserLevel.
Аккуратно, если запускать программу несколько раз, то все результаты накопятся в бд.
