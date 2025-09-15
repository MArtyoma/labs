MAX_NUMBERS = 2


def get_number() -> "bool, int":
    try:
        user_input = input("Введите число: ")
        number: int = int(user_input)

    except Exception:
        print(f"Ошибка ввода, введите число: {user_input}")
        return False, 0

    return True, number


def main() -> bool:
    numbers = [None] * MAX_NUMBERS

    for i in range(0, MAX_NUMBERS):
        result, numbers[i] = get_number()

        while not (result):
            result, numbers[i] = get_number()

    max_number = float('-inf')

    for i in range(0, MAX_NUMBERS):
        if (numbers[i] > max_number):
            max_number = numbers[i]

    print(f"Большее число: {max_number}")

    return True


if __name__ == "__main__":
    main()
