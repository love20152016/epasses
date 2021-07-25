package com.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.OperationLog;
import com.mapper.OperationLogMapper;
import com.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-08
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
