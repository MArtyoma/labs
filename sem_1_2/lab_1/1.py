MAX_LOOP = 10e8


def get_number() -> "bool, int":
    try:
        user_input = input("Введите число: ")
        number: int = int(user_input)

    except Exception:
        print(f"Ошибка ввода, введите число больше нуля: {user_input}")
        return False, 0

    if (number < 1):
        print(f"Число должно быть больше нуля: {user_input}")
        return False, 0

    return True, number


def print_numbers(max_int: int) -> bool:
    current: int = 1

    while current <= max_int:
        print(current)
        current += 1
        if (current > MAX_LOOP):
            break


def main() -> bool:
    result, number = get_number()

    while not (result):
        result, number = get_number()

    print_numbers(number)

    return True


if __name__ == "__main__":
    main()
