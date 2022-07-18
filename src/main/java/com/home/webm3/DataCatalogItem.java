package com.home.webm3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
  *
 */
class DataCatalogItem {

    private int itemId;
    private List<DataImage> listImages;
    private String attr0;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;

    private int attrInt0;
    private int attrInt1;
    private int attrInt2;
    private int attrInt3;
    private int attrInt4;

    public DataCatalogItem() {
        this.listImages = new ArrayList<>();
    }

    //этот конструктор удалить
    public DataCatalogItem(String attr0, String attr1, String attr2, String attr3, String attr4,
                           int attrInt0,int attrInt1,int attrInt2,int attrInt3,int attrInt4){
        this.listImages = new ArrayList<>();
        this.attr0 = attr0;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
        this.attrInt0 = attrInt0;
        this.attrInt1 = attrInt1;
        this.attrInt2 = attrInt2;
        this.attrInt3 = attrInt3;
        this.attrInt4 = attrInt4;
    }
    public DataCatalogItem(List<DataImage> listImages,
                           String attr0, String attr1, String attr2, String attr3, String attr4,
                           int attrInt0,int attrInt1,int attrInt2,int attrInt3,int attrInt4){
        this.listImages = listImages;
        this.attr0 = attr0;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
        this.attrInt0 = attrInt0;
        this.attrInt1 = attrInt1;
        this.attrInt2 = attrInt2;
        this.attrInt3 = attrInt3;
        this.attrInt4 = attrInt4;
    }

    //этот конструктор удалить
    public DataCatalogItem(int itemId, List<DataImage> listImages,
                           String attr0, String attr1, String attr2, String attr3, String attr4,
                           int attrInt0,int attrInt1,int attrInt2,int attrInt3,int attrInt4){
        this.itemId = itemId;
        this.listImages = listImages;
        this.attr0 = attr0;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
        this.attrInt0 = attrInt0;
        this.attrInt1 = attrInt1;
        this.attrInt2 = attrInt2;
        this.attrInt3 = attrInt3;
        this.attrInt4 = attrInt4;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public List<DataImage> getListImages() {
        return listImages;
    }

    public void setListImages(List<DataImage> listImages) {
        this.listImages = listImages;
    }

    public String getAttr0() {
        return attr0;
    }

    public void setAttr0(String attr0) {
        this.attr0 = attr0;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public int getAttrInt0() {
        return attrInt0;
    }

    public void setAttrInt0(int attrInt0) {
        this.attrInt0 = attrInt0;
    }

    public int getAttrInt1() {
        return attrInt1;
    }

    public void setAttrInt1(int attrInt1) {
        this.attrInt1 = attrInt1;
    }

    public int getAttrInt2() {
        return attrInt2;
    }

    public void setAttrInt2(int attrInt2) {
        this.attrInt2 = attrInt2;
    }

    public int getAttrInt3() {
        return attrInt3;
    }

    public void setAttrInt3(int attrInt3) {
        this.attrInt3 = attrInt3;
    }

    public int getAttrInt4() {
        return attrInt4;
    }

    public void setAttrInt4(int attrInt4) {
        this.attrInt4 = attrInt4;
    }

    @Override
    public String toString() {
        return "DataCatalogItem{" +
                "itemId=" + itemId +
                ", listImages=" + listImages.toString() +
                ", attr0='" + attr0 + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", attr4='" + attr4 + '\'' +
                ", attrInt0=" + attrInt0 +
                ", attrInt1=" + attrInt1 +
                ", attrInt2=" + attrInt2 +
                ", attrInt3=" + attrInt3 +
                ", attrInt4=" + attrInt4 +
                '}';
    }

}

