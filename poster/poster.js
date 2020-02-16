const request = require("request-promise");
const osc = require("node-osc");

const oscClient = new osc.Client('127.0.0.1', 12001);


const width = 1000;
const height = 920;

class Pos {
  constructor(x, y, addX, addY) {
    this.x = x;
    this.y = y;
    this.addX = addX;
    this.addY = addY;
  }

  move() {
    this.x += this.addX;
    this.y += this.addY;
    if (this.x < 0 || this.x > width) {
      this.addX *= -1;
    }

    if (this.y < 0 || this.y > height) {
      this.addY *= -1;
    }
  }

  get() {
    return {
      x: this.x,
      y: this.y,
      h: 100
    };
  }
}

const poss = [new Pos(100, 200, 0, 0),
new Pos(500, 600, 0, 0),
new Pos(500, 300, 3, 3),
new Pos(200, 300, 5, 5),
new Pos(200, 300, 8, 5),
new Pos(200, 300, 5, 8),
new Pos(500, 300, 2, 3),
new Pos(500, 700, 5, 2),
new Pos(100, 100, 2, 2),];

const sleep = msec => new Promise(resolve => setTimeout(resolve, msec));

async function main() {
  while (true) {
    const body = poss.map(x => {
      x.move();
      return x.get();
    });

    const message = new osc.Message("/add");
    message.append(JSON.stringify(body));


    try {
      oscClient.send(message);
    } catch (e) {

    }

    await new Promise(r => setTimeout(r, 100));

  }
}

main();
