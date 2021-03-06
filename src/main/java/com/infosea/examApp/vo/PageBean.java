package com.infosea.examApp.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public class PageBean<T> {
    private int curPage =1;             //当前页
    private int pageCount =1;           //总页数
    private int rowsCount;           //总行数
    private int pageSize=1;         //每页多少行

    private List<T> objects = new ArrayList<>();

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public PageBean() {
    }

    /**
     * 设置 总行数  总页数
     * @param rows
     */
    public PageBean(int rows){

        this.setRowsCount(rows);
        if(this.rowsCount % this.pageSize == 0){
            this.pageCount=this.rowsCount / this.pageSize;
        }
        else if(rows<this.pageSize){
            this.pageCount=1;
        }
        else{
            this.pageCount=this.rowsCount / this.pageSize +1;
        }
    }

    public PageBean(List<T> objects){
        this(objects.size());
        this.objects = objects;

    }

    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getRowsCount() {
        return rowsCount;
    }
    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public boolean hasPre(){
        return curPage!=1;
    }
    public boolean hasNext(){
        return curPage!=pageCount;
    }
}
