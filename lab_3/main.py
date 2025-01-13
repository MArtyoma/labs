from utils import (
    FILE_IS_NOT_EXISTS,
    PERMISSION_ERROR,
    currentFreeSpace,
    equils,
    getUserInput,
    isFileReady,
    listFiles,
    mtuci,
    r,
    readFile,
    writeToFile,
)

MIN_DISC_SPACE = 0.5
DEFAULT_FILE_NAME = "example.txt"


def createFile(fileName: str) -> bool:
    status = isFileReady(fileName)
    if (status == PERMISSION_ERROR):
        print(f"{r("Ошибка доступа")}")
        return False

    if (status == FILE_IS_NOT_EXISTS):
        print(f"Файл '{r(fileName)}' не существует")
        createNewFile = getUserInput(
            f"Создать новый файл '{fileName}' ({r("y/n")}, default: {r("y")}): ",
            "[YyNn]",
            "y"
        )
        # clearOutput()

        if (createNewFile.lower() == "y"):
            writeToFile(fileName, "")
            return True

        return False

    return True


def header() -> str:
    print("")
    freeSpace = currentFreeSpace()
    if (freeSpace < MIN_DISC_SPACE):
        print(f"{r("ПРЕДУПРЕЖДЕНИЕ!!! Не хватает места на диске!!!")}\n")

    print(f"{r(freeSpace)} GB: Свободного места на диске\n")
    print("Создание и чтение файлов\n")


def quest(fileName: str) -> bool:
    print(f"\nВыбрать действие: {r("1-6")} (default: {r(1)})")
    print(f"{r(1)}: Прочитать весь файл")
    print(f"{r(2)}: Прочитать файл построчно")
    print(f"{r(3)}: Записать в файл")
    print(f"{r(4)}: Выбрать другой файл")
    print(f"{r(5)}: Проверить Exception")
    print(f"{r(6)}: Выйти из программы")
    action = getUserInput(" : ", "[123456]", "1")

    if (action == "1"):
        readFile(fileName)
        return True

    if (action == "2"):
        readFile(fileName, False)
        return True

    if (action == "3"):
        print(f"\nЗапись в файл: {r("1-2")} (default: {r(2)})")
        print(f"{r(1)}: Перезаписать")
        print(f"{r(2)}: Дозаписать")

        action = getUserInput(" : ", "[12]", "2")

        if (action == "1"):
            action = "w"
        else:
            action = "a"

        writeToFile(fileName, getUserInput("Строка: ") + "\n", action)

        while (getUserInput(
            f"Продолжить запись? ({r("y/n")}, default: {r("y")}): ",
            "[YyNn]",
            "y").lower() == "y"
        ):
            writeToFile(fileName, getUserInput("Строка: ") + "\n", action)
        return True

    if (action == "5"):
        print("")
        try:
            with open(fileName, 'r') as file:
                content = file.read()
                print(content)

        except Exception as FileNotFoundError:
            print(f"Ошибка файл не существует: {r(FileNotFoundError.filename)}")

        return False

    if (action == "6"):
        quit()
        return False


def start():
    header()

    fileList = listFiles(__file__)

    fileName = getUserInput(
        f"Введите имя файла или его номер (default: {r(DEFAULT_FILE_NAME)}): "
    )

    if (equils(fileName, "[0-9]+") and len(fileName) > 0):
        index = int(fileName)
        if (index < 0 or index > len(fileList)):
            print("Файл не найден")
            return start()
        fileName = fileList[int(index) - 1]

    if (fileName == ""):
        fileName = DEFAULT_FILE_NAME

    while (isFileReady(fileName) < 1):
        if (not (createFile(fileName))):
            return

    print(f"\nВыбран файл: '{r(fileName)}'")

    if (not (quest(fileName))):
        start()
        return

    while ((result := getUserInput(
        f"Продолжить работу с файлом '{r(fileName)}'? ({r("y/n")}, default: {r("y")}): ",
        "[YyNn]",
        "y").lower()) == "y",
    ):
        if (result == "n"):
            break

        if (not (quest(fileName))):
            start()
            return


def main():
    mtuci()
    print(f"\nАвтор: {r("Меланич А. И.")} БВТ2401")
    print(f"Лабораторная работа {r("№3")}")
    start()

    while (getUserInput(
        f"Выйти из программы? ({r("y/n")}, default: {r("n")}): ",
        "[YyNn]",
        "n"
    ).lower() == "n"):
        start()


if __name__ == "__main__":
    main()
