var prefix = "/platform/push";
$(function() {
    load();
    load1();
});



function load() {
    var i = 1;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/pushList", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        timing : $('#timing').val()
                    };
                },
                columns : [
                    {
                        field : 'id', // 列字段名
                        title : '序号', // 列标题
                        formatter : function(value, row, index) {
                            var td = i++;
                            return td;
                        }
                    },
                    {
                        field : 'title',
                        title : '标题'
                    },
                    {
                        field : 'content',
                        title : '推送内容'
                    },
                    {
                        field : 'type',
                        title : '推送类型',
                        formatter : function(value, row, index) {
                            var td = '广播推送';
                            if (row.type == 1) {
                                td = '指定推送';
                            }
                            return td;
                        }
                    },
                    {
                        field : 'pushUsers',
                        title : '指定用户'
                    },
                    {
                        field : 'operator',
                        title : '操作人员'
                    },
                    {
                        field : 'ruleDescribe',
                        title : '定时规则'
                    },
                    {
                        field : 'createTime',
                        title : '时间'
                    }
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    var timing = $("#timing").val();
    layer.open({
        type : 2,
        title : '新建推送',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add/' + timing// iframe的url
    });
}

function edit(id) {
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}


function saveOrUpdate(type) {
        var form = document.getElementById('signupForm');
        var rows = $('#exampleTable_user').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['id'];
        });
        var url = "/platform/push/save";
        formData = new FormData(form);
        formData.append("ids", ids);
        $.ajax({
            cache: true,
            type: "POST",
            url: url,
            data: formData, // 你的formid
            async: false,
            processData: false,
            contentType: false,
            error: function (request) {
                alert("Connection error");
            },
            success: function (r) {
                if (r.code == 0) {
                    parent.layer.msg(r.msg);
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);

                } else {
                    parent.layer.msg(r.msg);
                }
            }
        });
}


function load1() {
    $('#exampleTable_user')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/platform/user/list", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        userId : $('#searchName').val()
                    };
                },
                columns : [
                    { // 列配置项
                        // 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
                        checkbox : true
                        // 列表中显示复选框
                    },
                    {
                        field : 'id',
                        title : '用户ID'
                    },
                    {
                        field : 'nickName',
                        title : '用户昵称'
                    },
                    {
                        field : 'city',
                        title : '所在城市'
                    },
                    {
                        field : 'age',
                        title : '年龄'
                    },
                    {
                        field : 'gender',
                        title : '性别',
                        formatter : function(value, row, index) {
                            var td = '未知';
                            if (row.gender == 1) {
                                td = '男';
                            }
                            if (row.gender == 2) {
                                td = '女';
                            }
                            return td;
                        }
                    },
                    {
                        field : 'flower',
                        title : '鲜花'
                    },
                    {
                        field : 'balance',
                        title : '钻石'
                    },
                    {
                        field : 'auth',
                        title : '是否认证',
                        formatter : function(value, row, index) {
                            var td = '是';
                            if (row.auth == 0) {
                                td = '否';
                            }
                            return td;
                        }
                    },
                    {
                        field : 'deleted',
                        title : '状态',
                        formatter : function(value, row, index) {
                            var td = '正常';
                            if (row.deleted == 1) {
                                td = '冻结';
                            }
                            return td;
                        }
                    }
                ]
            });
}