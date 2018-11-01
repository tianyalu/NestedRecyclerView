package com.sty.nested.recyclerview.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tian on 2018/11/1.
 */

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private List<ChildBean> childBeanList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChildBean> getChildBeanList() {
        return childBeanList;
    }

    public void setChildBeanList(List<ChildBean> childBeanList) {
        this.childBeanList = childBeanList;
    }

    public static class ChildBean implements Serializable{
        private static final long serialVersionUID = 1L;
        private String content;
        private String childValue;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getChildValue() {
            return childValue;
        }

        public void setChildValue(String childValue) {
            this.childValue = childValue;
        }
    }
}
