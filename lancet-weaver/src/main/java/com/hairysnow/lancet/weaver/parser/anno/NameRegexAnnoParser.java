package com.hairysnow.lancet.weaver.parser.anno;

import com.google.common.base.Strings;
import com.hairysnow.lancet.weaver.exception.IllegalAnnotationException;
import com.hairysnow.lancet.weaver.meta.HookInfoLocator;
import com.hairysnow.lancet.weaver.parser.AnnoParser;
import com.hairysnow.lancet.weaver.parser.AnnotationMeta;
import com.hairysnow.lancet.weaver.util.RefHolder;

import org.objectweb.asm.tree.AnnotationNode;

import java.util.List;


/**
 * Created by gengwanpeng on 17/5/5.
 */
public class NameRegexAnnoParser implements AnnoParser {


    @SuppressWarnings("unchecked")
    @Override
    public AnnotationMeta parseAnno(AnnotationNode annotationNode) {
        List<Object> values;
        RefHolder<String> regex = new RefHolder<>(null);
        if ((values = annotationNode.values) != null) {
            for (int i = 0; i < values.size(); i += 2) {
                switch ((String) values.get(i)) {
                    case "value":
                        regex.set((String) values.get(i + 1));
                        if (Strings.isNullOrEmpty(regex.get())) {
                            throw new IllegalAnnotationException("@NameRegexAnnoParser value can't be empty or null");
                        }

                        break;
                    default:
                        throw new IllegalAnnotationException();
                }
            }
            return new AnnotationMeta(annotationNode.desc) {
                @Override
                public void accept(HookInfoLocator locator) {
                    locator.setNameRegex(regex.get());
                }
            };
        }

        throw new IllegalAnnotationException("@NameRegexAnnoParser is illegal, must specify value field");
    }
}
