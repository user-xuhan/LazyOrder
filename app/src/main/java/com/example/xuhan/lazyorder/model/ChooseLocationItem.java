package com.example.xuhan.lazyorder.model;

import android.widget.ListView;

/**
 * Created by xuhan on 2017/4/8.
 */

public class ChooseLocationItem {
    String headerImage, headerText, headerId;

    public ChooseLocationItem(String headerImage, String headerText, String headerId) {
        this.headerImage = headerImage;
        this.headerText = headerText;
        this.headerId = headerId;
    }
    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }
}
