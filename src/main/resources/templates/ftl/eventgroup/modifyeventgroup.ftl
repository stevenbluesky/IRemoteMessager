<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <form id="defaultForm">
                <div class="text-center"><h1>修改事件组</h1></div>
                <hr>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="platform"  class="col-sm-4 control-label">厂商*</label>
                        <div class="col-sm-5">
                            <input type="hidden" name="msgeventgroupid" <#if eventgroup??>value="${eventgroup.msgeventgroupid}"</#if>>
                            <select name="platform" class="col-md-12 form-control" id="platform">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="eventgroupname"  class="col-sm-4 control-label">事件组名称*</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="eventgroupname" name="eventgroupname" <#if eventgroup??>value="${eventgroup.eventgroupname}"</#if> placeholder="事件组名称">
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div  class="form-group" align="right">
                        <label for="description"  class="col-sm-4 control-label">说明&nbsp;&nbsp;</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description" name="description" <#if eventgroup??>value="${eventgroup.decription}"</#if> placeholder="说明">
                        </div>
                    </div>
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
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: "../eventgroup/modifyeventgroupdata",
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            spop({template: '修改成功！', position: 'top-center', style: 'success', autoclose: 1500,onClose: function() {
                                    parent.location.href = parent.location.href;
                                    //self.location=document.referrer;
                                }});
                        } else {
                            spop({template: '修改失败！', position: 'top-center', style: 'error', autoclose: 2000});
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
                <#if msg??>pl="${msg.platform}"</#if>
                for(var i = 0; i < platform.length; i++){
                    if(pl!=""&&platform[i].platformValue==pl){
                        str += "<option value='" + platform[i].platformValue + "' selected='selected'>" + platform[i].platformName + "</option>";
                    }else {
                        str += "<option value='" + platform[i].platformValue + "'>" + platform[i].platformName + "</option>";
                    }
                }
                $("#platform").append(str);
            }
        </script>