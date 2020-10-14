<#include "/admin/utils/ui.ftl"/>
<@layout>

<section class="content-header">
    <h1>Quản lý đơn</h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin">Trang chủ</a></li>
        <li class="active">Quản lý bài viết</li>
    </ol>
</section>
<section class="content container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Danh sách đơn</h3>
                    <div class="box-tools">
                        <a class="btn btn-default btn-sm" href="${base}/admin/post/view">Thêm mới</a>
                        <a class="btn btn-default btn-sm" href="javascrit:;" data-action="batch_del">xóa hàng loạt</a>
                    </div>
                </div>
                <div class="box-body">
                    <form id="qForm" class="form-inline search-row">
                        <input type="hidden" name="pageNo" value="${page.number + 1}"/>
                        <div class="form-group">
                            <select class="form-control" name="channelId" data-select="${channelId}">
                                <option value="0">Tìm kiếm tất cả các đơn</option>
                                <#list channels as row>
                                    <option value="${row.id}">${row.name}</option>
                                </#list>
                            </select>
                        </div>
<#--                        test-->

                        <#--                        endtes-->
                        <div class="form-group">
                            <input type="text" name="title" class="form-control" value="${title}" placeholder="Vui Lòng nhập tên đơn khám">
                        </div>
                        <div class="form-group">
                            <input type="text" name="telephone" class="form-control" value="${telephone}" placeholder="Vui Lòng nhập số điện thoại">
                        </div>
                        <button type="submit" class="btn btn-default">Tìm kiếm</button>
                    </form>

<#--                    test-->
                    <form id="qForm" class="form-inline search-row">
                        <input type="hidden" name="pageNo" value="${page.number + 1}"/>

                        <#--                        test-->
                        <div class="form-group">
                            <select class="form-control" name="featured" data-select="${featured}">
                                <option value="0">Tìm kiếm tất cả các đơn</option>
                                <#list page.content as row>
                                    <#if (row.featured > 0)>
                                        <option class="label label-danger">${row.featured}</option>
                                    </#if>
                                    <#if (row.featured = 0)>
                                        <option class="label label-warning">${row.featured}</option>
                                    </#if>
                                </#list>
                            </select>
                            <span>1( đã duyệt) 2( chưa duyệt)</span>
                        </div>
                        <#--                        endtes-->

                        <button type="submit" class="btn btn-default">Tìm kiếm</button>
                    </form>
<#--                    endtest-->

                    <div class="table-responsive">
                        <table id="dataGrid" class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th width="30"><input type="checkbox" class="checkall"></th>
                                <th width="80">#</th>
                                <th>Tên đơn</th>
                                <th width="120">Người viết</th>
                                <th width="100">Ngày đăng</th>
                                <th width="100">Lượt xem</th>
                                <th width="80">Trạng thái</th>
                                <th width="110">Số điện thoại</th>
                                <th width="100">Địa chỉ</th>
                                <th width="170">Ngày Khám</th>
                                <th width="180">operating</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list page.content as row>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="id" value="${row.id}">
                                    </td>
                                    <td>
                                        <img src="<@resource src=row.thumbnail/>" style="width: 80px;">
                                    </td>
                                    <td>
                                        <a href="${base}/post/${row.id}" target="_blank">${row.title}</a>
                                    </td>
                                    <td>${row.author.username}</td>
                                    <td>${row.created?string('yyyy-MM-dd')}</td>
                                    <td><span class="label label-default">${row.views}</span></td>
                                    <td>
                                        <#if (row.featured > 0)>
                                            <span class="label label-danger">Đã duyệt</span>
                                        </#if>
                                        <#if (row.featured = 0)>
                                            <span class="label label-warning">Chưa duyệt</span>
                                        </#if>
                                        <#if (row.weight > 0)>
                                            <span class="label label-warning">Top</span>
                                        </#if>
                                    </td>
                                    <td><span>${row.telephone}</span></td>
                                    <td><span>${row.diachi}</span></td>
                                    <td><span>${row.ngaykham}</span></td>

                                    <td>
                                        <a href="javascript:void(0);" class="btn btn-xs btn-default" data-id="${row.id}" rel="duyetdon">Duyệt đơn</a>
                                        <#if (row.featured == 0)>
                                            <a href="javascript:void(0);" class="btn btn-xs btn-default" data-id="${row.id}" rel="featured" style="display: none;">Giới thiệu</a>
                                        <#else>
                                            <a href="javascript:void(0);" class="btn btn-xs btn-danger" data-id="${row.id}" rel="unfeatured" style="display: none;">Không tán thành</a>
                                        </#if>

                                        <#if (row.weight == 0)>
                                            <a href="javascript:void(0);" class="btn btn-xs btn-default" data-id="${row.id}" rel="weight" style="display: none;">Hàng đầu</a>
                                        <#else>
                                            <a href="javascript:void(0);" class="btn btn-xs btn-warning" data-id="${row.id}" rel="unweight" style="display: none;">Đầu vào</a>
                                        </#if>

                                        <a href="${base}/admin/post/view?id=${row.id}" class="btn btn-xs btn-info">Sửa đơn</a>
                                        <a href="javascript:void(0);" class="btn btn-xs btn-primary" data-id="${row.id}" rel="delete">Xóa</a>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="box-footer">
                    <@pager "list" page 5 />
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
var J = jQuery;

function ajaxReload(json){
    if(json.code >= 0){
        if(json.message != null && json.message != ''){
			layer.msg(json.message, {icon: 1});
        }
        $('#qForm').submit();
    }else{
		layer.msg(json.message, {icon: 2});
    }
}

function doDelete(ids) {
	J.getJSON('${base}/admin/post/delete', J.param({'id': ids}, true), ajaxReload);
}

function doUpdateFeatured(id, featured) {
    J.getJSON('${base}/admin/post/featured', J.param({'id': id, 'featured': featured}, true), ajaxReload);
}

function duyetdon(id, content="Hen ban mot ngay som nhat!") {
    J.getJSON('${base}/comment/submit', J.param({'toId': id, 'text': content}, true), ajaxReload);
    J.getJSON('${base}/admin/post/featured', J.param({'id': id, 'featured': 1}, true));
}

function doUpdateWeight(id, weight) {
    J.getJSON('${base}/admin/post/weight', J.param({'id': id, 'weight': weight}, true), ajaxReload);
}

$(function() {
    $('#dataGrid a[rel="delete"]').bind('click', function(){
        var that = $(this);
		layer.confirm('Bạn có chắc chắn xóa mục này?', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
			doDelete(that.attr('data-id'));
        }, function(){
        });
        return false;
    });

    $('#dataGrid a[rel="duyetdon"]').bind('click', function(){
        var that = $(this);
        layer.confirm('Bạn có muốn duyệt đơn này không?', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
            duyetdon(that.attr('data-id'));
        }, function(){
        });
        return false;
    });

    $('#dataGrid a[rel="featured"]').bind('click', function(){
        var that = $(this);
        layer.confirm('Bạn có chắc chắn để giới thiệu?', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
            doUpdateFeatured(that.attr('data-id'), 1);
        }, function(){
        });
        return false;
    });

    $('#dataGrid a[rel="unfeatured"]').bind('click', function(){
        var that = $(this);
        layer.confirm('Bạn có chắc chắn sẽ hủy?', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
            doUpdateFeatured(that.attr('data-id'), 0);
        }, function(){
        });
        return false;
    });

    $('#dataGrid a[rel="weight"]').bind('click', function(){
        var that = $(this);
        layer.confirm('Bạn có chắc chắn để dính mặt hàng này', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
            doUpdateWeight(that.attr('data-id'), 1);
        }, function(){
        });
        return false;
    });

    $('#dataGrid a[rel="unweight"]').bind('click', function(){
        var that = $(this);
        layer.confirm('Bạn có chắc chắn hủy không?\n?', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
            doUpdateWeight(that.attr('data-id'), 0);
        }, function(){
        });
        return false;
    });
    
    $('a[data-action="batch_del"]').click(function () {
		var check_length=$("input[type=checkbox][name=id]:checked").length;
		
		if (check_length == 0) {
			layer.msg("Vui lòng chọn ít nhất một mục", {icon: 2});
			return false;
		}
		
		var ids = [];
		$("input[type=checkbox][name=id]:checked").each(function(){
			ids.push($(this).val());
		});
		
		layer.confirm('Bạn có chắc chắn xóa mục này?\n', {
            btn: ['Yes','No'],
            shade: false
        }, function(){
			doDelete(ids);
        }, function(){
        });
    });
})
</script>
</@layout>
