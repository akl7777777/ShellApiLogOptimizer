package link.shellgpt.plugin.business.log.controller;

import cn.easyes.core.biz.EsPageInfo;
import cn.hutool.log.LogFactory;
import link.shellgpt.plugin.business.log.model.Log;
import link.shellgpt.plugin.business.log.service.LogService;
import link.shellgpt.plugin.common.utils.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

@RestController
@Validated
@RequestMapping("${api.version}/log")
public class LogController {

    public static final cn.hutool.log.Log log = LogFactory.get();

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(value = "/test")
    public ApiResponse<String> test() {

        return ApiResponse.success("测试成功");
    }


    @PostMapping(value = "/createIndex")
    public ApiResponse<String> createIndex(@RequestBody String keyword) {
        logService.createIndex();
        return ApiResponse.success(null);
    }

    @PostMapping(value = "/save")
    public ApiResponse<String> save(@RequestBody Log log, @RequestParam String dynamicIndex) {
        logService.save(log, dynamicIndex);
        return ApiResponse.success(null);

    }

    @PostMapping(value = "/saveBatch")
    public ApiResponse<String> saveBatch(@RequestBody List<Log> logs, @RequestParam String dynamicIndex) {
        logService.saveBatch(logs, dynamicIndex);
        return ApiResponse.success(null);

    }


    @PostMapping(value = "/deleteIndex")
    public ApiResponse<String> deleteIndex(@RequestBody List<String> indexList) {
        logService.deleteIndex(indexList);
        return ApiResponse.success(null);

    }


    @PostMapping("/search")
    public ApiResponse<List<Log>> search(@RequestBody Log log, @RequestParam String dynamicIndex) {
        List<Log> logList = logService.search(log, dynamicIndex);
        return ApiResponse.success(logList);
    }

    @PostMapping("/pageQuery")
    public ApiResponse<EsPageInfo<Log>> pageQuery(@RequestBody Log log,
                                                  @RequestParam String dynamicIndex,
                                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        EsPageInfo<Log> logList = logService.pageQuery(log, dynamicIndex, page, size);
        return ApiResponse.success(logList);
    }

    @PostMapping("/executeSql")
    public ApiResponse<String> executeSql(@RequestBody String sql) {
        try {
            String result = logService.executeSqlQuery(sql);
            return ApiResponse.success(result);
        } catch (Exception e) {
            log.error("SQL execution error", e);

            // 判断是否有是该类型的异常
            if (e instanceof UndeclaredThrowableException) {
                Throwable targetException = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
                if (targetException instanceof InvocationTargetException) {
                    InvocationTargetException sqlException = (InvocationTargetException) targetException;
                    return ApiResponse.error(500, "SQL execution failed: " + sqlException.getTargetException());
                }
            }
            return ApiResponse.error(500, "SQL execution failed: " + e.getMessage());
        }
    }


}
