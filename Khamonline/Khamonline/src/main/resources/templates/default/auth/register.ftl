<#include "/default/inc/layout.ftl"/>

<@layout "Đăng ký">
<div class="row">
    <div class="col-md-4 col-md-offset-4 floating-box">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Đăng ký tài khoản</h3>
            </div>
            <div class="panel-body">
                <#include "/default/inc/action_message.ftl"/>
                <div id="message">
                </div>
                <form id="submitForm" method="POST" action="register" accept-charset="UTF-8">
                    <div class="form-group ">
                        <label class="control-label" for="username">Tài khoản</label>
                        <input class="form-control" id="username" name="username" type="text" placeholder="A combination of letters and numbers, no less than 5 digits" required>
                    </div>
                    <@controls name="register_email_validate">
                        <div class="form-group">
                            <label class="control-label" for="username">Email</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="email" maxlength="64" placeholder="Vui lòng nhập địa chỉ email" required>
                                <span class="input-group-btn">
                                    <button class="btn btn-success" type="button" id="sendCode">Lấy mã xác minh</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="control-label" for="code">Mã xác nhận</label>
                            <input class="form-control" id="code" name="code" type="text" placeholder="Please enter the email verification code" maxlength="6" required>
                        </div>
                    </@controls>
                    <div class="form-group ">
                        <label class="control-label" for="username">Mật khẩu</label>
                        <input class="form-control" id="password" name="password" type="password" maxlength="18" placeholder="Please enter password" required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">Xác nhận mật khẩu</label>
                        <input class="form-control" id="password2" name="password2" type="password" placeholder="Please enter the password again" maxlength="18">
                    </div>
                    <button type="submit" class="btn btn-success btn-block">Gửi</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    seajs.use('validate', function (validate) {
        validate.register('#submitForm', '#sendCode');
    });
</script>

</@layout>