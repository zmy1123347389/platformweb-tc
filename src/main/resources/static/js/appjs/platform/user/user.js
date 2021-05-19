var prefix = "/platform/user";
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
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
                    // { // 列配置项
                    //     // 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
                    //     checkbox : true
                    //     // 列表中显示复选框
                    // },
                    // {
                    //     field : 'id', // 列字段名
                    //     title : '序号' // 列标题
                    // },
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
                        field : 'createTime',
                        title : '申请时间'
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
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (row.deleted == 0) {
                                var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="冻结" onclick="deal(\''
                                    + row.id
                                    + '\', 1)"><i class="fa fa-edit"></i></a> ';
                            } else {
                                var e = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="解禁"  mce_href="#" onclick="deal(\''
                                    + row.id
                                    + '\', 0)"><i class="fa fa-remove"></i></a> ';
                            }
                            return e;
                        }
                    }
                    ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function deal(id, type) {
    var content = "确定冻结用户？";
    if (type == 0) {
        content = "确定解禁用户？";
    }
    layer.confirm(content, {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/deal",
            type: "post",
            data: {
                'userId': id,
                'status':type
            },
            success: function (r) {
                if (r.code === 0) {
                    layer.msg(" 处理成功");
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}