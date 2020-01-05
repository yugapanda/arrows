
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
      h: 0
    };
  }
}

const poss = [new Pos(100, 200, 10, 10), new Pos(500, 600, 5, 5)];

async function moving() {
  new Promise(r => {
    while (true) {
      poss.forEach(x => {
        x.move();
        // console.log(x.get());
      });
    }
  });
}