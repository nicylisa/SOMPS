package com.ecnu.stldemo.elements.element;


import com.ecnu.stldemo.elements.visitor.STLElementVisitor;

/**
 * @author wangyao2221
 * @date 2020/10/28 15:40
 */
public interface Element {
    <T> T accept(STLElementVisitor visitor);
}
