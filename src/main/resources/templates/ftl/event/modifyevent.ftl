<#include "../_head0.ftl"/>
<div class="col-md-12">
    <form id="defaultForm" class="form-horizontal">
        <div class="text-center"><h1>修改事件</h1></div>
        <hr>
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div  class="form-group" align="right">
                <label for="eventname"  class="col-xs-4 col-sm-4 col-md-4 control-label">事件名称*</label>
                <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="hidden" name="msgeventtypeid" <#if event??>value="${event.msgeventtypeid?c}"</#if>>
                    <input type="text" class="form-control" id="eventname" name="eventname" <#if event??>value="${event.eventtypename}"</#if> placeholder="事件名称">
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div  class="form-group" align="right">
                <label for="eventcode"  class="col-xs-4 col-sm-4 col-md-4 control-label">事件代码*</label>
                <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" class="form-control" id="eventcode" name="eventcode" <#if event??>value="${event.eventcode}"</#if> placeholder="事件代码">
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div  class="form-group" align="right">
                <label for="description"  class="col-xs-4 col-sm-4 col-md-4 control-label">说明&nbsp;&nbsp;</label>
                <div class="col-xs-5 col-sm-5 col-md-5">
                    <input type="text" class="form-control" id="description" name="description" <#if event??&&event.decription??>value="${event.decription}"</#if> placeholder="说明">
                </div>
            </div>
        </div>
        <#--新增结果展示区-->
        <div id="msg" class="text-center" style="font-size:14px;">
        <#if msg??&&success??><font color="green">${msg}</font><#elseif msg??><font color="red">${msg}</font></#if>
        </div>

        <div class="row">
            <div class="col-xs-4 col-sm-4 col-md-4"></div>
            <div class="col-xs-2 col-sm-2 col-md-2" align="right">
                <button id="btn-submit" class="btn btn-default" style="width:100%">确定</button>
            </div>
            <div class="col-xs-1 col-sm-1 col-md-1"></div>
            <div class="col-xs-2 col-sm-2 col-md-2" align="right">
                <button id="btn-cancel" class="btn btn-default" style="width:100%">取消</button>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4"></div>
        </div>
    </form>
</div>
        <script type="text/javascript">
            $("#btn-cancel").click(function(r){
                $("input").val("");
            });
            $("#btn-submit").click(function (e) {
                document.getElementById("btn-submit").setAttribute("disabled", true);

                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: "../event/modifyeventdata",
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            spop({template: '修改成功！', position: 'top-center', style: 'success', autoclose: 1500,onClose: function() {
                                    parent.location.href = parent.location.href;
                                }});
                        } else {
                            spop({template: '修改失败！', position: 'top-center', style: 'error', autoclose: 2000});
                            $("#btn-submit").removeAttr("disabled");
                        }
                    },
                    error: function (data) {
                        spop({template: '修改失败！', position: 'top-center', style: 'error', autoclose: 2000});
                    }
                });
            });
        </script>