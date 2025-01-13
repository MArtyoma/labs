def get_number() -> "bool, int":
    try:
        user_input = input("Введите число: ")
        number: int = int(user_input)

    except Exception:
        print(f"Ошибка ввода, введите число: {user_input}")
        return False, 0

    if (number < 2):
        print(f"Число должно быть больше 1: {user_input}")
        return False, 0

    return True, number


def is_prime(number: int):
    del_count = 0

    num = abs(number)

    if num == 1:
        return False

    max = num // 2 + 1

    for i in range(2, max):
        if (num % i == 0):
            print(f"Делитель: {i}")
            del_count += 1

    if (del_count == 0):
        return True

    return False


def main() -> bool:
    result, number = get_number()

    while not (result):
        result, number = get_number()

    print(f"Число {number}")
    if (is_prime(number)):
        print("Простое")
    else:
        print("Не простое")

    return True


if __name__ == "__main__":
    main()
