const ArrowDetector = require("./domain/ArrowDetector");
const Range = require("./domain/Range");
const list = require("./frame.json");
const fs = require("fs");

/**
 * 
 * @param {Array<Point>} arr 
 */
const calcAverage = (arr) => {
    console.log(arr);

    try {
        const len = arr.length;
        const ans = arr.reduce((acc, now) => {
            console.log(now[1].point.x);
            return [acc[0] + now[1].point.x, acc[1] + now[1].point.y]
        }, [0, 0]);
        return [Math.trunc(ans[0] / len) * 10 , Math.trunc(ans[1] / len) * 10];
    } catch (e) {
        //console.log(e);
    }
}

const filterd = list.filter((x, i) => i % 10 == 0 && (Math.trunc(i / 1280)) % 10 == 0);

console.log(list.length);
console.log(filterd.length);

const points = ArrowDetector.detection(filterd, new Range(50, 150), 2, 5, 128, 72).filter(x => x.length > 5);

console.log(points.length);

const p = points.map(x => calcAverage(x));


fs.writeFileSync("result.json", JSON.stringify(p));


