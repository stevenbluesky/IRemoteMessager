<#include "../_head0.ftl"/>
    <div class="col-md-12">
        <div class="row">
      <div class="text-center"><h1>新增事件组事件</h1></div>
      <hr>
          <div class="form-group">
              <div class="col-xs-5 col-sm-5 col-md-5">
                  <label class="col-xs-4 col-sm-4 col-md-4  control-label" align="right">厂商：</label>
                  <div class="col-xs-8 col-sm-8 col-md-8">
                      <span id="platform"></span>
                  </div>
              </div>
              <div class="col-xs-5 col-sm-5 col-md-5">
                  <label class="col-xs-4 col-sm-4 col-md-4 control-label" align="right">事件组：</label>
                  <div class="col-xs-8 col-sm-8 col-md-8">
                        <#if eventgroup??&&eventgroup.eventgroupname??>${eventgroup.eventgroupname}</#if>
                  </div>
              </div>
              <div class="col-xs-2 col-sm-2 col-md-2" align="right"></div>
          </div>
      </div>
        <form class="form-horizontal" >
          <div class="form-group">
              <div class="col-xs-5 col-sm-5 col-md-5">
                  <label class="col-xs-4 col-sm-4 col-md-4  control-label">事件名称</label>
                  <div class="col-xs-8 col-sm-8 col-md-8">
                      <input type="text" class="form-control" id="searcheventname" name="searcheventname" placeholder="事件名称" >
                  </div>
              </div>
              <div class="col-xs-5 col-sm-5 col-md-5">
                  <label class="col-xs-4 col-sm-4 col-md-4 control-label">事件代码</label>
                  <div class="col-xs-8 col-sm-8 col-md-8">
                      <input type="text" class="form-control" id="searcheventcode" name="searcheventcode" placeholder="事件代码" >
                  </div>
              </div>
              <div class="col-xs-2 col-sm-2 col-md-2" align="right">
                  <div class="col-xs-12 col-sm-12 col-md-12">
                      <button type="button" id="searchsubmit" class="btn btn-default"
                              style="width:100%;">查询</button>
                  </div>
              </div>
          </div>
        </form>
        <hr>
	<table id="table" data-toggle="table">
        <thead>
        <tr>
            <th data-field="id">复选框</th>
            <th data-field="msgeventtypeid" data-visible="false">主键</th>
            <th data-field="eventname" class="text-center">事件名称</th>
            <th data-field="eventcode" class="text-center">事件代码</th>
            <th data-field="createtime" data-formatter="formatter_date" class="text-center">创建时间</th>
            <th data-field="description"  class="text-center">说明</th>
        </tr>
        </thead>
    </table>
        <div class="row">
            <div class="col-xs-2 col-sm-2 col-md-2"></div>
            <div class="col-xs-3 col-sm-3 col-md-3" align="right">
                <button id="btn-submit" class="btn btn-default"
                        style="width:50%">确定</button>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3" align="right">
                <button id="btn-cancel" class="btn btn-default"
                        style="width:50%">取消</button>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4"></div>
        </div>
        <br>
    </div>


    <script type="text/javascript">
        $(document).ready(function() {
            addPlatform();
        });
        var url = "../eventgroup/showaddeventgroupeventlist";
        <#if eventgroup??&&eventgroup.msgeventgroupid??>url = url+"?msgeventgroupid="+${eventgroup.msgeventgroupid?c}</#if>
        var temp;
        $('#table').bootstrapTable({
            url: url,
            method: 'GET',                      //请求方式（*）
            //toolbar: '#toolbar',              //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: '10',                     //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "msgeventtypeid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                temp = {
                    eventname: $("#searcheventname").val(),
                    eventcode: $("#searcheventcode").val(),
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit) + 1,   //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                };
                return temp;
            },
            columns: [{
                checkbox: true,
                visible: true
            }],
            onLoadSuccess: function () {
            },
            onLoadError: function () {

            },
            onDblClickRow: function (row, $element) {//双击操作，打开电话卡详情页

            }
        });
        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchsubmit").click(function () {
            $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
        });

        $("#btn-cancel").click(function(r){
            $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
        });
        $("#btn-submit").click(function (e) {
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请至少选择一个事件组事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            var url = "../eventgroup/addeventgroupeventtogroup";
            <#if eventgroup??&&eventgroup.msgeventgroupid??>url = url+"?msgeventgroupid="+${eventgroup.msgeventgroupid?c}</#if>
            document.getElementById("btn-submit").setAttribute("disabled", true);
            $.ajax({
                type: "POST",
                contentType: 'application/json',
                traditional: true,
                url: url,
                data: JSON.stringify(ids) ,
                success: function (data) {
                   /* var jsonObj = eval('(' + data + ')');
                    if (jsonObj['status'] == 1) {*/
                   if(data.status == 1){
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

        <#--获取复选框选中的列的id数组-->
        function getCheckedId() {
            var pid = $("#table").bootstrapTable('getSelections');
            var ids = [];
            for (var index in pid) {
                ids.push(pid[index].msgeventtypeid);
            }
            if (ids.length == 0) {
                return new Array("-1");
            }
            return ids;
        }

        function toAddEventGroupEventPage() {
            var msgeventgroupid = "";
            <#if eventgroup??&&eventgroup.msgeventgroupid??>msgeventgroupid=${eventgroup.msgeventgroupid?c}</#if>
            window.location.href='../eventgroup/addeventgroupeventpage?msgeventgroupid='+msgeventgroupid;
        }

        function deleteEventGroupEvent(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请至少选择一个事件组事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            $.confirm({
                title: '确认',
                content: '确定执行删除操作吗？',
                type: 'blue',
                icon: 'glyphicon glyphicon-question-sign',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            $.ajax({
                                type: 'post',
                                url: '../eventgroup/deleteeventgroupevents',
                                contentType: 'application/json',
                                traditional: true,
                                data: JSON.stringify(ids) ,
                                success: function (data) {
                                    if("1"==data["status"]) {
                                        spop({template: '删除成功！', position: 'top-center', style: 'success', autoclose: 2000});
                                    }else{
                                        spop({template: '删除失败！', position: 'top-center', style: 'error', autoclose: 2000});
                                    }
                                    $('#table').bootstrapTable('refresh');
                                },
                                error: function () {
                                    spop({template: '删除失败！', position: 'top-center', style: 'success', autoclose: 2000});
                                    $('#table').bootstrapTable('refresh');
                                }
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        }
        function formatter_date(value, row, index) {
            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
        }
        <#--日期格式化器-->
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
        function addPlatform() {
            var str = "";
            var pl = "";
            <#if eventgroup??>pl="${eventgroup.platform?c}"</#if>
            for(var i = 0; i < platform.length; i++){
                if(pl!=""&&platform[i].platformValue==pl){
                    str =  platform[i].platformName ;
                }
            }
            $("#platform").html(str);
        }
    </script>
<#include "../modal.ftl"/>
</body>
</head>