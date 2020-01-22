const ArrowDetector = require("./ArrowDetector");
const Range = require("./Range");

module.exports = function detection(list) {

    const filterd = list.filter((x, i) => i % 10 == 0 && (Math.trunc(i / 1280)) % 10 == 0);

    const points = ArrowDetector.detection(filterd, new Range(50, 300), 10, 5, 128, 72).filter(x => x.length > 5);

    return points.map(x => calcAverage(x));

}


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
        return [Math.trunc(ans[0] / len) * 10, Math.trunc(ans[1] / len) * 10];
    } catch (e) {
        //console.log(e);
    }
}




