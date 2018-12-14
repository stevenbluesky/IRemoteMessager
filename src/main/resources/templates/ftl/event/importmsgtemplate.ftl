<#include "../_head0.ftl"/>
<script src="../static/js/fileinput.js"></script>
<link href="../static/css/fileinput.css" rel="stylesheet">
    <div class="row-horizontal">
        <div class="col-md-12">
            <div class="text-center"><h1>导入消息模板</h1></div>
            <form id="defaultForm" class="form-horizontal" action="../event/importmsgtemplatefile" method="POST" enctype="multipart/form-data">
                <hr>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right" id="platformtr">
                        <label for="platform"  class="col-xs-4 col-sm-4 col-md-4 control-label">厂商*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <select name="platform" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="platform">

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
            });

            function addPlatform() {
                var str = "";
                for (var i = 0; i < platform.length; i++) {
                    str += "<option value='" + platform[i].platformValue + "'>" + platform[i].platformName + "</option>";
                }
                $("#platform").append(str);
                $("#platform").selectpicker('refresh');
            }
        </script>