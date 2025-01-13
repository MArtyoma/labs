import re


def getAge() -> "bool, int":
    try:
        userInput = input("Введите возраст: ")
        number: int = int(userInput)

    except Exception:
        print(f"Ошибка ввода: {userInput}")
        return False, 0

    if (number < 0 or number > 150):
        print(f"Вам точно не может быть столько лет: {number}")
        return False, 0

    return True, number


def getName():
    name = str(input("Введите Ваше имя: "))

    while len(name) < 2:
        print("Ваше имя слишком короткое")
        name = str(input("Введите Ваше имя: "))

    while len(name) > 20:
        print("Ваше имя слишком длинное")
        name = str(input("Введите Ваше имя: "))

    rename = " ".join(re.findall("[a-zA-Zа-яА-Я]+", name))

    while len(rename) != len(name):
        print("Имя должно содержать только буквы")
        name = str(input("Введите Ваше имя: "))
        rename = " ".join(re.findall("[a-zA-Zа-яА-Я]+", name))

    return True, name.lower().title()


def main() -> bool:
    result, age = getAge()

    while not (result):
        result, age = getAge()

    result, name = getName()

    while not (result):
        result, name = getName()

    print(f"Ваше имя: {name}, Ваш возраст: {age}")

    return True


if __name__ == "__main__":
    main()
