// jscs:disable requireCamelCaseOrUpperCaseIdentifiers

'use strict';
var count = 0;
var timerId;

function fnTimer(){
    count++;
    //Date是js中标准的类
    var now = new Date();//得到当前时间
    var year = now.getFullYear();
    var month = now.getMonth()+1;
    var date = now.getDate();
    var week = now.getDay();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    var h=hour+"AM";

    var str = "今天是：" + year + "年" + month + "月" + date + "日星期" +week+"&nbsp;&nbsp;";

    timer.innerHTML = str;


}

//写在函数外
//timerId = setInterval(fnTimer,1000);

$(function() {

    $('#scroll_top').on('click', function() {
        this.disabled = true;

        // 'html' for Mozilla Firefox, 'body' for other browsers
        $('body, html').animate({
            scrollTop: 0
        }, 800, $.proxy(function() {
            this.disabled = false;
        }, this));

        this.blur();
    });
    // Dropdown fix
    $('.dropdown > a[tabindex]').on('keydown', function(event) {
        // 13: Return

        if (event.keyCode == 13) {
            $(this).dropdown('toggle');
        }
    });
    $('.dropdown-menu .disabled, .dropdown-header, .dropdown-divider').on('click', function(event) {
        event.stopPropagation();
    });

    $('[data-submenu]').submenupicker();

    //hljs.initHighlighting();
});