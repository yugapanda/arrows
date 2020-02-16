
//square.js
inlets = 1;
outlets = 1;

// floatメッセージに対応する関数．
// float,intメッセージは，msg_[int/float]で定義する
var msg_int = function (in_val) {

  var ss = [];
  for (var i = 0; i < in_val; i++) {
    ss.push([1]);
  }

  for (var i = 0; i < 16 - in_val; i++) {
    ss.push([0]);
  }

  var one = recUR(ss, []);

  var ans = outerUR(one);

  outlet(0, Array.prototype.concat.apply([], ans));
}

var outerUR = function (list) {


  if (isAllSameLength(list)) {
    return list;
  }
  if (list.length === 1) {
    return list;
  }

  var last = list[list.length - 1];
  var last2 = list[list.length - 2];

  if (last.length < last2.length) {
    return list;
  }

  return outerUR(recUR(list, [], list[list.length - 1].length));

}



/**
 * 
 * @param {Arrya<Number>} list 
 * @param {Array<Number>} acc 
 */
var recUR = function (list, acc, start) {

  if (list.length === 0) {
    return acc;
  }

  if (start < list[list.length - 1].length) {

    return acc.concat(list);
  }

  if (list.length === 1) {
    acc.push(list[0]);
    return acc;
  }

  var head = list[0];
  var last = list[list.length - 1];

  if (isAllZero(head)) {
    return acc.concat(list);
  }

  if (head.length >= last.length) {
    var next = head.concat(last);
    acc.push(next);
    var sub = list.slice(1, list.length - 1);
    return recUR(sub, acc, start);
  }


}

var isAllZero = function (list) {
  for (var i = 0; i < list.length; i++) {
    if (list[i] === 1) {
      return false;
    }
  }
  return true;
}

var isAllSameLength = function (list) {

  for (var i = 1; i < list.length; i++) {
    if (list[0].length !== list[i]) {
      return false;
    }
  }
  return true;

}

