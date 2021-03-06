package com.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.annotation.CurrentUser;
import com.annotation.ValidationParam;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.config.ResponseHelper;
import com.config.ResponseModel;
import com.entity.Notice;
import com.entity.User;
import com.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 消息通知表 前端控制器
 * </p>
 *
 * @author liugh
 * @since 2018-07-27
 */
@RestController
@RequestMapping("/notice")
@ApiIgnore
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 获取自己的消息列表
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/infoList")
    public ResponseModel<Page<Notice>> findInfoList(@CurrentUser User user, @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) throws Exception{

        return ResponseHelper.succeed(noticeService.selectPage(new Page<>(pageIndex, pageSize),new EntityWrapper<Notice>().
                eq("mobile",user.getMobile()).orderBy("create_time",false)));
    }

    /**
     * 删除全部消息
     * @return
     * @throws Exception
     */
    @DeleteMapping
    public ResponseModel findInfoList(@CurrentUser User user) throws Exception{
        noticeService.deleteByNotices(user);
        return ResponseHelper.succeed(null);
    }

    /**
     * 消息改变为已读
     * @param requestJson
     * @return
     * @throws Exception
     */
    @PostMapping("/read")
    public ResponseModel read(@ValidationParam("noticeId,isRead")
                             @RequestBody JSONObject requestJson) throws Exception{
        noticeService.read(requestJson);
        return ResponseHelper.succeed(null);
    }
    /**
     * 未读消息总数
     * @return
     * @throws Exception
     */
    @GetMapping("/noReadCount")
    public ResponseModel getNoRead(@CurrentUser User user) throws Exception{
        return ResponseHelper.succeed(noticeService.selectList(new
                EntityWrapper<Notice>().where("mobile = {0} and is_read = 0",user.getMobile())).size());
    }


}

