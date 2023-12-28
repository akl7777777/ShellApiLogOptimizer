package link.shellgpt.plugin.business.log.model;

import lombok.Data;

@Data
public class Log {
    /**
     * es中的唯一id
     */
    private String id;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 日志内容
     */
    private String content;
}