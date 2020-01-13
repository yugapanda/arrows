const ArrowDetector = require("./domain/ArrowDetector");
const Range = require("./domain/Range");
const list = require("./frame.json");


const points = ArrowDetector.detection(list, new Range(50, 500), 1, 5, 128, 72);
points.filter(x => x.length !== 0).forEach(x => console.log(x));