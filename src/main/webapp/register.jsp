<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script>
        $(function () {
            $("#username").change(function () {
                $.get("user?method=checkedUser&username='" + $("#username").val() + "'", function (datas) {
                    if (datas == 1) {
                        $("#usernameMsg").html("<p class='text-danger'>用户已存在</p>")
                        $("#registerBtn").attr("disabled", true)
                    } else {
                        $("#usernameMsg").html("<p class='text-success'>验证通过</p>");
                        $("#registerBtn").removeAttr("disabled")
                    }
                })
            })
            $("#pass").change(function () {
                var pass = $(this).val();
                if (pass.length <= 6) {
                    $("#helpBlockf").html("<p class='text-danger'> 密码长度不得低于6位</p>")
                }else{
                    $("#helpBlockf").html("<p class='text-success'>验证通过</p>")
                }
            })
            $("#passt").change(function (){
                var passt = $(this).val();
                if(passt === $("#pass").val()){
                      $("#helpBlocks").html("<p class='text-success'>验证通过</p>")
                }else{
                    $("#helpBlocks").html("<p class='text-danger'>前后密码不一致</p>")
                }
            })
            $("#email").change(function (){
                var email=$(this).val();
                var check_email=/^([a-zA-Z0-9_.-]+)@([da-z.-]+).([a-z.]{2,6})$/;
                if(email.test(check_email)){
                    $("#helpBlocke").hide();
                }
                $("#helpBlocke").show();
            })
        })
    </script>

    <title>注册</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">小米商城</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="center-block" style="margin-top: 80px;"><%--user?method=register--%>
            <form class="form-horizontal" action="user?method=register" method="post">

                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" id="username" name="uname" class="form-control col-sm-10"
                               placeholder="Username" required/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span class="help-block " id="usernameMsg"></span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" name="upassword" id="pass"
                               class="form-control col-sm-10" placeholder="Password" required/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlockf" class="help-block ">请不输入6位以上字符</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" class="form-control col-sm-10"
                               placeholder="Password Again" id="passt" required/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlocks" class="help-block ">两次密码要输入一致哦</span></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" name="uemail" class="form-control col-sm-10"
                               placeholder="Email" id="email" required/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlocke" class="help-block ">填写正确邮箱格式</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-8" style="width: 40%">
                        <label class="radio-inline"> <input type="radio"
                                                            name="usex" value="男" required> 男
                        </label> <label class="radio-inline"> <input type="radio"
                                                                     name="usex" value="女"> 女
                    </label>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-push-2">
                        <input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg"
                               style="width: 200px;" disabled/> &nbsp; &nbsp;
                        <input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"/>
                    </div>
                </div>
                <div>${registerMsg}</div>
            </form>

        </div>
    </div>
</div>

</body>
</html>