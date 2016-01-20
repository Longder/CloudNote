/**
 * 登录和注册页面的js
 * Created by Longder on 2016/1/8.
 */
$(function () {
    var t = setTimeout("$('#zc').css({visibility:'visible'})", 800);

    //去到注册页面的点击事件
    $("#sig_in").click(function () {
        $("#dl").attr({class: "log log_out"});
        $("#zc").attr({class: "sig sig_in"});
    });
    //返回登录页面的点击事件
    $("#back").click(function () {
        $("#zc").attr({class: "sig sig_out"});
        $("#dl").attr({class: "log log_in"});
    });
    //密码框失去焦点事件
    $("#regist_password").blur(function () {
        var password = $("#regist_password").val();
        if (password.length < 6 && password.length >= 0) {
            $("#warning_2").show();
        }
        return false;
    }).focus(function () {    //密码框获得焦点事件
        $("#warning_2").hide();
    });


    //确认密码框失去焦点事件
    $("#final_password").blur(function () {
        var firstPassword = $("#regist_password").val();
        var finalPassword = $("#final_password").val();
        if (firstPassword != finalPassword) {
            $("#warning_3").show();
        }
        return false;
    }).focus(function () {  //确认密码框获得焦点事件
        $("#warning_3").hide();
    });
    //登录按钮点击事件
    $("#login").click(function () {
        //清空原有的提示信息
        $("#username_info").html("");
        $("#password_info").html("");
        var username = $("#username").val();
        var password = $("#password").val();
        var ok = true;
        //检测数据格式
        if (username == "") {
            ok = false;
            $("#username_info").html("用户名为空");
        }
        if (password == "") {
            ok = false;
            $("#password_info").html("密码为空");
        }
        //如果通过，发送ajax请求
        if (ok) {
            $.ajax({
                url: "user/login.do",
                type: "post",
                data: {"username": username, "password": password},
                dataType: "json",
                success: function (result) {
                    if (result.status == 0) {
                        //将返回的用户id写入Cookie
                        var id = result.data;
                        addCookie("userId", id, 2);
                        window.location.href = "edit.html";
                    } else if (result.status == 1) {
                        $("#username_info").html(data.message);
                    } else if (result.status == 2) {
                        $("#password_info").html(data.message);
                    }
                },
                error: function () {
                    alert("发生登录异常！");
                }
            });
        }
    });
    //注册按钮点击事件
    $("#regist_button").click(function () {
        console.log("开始注册");
        var username = $("#regist_username").val();
        var nickname = $("#nickname").val();
        var finalPassword = $("#final_password").val();
        //验证信息
        $("#warning_1").hide();
        var ok = true;
        if (username == "") {
            $("#warning_1>span").html("用户名不能为空");
            $("#warning_1").show();
            ok = false;
        }
        if (!$("#regist_password").blur()) {
            ok = false;
        }
        if (!$("#final_password").blur()) {
            ok = false;
        }
        if (ok) {
            //发送注册请求
            $.ajax({
                url: "user/regist.do",
                type: "post",
                data: {"username": username, "nickname": nickname, "password": finalPassword},
                dataType: "json",
                success: function (result) {
                    if (result.status == 0) {
                        alert("注册成功");
                        window.location.href = "log_in.html";
                    } else if (result.status == 1) {
                        $("#warning_1>span").html(result.message);
                        $("#warning_1").show();
                    }
                }, error: function () {
                    alert("注册用户失败！");
                }
            });
        }
    });
    //监听回车键
    $("body").keydown(function () {
        if (event.keyCode == 13) {
            //触发登录按钮点击
            $("#login").click();
        }
    });
});