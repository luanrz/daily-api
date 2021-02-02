package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 待办事项子任务表
 */
@Entity
@Table(name = "TASK_STEP")
public class TaskStep {
    //待办事项子任务id
    @Id
    @Column(name = "TASK_STEP_ID", nullable = false)
    private String taskStepId;

    //待办事项id
    @Column(name = "TASK_ID", nullable = false)
    private String taskId;

    //状态（0:已创建 1:已完成）
    @Column(name = "STATUS", nullable = false)
    private String status;

    //内容
    @Column(name = "CONTENT", nullable = false)
    private String content;

    //创建时间
    @Column(name = "CREATE_TIME", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    //截至时间
    @Column(name = "DEADLINE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date deadlineTime;

    //完成时间
    @Column(name = "FINISH_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date finishTime;

    public String getTaskStepId() {
        return taskStepId;
    }

    public void setTaskStepId(String taskStepId) {
        this.taskStepId = taskStepId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
