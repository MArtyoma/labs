import {ru, Faker} from '@faker-js/faker';
import sqlite3 from 'sqlite3'
import {open} from 'sqlite'

// .headers on
// .mode column

export const faker = new Faker({
  locale: [ru],
});

function createWorker(id : number, shop : number) {
  return {
    id,
    shop,
    salary: faker.number.int({
      min: 10_000,
      max: 300_000,
      multipleOf: 1000
    }),
    name: faker.person.fullName(),
    position: faker.person.jobType(),
  }
}

function createShop(id : number) {
  return {
    id,
    name: faker.company.name(),
    balance: faker.number.int({
      min: 100_000,
      max: 3_000_000,
      multipleOf: 1000
    }),
  }
}

function createProduct(id : number) {
  return {
    id,
    name: faker.commerce.productName(),
    price: faker.commerce.price(),
  }
}

function getInt(max : number) {
  return faker.number.int({
    min: 1,
    max,
  });
}

(async () => {
    const db = await open({
      filename: '/app/index.db',
      driver: sqlite3.Database,
    });
    await db.exec('DELETE FROM shop');
    await db.exec('DELETE FROM product');
    await db.exec('DELETE FROM warehouse');
    await db.exec('DELETE FROM worker');

    const MAX = 100;

    console.log('INIT DB');
    for (let i = 1; i <= MAX; ++i) {
      // SHOP
      const shop = createShop(i);
      await db.run(
        'INSERT INTO shop (id, name, balance) VALUES (?, ?, ?)',
        shop.id,
        shop.name,
        shop.balance,
      ).catch(() => {});

      // PRODUCT
      const product = createProduct(i);
      await db.run(
        'INSERT INTO product (id, name, price) VALUES (?, ?, ?)',
        product.id,
        product.name,
        product.price,
      ).catch(() => {});

      // WAREHOUSE
      for (let j = 0; j < 10; ++j) {
        await db.run(
          'INSERT INTO warehouse (shop_id, product_id, quantity) VALUES (?, ?, ?)',
          getInt(MAX),
          getInt(MAX),
          faker.number.int({
            min: 1,
            max: 3_000,
          }),
        ).catch(() => {});
      }

    }

    // WORKER
    for (let i = 0; i < MAX * 10; ++i) {
      const worker = createWorker(i, getInt(MAX));
      await db.run(
        'INSERT INTO worker (worker_id, shop_id, salary, position, name) VALUES (?, ?, ?, ?, ?)',
        worker.id,
        worker.shop,
        worker.salary,
        worker.position,
        worker.name,
      ).catch(() => {});
    }
    console.log('FINISH');
})();
