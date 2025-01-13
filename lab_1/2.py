MAX_NUMBERS = 2


def getNumber() -> "bool, int":
    try:
        userInput = input("Введите число: ")
        number: int = int(userInput)

    except Exception:
        print(f"Ошибка ввода, введите число: {userInput}")
        return False, 0

    return True, number


def main() -> bool:
    numbers = [None] * MAX_NUMBERS

    for i in range(0, MAX_NUMBERS):
        result, numbers[i] = getNumber()

        while not (result):
            result, numbers[i] = getNumber()

    maxNumber = float('-inf')

    for i in range(0, MAX_NUMBERS):
        if (numbers[i] > maxNumber):
            maxNumber = numbers[i]

    print(f"Большее число: {maxNumber}")

    return True


if __name__ == "__main__":
    main()
