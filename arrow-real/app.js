const rs2 = require('node-librealsense');
const rp = require("request-promise");

const sleep = msec => new Promise(resolve => setTimeout(resolve, msec));

async function main() {

  const colorizer = new rs2.Colorizer();  // This will make depth image pretty
  const pipeline = new rs2.Pipeline();  // Main work pipeline of RealSense camera
  pipeline.start();  // Start camera

  while (1) {
    try {
      const frameset = pipeline.waitForFrames();  // Get a set of frames
      const depth = frameset.depthFrame;  // Get depth data
      const body = depth.getData().toString().split(",");
      console.log(JSON.stringify(body));

      // const options = {
      //   method: "POST",
      //   json: true,
      //   uri: "http://localhost:18081/add",
      //   body
      // }

      // const res = await rp(options);

      await sleep(1000);
    } catch (e) {
      console.log(e)
      await sleep(1000);
    }
  }
// Before exiting, do cleanup.
rs2.cleanup();
}

main();

