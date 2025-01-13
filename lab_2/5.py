def getNumber() -> "bool, int":
    try:
        userInput = input("Введите число: ")
        number: int = int(userInput)

    except Exception:
        print(f"Ошибка ввода, введите число: {userInput}")
        return False, 0

    if (number < 2):
        print(f"Число должно быть больше 1: {userInput}")
        return False, 0

    return True, number


def is_prime(number: int):
    delCount = 0

    num = abs(number)

    if num == 1:
        return False

    for i in range(2, num + 1):
        if (num % i == 0):
            print(f"Делитель: {i}")
            delCount += 1

    if (delCount == 2):
        return True

    return False


def main() -> bool:
    result, number = getNumber()

    while not (result):
        result, number = getNumber()

    print(f"Число {number}")
    if (is_prime(number)):
        print("Простое")
    else:
        print("Не простое")

    return True


if __name__ == "__main__":
    main()
