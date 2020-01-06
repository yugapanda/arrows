
module.exports = class ArrowDetector {

  /**
   * 
   * @param {Array<number>} list list of depth
   * @param {number} min threshold min value
   * @param {number} max threshold max value
   */
  static detection(list, min, max, screenWidth, screenHeight, rectSize) {


    const sw = (screenWidth - rectSize) / rectSize;
    const sh = (screenHeight - rectSize) / rectSize;
    const concentrations = [];

    for (let hi = 0; hi < sh; hi++) {
      for (let wi = 0; wi < sw; wi++) {
        const w = wi * rectSize;
        const h = hi * rectSize;
        const start = w + (h * screenWidth);
        const concentration = ArrowDetector.rectExtractor(start, rectSize, screenWidth, list, min, max);
        concentrations.add(ArrowDetector.average(concentration));
      }
    }

    return concentrations.sort((a,b) => a - b).splice(0, 5);
    

  }

  /**
   * 
   * @param {Array<number>} list 
   */
  average(list) {
    return list.reduce((acc, now) => acc + now) / list.length;
  }

  /**
   * 指定した位置から 縦size pix * 横 size pix 分のindexを返す
   * @param {number} start 
   * @param {number} size 
   * @param {number} screenWidth 
   * @returns {Array<number>} indexes
   */
  static rectExtractor(start, size, screenWidth, list, min, max) {
    const acc = [];
    for (let w = 0; w < size; w++) {
      for (let h = 0; h < size; h++) {
        const point = list[start + w + (screenWidth * h)]
        if (min < point && point < max) {
          acc.add(point);
        } else {
          0
        }
      }
    }
    return acc;
  }
}
