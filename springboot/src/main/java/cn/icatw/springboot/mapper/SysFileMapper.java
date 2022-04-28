package cn.icatw.springboot.mapper;

import cn.icatw.springboot.entity.SysFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys文件映射器
 *
 * @author 76218
 * @description 针对表【sys_file】的数据库操作Mapper
 * @createDate 2022-04-28 21:36:04
 * @Entity cn.icatw.springboot.entity.SysFile
 * @date 2022/04/28
 */
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

}




