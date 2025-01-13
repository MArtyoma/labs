MAX_LOOP = 10e8


def getNumber() -> "bool, int":
    try:
        userInput = input("Введите число: ")
        number: int = int(userInput)

    except Exception:
        print(f"Ошибка ввода, введите число больше нуля: {userInput}")
        return False, 0

    if (number < 1):
        print(f"Число должно быть больше нуля: {userInput}")
        return False, 0

    return True, number


def printNumbers(maxInt: int) -> bool:
    current: int = 1

    while current <= maxInt:
        print(current)
        current += 1
        if (current > MAX_LOOP):
            break


def main() -> bool:
    result, number = getNumber()

    while not (result):
        result, number = getNumber()

    printNumbers(number)

    return True


if __name__ == "__main__":
    main()
