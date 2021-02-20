package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 操作记录表
 */
@Entity
@Table(name = "OPERATION")
public class Operation {
    @Id
    @Column(name = "OPERATION_ID", nullable = false)
    private String operationId;
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Column(name = "TYPE", nullable = false)
    private String type;
    @Column(name = "CONTENT", nullable = false)
    private String content;
    @Column(name = "OPERATE_TIME", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date operateTime;

    public String getOperationId() {
        return operationId;
    }

    public Operation setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Operation setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getType() {
        return type;
    }

    public Operation setType(String type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Operation setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public Operation setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
        return this;
    }
}
