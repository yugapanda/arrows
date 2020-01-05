const request = require("request-promise");

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

const poss = [new Pos(100, 200, 0, 0), new Pos(500, 600, 0, 0), new Pos(500, 300, 25, 25), new Pos(200, 300, 30, 30)];

const sleep = msec => new Promise(resolve => setTimeout(resolve, msec));

async function main() {
  while (true) {
    const body = poss.map(x => {
      x.move();
      return x.get();
    });

    const options = {
      //uri: "http://192.168.24.12:8081/add",
      uri: "http://localhost:8081/add",
      body,
      method: "POST",
      json: true
    };

    try {
      await request(options);
    } catch (e) {
      //console.log(e);
    }

    await new Promise(r => setTimeout(r, 100));

  }
}

main();
