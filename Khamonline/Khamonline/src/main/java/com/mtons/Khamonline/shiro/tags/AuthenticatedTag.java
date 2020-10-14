package com.mtons.Khamonline.shiro.tags;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * JSP tag that renders the tag body only if the current user has executed a <b>successful</b> authentication attempt
 * <em>during their current session</em>.
 * <p>This is more restrictive than the {@link UserTag}, which only
 * ensures the current user is known to the system, either via a current login or from Remember Me services,
 * which only makes the assumption that the current user is who they say they are, and does not guarantee it like
 * this tag does.
 * </p>
 * <p>The logically opposite tag of this one is the {@link NotAuthenticatedTag}
 * </p>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.AuthenticatedTag}</p>
 *
 * @since 0.2
 */
public class AuthenticatedTag extends SecureTag {
    private static final Logger log = Logger.getLogger("AuthenticatedTag");

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (getSubject() != null && (getSubject().isAuthenticated() || getSubject().isRemembered())) {
            if (log.isDebugEnabled()) {
                log.debug("Chủ thể tồn tại và được xác thực. Nội dung thẻ sẽ được đánh giá.");
            }

            renderBody(env, body);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Chủ đề không tồn tại hoặc không được xác thực. Nội dung thẻ sẽ không được đánh giá.");
            }
        }
    }
}
