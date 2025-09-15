def get_number() -> "bool, float":
    try:
        user_input = input("Введите число: ")
        number: float = float(user_input)

    except Exception:
        print(f"Ошибка ввода, введите число: {user_input}")
        return False, 0

    return True, number


def square(number) -> float:
    return number * number


def main() -> bool:
    result, number = get_number()

    while not (result):
        result, number = get_number()

    squared_number = round(square(number), 8)

    print(f"Квадрат числа {number} равен: {squared_number}")

    return True


if __name__ == "__main__":
    main()
