var prefix = "/platform/banner";
$(function() {
    load();
});

function load() {
    var i = 1;
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
                search : false, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
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
                        field : 'name',
                        title : '名称'
                    },
                    {
                        field: 'companyImage', title: '图片',
                        formatter:function(value,row,index){
                            var s;
                            if(row.image!=null){
                                var url = row.image;
                                s = '<a class = "view"  href="javascript:void(0)"><img style="width:300;height:40px;"  src="'+url+'" /></a>';
                            }
                            return s;
                        },
                        events: 'operateEvents'
                    },
                    {
                        field : 'url',
                        title : '链接内容'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    } ]
            });
}

window.operateEvents = {
    'click .view': function (e, value, row, index) {
        console.log(row);
        var url = row.image;
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: ['700px', '300px'],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: '<img  src="'+url+'"/>'
        });
    },
};

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    // iframe层
    layer.open({
        type : 2,
        title : '添加banner',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code === 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
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