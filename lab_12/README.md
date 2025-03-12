## Лабораторная работа 12: Основы выборки SQL

### Цель работы:
Целью данной лабораторной работы является изучение и применение операторов сортировки (ORDER BY) и группировки (GROUP BY), а также агрегатных функций в SQL для анализа данных.

### Шаги выполнения:

1. Создание таблиц БД:
```sql
CREATE TABLE shop (
id INTEGER PRIMARY KEY,
name VARCHAR(255) UNIQUE,
balance FLOAT NOT NULL);

CREATE TABLE product (
id INTEGER PRIMARY KEY,
name VARCHAR(255) UNIQUE,
price FLOAT NOT NULL);

CREATE TABLE warehouse (
shop_id INTEGER REFERENCES shop (id),
product_id INTEGER REFERENCES product (id),
quantity INTEGER NOT NULL,
PRIMARY KEY (shop_id, product_id));
```

2. Создание дополнительной таблицы "Сотрудник":
```sql
CREATE TABLE worker (
worker_id INTEGER PRIMARY KEY,
shop_id INTEGER REFERENCES shop (id), -- Исправлено: ссылка на поле id таблицы shop
name VARCHAR(255),
salary INTEGER NOT NULL,
position VARCHAR(255));
```

3. Внесение данных в таблицы:

```sql
INSERT INTO shop (id, name, balance) VALUES (1, 'пятерочка',31);
INSERT INTO shop (id, name, balance) VALUES (2, 'перекресток',133);
INSERT INTO shop (id, name, balance) VALUES (3, 'ашан',1000);

INSERT INTO product VALUES (1, 'молоко', 100);
INSERT INTO product VALUES (2, 'белый хлеб', 25);
INSERT INTO product VALUES (3, 'черный хлеб', 30);

INSERT INTO warehouse VALUES (1, 1, 20);
INSERT INTO warehouse VALUES (1, 2, 10);
INSERT INTO warehouse VALUES (2, 1, 30);

INSERT INTO worker (worker_id, shop_id, name, salary, position) VALUES
(1, 1, 'Иван Иванов', 40000, 'Менеджер'),
(2, 2, 'Петр Петров', 35000, 'Кассир'),
(3, 1, 'Сергей Сергеев', 38000, 'Продавец'),
(4, 3, 'Анна Аннатьева', 36000, 'Менеджер'),
(5, 2, 'Дмитрий Дмитриев', 37000, 'Продавец');
```

4. Написание запросов с использованием операторов GROUP BY и ORDER BY, а также агрегатных функций для таблицы "Сотрудник".

### Запросы:

1. **Отсортировать сотрудников по заработной плате в порядке убывания:**
```sql
SELECT name, salary FROM worker
ORDER BY salary DESC;
```

2. **Найти среднюю зарплату сотрудников по каждому магазину и отсортировать результаты по возрастанию средней зарплаты:**
```sql
SELECT shop_id, AVG(salary) as avg_salary 
FROM worker 
GROUP BY shop_id 
ORDER BY avg_salary;

SELECT shop.name, AVG(salary) as avg_salary 
FROM worker 
LEFT JOIN shop ON shop.id = worker.shop_id
GROUP BY shop_id 
ORDER BY avg_salary;
```

3. **Найти максимальную и минимальную зарплату среди сотрудников каждого магазина:**
```sql
SELECT shop_id, MAX(salary) as max_salary, MIN(salary) as min_salary 
FROM worker 
GROUP BY shop_id;

SELECT shop.name, MAX(salary) as max_salary, MIN(salary) as min_salary 
FROM worker 
LEFT JOIN shop ON shop.id = worker.shop_id
GROUP BY shop_id;
```

4. **Подсчитать количество сотрудников в каждом магазине и отсортировать по количеству в порядке убывания:**
```sql
SELECT shop_id, COUNT(*) as employee_count 
FROM worker 
GROUP BY shop_id 
ORDER BY employee_count DESC;

SELECT shop.name, count(*) as employee_count 
FROM worker 
LEFT JOIN shop ON shop.id = worker.shop_id
GROUP BY shop_id 
ORDER BY employee_count DESC;
```

5. **Получить список всех должностей среди сотрудников с указанием количества человек на каждой должности:**
```sql
SELECT position, COUNT(*) as amount 
FROM worker 
GROUP BY position;
```

### Заключение:
В ходе лабораторной работы были изучены основы выборки данных SQL. Мы научились создавать таблицы и заполнять их данными, а также использовать операторы сортировки (ORDER BY), группировки (GROUP BY) и агрегатные функции для анализа данных. Эти навыки позволяют эффективно работать с базами данных и получать нужную информацию из них.
