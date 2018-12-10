<#include "../_head0.ftl"/>
    <div class="col-md-12">
      <div class="row">
          <div class="text-center"><h1>消息推送设置列表</h1></div>
          <hr>
          <form id="searchForm" class="form-horizontal">
              <div class="form-group">
                  <div class="col-xs-5 col-sm-5 col-md-5">
                      <label class="col-xs-4 col-sm-4 col-md-4  control-label" >厂商</label>
                      <div class="col-xs-8 col-sm-8 col-md-8">
                          <select name="searchplatform" class="col-xs-12 col-sm-12 col-md-12 selectpicker form-control" data-live-search="true" id="searchplatform">

                          </select>
                      </div>
                  </div>
                  <div class="col-xs-5 col-sm-5 col-md-5">
                      <label class="col-xs-4 col-sm-4 col-md-4 control-label" >事件组名称</label>
                      <div class="col-xs-8 col-sm-8 col-md-8">
                          <input type="text" class="form-control" id="searcheventgroupname" name="searcheventgroupname" placeholder="事件组名称" >
                      </div>
                  </div>
                  <div class="col-xs-2 col-sm-2 col-md-2" align="right">
                      <#--<div class="col-md-5"></div>-->
                      <div class="col-xs-12 col-sm-12 col-md-12">
                          <button type="button" id="searchsubmit" class="btn btn-default"
                                  style="width:100%;">查询</button>
                      </div>
                  </div>

          </form>
      </div>
    </div>
      <hr>
    <button style="float: right;" type="button" class='btn btn-default' onclick='deletePushSetting()'>删除</button>

    <button style="float: right;" type="button" class='btn btn-default' onclick='modifyPushSetting()'>修改</button>

    <button style="float: right;" type="button" class='btn btn-default' onclick="window.location.href='../pushsetting/addpushsettingpage'">增加</button>

	<table id="table" data-toggle="table" style="table-layout: fixed;">
        <thead>
        <tr>
            <th data-field="id">复选框</th>
            <th data-field="msgpushsettingid" data-visible="false">主键</th>
            <th data-field="paltform" data-formatter="formatter_platform" class="text-center col-md-2" >厂商</th>
            <th data-field="eventgroupname" class="text-center col-md-3" >事件组</th>
            <th data-field="pushtargetclass" class="text-center col-md-3">推送对象决策类</th>
            <th data-field="pushmethodclass"  class="text-center col-md-3" >推送方式决策类</th>
            <th data-field="pushclass"  class="text-center col-md-5" >推送类</th>
        </tr>
        </thead>
    </table>
      </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            addPlatform();
        });
        var temp;
        $('#table').bootstrapTable({
            url: '../pushsetting/showpushsettinglist',
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
            uniqueId: "msgpushsettingid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                temp = {
                    platform: $("#searchplatform").val(),
                    eventgroupname: $("#searcheventgroupname").val(),
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

        <#--获取复选框选中的列的id数组-->
        function getCheckedId() {
            var pid = $("#table").bootstrapTable('getSelections');
            var ids = [];
            for (var index in pid) {
                ids.push(pid[index].msgpushsettingid);
            }
            if (ids.length == 0) {
                return new Array("-1");
            }
            return ids;
        }
        function modifyPushSetting(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请先选择一条记录！', position: 'top-center', style: 'warning', autoclose: 2000});
                return;
            }
            if(ids.length>1) {
                spop({template: '该操作仅支持单选！', position: 'top-center', style: 'warning', autoclose: 2000});
                return ;
            }else {
                $("#iframeDetail").attr("src", '../pushsetting/modifypushsettingpage?msgpushsettingid='+ids[0]);
                $('#myModal').modal('show');
                return;
            }
        }
        function deletePushSetting(){
            var ids = getCheckedId();
            if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
                spop({template: '请至少选择一条记录！', position: 'top-center', style: 'warning', autoclose: 2000});
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
                                url: '../pushsetting/deletepushsettings',
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

        function addPlatform() {
            var str = "<option value='888888'>全部</option>";
            for(var i = 0; i < platform.length; i++){
                str += "<option value='" + platform[i].platformValue +"'>" + platform[i].platformName + "</option>";
            }
            $("#searchplatform").append(str);
            $("#searchplatform").selectpicker('refresh');
        }
    </script>
<#include "../modal.ftl"/>
</body>
</head>