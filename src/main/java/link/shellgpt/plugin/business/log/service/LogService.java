package link.shellgpt.plugin.business.log.service;


import cn.easyes.core.biz.EsPageInfo;
import link.shellgpt.plugin.business.log.model.Log;

import java.util.List;

public interface LogService {
    void createIndex();

    void save(Log log, String dynamicIndex);

    List<Log> search(Log log, String dynamicIndex);

    EsPageInfo<Log> pageQuery(Log log, String dynamicIndex, int page, int size);

    void saveBatch(List<Log> logs, String dynamicIndex);

    void deleteIndex(List<String> indexList);

    String executeSqlQuery(String sql) throws Exception;

}