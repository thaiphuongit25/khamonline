<#include "/default/inc/layout.ftl"/>
<@layout "Chỉnh sửa thông tin">

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="profile">Thông tin cơ bản</a></li>
            <li><a href="email">Chỉnh sửa Email</a></li>
			<li class="active"><a href="avatar">Thay ảnh</a></li>
			<li><a href="password">Đổi mật khẩu</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
			<#include "/default/inc/action_message.ftl"/>
		</div>
			<div class="upload-btn">
				<label>
					<span>Bấm để chọn ảnh</span>
					<input id="upload_btn" type="file" name="file" accept="image/*" title="Bấm để thêm hình ảnh">
				</label>
			</div>
			<div class="update_ava">
				<img src="<@resource src=profile.avatar/>" id="target" alt="[Example]" />
			</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
    seajs.use('avatar');
</script>
</@layout>