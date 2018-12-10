<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-11">
            <div class="row">
                <div class="text-center"><h1>新增消息模板</h1></div>
                <hr>
                <div class="form-group">
                    <div class="col-xs-2 col-sm-2 col-md-2"></div>
                    <div class="col-xs-4 col-sm-4 col-md-4">
                        <label class="col-xs-5 col-sm-5 col-md-5  control-label">事件名称：</label>
                        <div class="col-xs-7 col-sm-7 col-md-7">
                          <#if event??&&event.eventtypename??>${event.eventtypename}</#if>
                        </div>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4">
                        <label class="col-xs-5 col-sm-5 col-md-5 control-label">事件代码：</label>
                        <div class="col-xs-7 col-sm-7 col-md-7">
                            <#if event??&&event.eventcode??>${event.eventcode}</#if>
                        </div>
                    </div>
                    <div class="col-xs-2 col-sm-2 col-md-2"></div>
                </div>

            </div>
            <form id="defaultForm" class="form-horizontal" method="POST">
                <hr>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="platform"  class="col-xs-4 col-sm-4 col-md-4 control-label">厂商*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <input type="hidden" name="msgeventtypeid" <#if event??>value="${event.msgeventtypeid?c}"</#if>>
                            <select name="platform" class="col-xs-12 col-sm-12 selectpicker col-md-12 form-control" data-live-search="true" id="platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="language"  class="col-xs-4 col-sm-4 col-md-4 control-label">语言*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <select name="language" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="language">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="type"  class="col-xs-4 col-sm-4 col-md-4 control-label">类型*</label>
                        <div class="col-xs-5 col-sm-5 col-sm-5">
                            <select name="type" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="type">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="description"  class="col-xs-4 col-sm-4 col-md-4 control-label">模板*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <textarea class="col-xs-12 col-sm-12 col-md-12 form-control" name="contenttemplate" id="contenttemplate"></textarea>
                        </div>
                    </div>
                </div>
                <#--新增结果展示区-->
                <div id="msg" class="text-center" style="font-size:14px;">
                <#if msg??&&success??><font color="green">${msg}</font><#elseif msg??><font color="red">${msg}</font></#if>
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
                addPlatform();
                addType();
                addLanguage();
            });
            $("#btn-cancel").click(function(r){
                $("input").val("");
            });
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: "../event/addmsgtemplatedata",
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            spop({template: '新增成功！', position: 'top-center', style: 'success', autoclose: 1500,onClose: function() {
                                    //parent.location.href = parent.location.href;
                                    self.location=document.referrer;
                                }});
                        } else {
                            spop({template: jsonObj['msg'], position: 'top-center', style: 'error', autoclose: 2000});
                            $("#btn-submit").removeAttr("disabled");
                        }
                    },
                    error: function (data) {
                        spop({template: '新增失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
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

            function addType() {
                var str = "";
                for(var i = 0; i < type.length; i++){
                    str += "<option value='" + type[i].typeValue +"'>" + type[i].typeName + "</option>";
                }
                $("#type").append(str);
                $("#type").selectpicker('refresh');
            }
        </script>