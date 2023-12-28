package link.shellgpt.plugin.business.log.service.impl;

import cn.easyes.core.conditions.select.LambdaEsQueryWrapper;
import link.shellgpt.plugin.business.log.dao.LogMapper;
import link.shellgpt.plugin.business.log.model.Log;
import link.shellgpt.plugin.business.log.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;


    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void createIndex() {
        logMapper.createIndex();
    }

    @Override
    public void save(Log log) {
        logMapper.insert(log);
    }

    @Override
    public List<Log> search(Log log) {
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();

        if (log.getUserId() != 0) {
            wrapper.eq(Log::getUserId, log.getUserId());
        }
        if (log.getType() != 0) {
            wrapper.eq(Log::getType, log.getType());
        }
        if (log.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, log.getChannelId());
        }
        if (log.getTokenKey() != null) {
            wrapper.eq(Log::getTokenKey, log.getTokenKey());
        }
        if (log.getCreatedAt() != 0) {
            wrapper.eq(Log::getCreatedAt, log.getCreatedAt());
        }
        if (log.getUsername() != null) {
            wrapper.eq(Log::getUsername, log.getUsername());
        }
        if (log.getTokenName() != null) {
            wrapper.eq(Log::getTokenName, log.getTokenName());
        }
        if (log.getModelName() != null) {
            wrapper.eq(Log::getModelName, log.getModelName());
        }
        if (log.getChannelName() != null) {
            wrapper.eq(Log::getChannelName, log.getChannelName());
        }
        if (log.getQuota() != 0) {
            wrapper.eq(Log::getQuota, log.getQuota());
        }
        if (log.getPromptTokens() != 0) {
            wrapper.eq(Log::getPromptTokens, log.getPromptTokens());
        }
        if (log.getCompletionTokens() != 0) {
            wrapper.eq(Log::getCompletionTokens, log.getCompletionTokens());
        }
        if (log.getPrompt() != null) {
            wrapper.eq(Log::getPrompt, log.getPrompt());
        }
        if (log.getRequestDuration() != 0) {
            wrapper.eq(Log::getRequestDuration, log.getRequestDuration());
        }
        if (log.getContent() != null) {
            wrapper.eq(Log::getContent, log.getContent());
        }
        return logMapper.selectList(wrapper);
    }

}