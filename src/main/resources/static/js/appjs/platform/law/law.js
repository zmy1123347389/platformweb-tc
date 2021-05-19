var prefix = "/platform/law";
$(function() {
    //load();
});

window.operateEvents = {
    'click .view': function (e, value, row, index) {
        console.log(row);
        var url = row.url;
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

function openList(id) {
	window.open(prefix + '/openList/' + id);
}