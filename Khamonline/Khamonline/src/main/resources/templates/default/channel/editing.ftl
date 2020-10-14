<#include "/default/inc/layout.ftl"/>
<@layout "Chỉnh sửa đơn">

<form id="submitForm" class="form" action="${base}/post/submit" method="post" enctype="multipart/form-data">
    <input type="hidden" name="status" value="${view.status!0}"/>
    <input type="hidden" name="editor" value="${editor!'tinymce'}"/>
    <div class="row">
        <div class="col-xs-12 col-md-8">
            <div id="message"></div>
            <#if view??>
                <input type="hidden" name="id" value="${view.id}"/>
                <input type="hidden" name="authorId" value="${view.authorId}"/>
            </#if>
            <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}"/>

            <div class="form-group">
                <input type="text" class="form-control" name="title" maxlength="128" value="${view.title}" placeholder="Tên đơn" required>
                <input type="text" name="tags" class="form-control" value="" placeholder="Tags" style="display: none;">
            </div>

            <div class="form-group">
                <input type="text" class="calendar" name="telephone" maxlength="128" value="${view.telephone}" placeholder="Số điện thoại" required>
                <input type="text" name="tags" class="form-control" value="" placeholder="Tags" style="display: none;">
                <input type="datetime-local" class="calendar" name="ngaykham" maxlength="128" value="${view.telephone}" placeholder="Ngày khám" required>
                <input type="text" name="tags" class="form-control" value="" placeholder="Tags" style="display: none;">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="diachi" maxlength="128" value="${view.diachi}" placeholder="Nhập địa chỉ" required>
                <input type="text" name="tags" class="form-control" value="" placeholder="Tags" style="display: none;">
            </div>





            <div class="form-group">
                <select class="form-control" name="channelId" required>
                    <option value="">Chọn mục khám</option>
                    <#list channels as row>
                        <option value="${row.id}" <#if (view.channelId == row.id)> selected </#if>>${row.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <#include "/default/channel/editor/${editor}.ftl"/>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default corner-radius help-box">
                <div class="thumbnail-box">
                    <div class="convent_choice" id="thumbnail_image" <#if view.thumbnail?? && view.thumbnail?length gt 0> style="background: url(<@resource src=view.thumbnail/>);" </#if>>
                        <div class="upload-btn">
                            <label>
                                <span>Thêm ảnh</span>
                                <input id="upload_btn" type="file" name="file" accept="image/*" title="Thêm hình ảnh">
                            </label>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="col-xs-12 col-md-12">
            <div class="form-group">
                <div class="text-center">
                    <button type="button" data-status="0" class="btn btn-primary" event="post_submit" style="padding-left: 30px; padding-right: 30px;">Gửi đơn</button>
                </div>
            </div>
    </div>
</form>
<!-- /form-actions -->
<script type="text/javascript">
seajs.use('post', function (post) {
	post.init();
});
</script>
</@layout>
