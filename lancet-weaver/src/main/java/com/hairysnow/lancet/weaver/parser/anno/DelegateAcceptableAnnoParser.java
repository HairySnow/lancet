package com.hairysnow.lancet.weaver.parser.anno;

import com.hairysnow.lancet.weaver.parser.AcceptableAnnoParser;
import com.hairysnow.lancet.weaver.parser.AnnoParser;
import com.hairysnow.lancet.weaver.parser.AnnotationMeta;

import org.objectweb.asm.tree.AnnotationNode;


/**
 * Created by gengwanpeng on 17/5/3.
 */
public class DelegateAcceptableAnnoParser implements AcceptableAnnoParser {

    private final String desc;
    private final AnnoParser parser;

    public DelegateAcceptableAnnoParser(String desc, AnnoParser parser) {

        this.desc = desc;
        this.parser = parser;
    }

    @Override
    public boolean accept(String desc) {
        return this.desc.equals(desc);
    }

    @Override
    public AnnotationMeta parseAnno(AnnotationNode annotationNode) {
        return parser.parseAnno(annotationNode);
    }
}
