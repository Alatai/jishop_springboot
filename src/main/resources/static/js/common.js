'use strict';

/* @author alatai */

/**
 * 获取url参数
 */
function getUrlParams(param) {
    let search = location.search; // 查询部分的字符串
    let arrParam = []; // 参数数组
    let arrValue = []; // 存储匹配的参数值

    if (search !== '') {
        let index = 0;
        search = search.substr(1); // 去除‘?’
        arrParam = search.split('&');

        for (let item in arrParam) {
            let paramPrev = param + '='; // 参数前缀

            if (arrParam[item].indexOf(paramPrev) === 0 &&
                paramPrev.length < arrParam[item].length) {
                // URI解码避免出现乱码
                arrValue[index] = decodeURI(arrParam[item].substr(paramPrev.length));
                index++
            }
        }

        if (arrValue.length === 1) {
            return arrValue[0];
        } else if (arrValue.length === 0) {
            return null;
        } else {
            return arrValue;
        }
    }
}