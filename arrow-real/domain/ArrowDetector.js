const Range = require("./Range");
const Depth = require("./Depth");
const DepthList = require("./DepthList");
const Point = require("./Point");

module.exports = class ArrowDetector {

  /**
   * 
   * @param {Array<Number>} list 
   * @param {Range} depthRange 
   * @param {Number} maxDistance 
   * @param {Number} minPointsNum 
   * @param {Number} screenWidth 
   * @param {Number} screenHeight 
   */
  static detection(list, depthRange, maxDistance, minPointsNum, screenWidth, screenHeight) {

    //1. 有効な範囲の点をフィルタする(範囲は変数にする)
    const inRangePoints = new Map(list.map((x, i) => [i, new Depth(new Point(i % screenWidth, Math.floor(i / screenHeight)), x)]));
    inRangePoints.forEach((v, k, m) => {
      if (v.depth < depthRange.min || depthRange.max < v.depth) {
        m.delete(k);
      }
    });


    //1. 有効な点から最小距離にある有効な点を求め、その点の最小距離にある点を求める、という操作を有効な点がなくなるまで繰り返す。
    const dl = new DepthList(inRangePoints, 1280, 720);
    const clusters = dl.getClusters(maxDistance, minPointsNum);

    return clusters;


    //1. 一度使用された点は対象としない。
    //1. 最小距離でも、指定された距離より遠い場合には対象としない(有効距離は変数にする)
    //1. 全ての点を走査し終わったら、各組について有効組条件を満たしているかでフィルタする(有効組条件は、主に組に含まれる有効点の数で、これも変数にする)
    //1. 各組の中心を求める
  }

}
