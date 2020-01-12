module.exports = class Point {
    /**
     * 
     * @param {Number} x 
     * @param {Number} y 
     */
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param {Number} index 
     */
    static makePoint(index) { 
        return new Point(index % width, index / window);
    }

    /**
     * 
     * @param {Number} width 
     */
    getIndex(width) {
        return this.x + (this.y * width);
    }
}