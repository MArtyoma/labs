import Matter from 'matter-js';

const Engine = Matter.Engine,
  Render = Matter.Render,
  Runner = Matter.Runner,
  MouseConstraint = Matter.MouseConstraint,
  Mouse = Matter.Mouse,
  Composite = Matter.Composite,
  Bodies = Matter.Bodies;

export const COLLISION = {
  default: 0x0001,
  player: 0x0002,
  text: 0x0004,
  ground: 0x0008,
};

export default class Physics {
  private static context : Physics;
  private world : Matter.World;

  public playerId = 0;
  public groundId = 0;

  private constructor() {
    const engine = Engine.create();
    this.world = engine.world;

    const render = Render.create({
      // element: document.body,
      engine: engine,
      options: {
        width: window.innerWidth,
        height: window.innerHeight,
        showAngleIndicator: true,
        // pixelRatio: 1,
      }
    });

    render.canvas.className = 'physics';

    Render.run(render);

    const runner = Runner.create();
    Runner.run(runner, engine);

    var mouse = Mouse.create(render.canvas),
      mouseConstraint = MouseConstraint.create(engine, {
      mouse: mouse,
      constraint: {
        stiffness: 0.2,
        render: {
          visible: false
        }
      }
    });

    Composite.add(this.world, mouseConstraint);

    render.mouse = mouse;

    Matter.Events.on(engine, 'collisionStart', (event) => {
          var pairs = event.pairs;

          for (var i = 0; i < pairs.length; i++) {
              var pair = pairs[i];

              if (pair.bodyA.id === this.playerId && pair.bodyB.id !== this.groundId) {
                gameOver();
              }

              if (pair.bodyB.id === this.playerId && pair.bodyA.id !== this.groundId) {
                gameOver();
              }

              if (pair.bodyB.id !== this.groundId && pair.bodyB.id !== this.playerId) {
                pair.bodyB.collisionFilter = {
                  mask: 0,
                };
              }

              if (pair.bodyA.id !== this.groundId && pair.bodyA.id !== this.playerId) {
                pair.bodyA.collisionFilter = {
                  mask: 0,
                };
              }
          }
      });
  }

  public static body(props : {
    x: number,
    y: number,
    w: number,
    h: number,
    lockRotation ?: boolean,
    collision ?: number,
    fixed ?: boolean,
    air ?: number,
  }) {
    const body = Bodies.rectangle(props.x, props.y, props.w, props.h, {
      inertia: props.lockRotation ? Infinity : undefined,
      collisionFilter: {
        category: props.collision,
      },
      frictionAir: props.air ?? 0,
      isStatic: props.fixed,
    });
    Composite.add(Physics.context.world, [body]);
    return body;
  }

  public static getEntity() {
    if (!this.context) {
      this.context = new Physics();
    }

    return this.context;
  }
}

function gameOver() {
  alert('GAME OVER');
  document.location.href = '/';
}
