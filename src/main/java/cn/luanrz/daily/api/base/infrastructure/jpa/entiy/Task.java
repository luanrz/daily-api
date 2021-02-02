package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 待办事项表
 */
@Entity
@Table(name = "TASK")
public class Task {
    //待办事项id
    @Id
    @Column(name = "TASK_ID", nullable = false)
    private String taskId;

    //用户id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

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

    //待办事项子任务列表
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TASK_ID", referencedColumnName = "TASK_ID")
    private List<TaskStep> taskSteps;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<TaskStep> getTaskSteps() {
        return taskSteps;
    }

    public void setTaskSteps(List<TaskStep> taskSteps) {
        this.taskSteps = taskSteps;
    }
}
