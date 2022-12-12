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

    const container = document.querySelector('.w-full .mt-3.flex')

    /**
     * 添加一个按钮到头部的工具栏中
     *
     * @param text {string}
     * @param eventListener {(ev: MouseEvent, el: HTMLElement) => void}
     */
    const appendToHeadBar = (text, eventListener) => {
        if (container) {
            const wrapper = document.createElement('div')
            {
                const content = document.createElement('div')
                content.style.cursor = 'pointer'
                content.classList.add('inline-block', 'rounded-[21px]', 'bg-opacity-[.15]', 'px-2.5', 'py-1',
                    'text-xs', 'font-medium', 'capitalize', 'dark:bg-opacity-[.15]',
                    'bg-blue', 'dark:bg-dark-blue', 'text-blue', 'dark:text-dark-blue')
                content.innerText = text
                wrapper.append(content)

                content.addEventListener('click', (e) => {
                    e.preventDefault()
                    e.stopPropagation()
                    if (typeof eventListener === 'function') {
                        eventListener(e, content)
                    }
                })
            }
            container.appendChild(wrapper)
        }
    }

    /**
     * 遍历所有子节点, 并替换其中可能存在的字符
     *
     * @param element {HTMLElement | ChildNode}
     */
    const foreachChildren = (element) => {
        if (element.hasChildNodes()) {
            element.childNodes.forEach(foreachChildren)
        } else {
            element.textContent = element.textContent
                .replaceAll('[', '{')
                .replaceAll(']', '}')
                .replaceAll('"', "'")
        }
    }

    // 添加一个 "转换" 按钮, 用于将描述中的输入转换为更有利于复制的形式
    appendToHeadBar('转换', () => {
        const codeblocks = document.querySelectorAll('pre')
        if (codeblocks && codeblocks.length !== 0) {
            codeblocks.forEach(foreachChildren)
        }
    })

    // 添加一个 "语言" 按钮, 用于切换题目的描述语言
    appendToHeadBar('语言', () => {
        document.querySelector('#headlessui-popover-panel-2')
            .querySelector('.cursor-pointer')
            .click()
    })
})();
