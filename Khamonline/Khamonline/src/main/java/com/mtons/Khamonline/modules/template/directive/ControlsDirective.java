package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.config.SiteOptions;
import com.mtons.Khamonline.modules.template.DirectiveHandler;
import com.mtons.Khamonline.modules.template.TemplateDirective;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by langhsu
 * on 2019/1/18
 */
@Component
public class ControlsDirective extends TemplateDirective {
    @Autowired
    private SiteOptions siteOptions;

    @Override
    public String getName() {
        return "controls";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        String control = handler.getString("name");

        if (StringUtils.isBlank(control)) {
            return;
        }

        String value = BeanUtils.getProperty(siteOptions.getControls(), control);
        if ("true".equalsIgnoreCase(value)) {
            handler.render();
        } else {
            if ("post".equalsIgnoreCase(control) && SecurityUtils.getSubject() != null && SecurityUtils.getSubject().hasRole(Consts.ROLE_ADMIN)) {
                handler.render();
            }
        }
    }
}
