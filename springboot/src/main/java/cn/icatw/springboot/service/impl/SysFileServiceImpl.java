package cn.icatw.springboot.service.impl;

import cn.icatw.springboot.entity.SysFile;
import cn.icatw.springboot.mapper.SysFileMapper;
import cn.icatw.springboot.service.SysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 76218
* @description 针对表【sys_file】的数据库操作Service实现
* @createDate 2022-04-28 21:36:04
*/
@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile>
    implements SysFileService{

}




