def getNumber() -> "bool, float":
    try:
        userInput = input("Введите число: ")
        number: float = float(userInput)

    except Exception:
        print(f"Ошибка ввода, введите число: {userInput}")
        return False, 0

    return True, number


def square(number) -> float:
    return number * number


def main() -> bool:
    result, number = getNumber()

    while not (result):
        result, number = getNumber()

    squaredNumber = round(square(number), 8)

    print(f"Квадрат числа {number} равен: {squaredNumber}")

    return True


if __name__ == "__main__":
    main()
