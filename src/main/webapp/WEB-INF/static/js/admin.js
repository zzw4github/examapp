$(function () {
    $("#usrBtn").click(function () {
        var usr =$("#usrInp").val();
        var usrOrd = $("#usrOrd").val();
        $.post("query",{"userName":usr,"userOrder":usrOrd},function(result){
            var html =' <div class="tablebody">';
            $.each(result, function (index, item) {
                html +='<div class="row" >';
                html += '<div class="col-xs-2 ">' + item.name + '</div>';
                html += '<div class="col-xs-2 ">' + item.xingming + '</div>';
                html += '<div class="col-xs-2 ">' + item.tel + '</div>';
                html += '<div class="col-xs-2 ">' + item.situation + '</div>';
                html += '<div class="col-xs-2"> <button class="btn btn-success btn-xs" id="updUsrBtn" data-toggle="#reviseUser">修改 </button> <button class="btn btn-danger btn-xs" data-toggle="modal"data-target="#deleteUser" id="delUsrBtn"+index>删除 </button></div>';
                html += '</div>';
            });
            html += '</div>';
            html +=' <footer class="footer"> <ul class="pagination"> <li> <select> <option>1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option> <option>6</option> <option>7</option>'+
               ' <option>8</option> <option>9</option> <option>10</option> </select>页 </li> <li class="gray">共20页 </li> <li> <i class="glyphicon glyphicon-menu-left"> </i> <li> <i class="glyphicon glyphicon-menu-right"> </i>'
                    +'</li> </ul> </footer>';
            $("#usrDiv").append($(html));
        });
    });
    $("#logout").click(function(){
        $.post("logout")
    });

    $('#savUpdUsrBtn').click(function(){
        var me = this;
       var divs =  $(me).parent().siblings();
        var data='{';
        eModal.confirm("确认修改此用户吗", null)
            .then(function(){
                $('#reviseUser').modal('hide');
                $('#updUsrForm input').each(function(){
                    if($(this).attr('type')==='radio' &&$(this).attr('checked')==='checked'){
                        data+='"'+$(this).attr("name") +'":"'+$(this).val()+'",';
                    }else if($(this).attr('type')!=='radio'){
                        data+='"'+$(this).attr("name") +'":"'+$(this).val()+'",';
                    }
                });
                data = data.substr(0, data.length-1);
                data+="}";
                $.ajax({
                    'type' :'POST',
                    'url' :'updateUser',
                    'data':data,
                    'contentType':'application/json',
                   'dataType' :'json',
                    success:function(result){
                       $(divs[0]).val(result.name) ;
                        $(divs[1]).val(result.xingming) ;
                        $( divs[3]).val(result.tel) ;
                        $(divs[4]).val(result.situation) ;
                },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log(XMLHttpRequest.status);
                        console.log(XMLHttpRequest.readyState);
                        console.log(textStatus);
                    }

                });
            });
    });
})

$(document).on('click','#updUsrBtn',function(){
    var me = this;
    $(me).parent().siblings().each(function(index){
        var inputs = $('#reviseUser input');
        $(inputs.get(index)).val($(this).text());
    });
    $('#reviseUser').modal('show');

});

$(document).on('clock','#delUsrBtn',function(){
    var me = this;
    eModal.confirm("确认删除此用户吗", null)
        .then(function(){
            var usr =$(me).parent().siblings(':first').html();
            $.post("query",{"userName":usr},function(result){
                if(result.messege=='success'){
                    $(me).parent().parent().remove();
                    eModal.alert("用户删除成功");
                }
            });
        });
});
