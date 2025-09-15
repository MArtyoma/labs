'use client';

import { useEffect, useRef } from 'react';
import Physics from '../Physics';
import Scene from '../Scene';

let ready = false;

export default function Portfolio() {
  console.log('render portfolio');

  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!ref.current) return;

    if (ready) return;

    ready = true;

    (async () => {
      Physics.getEntity();
      await Scene.getEntity();
    })();
  }, [ref]);

  return <>
    <header>
      <h2>МТУСИ БВТ 2401</h2>
    </header>
    <main>
      <div className='myFlex'>
        <p>Привет! Меня зовут Артем</p>
        <p>
          В этой игре на тебя падуют мои SKILL'ы<br />
          Попробуй увернись :)
        </p>
      </div>
      <div ref={ref} />
    </main>
    <footer>
      <a href="https://mtuci.ru/">Сайт ВУЗ'a</a>
      <a href="https://github.com/MArtyoma">Мой github</a>
    </footer>
  </>
}
