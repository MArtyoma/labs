import sqlite3
# .headers on
# .mode column

connection = sqlite3.connect('index.db')
cursor = connection.cursor()

cursor.execute('''
    CREATE TABLE IF NOT EXISTS shop (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    balance FLOAT NOT NULL);
''')

cursor.execute('''
    CREATE TABLE IF NOT EXISTS product (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    price FLOAT NOT NULL);
''')

cursor.execute('''
    CREATE TABLE IF NOT EXISTS  warehouse (
    shop_id INTEGER REFERENCES shop (id),
    product_id INTEGER REFERENCES product (id),
    quantity INTEGER NOT NULL,
    PRIMARY KEY (shop_id, product_id));
''')

try:
    cursor.execute('''
    INSERT INTO shop (id, name, balance) VALUES (?, ?, ?)
    ''', (1, 'пятерочка', 31))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO shop (id, name, balance) VALUES (?, ?, ?)
    ''', (2, 'перекресток', 133))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO product VALUES (?, ?, ?);
    ''', (1, 'молоко', 100))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO product VALUES (?, ?, ?);
    ''', (2, 'хлеб', 25))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO product VALUES (?, ?, ?);
    ''', (3, 'хлеб', 30))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO warehouse VALUES (?, ?, ?);
    ''', (1, 1, 20))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO warehouse VALUES (?, ?, ?);
    ''', (1, 2, 10))
except sqlite3.Error:
    pass

try:
    cursor.execute('''
    INSERT INTO warehouse VALUES (?, ?, ?);
    ''', (2, 1, 30))
except sqlite3.Error:
    pass


cursor.execute('SELECT * FROM Shop')
shop = cursor.fetchall()

print('\nSHOP')
for item in shop:
    print(item)

cursor.execute('SELECT * FROM product')
shop = cursor.fetchall()

print('\nPRODUCT')
for item in shop:
    print(item)

cursor.execute('SELECT * FROM warehouse WHERE shop_id = 1')
shop = cursor.fetchall()

print('\nWAREHOUSE WHERE 1')
for item in shop:
    print(item)

connection.commit()
connection.close()
