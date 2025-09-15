import bcrypt
from getpass import getpass

SALT = bcrypt.gensalt()


class UserAccount:
    def __init__(self, username, email, password):
        self.username = username
        self.email = email
        self.__password = self.get_hash(password)

    def set_password(self, new_password):

        old_password = getpass("Введите текущий пароль: ")

        if (not (self.check_password(old_password))):
            return print("Неверный пароль")

        self.__password = new_password
        print("Пароль успешно изменен")

    def check_password(self, password):
        return True if self.__password == self.get_hash(password) else False

    def get_hash(self, password):
        password_bits = password.encode('utf-8')
        return bcrypt.hashpw(password_bits, SALT)


account = UserAccount('Artem', 'artem@mail.ru', 'qwerty')
account.set_password('123')
