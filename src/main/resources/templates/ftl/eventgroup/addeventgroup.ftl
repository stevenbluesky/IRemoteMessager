<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <form id="defaultForm" action="addeventgroupdata" method="POST">
                <div class="text-center"><h1>新增事件组</h1></div>
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
                        <label for="eventgroupname"  class="col-sm-4 control-label">事件组名称*</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="eventgroupname" name="eventgroupname" value="" placeholder="事件组名称">
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="description"  class="col-sm-4 control-label">说明&nbsp;&nbsp;</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description" name="description" value="" placeholder="说明">
                        </div>
                    </div>
                </div>
                <#--新增结果展示区-->
                <div id="msg" class="text-center" style="font-size:14px;">
                <#if msg??&&success??><font color="green">${msg}</font><#elseif msg??><font color="red">${msg}</font></#if>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-3" align="right">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:30%">确定</button>
                </div>
                <div class="col-sm-3" align="right">
                    <button id="btn-cancel" class="btn btn-default"
                            style="width:30%">取消</button>
                </div>
                <div class="col-sm-4"></div>
            </div>

    </div>
<div class="col-md-1"></div>
        <script type="text/javascript">
            $(document).ready(function() {
                addPlatform();
            });
            $("#btn-cancel").click(function(r){
                $("input").val("");
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
        </script>