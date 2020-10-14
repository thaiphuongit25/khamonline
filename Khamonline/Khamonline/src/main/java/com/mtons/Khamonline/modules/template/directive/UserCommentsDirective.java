/**
 *
 */
package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.modules.data.CommentVO;
import com.mtons.Khamonline.modules.service.CommentService;
import com.mtons.Khamonline.modules.template.DirectiveHandler;
import com.mtons.Khamonline.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author landy
 * @since 3.0
 */
@Component
public class UserCommentsDirective extends TemplateDirective {
    @Autowired
	private CommentService commentService;

	@Override
	public String getName() {
		return "user_comments";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        Pageable pageable = wrapPageable(handler);

        Page<CommentVO> result = commentService.pagingByAuthorId(pageable, userId);
        handler.put(RESULTS, result).render();
    }

}
