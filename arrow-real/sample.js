const rs2 = require('node-librealsense');
const {GLFWWindow} = require('glf-windows');
const {glfw} = require('glf-windows');

// A GLFW Window to display the captured image
const win = new GLFWWindow(1280, 720, 'Node.js Capture Example');

// Colorizer is used to map distance in depth image into different colors
const colorizer = new rs2.Colorizer();

// The main work pipeline of camera
const pipeline = new rs2.Pipeline();

// Start the camera
pipeline.start();

while (! win.shouldWindowClose()) {
  const frameset = pipeline.waitForFrames();
  // Build the color map
  const depthMap = colorizer.colorize(frameset.depthFrame);
  if (depthMap) {
    // Paint the images onto the window
    win.beginPaint();
    const color = frameset.colorFrame;
    glfw.draw2x2Streams(win.window, 2,
        depthMap.data, 'rgb8', depthMap.width, depthMap.height,
        color.data, 'rgb8', color.width, color.height);
    win.endPaint();
  }
}

pipeline.stop();
pipeline.destroy();
win.destroy();
rs2.cleanup();