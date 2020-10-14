<form id="qForm" class="form-horizontal" method="post" action="update">
    <div class="form-group">
        <label class="col-sm-2 control-label">Địa chỉ SMTP</label>
        <div class="col-sm-6">
            <input type="text" name="mail_smtp_host" class="form-control" value="${options['mail_smtp_host']}" placeholder="smtp.mtons.com">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">địa chỉ email</label>
        <div class="col-sm-6">
            <input type="text" name="mail_smtp_username" class="form-control" value="${options['mail_smtp_username']}" placeholder="Vui lòng nhập tên tài khoản email của bạn">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">mật khẩu email</label>
        <div class="col-sm-6">
            <input type="text" name="mail_smtp_password" class="form-control" value="${options['mail_smtp_password']}" placeholder="Vui lòng nhập mật khẩu tài khoản email của bạn">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Gửi đi</button>
        </div>
    </div>
</form>