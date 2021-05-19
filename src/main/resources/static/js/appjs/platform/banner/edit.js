function saveOrUpdate(type) {
    var form = document.getElementById('signupForm');
    var url = "/platform/banner/update";
    if (type == 1) {
        url = "/platform/banner/save";
	}
    formData = new FormData(form);
    $.ajax({
		cache : true,
		type : "POST",
		url : url,
		data : formData, // 你的formid
		async : false,
        processData:false,
        contentType:false,
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