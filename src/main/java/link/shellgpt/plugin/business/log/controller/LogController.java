package link.shellgpt.plugin.business.log.controller;

import cn.hutool.log.LogFactory;
import link.shellgpt.plugin.business.log.model.Log;
import link.shellgpt.plugin.business.log.service.LogService;
import link.shellgpt.plugin.common.utils.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<String> save(@RequestBody Log log) {
        logService.save(log);
        return ApiResponse.success(null);

    }

    @PostMapping("/search")
    public ApiResponse<List<Log>> search(@RequestBody Log log) {
        List<Log> logList = logService.search(log);
        return ApiResponse.success(logList);
    }


}
