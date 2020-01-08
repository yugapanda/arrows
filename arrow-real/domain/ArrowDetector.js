
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
        concentrations.push([start, concentration.length]);
      }
    }

    return concentrations.filter(x => !isNaN(x[1])).sort((a,b) => b[1] - a[1]);
    
  }

  /**
   * 
   * @param {Array<number>} list 
   */
  static average(list) {

    const sum = list.reduce((acc, now) => acc + Number.parseInt(now), 0);
    return sum / list.length;
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
          acc.push(point);
        }
      }
    }
    return acc;
  }
}
