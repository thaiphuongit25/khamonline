<form id="qForm" class="form-horizontal" method="post" action="update">
    <div class="form-group">
        <div class="col-md-12">
            <div class="help-block">Nền tảng đăng nhập ba bên chưa được định cấu hình sẽ không được hiển thị trên trang đăng nhập</div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <div class="help-block">Đăng nhập</div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Địa chỉ gọi lại</label>
        <div class="col-sm-6">
            <input type="text" name="qq_callback" class="form-control" value="${options['qq_callback']}" placeholder="Ví dụ: http://{domain}/oauth/callback/qq">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">APP ID</label>
        <div class="col-sm-6">
            <input type="text" name="qq_app_id" class="form-control" value="${options['qq_app_id']}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">APP KEY</label>
        <div class="col-sm-6">
            <input type="text" name="qq_app_key" class="form-control" value="${options['qq_app_key']}">
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-12">
            <div class="help-block">Đăng nhập Weibo</div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Địa chỉ gọi lại</label>
        <div class="col-sm-6">
            <input type="text" name="weibo_callback" class="form-control" value="${options['weibo_callback']}" placeholder="Thí dụ: http://{domain}/oauth/callback/weibo">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">CLIENT ID</label>
        <div class="col-sm-6">
            <input type="text" name="weibo_client_id" class="form-control" value="${options['weibo_client_id']}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">CLIENT SERCRET</label>
        <div class="col-sm-6">
            <input type="text" name="weibo_client_sercret" class="form-control" value="${options['weibo_client_sercret']}">
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-12">
            <div class="help-block">Đăng nhập Github</div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Địa chỉ gọi lại</label>
        <div class="col-sm-6">
            <input type="text" name="github_callback" class="form-control" value="${options['github_callback']}" placeholder="Ví dụ: http://{domain}/oauth/callback/github">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">CLIENT ID</label>
        <div class="col-sm-6">
            <input type="text" name="github_client_id" class="form-control" value="${options['github_client_id']}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">CLIENT SERCRET</label>
        <div class="col-sm-6">
            <input type="text" name="github_secret_key" class="form-control" value="${options['github_secret_key']}">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Gửi</button>
        </div>
    </div>
</form>