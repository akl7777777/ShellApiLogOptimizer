package ltd.shellapi.plugin.business.log.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import ltd.shellapi.plugin.business.log.dao.LogMapper;
import ltd.shellapi.plugin.business.log.dto.LogQueryDTO;
import ltd.shellapi.plugin.business.log.model.Log;
import ltd.shellapi.plugin.business.log.service.LogService;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
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

    public String getCurrentIndexName() {
       /* LambdaEsIndexWrapper<Log> wrapper = new LambdaEsIndexWrapper<>();
        Field indexNameField = null;
        try {
            indexNameField = wrapper.getClass().getDeclaredField("indexName");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        indexNameField.setAccessible(true);
        try {
            return (String) indexNameField.get(wrapper);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
        return null;
    }

    @Override
    public void createIndex(LogQueryDTO queryDTO) {
        String dynamicIndex = queryDTO.getDynamicIndex();
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
            logMapper.createIndex(dynamicIndex);
        } else {
            logMapper.createIndex();
        }
    }

    @Override
    public void save(Log log, String dynamicIndex) {
        if (StrUtil.isNotBlank(dynamicIndex)) {
            logMapper.setCurrentActiveIndex(dynamicIndex);
        }
        logMapper.insert(log);
    }

    @Override
    public List<Log> search(LogQueryDTO queryDTO) {
        if (StrUtil.isNotBlank(queryDTO.getDynamicIndex())) {
            logMapper.setCurrentActiveIndex(queryDTO.getDynamicIndex());
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();
        // 添加新字段的查询条件
        if (StrUtil.isNotBlank(queryDTO.getRequestId())) {
            wrapper.eq(Log::getRequestId, queryDTO.getRequestId());
        }
        if (queryDTO.getIsStream() != null){
            wrapper.eq(Log::isStream, queryDTO.getIsStream());
        }
        if (StrUtil.isNotBlank(queryDTO.getIp())) {
            wrapper.eq(Log::getIp, queryDTO.getIp());
        }

        if (queryDTO.getUserId() != null && queryDTO.getUserId() != 0) {
            wrapper.eq(Log::getUserId, queryDTO.getUserId());
        }
        if (!CollUtil.isEmpty(queryDTO.getType())) {
            wrapper.in(Log::getType, queryDTO.getType());
        }
        if (queryDTO.getChannelId() != null && queryDTO.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, queryDTO.getChannelId());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, queryDTO.getTokenKey());
        }
        if (queryDTO.getStartTimestamp() != null && queryDTO.getStartTimestamp() != 0) {
            wrapper.ge(Log::getCreatedAt, queryDTO.getStartTimestamp() * 1000);
        }
        if (queryDTO.getEndTimestamp() != null && queryDTO.getEndTimestamp() != 0) {
            wrapper.le(Log::getCreatedAt, queryDTO.getEndTimestamp() * 1000);
        }
        if (StrUtil.isNotBlank(queryDTO.getUsername())) {
            wrapper.eq(Log::getUsername, queryDTO.getUsername());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenName())) {
            wrapper.eq(Log::getTokenName, queryDTO.getTokenName());
        }
        if (StrUtil.isNotBlank(queryDTO.getModelName())) {
            wrapper.eq(Log::getModelName, queryDTO.getModelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getChannelName())) {
            wrapper.eq(Log::getChannelName, queryDTO.getChannelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getContent())) {
            wrapper.like(Log::getContent, queryDTO.getContent());
        }
        return logMapper.selectList(wrapper);
    }

    @Override
    public EsPageInfo<Log> pageQuery(LogQueryDTO queryDTO) {
        if (StrUtil.isNotBlank(queryDTO.getDynamicIndex())) {
            logMapper.setCurrentActiveIndex(queryDTO.getDynamicIndex());
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();
        // 添加查询条件
        if (StrUtil.isNotBlank(queryDTO.getRequestId())) {
            wrapper.eq(Log::getRequestId, queryDTO.getRequestId());
        }
        if (queryDTO.getIsStream() != null) {
            wrapper.eq(Log::isStream, queryDTO.getIsStream());
        }
        if (StrUtil.isNotBlank(queryDTO.getIp())) {
            wrapper.eq(Log::getIp, queryDTO.getIp());
        }
        if (queryDTO.getUserId() != null && queryDTO.getUserId() != 0) {
            wrapper.eq(Log::getUserId, queryDTO.getUserId());
        }
        if (queryDTO.getType() != null && !queryDTO.getType().isEmpty()) {
            wrapper.in(Log::getType, queryDTO.getType());
        }
        if (queryDTO.getChannelId() != null && queryDTO.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, queryDTO.getChannelId());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, queryDTO.getTokenKey());
        }
        if (queryDTO.getStartTimestamp() != null && queryDTO.getStartTimestamp() != 0) {
            wrapper.ge(Log::getCreatedAt, queryDTO.getStartTimestamp() * 1000);
        }
        if (queryDTO.getEndTimestamp() != null && queryDTO.getEndTimestamp() != 0) {
            wrapper.le(Log::getCreatedAt, queryDTO.getEndTimestamp() * 1000);
        }
        if (StrUtil.isNotBlank(queryDTO.getUsername())) {
            wrapper.eq(Log::getUsername, queryDTO.getUsername());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenName())) {
            wrapper.eq(Log::getTokenName, queryDTO.getTokenName());
        }
        if (StrUtil.isNotBlank(queryDTO.getModelName())) {
            wrapper.eq(Log::getModelName, queryDTO.getModelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getChannelName())) {
            wrapper.eq(Log::getChannelName, queryDTO.getChannelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getContent())) {
            wrapper.like(Log::getContent, queryDTO.getContent());
        }

        // 处理 excludeTypes
        if (queryDTO.getExcludeTypes() != null && !queryDTO.getExcludeTypes().isEmpty()) {
            wrapper.must(w -> w.not(w1 -> w1.in(Log::getType, queryDTO.getExcludeTypes())));
        }

        // 处理 excludeFields
        if (queryDTO.getExcludeFields() != null && !queryDTO.getExcludeFields().isEmpty()) {
            wrapper.notSelect(queryDTO.getExcludeFields().toArray(new String[0]));
        }
        wrapper.orderBy(true, false, Log::getCreatedAt);

        // 物理分页
        EsPageInfo<Log> pageInfo = logMapper.pageQuery(wrapper, queryDTO.getPage(), queryDTO.getSize());


        return pageInfo;
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
            logMapper.deleteIndex();
            return;
        }
        logMapper.deleteIndex(ArrayUtil.toArray(indexList, String.class));
    }

    @Override
    public String executeSqlQuery(String sql) throws IOException {
        return logMapper.executeSQL(sql);
    }

    @Override
    public Long count(LogQueryDTO queryDTO) {
        if (StrUtil.isNotBlank(queryDTO.getDynamicIndex())) {
            logMapper.setCurrentActiveIndex(queryDTO.getDynamicIndex());
        }
        LambdaEsQueryWrapper<Log> wrapper = new LambdaEsQueryWrapper<>();

        // 添加查询条件
        if (StrUtil.isNotBlank(queryDTO.getRequestId())) {
            wrapper.eq(Log::getRequestId, queryDTO.getRequestId());
        }
        if (queryDTO.getIsStream() != null){
            wrapper.eq(Log::isStream, queryDTO.getIsStream());
        }
        if (StrUtil.isNotBlank(queryDTO.getIp())) {
            wrapper.eq(Log::getIp, queryDTO.getIp());
        }
        if (queryDTO.getUserId() != null && queryDTO.getUserId() != 0) {
            wrapper.eq(Log::getUserId, queryDTO.getUserId());
        }
        if (queryDTO.getType() != null && !queryDTO.getType().isEmpty()) {
            wrapper.in(Log::getType, queryDTO.getType());
        }
        if (queryDTO.getChannelId() != null && queryDTO.getChannelId() != 0) {
            wrapper.eq(Log::getChannelId, queryDTO.getChannelId());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenKey())) {
            wrapper.eq(Log::getTokenKey, queryDTO.getTokenKey());
        }
        if (queryDTO.getStartTimestamp() != null && queryDTO.getStartTimestamp() != 0) {
            wrapper.ge(Log::getCreatedAt, queryDTO.getStartTimestamp() * 1000);
        }
        if (queryDTO.getEndTimestamp() != null && queryDTO.getEndTimestamp() != 0) {
            wrapper.le(Log::getCreatedAt, queryDTO.getEndTimestamp() * 1000);
        }
        if (StrUtil.isNotBlank(queryDTO.getUsername())) {
            wrapper.eq(Log::getUsername, queryDTO.getUsername());
        }
        if (StrUtil.isNotBlank(queryDTO.getTokenName())) {
            wrapper.eq(Log::getTokenName, queryDTO.getTokenName());
        }
        if (StrUtil.isNotBlank(queryDTO.getModelName())) {
            wrapper.eq(Log::getModelName, queryDTO.getModelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getChannelName())) {
            wrapper.eq(Log::getChannelName, queryDTO.getChannelName());
        }
        if (StrUtil.isNotBlank(queryDTO.getContent())) {
            wrapper.like(Log::getContent, queryDTO.getContent());
        }
        // 处理 excludeTypes
        if (queryDTO.getExcludeTypes() != null && !queryDTO.getExcludeTypes().isEmpty()) {
            wrapper.must(w -> w.not(w1 -> w1.in(Log::getType, queryDTO.getExcludeTypes())));
        }

        return logMapper.selectCount(wrapper);
    }

}