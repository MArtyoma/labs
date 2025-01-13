from pathlib import Path
import ctypes
import os
import platform
import re

KB = 1024
MB = KB * 1024
GB = MB * 1024

PERMISSION_ERROR = -2
FILE_IS_NOT_EXISTS = -1
OKBLUE = '\033[94m'
OKCYAN = '\033[96m'
OKGREEN = '\033[92m'
WARNING = '\033[93m'
FAIL = '\033[91m'
ENDC = '\033[0m'
BOLD = '\033[1m'
UNDERLINE = '\033[4m'

CURSOR_UP_ONE = '\x1b[1A'
ERASE_LINE = '\x1b[2K'


def mtuci():
    print(f"{OKGREEN}")
    print("""
░▒▓██████████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░
░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    ░▒▓██████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░
          """)
    print(f"{ENDC}")


def m_input(val) -> str:
    u_input = ""
    try:
        u_input = input(val)
    except KeyboardInterrupt:
        print("\nВыход из программы\n")
        quit()

    return u_input


def clear_output(lines=1):
    for i in range(0, lines):
        print(CURSOR_UP_ONE + ERASE_LINE, end="")


def r(string: str) -> str:
    return f"{OKBLUE}{string}{ENDC}"


def is_writable(file_name: str):
    return os.access(file_name, os.R_OK)


def is_file_exists(file_name: str):
    e_file = Path(os.path.join(get_current_dir(), file_name))

    return e_file.is_file()


def is_file_ready(file_name: str) -> int:
    if (is_file_exists(file_name) and not (is_writable(file_name))):
        return PERMISSION_ERROR

    if (not (is_file_exists(file_name))):
        return FILE_IS_NOT_EXISTS

    return 1


def get_free_space(dirname: str, size=GB, precision=4) -> float:
    if platform.system() == 'Windows':
        free_bytes = ctypes.c_ulonglong(0)
        ctypes.windll.kernel32.GetDiskFreeSpaceExW(
            ctypes.c_wchar_p(dirname),
            None,
            None,
            ctypes.pointer(free_bytes)
        )
        return round(free_bytes.value / size, precision)
    else:
        st = os.statvfs(dirname)
        return round(st.f_bavail * st.f_frsize / size, precision)


def current_free_space() -> float:
    return get_free_space(get_current_dir())


def get_current_dir() -> str:
    return os.path.dirname(os.path.realpath(__file__))


def write_to_file(file_name: str, content: str, action="w") -> bool:
    if (is_file_ready(file_name) == PERMISSION_ERROR):
        print(f"Ошибка доступа к файлу '{r(file_name)}'")
        return False

    with open(file_name, action) as file:
        file.write(content)
        print(content, end="")

    return True


def get_current_file(file_name=__file__) -> str:
    return os.path.basename(file_name)


def list_files(exception="") -> list:
    current = get_current_file()
    exception = get_current_file(exception)

    path = get_current_dir()
    files = []
    for f in os.listdir(path):
        if os.path.isfile(os.path.join(path, f)):
            if (f != current and f != exception):
                files.append(f)

    files.sort()

    index = 1
    for f in files:
        print(f"{r(index)}: {f}")
        index += 1

    return files


def read_file(file_name: str, read_all=True) -> int:
    if ((error := is_file_ready(file_name)) < 0):
        print(f"Ошибка {error}")
        return False

    show_all = False

    print(f"{r("\n--- НАЧАЛО ФАЙЛА ---")}")

    if (read_all):
        with open(file_name, 'r') as file:
            content = file.read()
            print(content, end="")

            print(f"{r("--- КОНЕЦ ФАЙЛА ---")}")
            return True

    with open(file_name, 'r') as file:
        for line in file:
            print(line, end="")
            if (not (show_all)):
                u_input = m_input(
                    f"{r("Enter")}: следующая строка, {r("q")}: выйти, {r("r")}: вывести весь файл: "
                )
                clear_output()
            if (u_input == "q"):
                break
            if (u_input == "r"):
                show_all = True

    print(f"{r("--- КОНЕЦ ФАЙЛА ---")}")

    return True


def equils(string, rex):
    rex_input = " ".join(re.findall(rex, string))
    return len(rex_input.strip()) == len(string.strip())


def get_user_input(
        msg="",
        rex="[0-9a-zA-Zа-яА-Я ._-]{0,128}",
        default_value="",
        error_msg="Ошибка ввода",
) -> str:
    while not (equils(u_input := str(m_input(msg)), rex)):
        print(error_msg)

    if (u_input.strip() == ""):
        clear_output()

        return default_value

    clear_output()

    return u_input
