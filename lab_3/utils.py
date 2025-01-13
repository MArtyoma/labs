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


def mInput(val) -> str:
    uInput = ""
    try:
        uInput = input(val)
    except KeyboardInterrupt:
        print("\nВыход из программы\n")
        quit()

    return uInput


def clearOutput(lines=1):
    for i in range(0, lines):
        print(CURSOR_UP_ONE + ERASE_LINE, end="")


def r(string: str) -> str:
    return f"{OKBLUE}{string}{ENDC}"


def isWritable(fileName: str):
    return os.access(fileName, os.R_OK)


def isFileExists(fileName: str):
    eFile = Path(os.path.join(getCurrentDir(), fileName))

    return eFile.is_file()


def isFileReady(fileName: str) -> int:
    if (isFileExists(fileName) and not (isWritable(fileName))):
        return PERMISSION_ERROR

    if (not (isFileExists(fileName))):
        return FILE_IS_NOT_EXISTS

    return 1


def getFreeSpace(dirname: str, size=GB, precision=4) -> float:
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


def currentFreeSpace() -> float:
    return getFreeSpace(getCurrentDir())


def getCurrentDir() -> str:
    return os.path.dirname(os.path.realpath(__file__))


def writeToFile(fileName: str, content: str, action="w") -> bool:
    if (isFileReady(fileName) == PERMISSION_ERROR):
        print(f"Ошибка доступа к файлу '{r(fileName)}'")
        return False

    with open(fileName, action) as file:
        file.write(content)
        print(content, end="")

    return True


def getCurrentFile(fileName=__file__) -> str:
    return os.path.basename(fileName)


def listFiles(exception="") -> list:
    current = getCurrentFile()
    exception = getCurrentFile(exception)

    path = getCurrentDir()
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


def readFile(fileName: str, readAll=True) -> int:
    if ((error := isFileReady(fileName)) < 0):
        print(f"Ошибка {error}")
        return False

    showAll = False

    print(f"{r("\n--- НАЧАЛО ФАЙЛА ---")}")

    if (readAll):
        with open(fileName, 'r') as file:
            content = file.read()
            print(content, end="")

            print(f"{r("--- КОНЕЦ ФАЙЛА ---")}")
            return True

    with open(fileName, 'r') as file:
        for line in file:
            print(line, end="")
            if (not (showAll)):
                uInput = mInput(
                    f"{r("Enter")}: следующая строка, {r("q")}: выйти, {r("r")}: вывести весь файл: "
                )
                clearOutput()
            if (uInput == "q"):
                break
            if (uInput == "r"):
                showAll = True

    print(f"{r("--- КОНЕЦ ФАЙЛА ---")}")

    return True


def equils(string, rex):
    rexInput = " ".join(re.findall(rex, string))
    return len(rexInput.strip()) == len(string.strip())


def getUserInput(
        msg="",
        rex="[0-9a-zA-Zа-яА-Я ._-]{0,128}",
        defaultValue="",
        errorMsg="Ошибка ввода",
) -> str:
    while not (equils(uInput := str(mInput(msg)), rex)):
        print(errorMsg)

    if (uInput.strip() == ""):
        clearOutput()

        return defaultValue

    clearOutput()

    return uInput
