<#include "/default/inc/layout.ftl"/>
<@layout "Sửa đổi thông tin người dùng">

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="profile">Thông tin cơ bản</a></li>
            <li><a href="email">Sửa đổi email</a></li>
			<li><a href="avatar">Thay Avatar</a></li>
			<li class="active"><a href="password">đổi mật khẩu</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
		<#include "/default/inc/action_message.ftl"/>
		</div>
		<div class="tab-pane active" id="passwd">
			<form id="submitForm" action="password" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-3" for="password">mật khẩu hiện tại</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="oldPassword" maxlength="18" placeholder="Vui lòng nhập mật khẩu hiện tại" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="password">mật khẩu mới</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" id="password" name="password" placeholder="Vui lòng nhập mật khẩu mới" maxlength="18" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="password2">Xác nhận mật khẩu</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="password2" data-required placeholder="Vui lòng nhập lại mật khẩu mới" maxlength="18" required>
					</div>
				</div>
				<div class="form-group">
					<div class="text-center">
					<button type="submit" class="btn btn-primary">Gửi đi</button>
					</div>
				</div><!-- /form-actions -->
			</form>
		</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
    seajs.use('validate', function (validate) {
        validate.updatePassword('#submitForm');
    });
</script>
</@layout>