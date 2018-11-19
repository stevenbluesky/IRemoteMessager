<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row">
        <div class="col-md-10">
            <form id="defaultForm" action="addeventdata" method="POST" class="form-horizontal">
                <div class="text-center"><h1>新增消息推送设置</h1></div>
                <hr>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label  class="col-sm-4 control-label">厂商*</label>
                        <div class="col-sm-5">
                            <select name="platform" class="col-md-12 form-control" id=4"platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label class="col-sm-4 control-label">事件组*</label>
                        <div class="col-sm-5">
                            <select name="eventgroupname" class="col-md-12 form-control" id="eventgroupname">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="pushtargetclass"  class="col-sm-4 control-label">推送对象决策类*</label>
                        <div class="col-sm-5">
                            <select name="pushtargetclass" class="col-md-12 form-control" id="pushtargetclass">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="pushmethodclass"  class="col-sm-4 control-label">推送方式决策类*</label>
                        <div class="col-sm-5">
                            <select name="pushmethodclass" class="col-md-12 form-control" id="pushmethodclass">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="apppushclass"  class="col-sm-4 control-label">APP消息推送类*</label>
                        <div class="col-sm-5">
                            <select name="apppushclass" class="col-md-12 form-control" id="apppushclass">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="smspushclass"  class="col-sm-4 control-label">短信推送类*</label>
                        <div class="col-sm-5">
                            <select name="smspushclass" class="col-md-12 form-control" id="smspushclass">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="emailpushclass"  class="col-sm-4 control-label">邮件推送类*</label>
                        <div class="col-sm-5">
                            <select name="emailpushclass" class="col-md-12 form-control" id="emailpushclass">

                            </select>
                        </div>
                    </div>
                </div>
                    <#--新增结果展示区-->
                    <div id="msg" class="text-center" style="font-size:14px;">
                    <#if msg??&&success??><font color="green">${msg}</font><#elseif msg??><font color="red">${msg}</font></#if>
                    </div>
                </div>
            </form>
            <br>
            <div class="row">
                <div class="col-sm-12" align="center">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:23%">确定</button>
                </div>
            </div>

    </div>
<div class="col-md-1"></div>
        <script type="text/javascript">
            $(document).ready(function() {
                addPlatform();
                addEventGroup();
                addPushTargetClass();
                addPushMethodClass();
                addAPPClass();
                addSMSClass();
                addEmailClass();
            });
            $("#platform").onchange(function (e) {
                $("#platform").empty();
                addEventGroup();
            });
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);
                $("#defaultForm").submit();
            });

            function addPlatform() {
                var str = "";
                for(var i = 0; i < platform.length; i++){
                    str += "<option value='" + platform[i].platformValue +"'>" + platform[i].platformName + "</option>";
                }
                $("#platform").append(str);
            }
            function addEventGroup() {
                var platform = $("#platform").val();
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listalleventgroup?platform="+platform,
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#platform").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#eventgroupname").append(str);
            }
            function addPushTargetClass() {
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listprocessorclassbytype?type=1",
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#pushtargetclass").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#pushtargetclass").append(str);
            }
            function addPushMethodClass() {
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listprocessorclassbytype?type=2",
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#pushmethodclass").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#pushmethodclass").append(str);
            }
            function addAPPClass() {
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listprocessorclassbytype?type=4&subtype=1",
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#apppushclass").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#apppushclass").append(str);
            }
            function addSMSClass() {
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listprocessorclassbytype?type=4&subtype=3",
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#smspushclass").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#smspushclass").append(str);
            }
            function addEmailClass() {
                var str = "";
                $.ajax({
                    type: "POST",
                    contentType: 'application/json',
                    traditional: true,
                    url: "../pushsetting/listprocessorclassbytype?type=4&subtype=4",
                    success: function (data) {
                        var obj = JSON.parse(data);
                        for(var key in obj){
                            str += "<option value='" + obj[key] +"'>" + obj[value] + "</option>";
                        }
                        $("#emailpushclass").append(str);
                    },
                    error: function (data) {
                        spop({template: '操作失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
                $("#emailpushclass").append(str);
            }
        </script>