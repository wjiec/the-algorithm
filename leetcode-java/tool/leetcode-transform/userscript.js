// ==UserScript==
// @name         Leetcode Transform
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        https://leetcode.cn/problems/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=leetcode.cn
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    let timer = setInterval(() => {
        const btns = document.querySelectorAll('[data-key=description-content] button')
        if (btns && btns.length) {
            const feedback = btns[5];
            feedback.innerHTML = feedback.innerHTML.replace('反馈', '转换')
            clearInterval(timer);
            timer = null;

            feedback.onclick = (e) => {
                e.preventDefault()
                e.stopPropagation()

                document.querySelectorAll('[data-key=description-content] pre').forEach((el) => {
                    el.innerText = el.innerText.replace(/\[/g, '{').replace(/\]/g, '}').replace(/"/g, '\'')
                })
            }
        }
    }, 1000)
})();
