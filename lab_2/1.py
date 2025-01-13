import re


def greet():
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

    print(f"Ваше имя: {name.lower().title()}")
    return True


def main() -> bool:
    greet()
    return True


if __name__ == "__main__":
    main()
