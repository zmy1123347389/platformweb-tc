function update() {
    var form = $('#signupForm').serialize();
    var url = "/platform/version/update";
    $.ajax({
		cache : true,
		type : "POST",
		url : url,
		data : form, // 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(r) {
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