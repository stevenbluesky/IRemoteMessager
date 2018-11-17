<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <div class="row">
                <div class="text-center"><h1>新增消息模板</h1></div>
                <hr>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <label class="col-md-5  control-label">事件名称：</label>
                        <div class="col-md-7">
                          <#if event??&&event.eventtypename??>${event.eventtypename}</#if>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-md-5 control-label">事件代码：</label>
                        <div class="col-md-7">
                            <#if event??&&event.eventcode??>${event.eventcode}</#if>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>

            </div>
            <form id="defaultForm" method="POST">
                <hr>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="platform"  class="col-sm-4 control-label">厂商*</label>
                        <div class="col-sm-5">
                            <input type="hidden" name="msgeventtypeid" <#if event??>value="${event.msgeventtypeid}"</#if>>
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
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="type"  class="col-sm-4 control-label">类型*</label>
                        <div class="col-sm-5">
                            <select name="type" class="col-md-12 form-control" id="type">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="description"  class="col-sm-4 control-label">模板*</label>
                        <div class="col-sm-5">
                            <textarea class="col-md-12 form-control" name="contenttemplate" id="contenttemplate"></textarea>
                        </div>
                    </div>
                </div>
                <#--新增结果展示区-->
                <div id="msg" class="text-center" style="font-size:14px;">
                <#if msg??&&success??><font color="green">${msg}</font><#elseif msg??><font color="red">${msg}</font></#if>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-12" align="center">
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
                            spop({template: '新增失败！', position: 'top-center', style: 'error', autoclose: 2000});
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
            }

            function addLanguage() {
                var str = "";
                for(var i = 0; i < language.length; i++){
                    str += "<option value='" + language[i].languageValue +"'>" + language[i].languageName + "</option>";
                }
                $("#language").append(str);
            }

            function addType() {
                var str = "";
                for(var i = 0; i < type.length; i++){
                    str += "<option value='" + type[i].typeValue +"'>" + type[i].typeName + "</option>";
                }
                $("#type").append(str);
            }
        </script>