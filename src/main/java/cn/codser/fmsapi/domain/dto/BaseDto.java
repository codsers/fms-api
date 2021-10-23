package cn.codser.fmsapi.domain.dto;

import lombok.Data;

@Data
public class BaseDto {
    private Integer pageSize=20;
    private Integer currentPage=1;
    private Integer lim0;
    private Integer lim1;
    public Integer getLim0() {
        return (this.currentPage-1)*this.pageSize;
    }

    public void setLim0(Integer lim0) {
        this.lim0 = lim0;
    }

    public Integer getLim1() {
        return this.pageSize;
    }

    public void setLim1(Integer lim1) {
        this.lim1 = lim1;
    }
}
