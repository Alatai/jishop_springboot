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
    private Integer displayPages;
    private Integer[] displayNums;

    private Integer totalPages; // 总页数
    private Integer number; // 当前页码
    private long totalElements; // 总条数
    private Integer size; // 当前页面大小
    private Integer numberOfElements; // 当前页面查询条数
    private List<T> content; // 查询结果集
    private boolean isFirst; // 是否为第一页
    private boolean isLast; // 是否为最后一页
    private boolean hasNext; // 是否有下一页
    private boolean hasPrevious; // 是否有上一页

    public PageResult() {
    }

    public PageResult(Page<T> page, Integer displayPages) {
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
        Integer[] displayNums;

        // 总页数 <= 需要显示的页数
        if (totalPages <= displayPages) {
            displayNums = new Integer[totalPages];

            for (int i = 0; i < totalPages; i++) {
                displayNums[i] = i + 1;
            }
        } else { // 总页数 > 需要显示的页数
            displayNums = new Integer[displayPages];
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

    public Integer getDisplayPages() {
        return displayPages;
    }

    public void setDisplayPages(Integer displayPages) {
        this.displayPages = displayPages;
    }

    public Integer[] getDisplayNums() {
        return displayNums;
    }

    public void setDisplayNums(Integer[] displayNums) {
        this.displayNums = displayNums;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
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


