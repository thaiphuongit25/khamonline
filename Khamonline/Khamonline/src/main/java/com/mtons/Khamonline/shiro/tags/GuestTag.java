package com.mtons.Khamonline.shiro.tags;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * JSP tag that renders the tag body if the current user <em>is not</em> known to the system, either because they
 * haven't logged in yet, or because they have no 'RememberMe' identity.
 * <p>The logically opposite tag of this one is the {@link UserTag}.  Please read that class's JavaDoc as it explains
 * more about the differences between Authenticated/Unauthenticated and User/Guest semantic differences.
 * </p>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.GuestTag}</p>
 *
 * @since 0.9
 */
public class GuestTag extends SecureTag {
    private static final Logger log = Logger.getLogger("AuthenticatedTag");

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (getSubject() == null || getSubject().getPrincipal() == null) {
            if (log.isDebugEnabled()) {
                log.debug("Chủ thể không tồn tại hoặc không có danh tính đã biết.  " +
                        "Nội dung thẻ sẽ được đánh giá.");
            }

            renderBody(env, body);
        } else {
            if (log.isDebugEnabled()) {
                log.debug(
                        "Đối tượng tồn tại hoặc có danh tính đã biết.  " +
                        "Nội dung thẻ sẽ không được đánh giá.");
            }
        }
    }
}