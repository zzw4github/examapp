
$("#tijiao").click(
    function () {
        eModal.ajax('flash', '测试成绩');
    }
);
$("#pre").click(
    function () {

    }
);
$(document).ready(
    function () {
            var tMinute = parseInt($("#tMinute").text());
            var tSecond = parseInt($("#tSecond").text());
            var t = tMinute*60+tSecond;
            function GetRTime() {
                if (t >= 0) {
                    var m = Math.floor(t / 60);
                    var s = Math.floor(t % 60);
                    $("#tMinute").html( m );
                    $("#tSecond").html( s );
                    --t;
                } else {
                    clearInterval(timer);
                    eModal.ajax('main/flash', '考试时间到，你的成绩是：');
                }
            }
            var timer = setInterval(GetRTime, 1000);
    });
$('#goto').keydown(function (e) {
    if (e.keyCode == 13) {
        $("form").submit();
    }
})
