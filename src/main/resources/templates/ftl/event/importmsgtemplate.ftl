<#include "../_head0.ftl"/>
<script src="../static/js/fileinput.js"></script>
<link href="../static/css/fileinput.css" rel="stylesheet">
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <div class="text-center"><h1>导入消息模板</h1></div>
            <form id="defaultForm" class="form-horizontal" action="../event/importmsgtemplatefile" method="POST" enctype="multipart/form-data">
                <hr>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right" id="platformtr">
                        <label for="platform"  class="col-xs-4 col-sm-4 col-md-4 control-label">厂商*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <select name="platform" class="col-xs-12 col-sm-12 col-md-12 form-control" id="platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="type"  class="col-xs-4 col-sm-4 col-md-4 control-label">模板文件*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <input id="input-b2" name="filename" type="file" class="file" data-show-upload="false" data-show-preview="false">
                        </div>
                    </div>
                </div>

            </form>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12" align="center">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:20%">确定</button>
                </div>
            </div>

    </div>
<div class="col-md-1"></div>
        <script type="text/javascript">
            $(document).ready(function() {
                document.getElementById("platformtr").style.visibility="hidden";
            });
            window.onload = function () {
                addPlatform();
            }
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);
                $('#defaultForm').submit();
                /*$.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: "../event/importmsgtemplatefile",
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            spop({template: '导入成功！', position: 'top-center', style: 'success', autoclose: 1500,onClose: function() {
                                    //parent.location.href = parent.location.href;
                                    self.location=document.referrer;
                                }});
                        } else {
                            spop({template: '新增失败！', position: 'top-center', style: 'error', autoclose: 2000});
                        }
                    },
                    error: function (data) {
                        spop({template: '新增失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });*/
            });

            function addPlatform() {
                var str = "";
                for (var i = 0; i < platform.length; i++) {
                    str += "<option value='" + platform[i].platformValue + "'>" + platform[i].platformName + "</option>";
                }
                $("#platform").append(str);
            }
        </script>