<#include "/default/inc/layout.ftl"/>

<#assign title = view.title + ' - ' + options['site_name'] />
<#assign keywords = view.keywords?default(options['site_keywords']) />
<#assign description = view.description?default(options['site_description']) />

<@layout title>
    <div class="row main">
        <div class="col-xs-12 col-md-9 side-left topics-show">
            <!-- view show -->
            <div class="topic panel panel-default">
                <div class="infos panel-heading">
                    <h1 class="panel-title topic-title">${view.title}</h1>
                    <div class="meta inline-block">
                        <a class="author" href="${base}/users/${view.author.id}">
                            ${view.author.name}
                        </a>
                        <abbr class="timeago">${timeAgo(view.created)}</abbr>
                        ⋅
                        ${view.views} lượt xem
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="content-body entry-content panel-body ">
                    <div class="markdown-body">
                        ${view.content}
                    </div>
                </div>
                <div class="panel-footer operate">
                    <#list view.tagsArray as tag>
                        <span>
                        <a class="label label-default" href="${base}/tag/${tag}/">#${tag}</a>
                    </span>
                    </#list>
                </div>
                <div class="panel-footer">
                    <div class="hidden-xs">
                        <div class="social-share" data-sites="qq, weibo, wechat, qzone, facebook, twitter, google"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <!-- Comments -->
            <@controls name="comment">
                <div id="chat" class="chats shadow-box">
                    <div class="chat_header">
                        <h4>Các đánh giá: <i id="chat_count">0</i> đơn</h4>
                    </div>
                    <ul id="chat_container" class="its"></ul>
                    <div id="pager" class="text-center"></div>
                    <div class="chat_post">

                    </div>
                </div>
            </@controls>
            <!-- /view show -->
        </div>
        <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
            <ul class="list-group about-user">
                <li class="list-group-item user-card" >
                    <div class="user-avatar">
                        <@utils.showAva view.author "img-circle"/>
                    </div>
                    <div class="user-name">
                        <span>${view.author.name}</span>
                    </div>
                </li>

                <li class="list-group-item">
                    <div class="user-datas">
                        <ul>
                            <li><strong>${view.author.posts}</strong><span>giải phóng</span></li>
                            <li class="noborder"><strong>${view.author.comments}</strong><span>đánh giá</span></li>
                        </ul>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="text-center">
                        <a class="btn btn-default btn-sm" href="javascript:void(0);" data-id="${view.id}" rel="favor">
                            <i class="icon icon-star"></i> Lưu <strong id="favors">${view.favors}</strong>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <script type="text/plain" id="chat_template">
    <li id="chat{5}">
        <a class="avt fl" target="_blank" href="${base}/users/{0}">
            <img src="{1}">
        </a>
        <div class="chat_body">
            <h5>
                <div class="fl"><a class="chat_name" href="${base}/users/{0}">{2}</a><span>{3}</span></div>
                <div class="clear"></div>
            </h5>
            <div class="chat_p">
                <div class="chat_pct">{4}</div> {6}
            </div>
        </div>
        <div class="clear"></div>
    </li>
</script>

    <script type="text/javascript">

        var template = $('#chat_template')[0].text;

        seajs.use(['comment', 'view'], function (comment) {
            comment.init({
                load: '${site.controls.comment}',
                load_url: '${base}/comment/list/${view.id}',
                post_url: '${base}/comment/submit',
                toId: '${view.id}',
                onLoad: function (i, data) {
                    var content = data.content;

                    var quoto = '';
                    if (data.pid > 0 && !(data.parent === null)) {
                        var pat = data.parent;
                        var pcontent = pat.content;
                        quoto = '<div class="quote"><a href="${base}/users/' + pat.author.id + '">@' + pat.author.name + '</a>: ' + pcontent + '</div>';
                    }
                    var item = jQuery.format(template,
                        data.author.id,
                        data.author.avatar,
                        data.author.name,
                        data.created,
                        content,
                        data.id, quoto);
                    return item;
                }
            });
        });
    </script>
</@layout>
