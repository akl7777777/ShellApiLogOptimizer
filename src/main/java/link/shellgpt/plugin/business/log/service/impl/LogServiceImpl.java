package link.shellgpt.plugin.business.log.service.impl;

import cn.easyes.core.biz.EsPageInfo;
import cn.easyes.core.conditions.select.LambdaEsQueryWrapper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import link.shellgpt.plugin.business.log.dao.LogMapper;
import link.shellgpt.plugin.business.log.model.Log;
import link.shellgpt.plugin.business.log.service.LogService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;
    private final RestHighLevelClient restHighLevelClient;


    public LogServiceImpl(LogMapper logMapper, RestHighLevelClient restHighLevelClient) {
        this.logMapper = logMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public void createIndex() {
        logMapper.createIndex();
    }

    @Override
    public void save(Log log, String dynamicIndex) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        logMapper.insert(log);
    }

    @Override
    public List<Log> search(Log log, String dynamicIndex) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();
        // 添加新字段的查询条件
        if (StrUtil.isNotBlank(log.getRequestId())) {
            wrapper.eq(Log::getRequestId, log.getRequestId());
        }
        if (log.getResponseFirstByteDuration() != 0) {
            wrapper.eq(Log::getResponseFirstByteDuration, log.getResponseFirstByteDuration());
        }
        if (log.getTotalDuration() != 0) {
            wrapper.eq(Log::getTotalDuration, log.getTotalDuration());
        }
        wrapper.eq(Log::isStream, log.isStream());
        if (StrUtil.isNotBlank(log.getIp())) {
            wrapper.eq(Log::getIp, log.getIp());
        }

        if (log.getUserId() != 0) {
            wrapper.eq(Log::getUserId, log.getUserId());
        }
        if (log.getType() != 0) {
            wrapper.eq(Log::getType, log.getType());
        }
        if (log.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, log.getChannelId());
        }
        if (StrUtil.isNotBlank(log.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, log.getTokenKey());
        }
        if (log.getCreatedAt() != 0) {
            wrapper.eq(Log::getCreatedAt, log.getCreatedAt());
        }
        if (StrUtil.isNotBlank(log.getUsername())) {
            wrapper.eq(Log::getUsername, log.getUsername());
        }
        if (StrUtil.isNotBlank(log.getTokenName())) {
            wrapper.eq(Log::getTokenName, log.getTokenName());
        }
        if (StrUtil.isNotBlank(log.getModelName())) {
            wrapper.eq(Log::getModelName, log.getModelName());
        }
        if (StrUtil.isNotBlank(log.getChannelName())) {
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
        if (StrUtil.isNotBlank(log.getPrompt())) {
            wrapper.eq(Log::getPrompt, log.getPrompt());
        }
        if (log.getRequestDuration() != 0) {
            wrapper.eq(Log::getRequestDuration, log.getRequestDuration());
        }
        if (StrUtil.isNotBlank(log.getContent())) {
            wrapper.eq(Log::getContent, log.getContent());
        }
        return logMapper.selectList(wrapper);
    }

    @Override
    public EsPageInfo<Log> pageQuery(Log log, String dynamicIndex, int page, int size) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();
        // 添加新字段的查询条件
        if (StrUtil.isNotBlank(log.getRequestId())) {
            wrapper.eq(Log::getRequestId, log.getRequestId());
        }
        if (log.getResponseFirstByteDuration() != 0) {
            wrapper.eq(Log::getResponseFirstByteDuration, log.getResponseFirstByteDuration());
        }
        if (log.getTotalDuration() != 0) {
            wrapper.eq(Log::getTotalDuration, log.getTotalDuration());
        }
        wrapper.eq(Log::isStream, log.isStream());
        if (StrUtil.isNotBlank(log.getIp())) {
            wrapper.eq(Log::getIp, log.getIp());
        }
        if (log.getUserId() != 0) {
            wrapper.eq(Log::getUserId, log.getUserId());
        }
        if (log.getType() != 0) {
            wrapper.eq(Log::getType, log.getType());
        }
        if (log.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, log.getChannelId());
        }
        if (StrUtil.isNotBlank(log.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, log.getTokenKey());
        }
        if (log.getCreatedAt() != 0) {
            wrapper.eq(Log::getCreatedAt, log.getCreatedAt());
        }
        if (StrUtil.isNotBlank(log.getUsername())) {
            wrapper.eq(Log::getUsername, log.getUsername());
        }
        if (StrUtil.isNotBlank(log.getTokenName())) {
            wrapper.eq(Log::getTokenName, log.getTokenName());
        }
        if (StrUtil.isNotBlank(log.getModelName())) {
            wrapper.eq(Log::getModelName, log.getModelName());
        }
        if (StrUtil.isNotBlank(log.getChannelName())) {
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
        if (StrUtil.isNotBlank(log.getPrompt())) {
            wrapper.eq(Log::getPrompt, log.getPrompt());
        }
        if (log.getRequestDuration() != 0) {
            wrapper.eq(Log::getRequestDuration, log.getRequestDuration());
        }
        if (StrUtil.isNotBlank(log.getContent())) {
            wrapper.eq(Log::getContent, log.getContent());
        }
        // 物理分页
        return logMapper.pageQuery(wrapper, page, size);
    }

    @Override
    public void saveBatch(List<Log> logs, String dynamicIndex) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        logMapper.insertBatch(logs);
    }

    @Override
    public void deleteIndex(List<String> indexList) {
        if (CollUtil.isEmpty(indexList)) {
            logMapper.deleteIndex("log_index");
            return;
        }
        logMapper.deleteIndex(ArrayUtil.toArray(indexList, String.class));
    }

    @Override
    public String executeSqlQuery(String sql) throws IOException {
        return logMapper.executeSQL(sql);
    }

    @Override
    public Long count(Log log, String dynamicIndex) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();
        // 添加新字段的查询条件
        if (StrUtil.isNotBlank(log.getRequestId())) {
            wrapper.eq(Log::getRequestId, log.getRequestId());
        }
        if (log.getResponseFirstByteDuration() != 0) {
            wrapper.eq(Log::getResponseFirstByteDuration, log.getResponseFirstByteDuration());
        }
        if (log.getTotalDuration() != 0) {
            wrapper.eq(Log::getTotalDuration, log.getTotalDuration());
        }
        wrapper.eq(Log::isStream, log.isStream());
        if (StrUtil.isNotBlank(log.getIp())) {
            wrapper.eq(Log::getIp, log.getIp());
        }
        if (log.getUserId() != 0) {
            wrapper.eq(Log::getUserId, log.getUserId());
        }
        if (log.getType() != 0) {
            wrapper.eq(Log::getType, log.getType());
        }
        if (log.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, log.getChannelId());
        }
        if (StrUtil.isNotBlank(log.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, log.getTokenKey());
        }
        if (log.getCreatedAt() != 0) {
            wrapper.eq(Log::getCreatedAt, log.getCreatedAt());
        }
        if (StrUtil.isNotBlank(log.getUsername())) {
            wrapper.eq(Log::getUsername, log.getUsername());
        }
        if (StrUtil.isNotBlank(log.getTokenName())) {
            wrapper.eq(Log::getTokenName, log.getTokenName());
        }
        if (StrUtil.isNotBlank(log.getModelName())) {
            wrapper.eq(Log::getModelName, log.getModelName());
        }
        if (StrUtil.isNotBlank(log.getChannelName())) {
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
        if (StrUtil.isNotBlank(log.getPrompt())) {
            wrapper.eq(Log::getPrompt, log.getPrompt());
        }
        if (log.getRequestDuration() != 0) {
            wrapper.eq(Log::getRequestDuration, log.getRequestDuration());
        }
        if (StrUtil.isNotBlank(log.getContent())) {
            wrapper.eq(Log::getContent, log.getContent());
        }
        return logMapper.selectCount(wrapper);
    }
}