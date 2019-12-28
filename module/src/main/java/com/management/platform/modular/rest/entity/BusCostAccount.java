package com.management.platform.modular.rest.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 成本核算
 */
@Data
@TableName("bus_cost_account")
public class BusCostAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 成本
     */
    @TableField("cost")
    private Double cost;

    /**
     * 成本平均值
     */
    @TableField("cost_avg")
    private Double costAvg;

    /**
     * 成本描述
     */
    /**
     * 描述
     */
    @TableField("description")
    private String description;


    /**
     * 创建时间
     */
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 修改时间
     */
    @TableField(value = "end_time")
    private Date endTime;

}
