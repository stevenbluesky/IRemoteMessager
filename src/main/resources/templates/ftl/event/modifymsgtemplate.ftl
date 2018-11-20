<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <div class="row">
                <div class="text-center"><h1>修改消息模板</h1></div>
                <hr>
                <div class="form-group">
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <label class="col-md-4  control-label">事件名称：</label>
                        <div class="col-md-8">
                          <#if event??&&event.eventtypename??>${event.eventtypename}</#if>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <label class="col-md-4 control-label">事件代码：</label>
                        <div class="col-md-8">
                            <#if event??&&event.eventcode??>${event.eventcode}</#if>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

            </div>
            <form id="defaultForm" class="form-horizontal" method="POST">
                <hr>
                <div class="col-sm-12">
                    <div  class="form-group" align="right" id="platformtr">
                        <label for="platform"  class="col-sm-4 control-label">厂商*</label>
                        <div class="col-sm-5">
                            <input type="hidden" name="msgeventtypeid" <#if event??>value="${event.msgeventtypeid}"</#if>>
                            <input type="hidden" name="msgcontenttemplateid" <#if msg??>value="${msg.msgcontenttemplateid}"</#if>>
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
                            <textarea class="col-md-12 form-control" name="contenttemplate" id="contenttemplate" ><#if msg??>${msg.contenttemplate}</#if></textarea>
                        </div>
                    </div>
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
                //document.getElementById("platformtr").style.visibility="hidden";
                document.getElementById("platformtr").style.display="none";
            });
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: "../event/modifymsgtemplatedata",
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            spop({template: '修改成功！', position: 'top-center', style: 'success', autoclose: 1500,onClose: function() {
                                    parent.location.href = parent.location.href;
                                    //self.location=document.referrer;
                                }});
                        } else {
                            spop({template: jsonObj['msg'], position: 'top-center', style: 'error', autoclose: 2000});
                            $("#btn-submit").removeAttr("disabled");
                        }
                    },
                    error: function (data) {
                        spop({template: '修改失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
            });
            function addPlatform() {
                var str = "";
                var pl = "";
                <#if msg??>pl="${msg.platform?c}"</#if>
                for(var i = 0; i < platform.length; i++){
                    if(pl!=""&&platform[i].platformValue==pl){
                        str += "<option value='" + platform[i].platformValue + "' selected='selected'>" + platform[i].platformName + "</option>";
                    }else {
                        str += "<option value='" + platform[i].platformValue + "'>" + platform[i].platformName + "</option>";
                    }
                }
                $("#platform").append(str);
            }

            function addLanguage() {
                var str = "";
                var lan = "";
                <#if msg??>lan="${msg.language}"</#if>
                for(var i = 0; i < language.length; i++){
                    if(lan!=""&&language[i].languageValue==lan){
                        str += "<option value='" + language[i].languageValue + "' selected='selected'>" + language[i].languageName + "</option>";
                    }else {
                        str += "<option value='" + language[i].languageValue + "'>" + language[i].languageName + "</option>";
                    }
                }
                $("#language").append(str);
            }

            function addType() {
                var str = "";
                var ty = "";
                <#if msg??>ty="${msg.type}"</#if>
                for(var i = 0; i < type.length; i++){
                    if(ty!=""&&type[i].typeValue==ty){
                        str += "<option value='" + type[i].typeValue + "' selected='selected'>" + type[i].typeName + "</option>";
                    }else {
                        str += "<option value='" + type[i].typeValue + "'>" + type[i].typeName + "</option>";
                    }
                }
                $("#type").append(str);
            }
        </script>