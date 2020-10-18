<#include "/admin/utils/ui.ftl"/>
<@layout>

    <section class="content-header">
        <h1>Danh sách thanh toán</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">Trang Chủ</a></li>
            <li class="active">Lịch sử thanh toán</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-body">
                        <div class="table-responsive">
                            <table id="dataGrid" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="80">.</th>
                                    <th>Người đăng ký</th>
                                    <th>Tên bài post</th>
                                    <th>Mã hóa đơn</th>
                                    <th>Số tiền</th>
                                    <th>Loại tiền</th>
                                    <th>trạng thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list paymentHistoryViews as row>
                                    <tr>
                                        <td>${row.id}</td>
                                        <td>${row.user.name}</td>
                                        <td>${row.post.title}</td>
                                        <td>${row.invoiceId}</td>
                                        <td>${row.amount}</td>
                                        <td>${row.currency}</td>
                                        <td>${row.status}</td>
                                        <td>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        var J = jQuery;

        function ajaxReload(json) {
            if (json.code >= 0) {
                if (json.message != null && json.message != '') {
                    layer.msg(json.message, {icon: 1});
                }
                window.location.reload();
            } else {
                layer.msg(json.message, {icon: 2});
            }
        }

        function doUpdateWeight(id, weight) {
            J.getJSON('${base}/admin/payment/history', ajaxReload);
        }

    </script>
</@layout>