package cn.icatw.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * 代码生成器
 *
 * @author icatw
 * @date 2022/4/27
 * @email 762188827@qq.com
 * @apiNote
 */
public class CodeGenerator {
    public static void main(String[] args) {
        codeGenera();

    }

    private static void codeGenera() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/icatw?serverTimezone=GMT%2b8", "root", "12345")
                .globalConfig(builder -> {
                    builder.author("王顺") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Java_Code\\springboot+vue后台管理系统\\springboot\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.icatw.springboot") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Java_Code\\springboot+vue后台管理系统\\springboot\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
