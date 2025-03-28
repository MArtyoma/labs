# Лабораторная работа №7  Работа с классами ч.3

**Цель работы:** Разработать систему управления сотрудниками, демонстрирующую множественное наследование, инкапсуляцию и полиморфизм в Python. Система должна уметь обрабатывать различные типы сотрудников, включая менеджеров и технических специалистов, а также предоставлять возможность для расширения и добавления новых ролей.

## Задание

1.	Создайте класс **Employee** с общими атрибутами, такими как **name** (имя), **id** (идентификационный номер) и методами, например, **get_info()**, который возвращает базовую информацию о сотруднике.
2.	Создайте класс **Manager** с дополнительными атрибутами, такими как **department** (отдел) и методами, например, **manage_project()**, символизирующим управление проектами.
3.	Создайте класс **Technician** с уникальными атрибутами, такими как **specialization** (специализация), и методами, например, **perform_maintenance()**, означающим выполнение технического обслуживания.
4.	Создайте класс **TechManager**, который наследует как **Manager**, так и **Technician**. Этот класс должен комбинировать управленческие способности и технические навыки, например, иметь методы для управления проектами и выполнения технического обслуживания.
5.	Добавьте метод **add_employee()**, который позволяет **TechManager** добавлять сотрудников в список подчинённых.
6.	Реализуйте метод **get_team_info()**, который выводит информацию о всех подчинённых сотрудниках.
7.	Создайте объекты каждого класса и демонстрируйте их функциональность.



