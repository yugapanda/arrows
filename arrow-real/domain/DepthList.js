const ArrayUtil = require("../util/ArrayUtil");
const Point = require("./Point");
const Depth = require("./Depth");

module.exports = class DepthList {

    /**
     * 
     * @param {Map<Number, Depth>} map 
     * @param {Number} width 
     * @param {Number} height 
     */
    constructor(map, width, height) {
        this.depthMap = map;
        this.width = width;
        this.height = height;
    }

    getClusters(nearLimit) {
        const cluster = [];
        while (this.depthMap.size > 0) {

            const d = this.depthMap.entries().next().value;
            // console.log(d);
            const local = this.getCluster(d, nearLimit);

            cluster.push(local);
            //local.forEach(x => console.log(x));
            local.forEach(x => this.depthMap.delete(x[1].point.getIndex()));
            this.depthMap.delete(d[0]);

        }

        return cluster;
    }

    getCluster(target, nearLimit) {
        const results = [];
        while (true) {
            //console.log(target);
            const targetIndex = this.rangeIndexer(target[1].point.x, target[1].point.y, nearLimit, nearLimit);
            // console.log(targetIndex);
            const depthInRange = this.rangeExtractor(targetIndex);
            console.log(depthInRange);
            const mostNear = this.getMostNear(target[1], depthInRange);
            //console.log(mostNear);
            if (mostNear != undefined) {
                //console.log(mostNear);
                results.push([mostNear.depth.point.getIndex(), mostNear.depth]);
                target = [mostNear.depth.point.getIndex(), mostNear.depth];
            } else {
                return results;
            }
        }
    }

    /**
     * xとyの値を入力すると、その位置の値を返す。
     * 存在しない場合にはundefinedを返す。
     * @param {Number} x 
     * @param {Number} y 
     */
    getDepth(x, y) {
        return this.depthMap.get(y * this.width + x);
    }

    /**
     * 対象となるdepthと、比較したいdepthのリストから、最短距離のものを返す
     * @param {Depth} depth 
     * @param {Array<Depth>} depthList
     * @returns {{dist: Number, depth: Depth}}
     */
    getMostNear(depth, depthList, maxDist) {
        return depthList.reduce((acc, now) => {
            if (depth.point.x == now.point.x && depth.point.y == now.point.y) {
                return acc;
            }
            const dist = Math.abs(depth.point.x - now.point.x) + Math.abs(depth.point.y - now.point.y);
            //console.log(dist);
            if (acc == undefined || acc.dist > dist) {
                return {
                    dist,
                    depth: now
                }
            }
            return acc;
        }, undefined);
    }


    /**
     * xとyで指定された位置を中心に、左右にw, 上下にhの範囲のindexを返す
     * @param {Number} x 
     * @param {Number} y 
     * @param {Number} w 
     * @param {Number} h 
     */
    rangeIndexer(x, y, w, h) {
        const wRange = ArrayUtil.range(w * -1, w).map(w => w + x);
        const hRange = ArrayUtil.range(h * -1, h).map(h => h + y);
        const index = wRange.map(w => {
            return hRange.map(h => {
                return w +  (h * this.width);
            });
        });
        return ArrayUtil.flatten(index);
    }

    /**
     * indexの配列を入力すると、undefinedでない配列を返す。
     * その範囲が全てundefinedであった場合には、空の配列が返る。
     * @param {Array<Number>} index 
     */
    rangeExtractor(index) {
        return index.map(x => this.depthMap.get(x)).filter(x => x != undefined);
    }


};