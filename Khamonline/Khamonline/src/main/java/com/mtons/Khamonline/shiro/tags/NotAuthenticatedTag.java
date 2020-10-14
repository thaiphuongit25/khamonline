package com.mtons.Khamonline.shiro.tags;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * Freemarker tag that renders the tag body only if the current user has <em>not</em> executed a successful authentication
 * attempt <em>during their current session</em>.
 * <p>The logically opposite tag of this one is the {@link org.apache.shiro.web.tags.AuthenticatedTag}.
 * </p>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.NotAuthenticatedTag}</p>
 */
public class NotAuthenticatedTag extends SecureTag {
    static final Logger log = Logger.getLogger("NotAuthenticatedTag");

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (getSubject() == null || !(getSubject().isAuthenticated() || getSubject().isRemembered())) {
            log.debug("Chủ đề không tồn tại hoặc không được xác thực. Nội dung thẻ sẽ được đánh giá.");
            renderBody(env, body);
        } else {
            log.debug("Chủ thể tồn tại và được xác thực. Nội dung thẻ sẽ không được đánh giá.");
        }
    }
}
