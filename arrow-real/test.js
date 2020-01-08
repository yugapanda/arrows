const ArrowDetector = require("./domain/ArrowDetector");

const list = require("./frame.json");

const points = ArrowDetector.detection(list, 50, 150, 1280, 720, 100);
console.log(points);