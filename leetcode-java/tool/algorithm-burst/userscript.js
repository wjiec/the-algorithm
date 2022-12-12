// ==UserScript==
// @name         Algorithm Burst
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       Jayson Wang
// @match        https://leetcode.cn/problems/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=leetcode.cn
// @grant        none
// @run-at       document-start
// @require      https://jpillora.com/xhook/dist/xhook.min.js
// ==/UserScript==
// noinspection JSUnresolvedVariable,JSUnresolvedFunction

(function() {
    'use strict';

    /**
     * 添加一个按钮到头部的工具栏中
     *
     * @param text {string}
     * @param eventListener {(ev: MouseEvent, el: HTMLElement) => void}
     */
    const appendToHeadBar = (text, eventListener) => {
        const container = document.querySelector('.w-full .mt-3.flex')

        if (container) {
            const wrapper = document.createElement('div')
            {
                const button = document.createElement('div')
                button.style.cursor = 'pointer'
                button.classList.add(
                    'inline-block', 'rounded-[21px]', 'bg-opacity-[.15]', 'px-2.5', 'py-1',
                    'text-xs', 'font-medium', 'capitalize', 'dark:bg-opacity-[.15]',
                    'bg-blue', 'dark:bg-dark-blue', 'text-blue', 'dark:text-dark-blue')
                button.innerText = text
                wrapper.append(button)

                button.addEventListener('click', (ev) => {
                    ev.preventDefault()
                    ev.stopPropagation()
                    if (typeof eventListener === 'function') {
                        eventListener(ev, button)
                    }
                })
            }
            container.appendChild(wrapper)
        }
    }

    const graphql = {} // 记录所有相关的请求
    xhook.after((request, response) => {
        if (request.url === '/graphql/') {
            const matches = request.body.match(/query\s(\w+)/)
            if (matches && matches.length > 1) {
                try {
                    graphql[matches[1]] = JSON.parse(response.text)
                } catch (ignored) {}
            }
        }
    })

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

    /**
     * 将HTML解析成无标签的纯文本
     *
     * @param html {string}
     * @returns {string}
     */
    const unwrapHTML = (html) => {
        const div = document.createElement('div')
        div.innerHTML = html
        return div.innerText
    }

    /**
     * 根据标题信息和内容信息生成模板内容
     *
     * @param titleResponse {object}
     * @param contentResponse {object}
     */
    const renderCodeTemplate = (titleResponse, contentResponse) => {
        const content = unwrapHTML(contentResponse.content)
        const finalContent = renderTemplate(commentTemplate, [
            ['TITLE', `${titleResponse.questionFrontendId}. ${titleResponse.title}`],
            ['URL', window.location.href],
            ['CONTENT', content.split('\n').map(text => ` * ${text}`).join('\n')]
        ])

        navigator.clipboard.writeText(finalContent).then(() => {})
    }

    /**
     * 根据模板以及数据生成对应的文本
     *
     * @param template {string}
     * @param data {Map<string, string> | string[][]}
     */
    const renderTemplate = (template, data) => {
        new Map(data).forEach((value, key) => {
            template = template.replaceAll(`#{${key}}`, value)
        })
        return template
    }

    // 等到界面渲染完成(编辑器渲染完成)之后为页面添加按钮
    new MutationObserver(function() {
        if (document.querySelector('.monaco-editor')) {
            this.disconnect()

            // 添加一个 "转换" 按钮, 用于将描述中的输入转换为更有利于复制的形式
            appendToHeadBar('转换', () => {
                const codeblocks = document.querySelectorAll('pre')
                if (codeblocks && codeblocks.length !== 0) {
                    codeblocks.forEach(foreachChildren)
                }
            })

            // 添加一个 "模板" 按钮, 用于复制题目的描述模板
            appendToHeadBar('模板', () => {
                const questionTitle = graphql.questionTitle.data.question;
                const questionContent = graphql.questionContent.data.question;
                if (questionTitle && questionContent) {
                    renderCodeTemplate(questionTitle, questionContent)
                }
            })
        }
    }).observe(document, {childList: true, subtree: true})

    // 注释模板
    const commentTemplate = `/**\n * #{TITLE}\n *\n * #{URL}\n *\n#{CONTENT}\n */`
})();
