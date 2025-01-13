from utils import (
    FILE_IS_NOT_EXISTS,
    PERMISSION_ERROR,
    current_free_space,
    equils,
    get_user_input,
    is_file_ready,
    list_files,
    mtuci,
    r,
    read_file,
    write_to_file,
)

MIN_DISC_SPACE = 0.5
DEFAULT_FILE_NAME = "example.txt"


def create_file(file_name: str) -> bool:
    status = is_file_ready(file_name)
    if (status == PERMISSION_ERROR):
        print(f"{r("Ошибка доступа")}")
        return False

    if (status == FILE_IS_NOT_EXISTS):
        print(f"Файл '{r(file_name)}' не существует")
        create_new_file = get_user_input(
            f"Создать новый файл '{file_name}' ({r("y/n")}, default: {r("y")}): ",
            "[YyNn]",
            "y"
        )

        if (create_new_file.lower() == "y"):
            write_to_file(file_name, "")
            return True

        return False

    return True


def header() -> str:
    print("")
    free_space = current_free_space()
    if (free_space < MIN_DISC_SPACE):
        print(f"{r("ПРЕДУПРЕЖДЕНИЕ!!! Не хватает места на диске!!!")}\n")

    print(f"{r(free_space)} GB: Свободного места на диске\n")
    print("Создание и чтение файлов\n")


def quest(file_name: str) -> bool:
    print(f"\nВыбрать действие: {r("1-6")} (default: {r(1)})")
    print(f"{r(1)}: Прочитать весь файл")
    print(f"{r(2)}: Прочитать файл построчно")
    print(f"{r(3)}: Записать в файл")
    print(f"{r(4)}: Выбрать другой файл")
    print(f"{r(5)}: Проверить Exception")
    print(f"{r(6)}: Выйти из программы")
    action = get_user_input(" : ", "[123456]", "1")

    if (action == "1"):
        read_file(file_name)
        return True

    if (action == "2"):
        read_file(file_name, False)
        return True

    if (action == "3"):
        print(f"\nЗапись в файл: {r("1-2")} (default: {r(2)})")
        print(f"{r(1)}: Перезаписать")
        print(f"{r(2)}: Дозаписать")

        action = get_user_input(" : ", "[12]", "2")

        if (action == "1"):
            action = "w"
        else:
            action = "a"

        write_to_file(file_name, get_user_input("Строка: ") + "\n", action)

        while (get_user_input(
            f"Продолжить запись? ({r("y/n")}, default: {r("y")}): ",
            "[YyNn]",
            "y").lower() == "y"
        ):
            write_to_file(file_name, get_user_input("Строка: ") + "\n", action)
        return True

    if (action == "5"):
        print("")
        try:
            with open(file_name, 'r') as file:
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

    file_list = list_files(__file__)

    file_name = get_user_input(
        f"Введите имя файла или его номер (default: {r(DEFAULT_FILE_NAME)}): "
    )

    if (equils(file_name, "[0-9]+") and len(file_name) > 0):
        index = int(file_name)
        if (index < 0 or index > len(file_list)):
            print("Файл не найден")
            return start()
        file_name = file_list[int(index) - 1]

    if (file_name == ""):
        file_name = DEFAULT_FILE_NAME

    while (is_file_ready(file_name) < 1):
        if (not (create_file(file_name))):
            return

    print(f"\nВыбран файл: '{r(file_name)}'")

    if (not (quest(file_name))):
        start()
        return

    while ((result := get_user_input(
        f"Продолжить работу с файлом '{r(file_name)}'? ({r("y/n")}, default: {r("y")}): ",
        "[YyNn]",
        "y").lower()) == "y",
    ):
        if (result == "n"):
            break

        if (not (quest(file_name))):
            start()
            return


def main():
    mtuci()
    print(f"\nАвтор: {r("Меланич А. И.")} БВТ2401")
    print(f"Лабораторная работа {r("№3")}")
    start()

    while (get_user_input(
        f"Выйти из программы? ({r("y/n")}, default: {r("n")}): ",
        "[YyNn]",
        "n"
    ).lower() == "n"):
        start()


if __name__ == "__main__":
    main()
