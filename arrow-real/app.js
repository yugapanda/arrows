const rs2 = require('node-librealsense');
const rp = require("request-promise");
const detection = require("./domain/Detection");
const Arrow = require("./domain/Arrow");

const sleep = msec => new Promise(resolve => setTimeout(resolve, msec));

async function main() {

  const colorizer = new rs2.Colorizer();  // This will make depth image pretty
  const pipeline = new rs2.Pipeline();  // Main work pipeline of RealSense camera
  pipeline.start();  // Start camera

  while (1) {
    try {
      const frameset = pipeline.waitForFrames();  // Get a set of frames
      const depth = frameset.depthFrame;  // Get depth data
      const points = depth.getData().toString().split(",");

      const pairs = detection(points);

      const body = Arrow.bulk(pairs);


      const options = {
        method: "POST",
        json: true,
        uri: "http://localhost:8081/add",
        body
      }

      const res = await rp(options);

    } catch (e) {
      await sleep(1000);
    }
  }
// Before exiting, do cleanup.
rs2.cleanup();
}

main();

