<#include "../_head0.ftl"/>
    <div class="col-md-1"></div>
    <div class="col-md-10">
      <div class="row">
          <div class="text-center"><h1>事件定义列表</h1></div>
          <hr>
          <form id="searchForm" class="form-horizontal" method="POST" >
              <div class="form-group">
                  <div class="col-md-5">
                      <label class="col-md-4  control-label">事件名称</label>
                      <div class="col-md-8">
                          <input type="text" class="form-control" id="searcheventname" name="searcheventname" placeholder="事件名称" >
                      </div>
                  </div>
                  <div class="col-md-5">
                      <label class="col-md-4 control-label">事件代码</label>
                      <div class="col-md-8">
                          <input type="text" class="form-control" id="searcheventcode" name="searcheventcode" placeholder="事件代码" >
                      </div>
                  </div>
                  <div class="col-md-2" align="right">
                      <div class="col-md-12">
                          <button type="button" id="searchsubmit" class="btn btn-default"
                                  style="width:100%;">查询</button>
                      </div>
                  </div>

          </form>
      </div>
    </div>
      <hr>
    <button style="float: right;" type="button" id='deletePhonecard' class='btn btn-default' onclick='deleteEvent()'>删除</button>

    <button style="float: right;" type="button" id='stopPhonecard' class='btn btn-default' onclick='modifyEvent()'>修改</button>

    <button style="float: right;" type="button" id='startPhonecard' class='btn btn-default' onclick="window.location.href='../event/addeventpage'">增加</button>

    <button style="float: right;" type="button" class="btn btn-default" onclick='messageTemplate()'>消息模板</button>

    <button style="float: right;" type="button" class="btn btn-default" onclick="window.location.href='../event/importmsgtemplatepage'">导入消息模板</button>

    <button style="float: right;" type="button" class="btn btn-default" onclick="window.location.href='../event/exportmsgtemplatepage'">导出消息模板</button>

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
      </div>
        <div class="col-md-1"></div>
    </div>

    <script type="text/javascript">
        var temp;
        var confirmdelete = "";
        $('#table').bootstrapTable({
            url: '../event/showeventlist',
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
        function modifyEvent(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请先选择一个事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            if(ids.length>1) {
                spop({template: '该操作仅支持单选！', position: 'top-center', style: 'warning', autoclose: 2000});
                return ;
            }else {
                $("#iframeDetail").attr("src", '../event/modifyeventpage?msgeventtypeid='+ids[0]);
                $('#myModal').modal('show');
                return;
            }
        }
        function deleteEvent(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请至少选择一个事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            $.confirm({
                title: '确认',
                content: '确定执行删除操作吗？',
                //type: 'green',
                icon: 'glyphicon glyphicon-question-sign',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            $.ajax({
                                type: 'post',
                                url: '../event/deleteevents',
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

        function messageTemplate(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请先选择一个事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            if(ids.length>1) {
                spop({template: '该操作仅支持单选！', position: 'top-center', style: 'warning', autoclose: 2000});
                return ;
            }else {
                window.location.href='../event/messagetemplatepage?msgeventtypeid='+ids[0];
            }
        }

    </script>
<#include "../modal.ftl"/>
</body>
</head>