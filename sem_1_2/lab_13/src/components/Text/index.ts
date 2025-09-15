import { Text } from 'pixi.js';
import Physics, { COLLISION } from "../Physics";
import Matter from "matter-js";
import { rand } from '../Scene';

export class TextBlock {
  private x : number = 300;
  private y : number = 600;
  private w : number = 10;
  private h : number = 10;
  private r : number = 0;
  private text : Text = new Text();
  private body ?: Matter.Body;
  private name : string = 'Text';

  private direction = {
    x: 0,
    y: 0,
  };

  constructor(name ?: string, x ?: number, y ?: number, r ?: number) {
    this.name = name || this.name;
    this.x = x || this.x;
    this.y = y || this.y;
    this.r = r || this.r;
  }

  public get() {
    return this.text;
  }

  async init() {
    this.text = new Text(
      this.name,
      {
        fontFamily : 'Arial',
        fontSize: rand(16, 32),
        fill : 0xeeeeee,
        align : 'center'
      },
    );

    this.w = this.text.width;
    this.h = this.text.height;

    this.body = Physics.body({
      x: this.x,
      y: this.y,
      w: this.w,
      h: this.h,
      collision: COLLISION.text | COLLISION.player | COLLISION.default,
      air: 0.2,
    });

    Matter.Body.setAngle(this.body, this.r);

    this.text.x = this.x;
    this.text.y = this.y;

    this.text.anchor.x = 0.5;
    this.text.anchor.y = 0.5;

    return this.text;
  }

  render() {
    if (!this.body) return;

    if (this.text.y > window.innerHeight) {
      return false;
    }

    this.text.x = this.body.position.x;
    this.text.y = this.body.position.y;
    this.text.rotation = this.body.angle;

    return true;
  }
}
