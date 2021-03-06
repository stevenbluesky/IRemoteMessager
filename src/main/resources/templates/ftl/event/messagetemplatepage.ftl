<#include "../_head0.ftl"/>
    <div class="col-md-12">
      <div class="row">
          <div class="text-center"><h1>消息模板</h1></div>
          <hr>
              <div class="form-group">
                  <div class="col-xs-1 col-sm-1 col-md-1"></div>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                      <label class="col-xs-4 col-sm-4 col-md-4  control-label">事件名称：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8">
                          <#if event??&&event.eventtypename??>${event.eventtypename}</#if>
                      </div>
                  </div>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                      <label class="col-xs-4 col-sm-4 col-md-4 control-label">事件代码：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8">
                            <#if event??&&event.eventcode??>${event.eventcode}</#if>
                      </div>
                  </div>
                  <div class="col-xs-1 col-sm-1 col-md-1"></div>
              </div>
      </div>
      <hr>
    <button style="float: right;" type="button" id='deletePhonecard' class='btn btn-default' onclick='deleteMsgTemplate()'>删除</button>

    <button style="float: right;" type="button" id='stopPhonecard' class='btn btn-default' onclick='modifyMsgTemplate()'>修改</button>

    <button style="float: right;" type="button" id='startPhonecard' class='btn btn-default' onclick="toAddMsgTemplatePage()">增加</button>

	<table id="table" data-toggle="table">
        <thead>
        <tr>
            <th data-field="id">复选框</th>
            <th data-field="msgcontenttemplateid" data-visible="false">主键</th>
            <th data-field="platform" data-formatter="formatter_platform" class="text-center col-md-1">厂商</th>
            <th data-field="type" data-formatter="formatter_type" class="text-center col-md-1">类型</th>
            <th data-field="language" data-formatter="formatter_language" class="text-center  col-md-1">语言</th>
            <th data-field="contenttemplate" class="text-center col-md-7">模板</th>
            <th data-field="createtime" data-formatter="formatter_date" class="text-center col-md-2">创建时间</th>
        </tr>
        </thead>
    </table>
    </div>

    <script type="text/javascript">
        var url = "../event/showmessagetemplatelist";
        <#if event??&&event.msgeventtypeid??>url = url+"?msgeventtypeid="+${event.msgeventtypeid?c}</#if>
        var temp;
        var confirmdelete = url;
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
            uniqueId: "msgcontenttemplateid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                temp = {
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
                ids.push(pid[index].msgcontenttemplateid);
            }
            if (ids.length == 0) {
                return new Array("-1");
            }
            return ids;
        }
        function modifyMsgTemplate(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请先选择一个事件！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            if(ids.length>1) {
                spop({template: '该操作仅支持单选！', position: 'top-center', style: 'warning', autoclose: 2000});
                return ;
            }else {
                $("#iframeDetail").attr("src", '../event/modifymsgpage?msgcontenttemplateid='+ids[0]);
                $('#myModal').modal('show');
                return;
            }
        }

        function toAddMsgTemplatePage() {
            var msgeventtypeid = "";
            <#if event??&&event.msgeventtypeid??>msgeventtypeid = ${event.msgeventtypeid?c}</#if>
            window.location.href='../event/addmsgtemplatepage?msgeventtypeid='+msgeventtypeid;
        }

        function deleteMsgTemplate(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请至少选择一个消息模板！', position: 'top-center', style: 'warning', autoclose: 2000});
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
                                url: '../event/deletemsgs',
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

    </script>
<#include "../modal.ftl"/>
</body>
</head>