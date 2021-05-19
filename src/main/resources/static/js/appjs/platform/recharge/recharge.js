var prefix = "/platform/account";
$(function() {
    load();
});
function load() {
    var i = 1;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/rechargeList", // 服务器数据的加载地址
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
                        userId : $('#userId').val(),
                        payType: $('#payType').val(),
                        status:  $('#status').val()
                    };
                },
                columns : [
                    {
                        field : 'id', // 列字段名
                        title : '序号', // 列标题
                        formatter : function(value, row, index) {
                            td = i++;
                            return td;
                        }
                    },
                    {
                        field : 'userId',
                        title : '用户ID'
                    },
                    {
                        field : 'nickName',
                        title : '用户昵称'
                    },
                    {
                        field : 'amount',
                        title : '充值金额'
                    },
                    {
                        field : 'balance',
                        title : '账户余额'
                    },
                    {
                        field : 'payType',
                        title : '充值方式',
                        formatter : function(value, row, index) {
                            var td = '微信';
                            if (row.payType == 1) {
                                td = '支付宝';
                            }
                            return td;
                        }
                    },
                    {
                        field : 'createTime',
                        title : '充值时间'
                    },
                    {
                        field : 'status',
                        title : '状态',
                        formatter : function(value, row, index) {
                            var td = '未支付';
                            if (row.status == 1) {
                                td = '支付成功';
                            }
                            return td;
                        }
                    }
                    ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}