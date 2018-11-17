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
                    href:"push/pushsettinglistpage"
                },
                {
                    text: "事件组定义",
                    href:"eventgroup/eventgrouplistpage"
                },
                {
                    text: "事件定义",
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
<br/><br/>
<div class="row">
    <div class="text-right">欢迎您：
        <#if person??>${person.phonenumber}</#if>
        <a href="logout">安全退出&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
    <div id="wrap" class="col-xs-2 col-sm-2 col-md-2" >
        <div class="container">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-md-3">
                    <div id="treeview12" class=""></div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-10 col-sm-10 col-md-10" style="height: 800px;overflow: hidden;z-index: 999;">
        <iframe class="" id="right" name="right" frameborder="0" src="hello"
                style="height: 100%;width: 100%;overflow-x: hidden;" ></iframe>
    </div>
</div>


