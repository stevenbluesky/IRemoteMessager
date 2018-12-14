<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
    <script src="static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/html5shiv.min.js"></script>
    <script src="static/js/respond.min.js"></script>
    <script src="static/js/bootstrap-treeview.min.js"></script>
    <style type="text/css">
        .form-group > div {
            margin-bottom: 10px;
        }
        div.form-group>label {
            text-align: left;
        }
    </style>
    <script>
        $(document).ready(function() {
            var obj = document.getElementById("framediv");
            obj.style.cssText="height:"+($(window).height()-50)+"px;overflow: hidden;z-index: 999;";
            /*alert($(window).height()); //浏览器当前窗口可视区域高度
            alert($(document).height()); //浏览器当前窗口文档的高度
            alert($(document.body).height());//浏览器当前窗口文档body的高度
            alert($(document.body).outerHeight(true));//浏览器当前窗口文档body的总高度 包括border padding margin
            alert($(window).width()); //浏览器当前窗口可视区域宽度
            alert($(document).width());//浏览器当前窗口文档对象宽度
            alert($(document.body).width());//浏览器当前窗口文档body的宽度
            alert($(document.body).outerWidth(true));//浏览器当前窗口文档body的总宽度 包括border padding margin*/
        })
        $(function() {
            $('#treeview12').treeview({data: getTree()});
            $('#treeview12').on('nodeSelected', function(event, data) {
                $("#right").attr("src",data.href);
            });
        });
        function getTree() {
            var obj =[
                {
                    text: "消息推送设置",
                    href:"pushsetting/pushsettinglistpage"
                },
                {
                    text: "事件组列表",
                    href:"eventgroup/eventgrouplistpage"
                },
                {
                    text: "事件列表",
                    href:"event/eventlistpage"
                }
            ];
            var jsontree = JSON.stringify(obj);
            return jsontree;
        }
        function hello() {
            $("#right").attr("src",JSON.parse(jsontree)[0].href);
            $('#treeview12').on('nodeSelected', function(event, data) {
                $("#right").attr("src",data.href);
            });
        }
    </script>
</head>
<body style="overflow-x: hidden;">
<br/>
<div class="row">
    <div class="col-xs-11 col-sm-11 col-md-11" align="right">
        欢迎您：
        <#if person??>${person.phonenumber}</#if>
        <a href="logout">安全退出</a>
    </div>
    <div class="col-xs-1 col-sm-1 col-md-1">
            &nbsp;
    </div>
    <div id="wrap" class="col-xs-2 col-sm-2 col-md-2" >
        <div id="treeview12" class=""></div>
    </div>
<#--height: 800px;-->
    <div class="col-xs-10 col-sm-10 col-md-10" id="framediv" >
        <iframe class="" id="right" name="right" frameborder="0" src="pushsetting/pushsettinglistpage"
                style="height: 100%;width: 100%;overflow-x: hidden;" ></iframe>
    </div>
</div>


