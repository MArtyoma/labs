import { Sprite, Assets } from 'pixi.js';
import Physics, { COLLISION } from "../Physics";
import Matter from "matter-js";

export class Player {
  private x : number = 300;
  private y : number = 300;
  private w : number = 10;
  private h : number = 10;
  private player : Sprite = new Sprite();
  private body ?: Matter.Body;
  private velocity = 4;
  private jumpVelocity = 8;

  private direction = {
    x: 0,
    y: 0,
  };

  public get() {
    return this.player;
  }

  constructor(x ?: number, y ?: number) {
    this.x = x || this.x;
    this.y = y || this.y;

    window.addEventListener("keydown", (e) => {
      switch (e.key) {
        case "w": {
          this.direction.y = -this.jumpVelocity;
          break;
        }
        case "s": {
          this.direction.y = this.jumpVelocity;
          break;
        }
        case "a": {
          this.direction.x = -this.velocity;
          break;
        }
        case "d": {
          this.direction.x = this.velocity;
          break;
        }
      }
    });

    window.addEventListener("keyup", (e) => {
      switch (e.key) {
        case "w": {
          this.direction.y = 0;
          break;
        }
        case "s": {
          this.direction.y = 0;
          break;
        }
        case "a": {
          this.direction.x = 0;
          break;
        }
        case "d": {
          this.direction.x = 0;
          break;
        }
      }
    });
  }

  async init() {
    const texture = await Assets.load('bunny.png');
    this.player = new Sprite(texture);

    this.w = this.player.texture.width;
    this.h = this.player.texture.height;

    this.body = Physics.body({
      x: this.x,
      y: this.y,
      w: this.w,
      h: this.h,
      lockRotation: true,
      collision: COLLISION.player | COLLISION.ground | COLLISION.text | COLLISION.default,
    });

    Physics.getEntity().playerId = this.body.id;
  
    
    this.player.x = this.x;
    this.player.y = this.y;

    this.player.anchor.x = 0.5;
    this.player.anchor.y = 0.5;

    return this.player;
  }

  render() {
    if (!this.body) return;

    if (this.direction.y || this.direction.x) {
      Matter.Body.setVelocity(this.body, {
        x: this.direction.x,
        y: (this.direction.y && !this.body.velocity.y) ? this.direction.y : this.body.velocity.y,
      });
    }


    this.player.x = this.body.position.x;
    this.player.y = this.body.position.y;

    return true;
  }
}
