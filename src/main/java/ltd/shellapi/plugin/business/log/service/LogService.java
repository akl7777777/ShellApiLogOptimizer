package ltd.shellapi.plugin.business.log.service;


import ltd.shellapi.plugin.business.log.dto.LogQueryDTO;
import ltd.shellapi.plugin.business.log.model.Log;
import org.dromara.easyes.core.biz.EsPageInfo;

import java.util.List;

public interface LogService {
    void createIndex(LogQueryDTO queryDTO);

    void save(Log log, String dynamicIndex);

    List<Log> search(LogQueryDTO queryDTO);

    EsPageInfo<Log> pageQuery(LogQueryDTO queryDTO);

    Long count(LogQueryDTO queryDTO);

    void saveBatch(List<Log> logs, String dynamicIndex);

    void deleteIndex(List<String> indexList);

    String executeSqlQuery(String sql) throws Exception;

    // getCurrentIndexName
    String getCurrentIndexName();
}