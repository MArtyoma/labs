import { Basic } from "../Basic";
import { Sprite, Assets, Text } from 'pixi.js';
import Physics, { COLLISION } from "../Physics";

export default class GameObject extends Basic {
  public async init() {
    const texture = await Assets.load('ground.jpg');
    this.pixiBody = new Sprite(texture);

    this.w = this.pixiBody.texture.width;
    this.h = this.pixiBody.texture.height;

    this.x += this.w / 2;
    this.y += this.h / 2;

    this.body = Physics.body({
      x: this.x,
      y: this.y,
      w: this.w,
      h: this.h,
      lockRotation: true,
      fixed: true,
      collision: COLLISION.player | COLLISION.ground | COLLISION.default,
    });

    Physics.getEntity().groundId = this.body.id;

    this.pixiBody.x = this.x;
    this.pixiBody.y = this.y;

    this.pixiBody.anchor.x = 0.5;
    this.pixiBody.anchor.y = 0.5;

    return this.pixiBody;
  }
}
