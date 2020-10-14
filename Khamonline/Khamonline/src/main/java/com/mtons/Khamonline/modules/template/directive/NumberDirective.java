package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.modules.template.DirectiveHandler;
import com.mtons.Khamonline.modules.template.TemplateDirective;
import org.springframework.stereotype.Component;

/**
 * Created by langhsu on 2015/10/8.
 */
@Component
public class NumberDirective extends TemplateDirective {
    @Override
    public String getName() {
        return "num";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer value = handler.getInteger("value", 1);
        String out = value.toString();

        if (value > 1000) {
            out = value / 1000 + "k";
        } else if (value > 10000) {
            out = value / 10000 + "m";
        }
        handler.renderString(out);
    }

}
