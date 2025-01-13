class Employee():
    def __init__(self, name, id):
        self.name = name
        self.id = id

    def get_info(self):
        pass


class Manager(Employee):
    def __init__(self, name, id, department=""):
        Employee.__init__(self, name, id)
        self.department = department

    def manage_project(self):
        return f"{self.name} из {self.department} будет руководить проектом."

    def get_info(self):
        return f"Имя: {self.name}, ID: {self.id}, Отдел: {self.department}"


class Technician(Employee):
    def __init__(self, name, id, specialization):
        Employee.__init__(self, name, id)
        self.specialization = specialization

    def perform_maintenance(self):
        return f"{self.name} из {self.specialization} будет выполнять техническое обслуживание."

    def get_info(self):
        return f"Имя: {self.name}, ID: {self.id}, Специализация: {self.specialization}"


class TechManager(Manager, Technician):
    def __init__(self, name, id, department, specialization):
        Manager.__init__(self, name, id, department)
        Technician.__init__(self, name, id, specialization)
        self.employees = []

    def manage_project(self):
        return (
            f"{self.name} из {self.department}, специализация:"
            f"{self.specialization} будет руководить проектом."
        )

    def perform_maintenance(self):
        return (
            f"{self.name} из {self.department}, специализация:"
            f"{self.specialization} будет выполнять техническое обслуживание."
        )

    def get_info(self):
        return (
            f"Имя: {self.name}, ID: {self.id}, Отдел:"
            f"{self.department}, Специализация: {self.specialization}"
        )

    def add_employee(self, employee):
        self.employees.append(employee)

    def get_team_info(self):
        info = "Список подчиненных сотрудников:\n"
        for emp in self.employees:
            info += f"Имя: {emp.name}, ID: {emp.id}\n"
        return info


class TechManagerExample(TechManager):
    def __init__(self, name, id, department, specialization):
        super().__init__(name, id, department, specialization)
        self.employees = []

    def run_example(self):
        emp1 = Manager("Иван", 1, "Офис")
        emp2 = Technician("Петр", 2, "Технологии")
        self.add_employee(emp1)
        self.add_employee(emp2)

        print(self.get_team_info())
        print(emp1.get_info())
        print(emp2.get_info())

        print(self.manage_project())
        print(self.perform_maintenance())


example = TechManagerExample("Ольга", 3, "Руководство", "Технологии")
example.run_example()
