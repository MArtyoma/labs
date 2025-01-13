MAX_NUMBERS = 2


def get_number() -> "bool, int":
    try:
        user_input = input("Введите число: ")
        number: int = int(user_input)

    except Exception:
        print(f"Ошибка ввода, введите число: {user_input}")
        return False, 0

    return True, number


def max_of_two(x, y) -> int:
    if (x < y):
        return y

    return x


def main() -> bool:
    numbers = [None] * MAX_NUMBERS

    for i in range(0, MAX_NUMBERS):
        result, numbers[i] = get_number()

        while not (result):
            result, numbers[i] = get_number()

    print(f"Первое число {numbers[0]}")
    print(f"Второе число {numbers[1]}")
    print(f"Большее число: {max_of_two(numbers[0], numbers[1])}")

    return True


if __name__ == "__main__":
    main()
