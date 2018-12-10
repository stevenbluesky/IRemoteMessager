<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-11">
            <form id="defaultForm" class="form-horizontal" action="addeventdata" method="POST">
                <div class="text-center"><h1>新增事件</h1></div>
                <hr>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="eventname"  class="col-xs-4 col-sm-4 col-md-4 control-label">事件名称*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <input type="text" class="form-control" id="eventname" name="eventname" value="" placeholder="事件名称">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="eventcode"  class="col-xs-4 col-sm-4 col-md-4 control-label">事件代码*</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
                            <input type="text" class="form-control" id="eventcode" name="eventcode" value="" placeholder="事件代码">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div  class="form-group" align="right">
                        <label for="description"  class="col-xs-4 col-sm-4 col-md-4 control-label">说明&nbsp;&nbsp;</label>
                        <div class="col-xs-5 col-sm-5 col-md-5">
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
                    <div class="col-xs-2 col-sm-2 col-md-2"></div>
                    <div class="col-xs-3 col-sm-3 col-md-3" align="right">
                        <button id="btn-submit" class="btn btn-default"
                                style="width:30%">确定</button>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3" align="right">
                        <button id="btn-cancel" class="btn btn-default"
                                style="width:30%">取消</button>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4"></div>
                </div>

    </div>
<div class="col-md-1"></div>
        <script type="text/javascript">
            $("#btn-cancel").click(function(r){
                $("input").val("");
            });
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);
                $("#defaultForm").submit();
            });
        </script>