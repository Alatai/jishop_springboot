package com.alatai.jishop.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页结果集，对spring.jpa的Page进行封装
 *
 * @author Alatai
 * @version 1.0
 * @date 2021/07/14 16:27
 */
public class PageResult<T> {

    private Page<T> page;
    // 显示页码数
    // e.g. 1 2 3 currentPage 5 6 7
    private int displayPages;
    private int[] displayNums;

    private int totalPages; // 总页数
    private int number; // 当前页码
    private long totalElements; // 总条数
    private int size; // 当前页面大小
    private int numberOfElements; // 当前页面查询条数
    private List<T> content; // 查询结果集
    private boolean isFirst; // 是否为第一页
    private boolean isLast; // 是否为最后一页
    private boolean hasNext; // 是否有下一页
    private boolean hasPrevious; // 是否有上一页

    public PageResult() {
    }

    public PageResult(Page<T> page, int displayPages) {
        this.page = page;
        this.displayPages = displayPages;

        this.totalPages = page.getTotalPages();
        this.number = page.getNumber();
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
        this.content = page.getContent();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();

        calcDisplayPages();
    }

    /**
     * 计算显示页码数
     */
    private void calcDisplayPages() {
        int[] displayNums;

        // 总页数 <= 需要显示的页数
        if (totalPages <= displayPages) {
            displayNums = new int[totalPages];

            for (int i = 0; i < totalPages; i++) {
                displayNums[i] = i + 1;
            }
        } else { // 总页数 > 需要显示的页数
            displayNums = new int[displayPages];
            int start = number - displayPages / 2;
            int end = number + displayPages / 2;

            if (start < 1) {
                start = 1;
                for (int i = 0; i < displayPages; i++) {
                    displayNums[i] = start++;
                }
            } else if (end > totalPages) {
                end = totalPages;
                for (int i = displayPages - 1; i >= 0; i--) {
                    displayNums[i] = end--;
                }
            } else {
                for (int i = 0; i < displayPages; i++) {
                    displayNums[i] = start++;
                }
            }
        }

        this.displayNums = displayNums;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getDisplayPages() {
        return displayPages;
    }

    public void setDisplayPages(int displayPages) {
        this.displayPages = displayPages;
    }

    public int[] getDisplayNums() {
        return displayNums;
    }

    public void setDisplayNums(int[] displayNums) {
        this.displayNums = displayNums;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}


