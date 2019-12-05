$(function () {
    $.ajaxSetup({
        complete:function (XMLHttpRequest) {
            //获取响应头
            console.log(XMLHttpRequest);
            var isAjax = XMLHttpRequest.getResponseHeader("isAjax");
            if (isAjax == "timeout"){
                if (window.top != window.self){
                    top.location.href = "/login.jsp";
                }else {
                    window.location.href="/login.jsp";
                }
            }
        }
    })
})
