const rs2 = require('node-librealsense');
const detection = require("./domain/Detection");
const Arrow = require("./domain/Arrow");
const osc = require("node-osc");

const sleep = msec => new Promise(resolve => setTimeout(resolve, msec));

const oscClient = new osc.Client('127.0.0.1', 12001);

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

      const message = new osc.Message("/add");
      message.append(JSON.stringify(body));
      
      oscClient.send(message);
      await sleep(1);

    } catch (e) {
      await sleep(1000);
    }
  }
// Before exiting, do cleanup.
rs2.cleanup();
}

main();

