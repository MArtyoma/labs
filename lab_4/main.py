from math import sqrt
from datetime import datetime
from package.my_module import sum
from package.moduleA import printer
from package.moduleB import user_input

print(f"MATH SQRT 4: {int(sqrt(4))}")
print(f"DATETIME NOW: {datetime.now()}")
print(f"SUM 10, 50: {sum(10, 50)}")
printer("Hello world")
user_input()
