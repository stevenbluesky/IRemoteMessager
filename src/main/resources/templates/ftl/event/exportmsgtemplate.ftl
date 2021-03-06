<#include "../_head0.ftl"/>
<div class="row-horizontal">
        <div class="col-md-12">
            <div class="text-center"><h1>导出消息模板</h1></div>
            <form id="defaultForm" class="form-horizontal" method="POST">
                <hr>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group">
                        <label for="platform"  class="col-xs-4 col-sm-4 col-md-4 control-label" style="text-align: right;">厂商*</label>
                        <div class="col-xs-4 col-sm-4 col-md-4">
                            <select name="platform" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="language"  class="col-xs-4 col-sm-4 col-md-4 control-label">语言*</label>
                        <div class="col-xs-4 col-sm-4 col-sm-4">
                            <select name="language" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="language">

                            </select>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12" align="center">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:20%">导出</button>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        addPlatform();
        addLanguage();
    });
    $("#btn-submit").click(function (e) {
        //document.getElementById("btn-submit").setAttribute("disabled", true);
        var platform = $("#platform").val();
        var language = $("#language").val();
        window.location.href="../event/exportmessagetemplate?platform="+platform+"&language="+language;
    });
    function addPlatform() {
        var str = "";
        for(var i = 0; i < platform.length; i++){
            str += "<option value='" + platform[i].platformValue +"'>" + platform[i].platformName + "</option>";
        }
        $("#platform").append(str);
        $("#platform").selectpicker('refresh');
    }

    function addLanguage() {
        var str = "";
        for(var i = 0; i < language.length; i++){
            str += "<option value='" + language[i].languageValue +"'>" + language[i].languageName + "</option>";
        }
        $("#language").append(str);
        $("#language").selectpicker('refresh');
    }
</script>