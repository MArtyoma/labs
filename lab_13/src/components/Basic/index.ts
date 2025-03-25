import { Sprite, Text } from 'pixi.js';
import Matter from "matter-js";

export class Basic {
  public x : number = 0;
  public y : number = 0;
  public w : number = 0;
  public h : number = 0;
  public r : number = 0;
  public body ?: Matter.Body;
  public pixiBody : Text | Sprite = new Sprite();

  public direction = {
    x: 0,
    y: 0,
  };

  constructor(x ?: number, y ?: number, name ?: string, r ?: number) {
    this.x = x || this.x;
    this.y = y || this.y;
    this.r = r || this.r;
  }

  public get() {
    return this.pixiBody;
  }

  public async init() {
    return this.pixiBody;
  }

  public render() {
    return true;
  }
}
