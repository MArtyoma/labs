import re


def get_age() -> "bool, int":
    try:
        user_input = input("Введите возраст: ")
        number: int = int(user_input)

    except Exception:
        print(f"Ошибка ввода: {user_input}")
        return False, 0

    if (number < 0 or number > 150):
        print(f"Вам точно не может быть столько лет: {number}")
        return False, 0

    return True, number


def get_name():
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
    result, age = get_age()

    while not (result):
        result, age = get_age()

    result, name = get_name()

    while not (result):
        result, name = get_name()

    print(f"Ваше имя: {name}, Ваш возраст: {age}")

    return True


if __name__ == "__main__":
    main()
