import math


class Circle():
    def __init__(self, radius=1):
        self.radius = radius

    def get_radius(self):
        return self.radius

    def get_circle_ln(self):
        return self.radius * 2 * math.pi

    def set_radius(self, radius: int):
        self.radius = radius


circle = Circle(5)
print(f"Circle radius: {circle.get_radius()}")

circle.set_radius(10)
print(f"Circle radius: {circle.get_radius()}")

print(circle.get_circle_ln())
