<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <div class="text-center"><h1>导出消息模板</h1></div>
            <form id="defaultForm" method="POST">
                <hr>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="platform"  class="col-sm-4 control-label">厂商*</label>
                        <div class="col-sm-5">
                            <select name="platform" class="col-md-12 form-control" id="platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="language"  class="col-sm-4 control-label">语言*</label>
                        <div class="col-sm-5">
                            <select name="language" class="col-md-12 form-control" id="language">

                            </select>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-12" align="center">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:20%">导出</button>
                </div>
            </div>

    </div>
<div class="col-md-1"></div>
<script type="text/javascript">
    $(document).ready(function() {
        addPlatform();
        addLanguage();
    });
    $("#btn-submit").click(function (e) {
        document.getElementById("btn-submit").setAttribute("disabled", true);
        $.ajax({
            type: "POST",
            dataType: "html",
            url: "../event/exportmessagetemplate",
            data: $('#defaultForm').serialize(),
            success: function (data) {
                var jsonObj = eval('(' + data + ')');
                if (jsonObj['status'] == 1) {
                    spop({template: jsonObj['msg'], position: 'top-center', style: 'success', autoclose: 3000,onClose: function() {
                            self.location=document.referrer;
                        }});
                } else {
                    spop({template: '导出失败！', position: 'top-center', style: 'error', autoclose: 2000});
                }
            },
            error: function (data) {
                spop({template: '导出失败！', position: 'top-center', style: 'error', autoclose: 2000});
            }
        });
    });
    function addPlatform() {
        var str = "";
        for(var i = 0; i < platform.length; i++){
            str += "<option value='" + platform[i].platformValue +"'>" + platform[i].platformName + "</option>";
        }
        $("#platform").append(str);
    }

    function addLanguage() {
        var str = "";
        for(var i = 0; i < language.length; i++){
            str += "<option value='" + language[i].languageValue +"'>" + language[i].languageName + "</option>";
        }
        $("#language").append(str);
    }
</script>