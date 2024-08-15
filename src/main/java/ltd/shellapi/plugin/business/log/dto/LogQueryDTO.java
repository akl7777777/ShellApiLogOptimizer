package ltd.shellapi.plugin.business.log.dto;

import lombok.Data;

import java.util.List;

@Data
public class LogQueryDTO {
    private String requestId;
    private Integer userId;
    private List<Integer> type;
    private Long startTimestamp;
    private Long endTimestamp;
    private String modelName;
    private String username;
    private String tokenName;
    private String tokenKey;
    private String channelName;
    private Integer channelId;
    private String content;
    private Integer page;
    private Integer size;
    private String dynamicIndex;
    private String sortField;
    private Boolean isStream;  // 而不是 isStream
    private String sql;
    private String ip;

    // 可以添加构造函数、builder 方法等，根据需要使用
}
