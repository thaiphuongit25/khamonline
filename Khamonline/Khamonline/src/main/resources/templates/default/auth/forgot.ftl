<#include "/default/inc/layout.ftl"/>

<@layout "đặt lại mật khẩu">

<div class="row">
    <div class="col-md-4 col-md-offset-4 floating-box">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Lấy lại mật khẩu</h3>
            </div>
            <div class="panel-body">
                <div id="message">
                    <#include "/default/inc/action_message.ftl"/>
                </div>
                <form id="submitForm" method="POST" action="${base}/forgot" accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="control-label" for="email">địa chỉ email</label>
                        <div class="input-group">
                            <input type="text" class="form-control" name="email" maxlength="64" required>
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="sendCode">lấy mã xác minh</button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Mã xác nhận</label>
                        <input type="text" class="form-control" name="code" maxlength="6" required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">mật khẩu</label>
                        <input class="form-control" name="password" id="password" type="password" maxlength="18" placeholder="mật khẩu mới" required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">Xác nhận mật khẩu</label>
                        <input class="form-control" name="password2" type="password" maxlength="18" placeholder="Vui lòng nhập lại mật khẩu">
                    </div>
                    <button type="submit" class="btn btn-success btn-block">Gửi đi</button>

                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    seajs.use('validate', function (validate) {
        validate.forgot('#submitForm', '#sendCode');
    });
</script>
</@layout>