module.exports = class Arrow {

    /**
     * 
     * @param {number} x x position
     * @param {number} y y position
     * @param {number} h hight
     */
    constructor(x, y, h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    /**
     * 
     * @param {Array<Array<number>>} arr 
     */
    static bulk(arr) {
        return arr.map(x => new Arrow(x[0], x[1], 100));
    }

}