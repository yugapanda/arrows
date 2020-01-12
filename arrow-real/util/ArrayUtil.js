module.exports = class ArrayUtil {
 
    /**
     * 
     * @param {Number} start 
     * @param {Number} stop 
     * @returns {Array<Number>} 
     */
    static range (start, stop) {
        return Array.from({ length: (stop - start) + 1}, (_, i) => start + i);
    }
    
    static flatten(nested) {
        return Array.prototype.concat.apply([], nested);
    }

};