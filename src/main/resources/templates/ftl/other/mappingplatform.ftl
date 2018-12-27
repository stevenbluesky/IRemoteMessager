<#include "../_head0.ftl"/>
    <div class="col-md-12">
        <div class="text-center"><h1>平台映射</h1></div>
        <hr>
        <button style="float: right;" type="button" id='deletePhonecard' class='btn btn-default' onclick='deleteMapping()'>删除</button>

        <button style="float: right;" type="button" id='stopPhonecard' class='btn btn-default' onclick='add()'>新增</button>
        <table id="table" data-toggle="table">
            <thead>
            <tr>
                <th data-field="id">复选框</th>
                <th data-field="platformmappingid" data-visible="false">主键</th>
                <th data-field="fromplatform" class="text-center">原始平台</th>
                <th data-field="toplatform" class="text-center">映射平台</th>
            </tr>
            </thead>
        </table>
    </div>
<script type="text/javascript">
    $('#table').bootstrapTable({
        url: '../other/listmapping',
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
        uniqueId: "platformmappingid",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams: function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            return {
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order //排位命令（desc，asc）
            };
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

    function add() {
        $.confirm({
            title: '添加',
            content: '' +
            '<form action="" class="formName">' +
            '<div class="form-group">' +
            '<label>原始平台</label>' +
            '<input type="text" placeholder="origin" class="origin form-control" required />' +
            '</div>' +
            '<div class="form-group">' +
            '<label>映射平台</label>' +
            '<input type="text" placeholder="mapping to" class="mapping form-control" required />' +
            '</div>' +
            '</form>',
            buttons: {
                formSubmit: {
                    text: 'Submit',
                    btnClass: 'btn-blue',
                    action: function () {
                        var origin = this.$content.find('.origin').val();
                        var mapping = this.$content.find('.mapping').val();
                        if(!origin || !mapping || isNaN(origin) || isNaN(mapping) || (origin == mapping)){
                            $.alert('provide a valid platform');
                            return false;
                        }
                        $.ajax({
                            type: 'post',
                            url: 'addmapping',
                            contentType: 'application/json',
                            traditional: true,
                            data: "{\"fromplatform\":" + origin + ",\"toplatform\":" + mapping + "}",
                            success: function (data) {//返回json结果
                                spop({template: data.msg, position: 'top-center', style: 'warning', autoclose: 2000});
                                $('#table').bootstrapTable('refresh');
                            },
                            error: function () {// 请求失败处理函数
                                spop({template: '添加失败', position: 'top-center', style: 'warning', autoclose: 2000});
                            }

                        });
                    }
                },
                cancel: function () {
                    //close
                },
            },
            onContentReady: function () {
                // bind to events
                var jc = this;
                this.$content.find('form').on('submit', function (e) {
                    // if the user submits the form by pressing enter in the field.
                    e.preventDefault();
                    jc.$$formSubmit.trigger('click'); // reference the button and click it
                });
            }
        });
    }

    function getCheckedId() {
        var pid = $("#table").bootstrapTable('getSelections');
        var ids = [];
        for (var index in pid) {
            ids.push(pid[index].platformmappingid);
        }
        if (ids.length == 0) {
            return new Array("-1");
        }
        return ids;
    }

    function deleteMapping(){
        var ids = getCheckedId();
        if (ids[0] == null || ids[0] == ""|| ids[0] == "-1") {
            spop({template: '请至少选择一条记录！', position: 'top-center', style: 'warning', autoclose: 2000});
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
                            url: '../other/deletemapping',
                            contentType: 'application/json',
                            traditional: true,
                            data: JSON.stringify(ids) ,
                            success: function (data) {
                                if("1"==data["status"]) {
                                    spop({template: '删除成功！', position: 'top-center', style: 'success', autoclose: 2000});
                                    $('#table').bootstrapTable('refresh');
                                }else{
                                    spop({template: '删除失败！', position: 'top-center', style: 'error', autoclose: 2000});
                                }
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
</body>
</html>
