import { Application } from 'pixi.js';
import { Player } from '../Player';
import { TextBlock } from '../Text';
import GameObject from '../GameObject';

const skillsAndQualities = [
    // Технические навыки
    "Знание JavaScript и его синтаксиса",
    "Понимание ООП",
    "Работа с DOM",
    "Асинхронное программирование",
    "Работа с API",
    "Git и системы контроля версий",
    "HTML и CSS",
    "Отладка кода",
    "Работа с промисами",
    "Алгоритмы и структуры данных",
    
    // Фреймворки и библиотеки
    "React",
    "Angular",
    "Vue.js",
    "Node.js",
    "Express.js",
    "Redux",
    "Vuex",
    "Angular CLI",
    "Webpack",
    "Babel",
    
    // Методологии разработки
    "Agile",
    "Scrum",
    "Kanban",
    "TDD",
    "CI/CD",
    "REST API",
    "WebSockets",
    "GraphQL",
    "Docker",
    "AWS/GCP",
    
    // Личные качества
    "Аналитическое мышление",
    "Коммуникабельность",
    "Проактивность",
    "Самостоятельность",
    "Внимание к деталям",
    "Критическое мышление",
    "Способность к обучению",
    "Стрессоустойчивость",
    "Организованность",
    "Ответственность",
    
    // Soft skills
    "Работа в команде",
    "Публичные выступления",
    "Наставничество",
    "Управление временем",
    "Решение конфликтов",
    "Клиентоориентированность",
    "Эмпатия",
    "Гибкость",
    "Инициативность",
    "Лидерские качества",
    
    // Дополнительные навыки
    "Документация кода",
    "Тестирование",
    "Оптимизация производительности",
    "Безопасность приложений",
    "Кроссбраузерная верстка",
    "Мобильная разработка",
    "Работа с базами данных",
    "SQL/NoSQL",
    "Микросервисы",
    "CI/CD инструменты",
    
    // Продвинутые темы
    "WebRTC",
    "PWA",
    "WebGL",
    "WebSocket",
    "Serverless",
    "Machine Learning",
    "Blockchain",
    "IoT",
    "AR/VR",
    "DevOps",
    
    // Базовые компетенции
    "Логическое мышление",
    "Работа с документацией",
    "Чтение кода",
    "Написание комментариев",
    "Рефакторинг",
    "Code review",
    "Проектирование UI",
    "Работа с макетами",
    "Оптимизация",
    "Тестирование производительности",
    
    // Дополнительные качества
    "Усидчивость",
    "Терпение",
    "Креативность",
    "Системное мышление",
    "Проблемное мышление",
    "Адаптивность",
    "Устойчивость к изменениям",
    "Способность к многозадачности",
    "Умение расставлять приоритеты",
    "Способность к самоанализу"
];

export default class Scene {
  private static context ?: Scene;
  private id : number = 0;
  private app = new Application();

  private block : {
    [prop : string] : Player | TextBlock | GameObject,
  } = {};

  private add(item : Player | TextBlock | GameObject) {
    this.block[this.id++] = item;
  }

  private constructor() {}

  async init() {
    await this.app.init({
      background: '#1099bb',
      width: window.innerWidth,
      height: window.innerHeight,
    });

    document.body.appendChild(this.app.canvas);

    this.add(new GameObject(
       0, window.innerHeight - 100,
    ));
    this.add(new Player());

    for (const item in this.block) {
      if (!Object.prototype.hasOwnProperty.call(this.block, item)) continue;
      this.app.stage.addChild(await this.block[item].init());
    }

    setInterval(async () => {
      const newText = new TextBlock(
        skillsAndQualities[Math.ceil(rand(0, skillsAndQualities.length - 1))],
        rand(100, window.innerWidth - 100),
        -200,
        // rand(0, window.innerHeight / 4),
        rand(-.4, .4),
      )
      this.add(newText);
      this.app.stage.addChild(await newText.init());
      console.log(this.block);
    }, 1000);

    this.app.ticker.add(() => {
      for (const item in this.block) {
        if (!Object.prototype.hasOwnProperty.call(this.block, item)) continue;
        const result = this.block[item].render();
        if (!result) {
          this.app.stage.removeChild(this.block[item].get());
          delete this.block[item];
        } 
      }
    });
  }

  public static async getEntity() {
    if (!this.context) {
      this.context = new Scene();
      await this.context.init();
    }

    return this.context;
  }
}

export function rand(min : number, max : number) {
    return (Math.random() * (max - min) + min);
}
