package link.shellgpt.plugin.business.log.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import link.shellgpt.plugin.business.log.service.LogService;
import link.shellgpt.plugin.common.utils.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("${api.version}/log")
public class LogController {

    public static final Log log = LogFactory.get();

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
    public ApiResponse<String> save(@RequestBody String json) {
        return ApiResponse.success(null);

    }



}
