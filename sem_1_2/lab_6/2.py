class Vehicle:
    def __init__(self, make, model):
        self.make = make
        self.model = model

    def get_info(self):
        return f"Марка: {self.make}, Модель: {self.model}"


class Car(Vehicle):
    def __init__(self, make, model, fuel_type):
        super().__init__(make, model)
        self.fuel_type = fuel_type

    def get_info(self):
        return f"{super().get_info()} Тип топлива: {self.fuel_type}"


veh1 = Vehicle("Шкода", "Актавиа")
veh2 = Car("Марсодас", "Жип", "95")
print(veh1.get_info())
print(veh2.get_info())
