package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(value = "task_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String taskId;

    //用户id
    @Column(name = "USER_ID", nullable = false)
    @JsonProperty(value = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    //状态（0:已创建 1:已完成）
    @Column(name = "STATUS", nullable = false)
    @JsonProperty(value = "status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    //内容
    @Column(name = "CONTENT", nullable = false)
    @JsonProperty(value = "content")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    //创建时间
    @Column(name = "CREATE_TIME", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JsonProperty(value = "create_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;

    //截至时间
    @Column(name = "DEADLINE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JsonProperty(value = "deadline_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date deadlineTime;

    //完成时间
    @Column(name = "FINISH_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JsonProperty(value = "finish_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date finishTime;

    //待办事项子任务列表
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TASK_ID", referencedColumnName = "TASK_ID")
    @JsonProperty(value = "task_steps")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TaskStep> taskSteps;

    public String getTaskId() {
        return taskId;
    }

    public Task setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Task setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Task setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Task setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Task setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public Task setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public Task setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public List<TaskStep> getTaskSteps() {
        return taskSteps;
    }

    public Task setTaskSteps(List<TaskStep> taskSteps) {
        this.taskSteps = taskSteps;
        return this;
    }
}
