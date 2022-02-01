// ==UserScript==
// @name         LeetCodeCN Pager
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  Fucking Pagination
// @author       Jayson
// @match        http*://leetcode-cn.com/*
// @icon         https://www.google.com/s2/favicons?domain=leetcode-cn.com
// @require      https://lib.baomitu.com/qs/6.10.1/qs.min.js
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    const LOCAL_PAGE_CACHE = "leetcode.problemset.page"

    const currentPageFromUri = () => {
        const query = Qs.parse(window.location.search.substr(1));
        if (query.page) {
            return parseInt(query.page)
        }
        return 1;
    }

    const isProblemSetPage = (path) => {
        return /problemset\/all/.test(path)
    }

    const storeCurrentPage = (page) => {
        window.localStorage.setItem(LOCAL_PAGE_CACHE, page)
    }

    const loadCurrentPage = () => {
        const page = window.localStorage.getItem(LOCAL_PAGE_CACHE);
        return page == null ? 1 : parseInt(page);
    }

    const checkAndStore = () => {
        console.log("current page: ", currentPageFromUri())
        console.log("cached page: ", loadCurrentPage())

        if (isProblemSetPage(window.location.pathname)) {
            if (currentPageFromUri() === 1 && loadCurrentPage() !== 1) {
                window.location.href = "/problemset/all/?page=" + loadCurrentPage()
            } else {
                storeCurrentPage(currentPageFromUri());
            }
        }
    }

    checkAndStore()
    document.addEventListener('click', (e) => {
        storeCurrentPage(currentPageFromUri());
        if (e.target.tagName.toLocaleLowerCase() === 'a') {
            if (isProblemSetPage(e.href)) {

            }
        }
    })

    document.addEventListener('ready', () => {
        document.querySelectorAll('a[href]').forEach((e) => {
            console.log({h: e.href, t: /problemset\/all/.test(e.href)})
            if (isProblemSetPage(e.href)) {
                console.log(e)
                e.href = "problemset/all/?page=" + loadCurrentPage()
            }
        })
    })
})();
