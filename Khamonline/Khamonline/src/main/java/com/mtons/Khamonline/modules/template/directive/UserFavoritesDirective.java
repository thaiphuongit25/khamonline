/**
 *
 */
package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.modules.data.FavoriteVO;
import com.mtons.Khamonline.modules.service.FavoriteService;
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
public class UserFavoritesDirective extends TemplateDirective {
    @Autowired
	private FavoriteService favoriteService;

	@Override
	public String getName() {
		return "user_favorites";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        Pageable pageable = wrapPageable(handler);

        Page<FavoriteVO> result = favoriteService.pagingByUserId(pageable, userId);
        handler.put(RESULTS, result).render();
    }

}
