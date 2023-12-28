package link.shellgpt.plugin.business.log.service;


import link.shellgpt.plugin.business.log.model.Log;

import java.util.List;

public interface LogService {
    void createIndex();

    void save(Log log);

    List<Log> search(Log log);
}