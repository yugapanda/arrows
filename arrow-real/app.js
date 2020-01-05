const rs2 = require('node-librealsense');

const colorizer = new rs2.Colorizer();  // This will make depth image pretty
const pipeline = new rs2.Pipeline();  // Main work pipeline of RealSense camera
pipeline.start();  // Start camera

const frameset = pipeline.waitForFrames();  // Get a set of frames
const depth = frameset.depthFrame;  // Get depth data
const depthRGB = colorizer.colorize(depth);  // Make depth image pretty
const color = frameset.colorFrame;  // Get RGB image

// TODO: use frame buffer data
depthRGB.getData();
depth.getData().forEach(x => console.log(x));

// Before exiting, do cleanup.
rs2.cleanup();