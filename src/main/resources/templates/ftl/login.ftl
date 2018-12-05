<!DOCTYPE>
<html>
<head>
    <title>登录</title>
</head>
<link href="static/css/loginstyle.css" rel="stylesheet" type="text/css" media="all" />
<#--<script>var __links = document.querySelectorAll('a');function __linkClick(e) { parent.window.postMessage(this.href, '*');} ;for (var i = 0, l = __links.length; i < l; i++) {if ( __links[i].getAttribute('data-t') == '_blank' ) { __links[i].addEventListener('click', __linkClick, false);}}</script>-->
<script src="static/js/jquery.min.js"></script>
<script src="static/js/spop.js"></script>
<link rel="stylesheet" href="static/css/spop.css">
<body>
<!-- contact-form -->
<div class="message warning">
    <div class="inset">
        <div class="login-head">
            <h1>管理员登录</h1>
        </div>
        <form id="defaultForm">
            <li>
                <input type="text" name="account" class="text" placeholder = '账号'><a class=" icon user"></a>
            </li>
            <div class="clear"> </div>
            <li>
                <input type="password" name="password" placeholder = '密码' onkeydown=KeyDown() > <a class="icon lock"></a>
            </li>
            <div class="clear"> </div>
            <li>
                <input type="text" name="platform" class="text" placeholder = '厂商' onkeydown=KeyDown() onkeydown=KeyDown()><a class="icon lock"></a>
            </li>
            <div class="clear"> </div>
            <div class="submit">
                <input type="button" id="login-button" value="登 录" >
                <div class="clear">  </div>
            </div>
        </form>
    </div>
</div>
</div>
<div class="clear"> </div>
<!--- footer --->
<div class="footer">

</div>
<script>
    $(document).ready(function () {
        loginInterceptor();
    });
    $("#login-button").click(function(r){
        $.ajax({
            type: 'post',
            url: 'checklogin',
            traditional: true,
            data:  $('#defaultForm').serialize() ,
            success: function (data) {//返回json结果
                if(data=="unregistered"){
                    spop({template: '用户名或密码错误！', position  : 'top-center', style: 'warning', autoclose: 2000});
                }else if(data=="success") {
                    window.location.href="index";
                    window.event.returnValue=false;
                }else{
                    spop({template: '登录失败！', position  : 'top-center', style: 'error'});
                }
            },
            error: function (data) {// 请求失败处理函数
                spop({template: '登录失败！', position  : 'top-center', style: 'error'});
            }
        });
    });
    function KeyDown() {
        if (event.keyCode == 13) {
            event.returnValue=false;
            event.cancel = true;
            $("#login-button").click();
        }
    }
    function loginInterceptor() {
        if (document != window.self.parent.document) {
            window.self.parent.document.location.reload();
        }
    }
</script>
</body>
</html>
