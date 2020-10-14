<form id="qForm" class="form-horizontal" method="post" action="update">
    <div class="form-group">
        <label class="col-sm-2 control-label">Phương pháp lưu trữ</label>
        <div class="col-sm-3">
            <select class="form-control" name="storage_scheme" data-select="${options['storage_scheme']}">
                <option value="native">Lưu trữ cục bộ</option>
                <option value="upyun">Lưu vào đám mây</option>
                <option value="aliyun">Lưu vào Cloud</option>
                <option value="qiniu">Qiniu Cloud</option>
            </select>
        </div>
    </div>
    <div class="scheme" data-scheme="upyun">
        <div class="form-group">
            <label class="col-sm-2 control-label">Tên không gian</label>
            <div class="col-sm-6">
                <input type="text" name="upyun_oss_bucket" class="form-control" value="${options['upyun_oss_bucket']}" placeholder="Lấy tên nhóm đám mây khác">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Tên người vận hành</label>
            <div class="col-sm-6">
                <input type="text" name="upyun_oss_operator" class="form-control" value="${options['upyun_oss_operator']}" placeholder="Sử dụng nhà điều hành đám mây khác">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Mật khẩu nhà điều hành</label>
            <div class="col-sm-6">
                <input type="text" name="upyun_oss_password" class="form-control" value="${options['upyun_oss_password']}" placeholder="Lấy mật khẩu nhà điều hành đám mây khác">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Ràng buộc tên miền</label>
            <div class="col-sm-6">
                <input type="text" name="upyun_oss_domain" class="form-control" value="${options['upyun_oss_domain']}" placeholder="Ví dụ: http://mtons.b0.upaiyun.com">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Tập tin thư mục</label>
            <div class="col-sm-6">
                <input type="text" name="upyun_oss_src" class="form-control" value="${options['upyun_oss_src']}" placeholder="Ví dụ: /static/">
            </div>
        </div>
    </div>
    <div class="scheme" data-scheme="aliyun">
        <div class="form-group">
            <label class="col-sm-2 control-label">Tên không gian</label>
            <div class="col-sm-6">
                <input type="text" name="aliyun_oss_bucket" class="form-control" value="${options['aliyun_oss_bucket']}" placeholder="Tên nhóm Ali oss">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">AccessKey</label>
            <div class="col-sm-6">
                <input type="text" name="aliyun_oss_key" class="form-control" value="${options['aliyun_oss_key']}" placeholder="AccessKeyId">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">AccessSecret</label>
            <div class="col-sm-6">
                <input type="text" name="aliyun_oss_secret" class="form-control" value="${options['aliyun_oss_secret']}" placeholder="AccessKeySecret">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">EndPoint</label>
            <div class="col-sm-6">
                <input type="text" name="aliyun_oss_endpoint" class="form-control" value="${options['aliyun_oss_endpoint']}" placeholder="Ví dụ: oss-cn-beijing.aliyuncs.com">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Tập tin thư mục</label>
            <div class="col-sm-6">
                <input type="text" name="aliyun_oss_src" class="form-control" value="${options['aliyun_oss_src']}" placeholder="Ví dụ: static/images/">
            </div>
        </div>
    </div>
    <div class="scheme" data-scheme="qiniu">
        <div class="form-group">
            <label class="col-sm-2 control-label">Tên không gian</label>
            <div class="col-sm-6">
                <input type="text" name="qiniu_oss_bucket" class="form-control" value="${options['qiniu_oss_bucket']}" placeholder="Tên nhóm đám mây Qiniu">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">AccessKey</label>
            <div class="col-sm-6">
                <input type="text" name="qiniu_oss_key" class="form-control" value="${options['qiniu_oss_key']}" placeholder="AccessKey">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">SecretKey</label>
            <div class="col-sm-6">
                <input type="text" name="qiniu_oss_secret" class="form-control" value="${options['qiniu_oss_secret']}" placeholder="SecretKey">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Ràng buộc tên miền</label>
            <div class="col-sm-6">
                <input type="text" name="qiniu_oss_domain" class="form-control" value="${options['qiniu_oss_domain']}" placeholder="Ví dụ: http://qiniu.mtons.com">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Tập tin thư mục</label>
            <div class="col-sm-6">
                <input type="text" name="qiniu_oss_src" class="form-control" value="${options['qiniu_oss_src']}" placeholder="Ví dụ: /static/">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Gửi đi</button>
        </div>
    </div>
</form>
<script>
    $(function () {
        $('select[name=storage_scheme]').change(function () {
            var value = $(this).val();
            $('.scheme').each(function () {
                if ($(this).data('scheme') === value) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        }).trigger('change');
    });
</script>