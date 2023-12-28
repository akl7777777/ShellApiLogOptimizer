package link.shellgpt.plugin.business.log.model;

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.Analyzer;
import cn.easyes.annotation.rely.FieldStrategy;
import cn.easyes.annotation.rely.FieldType;
import lombok.Data;

@Data
@IndexName("log_index")
public class Log {
    @IndexField
    private int id;

    @IndexField
    private int userId;

    @IndexField(fieldType = FieldType.DATE, dateFormat = "epoch_millis")
    private long createdAt;

    @IndexField
    private int type;

//    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    @IndexField(fieldType = FieldType.TEXT)
    private String content;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String username;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String tokenName;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String modelName;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String channelName;

    @IndexField
    private int quota;

    @IndexField
    private int promptTokens;

    @IndexField
    private int completionTokens;

    @IndexField
    private int channelId;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String tokenKey;

    @IndexField(fieldType = FieldType.TEXT)
    private String prompt;

    @IndexField(fieldType = FieldType.DATE, dateFormat = "epoch_millis")
    private long requestDuration;
}