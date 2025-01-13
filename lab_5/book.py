class Book():
    def __init__(self, title: str, author: str, year: int):
        self.title = title
        self.author = author
        self.year = year

    def get_info(self) -> "str, str, int":
        return self.title, self.author, self.year


book = Book('1', '2', 3)
print(book.get_info())
