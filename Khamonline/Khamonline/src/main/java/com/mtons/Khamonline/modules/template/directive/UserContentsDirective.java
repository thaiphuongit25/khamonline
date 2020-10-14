/**
 *
 */
package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.modules.template.DirectiveHandler;
import com.mtons.Khamonline.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author langhsu
 *
 */
@Component
public class UserContentsDirective extends TemplateDirective {
    @Autowired
	private PostService postService;

	@Override
	public String getName() {
		return "user_contents";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        Pageable pageable = wrapPageable(handler);

        Page<PostVO> result = postService.pagingByAuthorId(pageable, userId);
        handler.put(RESULTS, result).render();
    }

}
